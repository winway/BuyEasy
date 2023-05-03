package com.example.buyeasy.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * @PackageName: com.example.buyeasy.bean
 * @ClassName: ReceiverInfoBean
 * @Author: winwa
 * @Date: 2023/4/29 7:35
 * @Description:
 **/
public class ReceiverInfoBean implements Serializable {
    private int id;
    private String name;
    private String phone;
    private String city;
    private String street;
    private boolean isDefault;
    private boolean isTop;

    public ReceiverInfoBean() {
    }

    public ReceiverInfoBean(String name, String phone, String city, String street) {
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.street = street;
    }

    public ReceiverInfoBean(int id, String name, String phone, String city, String street) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.street = street;
    }

    public ReceiverInfoBean(int id, String name, String phone, String city, String street, boolean isDefault, boolean isTop) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.street = street;
        this.isDefault = isDefault;
        this.isTop = isTop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiverInfoBean that = (ReceiverInfoBean) o;
        return Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(city, that.city) && Objects.equals(street, that.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, city, street);
    }

    public void copyDataFromObject(ReceiverInfoBean o) {
        this.setId(o.getId());
        this.setName(o.getName());
        this.setPhone(o.getPhone());
        this.setCity(o.getCity());
        this.setStreet(o.getStreet());
    }

    public String getClipData() {
        return String.format("姓名：%s\n电话：%s\n地址：%s%s", name, phone, city, street);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }
}
