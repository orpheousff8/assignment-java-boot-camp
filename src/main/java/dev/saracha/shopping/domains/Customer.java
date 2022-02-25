package dev.saracha.shopping.domains;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_Id", nullable = false)
    private Long id;

    @Column(name = "customer_name", nullable = false, length = 30)
    private String name;

    @Embedded
    private CustomerContact contact;

    @Column(name = "customer_created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "customer_updated_date")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    public Customer() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerContact getContact() {
        return contact;
    }

    public void setContact(CustomerContact contact) {
        this.contact = contact;
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
}
