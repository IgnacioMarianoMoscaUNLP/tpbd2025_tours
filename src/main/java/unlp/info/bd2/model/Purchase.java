package unlp.info.bd2.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private float totalPrice;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @OneToOne(mappedBy = "purchase")
    private Review review;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemService> itemServiceList;

    public Purchase() {
    }

    public Purchase(String code, Route route, User user) {
        this.code = code;
        this.route = route;
        this.user = user;
        this.totalPrice = route.getPrice();
        this.date = new Date();
        this.review = null;
        this.itemServiceList = new ArrayList<>();
    }

    public Purchase(String code,Date day, Route route, User user) {
        this.code = code;
        this.route = route;
        this.user = user;
        this.totalPrice = route.getPrice();
        this.date = day;
        this.itemServiceList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public List<ItemService> getItemServiceList() {
        return itemServiceList;
    }

    public void setItemServiceList(List<ItemService> itemServiceList) {
        this.itemServiceList = itemServiceList;
    }
}
