package unlp.info.bd2.repository;

import org.springframework.data.repository.CrudRepository;
import unlp.info.bd2.model.TourGuideUser;

import java.util.Optional;

public interface TourGuideUserRepository extends CrudRepository<TourGuideUser,Long> {
    Optional<TourGuideUser> findByUsername(String username);
}
