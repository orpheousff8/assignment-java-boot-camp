package dev.saracha.shopping.domains;

import javax.persistence.Embeddable;
import javax.persistence.Column;

@Embeddable
public class CustomerContact {
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "telephone_no", nullable = false, length = 20)
    private String telephoneNo;

    @Column(name = "house_no", nullable = false, length = 45)
    private String houseNo;

    @Column(name = "moo", length = 3)
    private String moo;

    @Column(name = "village", length = 50)
    private String village;

    @Column(name = "street", length = 50)
    private String street;

    @Column(name = "soi", length = 50)
    private String soi;

    @Column(name = "sub_district", nullable = false, length = 45)
    private String subDistrict;

    @Column(name = "district", nullable = false, length = 45)
    private String district;

    @Column(name = "province", nullable = false, length = 45)
    private String province;

    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

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

}
