package unlp.info.bd2.repository;

import org.springframework.data.repository.CrudRepository;
import unlp.info.bd2.model.Purchase;

import java.util.Date;

public interface PurchaseRepository extends CrudRepository<Purchase,Long> {
    Long findCountOfPurchasesBetweenDates(Date start, Date end);

}
