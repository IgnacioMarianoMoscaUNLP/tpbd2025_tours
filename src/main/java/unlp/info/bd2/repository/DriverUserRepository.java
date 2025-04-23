package unlp.info.bd2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import unlp.info.bd2.model.DriverUser;

import java.util.Optional;

public interface DriverUserRepository extends CrudRepository<DriverUser, Long> {
    Optional<DriverUser> findDriverByUsername(String username);

    @Query("select max(size(d.routes))from DriverUser d")
    DriverUser findDriverUserByMaxRoutes();
}
