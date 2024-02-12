package fitnesstracker.applications;

import fitnesstracker.developers.DeveloperAdapter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    private final ApplicationService appService;

    public ApplicationController(ApplicationService appService) {
        this.appService = appService;
    }

    @PostMapping(path = "/api/applications/register")
    public ResponseEntity<ApplicationInfoResponse> registerApplication(@Valid @RequestBody ApplicationRegisterDTO appDto, @AuthenticationPrincipal DeveloperAdapter adapter) {
        ApplicationInfoResponse appInfo = appService.registerApplication(appDto, adapter.getDeveloper());
        return ResponseEntity.status(HttpStatus.CREATED).body(appInfo);
    }

    @PostMapping(path = "/api/applications/{id}/apikey")
    @PreAuthorize("@dm.decide(#user, #id)")
    public ResponseEntity<ApplicationInfoResponse> recreateApiKey(@PathVariable Long id,
                                                    @AuthenticationPrincipal DeveloperAdapter adapter) {
        ApplicationInfoResponse appInfo = appService.regenerateApiKey(id, adapter.getDeveloper());
        return ResponseEntity.ok(appInfo);
    }
}
