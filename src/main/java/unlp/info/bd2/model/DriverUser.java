package unlp.info.bd2.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Entity
@DiscriminatorValue("DRIVER")
public class DriverUser extends User {

    @Column(nullable = true)
    private String expedient;

    @ManyToMany(mappedBy = "driverList")
    private List<Route> routes;

    public DriverUser(
            String username, String password, String name, String email, Date birthdate,
            String phoneNumber, String expedient, boolean active, List<Purchase> purchaseList) {
        super(username, password, name, email, birthdate, phoneNumber, active, purchaseList);
        this.expedient = expedient;
        this.routes = new ArrayList<>();
    }

    public DriverUser() {

    }

    public String getExpedient() {
        return expedient;
    }

    public void setExpedient(String expedient) {
        this.expedient = expedient;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRouts(List<Route> routs) {
        this.routes = routs;
    }
}
