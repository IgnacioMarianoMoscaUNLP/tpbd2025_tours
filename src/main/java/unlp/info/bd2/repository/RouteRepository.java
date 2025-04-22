package unlp.info.bd2.repository;

import org.springframework.data.repository.CrudRepository;
import unlp.info.bd2.model.Route;

import java.util.List;

public interface RouteRepository extends CrudRepository<Route, Long> {
    List<Route> findByPriceLessThan(float price);
    //hacer query de routes con mas stops
}
