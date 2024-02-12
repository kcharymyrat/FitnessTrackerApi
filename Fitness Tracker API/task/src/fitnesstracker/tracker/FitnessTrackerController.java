package fitnesstracker.tracker;

import fitnesstracker.applications.Application;
import fitnesstracker.tracker.FitnessTrackerEntity;
import fitnesstracker.tracker.FitnessTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class FitnessTrackerController {
    private final FitnessTrackerRepository repository;

    @Autowired
    public FitnessTrackerController(FitnessTrackerRepository repository) {
        this.repository = repository;
    }

    @PostMapping(path = "/api/tracker")
    public ResponseEntity<Void> createFitnessTracker(@RequestBody FitnessTrackerEntity fitnessTracker,
                                                     @AuthenticationPrincipal Application application) {
        fitnessTracker.setApplication(application.getName());
        FitnessTrackerEntity savedFitnessTracker = repository.save(fitnessTracker);
        URI location = URI.create(String.format("/api/tracker/%s", savedFitnessTracker.getId()));
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/api/tracker")
    public ResponseEntity<Iterable<FitnessTrackerEntity>> getFitnessTrackerList() {
        Sort sortByIdDesc = Sort.by("id").descending();
        Iterable<FitnessTrackerEntity> fitnessTrackerEntityList = repository.findAll(sortByIdDesc);
        return ResponseEntity.ok(fitnessTrackerEntityList);
    }
}
