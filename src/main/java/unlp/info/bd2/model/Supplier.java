package unlp.info.bd2.model;

import java.util.List;
import jakarta.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String businessName;

    @Column(nullable = false, unique = true)
    private String authorizationNumber;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Service> services = new ArrayList<>();

    public Supplier() {
    }

    public Supplier(String businessName, String authorizationNumber) {
        this.businessName = businessName;
        this.authorizationNumber = authorizationNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAuthorizationNumber() {
        return authorizationNumber;
    }

    public void setAuthorizationNumber(String authorizationNumber) {
        this.authorizationNumber = authorizationNumber;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

}
