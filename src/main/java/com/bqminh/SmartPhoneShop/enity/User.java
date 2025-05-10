package com.bqminh.SmartPhoneShop.enity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message ="Email must not be blank")
    @Email
    private String email;
    @NotBlank(message = "Password must not be blank")
    private String password;
    private String address;
    @NotBlank(message = "Full Name must not be blank")
    private String fullName;
    private String phone;
    private String avatar;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Order>orders;
    @OneToOne(mappedBy = "user")
    private Cart cart;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public /*@NotNull @Email(message = "Email cannot be null", regexp = "^[a-ZA-ZO-9_|#$%&'*+/=?*{|f~^.-]+@[a-zA-Z0-9.-]+$")*/ String getEmail() {
        return email;
    }

    public void setEmail(/*@NotNull @Email(message = "Email cannot be null", regexp = "^[a-ZA-ZO-9_|#$%&'*+/=?*{|f~^.-]+@[a-zA-Z0-9.-]+$")*/ String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}

