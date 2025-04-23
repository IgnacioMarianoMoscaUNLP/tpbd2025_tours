package unlp.info.bd2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import unlp.info.bd2.model.Purchase;

import java.util.Date;

public interface PurchaseRepository extends CrudRepository<Purchase,Long> {
    long countByDateBetween(Date start, Date end);
}
