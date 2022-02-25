package dev.saracha.shopping.domains;

import dev.saracha.shopping.domains.Customer;
import dev.saracha.shopping.domains.Order;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_id", nullable = false)
    private Long id;

    @Column(name = "shipping_email", nullable = false, length = 50)
    private String email;

    @Column(name = "shipping_telephone_no", nullable = false, length = 20)
    private String telephoneNo;

    @Column(name = "shipping_name", nullable = false, length = 50)
    private String name;

    @Column(name = "shipping_house_no", nullable = false, length = 45)
    private String houseNo;

    @Column(name = "shipping_moo", length = 3)
    private String moo;

    @Column(name = "shipping_village", length = 50)
    private String village;

    @Column(name = "shipping_street", length = 50)
    private String street;

    @Column(name = "shipping_soi", length = 50)
    private String soi;

    @Column(name = "shipping_sub_district", nullable = false, length = 45)
    private String subDistrict;

    @Column(name = "shipping_district", nullable = false, length = 45)
    private String district;

    @Column(name = "shipping_province", nullable = false, length = 45)
    private String province;

    @Column(name = "shipping_zip_code", nullable = false, length = 10)
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getMoo() {
        return moo;
    }

    public void setMoo(String moo) {
        this.moo = moo;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSoi() {
        return soi;
    }

    public void setSoi(String soi) {
        this.soi = soi;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    @Override
    public String toString() {
        return "Shipping{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", telephoneNo" + telephoneNo + '\'' +
                ", name='" + name + '\'' +
                ", houseNo='" + houseNo + '\'' +
                ", moo='" + moo + '\'' +
                ", village='" + village + '\'' +
                ", soi='" + soi + '\'' +
                ", street='" + street + '\'' +
                ", district='" + district + '\'' +
                ", province='" + province + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", order=" + order +
                ", customer=" + customer +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}