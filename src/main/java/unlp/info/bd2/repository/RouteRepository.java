package unlp.info.bd2.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    List<Route> findTop3RoutesByStopCount(Pageable pageable);

    @Query("select r from Route r join Purchase p on r.id = p.route.id where p.review.rating = 1 group by r")
    List<Route> findRoutesByRatingEqualsOne();

    @Query("SELECT r FROM Route r JOIN Purchase p ON r.id = p.route.id " +
            "join Review rw on rw.id = p.review.id where rw.rating IS NOT NULL " +
            "GROUP BY r " +
            "ORDER BY AVG(rw.rating) DESC")
    List<Route> findTop3RoutesByAverageRating(Pageable pageable);


}
