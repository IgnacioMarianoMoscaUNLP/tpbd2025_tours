package unlp.info.bd2.repository;

import org.springframework.data.repository.CrudRepository;
import unlp.info.bd2.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
