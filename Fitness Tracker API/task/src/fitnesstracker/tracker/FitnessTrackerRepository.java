package fitnesstracker.tracker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FitnessTrackerRepository extends PagingAndSortingRepository<FitnessTrackerEntity, Long>,
        CrudRepository<FitnessTrackerEntity, Long> {
}
