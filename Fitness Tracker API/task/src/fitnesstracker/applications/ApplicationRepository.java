package fitnesstracker.applications;


import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
    Optional<Application> findByApikey(String apiKey);
}
