package fitnesstracker.developers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class DeveloperController {
    private final DeveloperService service;

    @Autowired
    public DeveloperController(DeveloperService service) {
        this.service = service;
    }

    @PostMapping(path = "/api/developers/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody DeveloperSignupDTO signupDTO) {
        Long id = service.signupDeveloper(signupDTO);
        return ResponseEntity
                .created(URI.create("/api/developers/" + id))
                .build();
    }

    @GetMapping(path = "/api/developers/{id}")
    @PreAuthorize("#user.developer.id == #id")
    public ResponseEntity<DeveloperInfoResponse> getDeveloperInfoResponse(@PathVariable Long id, @AuthenticationPrincipal DeveloperAdapter user) {
        DeveloperInfoResponse developerInfo = service.getDeveloperInfo(id);
        return ResponseEntity.ok(developerInfo);
    }
}

