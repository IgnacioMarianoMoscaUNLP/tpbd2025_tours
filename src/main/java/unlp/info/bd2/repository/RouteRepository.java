package unlp.info.bd2.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import unlp.info.bd2.model.Route;
import unlp.info.bd2.model.Stop;

import java.util.Date;
import java.util.List;

public interface RouteRepository extends CrudRepository<Route, Long> {
    List<Route> findByPriceLessThan(float price);
    List<Route>findByStop(Stop stop);
    @Query("select max(size(r.stops))from Route r")
    Long findByMaxStopsCount();

    Long findByStopsBetweenDates(Date start, Date end);

    @Query("select r from Route r group by r.id order by size(r.stops) desc")
    List<Route> findTop3RoutesByStopCount(PageRequest pageRequest);
}
