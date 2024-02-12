package fitnesstracker.applications;

import java.util.Random;
import fitnesstracker.configs.FitnessTrackerApiException;
import fitnesstracker.developers.Developer;
import fitnesstracker.developers.DeveloperService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final DeveloperService developerService;

    public ApplicationService(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @Transactional
    public ApplicationInfoResponse registerApplication(ApplicationRegisterDTO appDto, Developer developer) {
        boolean isNameExist = developer.getApplications().stream()
                .map(Application::getName)
                .anyMatch(appName -> appName.equals(appDto.name()));
        if (isNameExist) {
            throw new FitnessTrackerApiException("Application with this name already exists");
        }

        String apiKey = generateRandomString(15);
        Application app = new Application();

        app.setName(appDto.name());
        app.setDescription(appDto.description());
        app.setApikey(apiKey);
        app.setDeveloper(developer);

        developer.getApplications().add(app);
        developerService.updateDeveloper(developer);

        return new ApplicationInfoResponse(app.getName(), app.getApikey());
    }

    @Transactional
    public ApplicationInfoResponse regenerateApiKey(long id, Developer developer) {
        var application = developer.getApplications().stream()
                .filter(app -> app.getId() == id)
                .findFirst()
                .orElseThrow(() -> new FitnessTrackerApiException("Application not found"));

        developer.getApplications().remove(application);
        String apikey = generateRandomString(15);
        application.setApikey(apikey);
        developer.getApplications().add(application);
        developerService.updateDeveloper(developer);

        return new ApplicationInfoResponse(application.getName(), apikey);
    }


    public String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            // Generate a random ASCII character between 33 ('!') and 126 ('~')
            int randomChar = 33 + random.nextInt(94);
            sb.append((char) randomChar);
        }

        return sb.toString();
    }
}
