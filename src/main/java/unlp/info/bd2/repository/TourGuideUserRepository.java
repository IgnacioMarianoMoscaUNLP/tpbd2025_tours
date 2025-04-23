package unlp.info.bd2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import unlp.info.bd2.model.TourGuideUser;
import unlp.info.bd2.model.Route;
import java.util.List;
import java.util.Optional;

public interface TourGuideUserRepository extends CrudRepository<TourGuideUser,Long> {
    Optional<TourGuideUser> findByUsername(String username);

    @Query("select t from TourGuideUser t inner join Route r   join Purchase p on r.id = p.route.id where p.review.rating =1 group by t")
    List<TourGuideUser> findTourGuideUsersByRatingEqualsOne();
}
