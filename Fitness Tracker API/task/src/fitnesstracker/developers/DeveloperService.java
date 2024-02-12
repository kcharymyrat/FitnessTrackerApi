package fitnesstracker.developers;

import fitnesstracker.applications.ApplicationResponse;
import fitnesstracker.applications.Application;
import fitnesstracker.configs.FitnessTrackerApiException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Locale;

@Service
public class DeveloperService {
    private final DeveloperRepository repository;
    private final PasswordEncoder passwordEncoder;

    public DeveloperService(DeveloperRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long signupDeveloper(DeveloperSignupDTO signupDTO) {
        try {
            Developer developer = new Developer();
            developer.setEmail(signupDTO.email().toLowerCase(Locale.ROOT));
            developer.setPassword(passwordEncoder.encode(signupDTO.password()));
            developer.setApplications(new HashSet<>(0));

            return repository.save(developer).getId();
        } catch (DataIntegrityViolationException e) {
            throw new FitnessTrackerApiException("Email already taken");
        }
    }

    public DeveloperInfoResponse getDeveloperInfo(long id) {
        Developer developer = repository.findById(id)
                .orElseThrow(() -> new FitnessTrackerApiException("Profile not found"));

        return new DeveloperInfoResponse(
                developer.getId(),
                developer.getEmail(),
                developer.getApplications().stream()
                        .sorted(Comparator.comparing(Application::getTimestamp).reversed())
                        .map(app -> new ApplicationResponse(
                                String.valueOf(app.getId()),
                                app.getName(),
                                app.getDescription(),
                                app.getApikey()
                        )).toList()
        );
    }

    @Transactional
    public void updateDeveloper(Developer developer) {
        repository.save(developer);
    }

}
