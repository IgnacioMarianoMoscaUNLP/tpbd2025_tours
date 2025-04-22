package unlp.info.bd2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item_service")
public class ItemService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    public ItemService() {
    }

    public ItemService(Service service, int quantity, Purchase purchase) {
        this.service = service;
        this.quantity = quantity;
        this.purchase = purchase;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
