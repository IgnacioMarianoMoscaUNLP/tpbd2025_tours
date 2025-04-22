package unlp.info.bd2.repository;

import org.springframework.data.repository.CrudRepository;
import unlp.info.bd2.model.DriverUser;

import java.util.Optional;

public interface DriverUserRepository extends CrudRepository<DriverUser, Long> {
    Optional<DriverUser> findDriverByUsername(String username);
}
