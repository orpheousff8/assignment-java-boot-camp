package dtos;

import dev.saracha.shopping.domains.Shipping;

import java.io.Serializable;

public class ShippingRequestDTO implements Serializable {
    private String name;
    private String email;
    private String telephoneNo;
    private String houseNo;
    private String moo;
    private String village;
    private String street;
    private String soi;
    private String subDistrict;
    private String district;
    private String province;
    private String zipCode;
    private boolean isCurrentAddress;

    public ShippingRequestDTO() {
    }

    public ShippingRequestDTO(Shipping shipping) {
        this.name = shipping.getName();
        this.email = shipping.getEmail();
        this.telephoneNo = shipping.getTelephoneNo();
        this.houseNo = shipping.getHouseNo();
        this.moo = shipping.getMoo();
        this.village = shipping.getVillage();
        this.street = shipping.getStreet();
        this.soi = shipping.getSoi();
        this.subDistrict = shipping.getSubDistrict();
        this.district = shipping.getDistrict();
        this.province = shipping.getProvince();
        this.zipCode = shipping.getZipCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isCurrentAddress() {
        return isCurrentAddress;
    }

    public void setIsCurrentAddress(boolean isCurrentAddress) {
        this.isCurrentAddress = isCurrentAddress;
    }

    @Override
    public String toString() {
        return "ShippingRequestDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", telephoneNo='" + telephoneNo + '\'' +
                ", houseNo='" + houseNo + '\'' +
                ", moo='" + moo + '\'' +
                ", soi='" + soi + '\'' +
                ", street='" + street + '\'' +
                ", subDistrict='" + subDistrict + '\'' +
                ", district='" + district + '\'' +
                ", province='" + province + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", isUseCurrentAddress=" + isCurrentAddress +
                '}';
    }
}
