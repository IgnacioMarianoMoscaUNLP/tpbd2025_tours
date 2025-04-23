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

    List<Route> findByStopsContaining(Stop stop);
    @Query("select max(size(r.stops))from Route r")
    Long findByMaxStopsCount();

    //Long findRoutesByByBetweenDates(Date start, Date end);

    @Query("select r from Route r group by r.id order by size(r.stops) desc")
    List<Route> findTop3RoutesByStopCount(PageRequest pageRequest);

    @Query("SELECT r FROM Route r JOIN Purchase p ON p.route = r WHERE p.review IS NOT NULL GROUP BY r ORDER BY MAX(p.review.rating) DESC")
    List<Route> findTop3RoutesByMaxRating(PageRequest pageRequest);

    @Query("select r from Route r left join Purchase p on r.id = p.route.id where p.review.rating = 1 group by (r.id)")
    List<Route> findRoutesByRatingEqualsOne();

}
