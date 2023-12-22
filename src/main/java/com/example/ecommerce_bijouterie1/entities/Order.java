package com.example.ecommerce_bijouterie1.entities;

import com.example.ecommerce_bijouterie1.services.impl.BijouxServiceImpl;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    @SequenceGenerator(name = "orders_seq", sequenceName = "orders_seq", initialValue = 6, allocationSize = 1)
    private Long id;

    @Getter
    @Column(name = "total_price", nullable = false)
    private Double totalPrice= (double) 0;

    @Getter
    @Column(name = "date", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime date = LocalDateTime.now();

    @Getter
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Getter
    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "address", nullable = false)
    private String address;

    @Getter
    @Column(name = "email", nullable = false)
    private String email;

    @Getter
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Getter
    @Column(name = "post_index", nullable = false)
    private Integer postIndex;

    @Getter
    @ManyToMany
    private List<Bijoux> bijoux = new ArrayList<>();

    @Getter
    @ManyToOne
    private User user;
    public void AddtoShoppingCart(){
        Bijoux bij=new Bijoux();
   bijoux.add(bij);
    bijoux.forEach(bi -> totalPrice+=bi.getPrice());


    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotalPrice(Double totalPrice) {

        this.totalPrice = totalPrice;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPostIndex(Integer postIndex) {
        this.postIndex = postIndex;
    }

    public void setBijoux(List<Bijoux> bijoux) {
        this.bijoux = bijoux;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Bijoux> getBijoux() {
        return bijoux;
    }

    public  List<Bijoux> getBijouxs() {
        return bijoux;
    }
}
