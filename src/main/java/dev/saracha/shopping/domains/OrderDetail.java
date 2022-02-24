package dev.saracha.shopping.domains;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue
    @Column(name = "order_detail_id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "oder_quantity")
    private Integer oderQuantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "order_detail_created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "order_detail_updated_date")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getOderQuantity() {
        return oderQuantity;
    }

    public void setOderQuantity(Integer oderQuantity) {
        this.oderQuantity = oderQuantity;
    }

    public Cart getShoppingCart() {
        return cart;
    }

    public void setShoppingCart(Cart cart) {
        this.cart = cart;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

//    @Override
//    public String toString() {
//        return "OrderDetail{" +
//                "id=" + id +
//                ", order=" + order +
//                ", product=" + product +
//                ", oderQuantity=" + oderQuantity +
//                ", createdDate=" + createdDate +
//                ", updatedDate=" + updatedDate +
//                ", shoppingCart=" + cart +
//                '}';
//    }
}
