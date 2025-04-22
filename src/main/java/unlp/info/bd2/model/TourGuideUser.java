package unlp.info.bd2.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("GUIDE")
public class TourGuideUser extends User {

    @Column(nullable = true)
    private String education;


    @ManyToMany(mappedBy = "tourGuideList")
    private List<Route> routes;

    public TourGuideUser(String username, String password, String name, String email, Date birthdate,
                         String phoneNumber, String education, boolean active, List<Purchase> purchaseList) {
        super(
                username, password, name, email, birthdate, phoneNumber, active, purchaseList);
        this.education = education;
        this.routes = new ArrayList<>();
    }

    public TourGuideUser() {

    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void addRoute(Route route) {this.routes.add(route);}

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

}
