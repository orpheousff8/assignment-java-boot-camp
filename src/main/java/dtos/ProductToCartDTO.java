package dtos;

import java.io.Serializable;

public class ProductToCartDTO implements Serializable {
    private Long id;
    private Integer quantity;

    public ProductToCartDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductToCartDTO{" +
                "productId=" + id +
                ", qty=" + quantity +
                '}';
    }
}
