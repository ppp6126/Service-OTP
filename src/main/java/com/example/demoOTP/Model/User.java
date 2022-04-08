package com.example.demoOTP.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String gender;
    private String city;
    private String email;
    private String tel;
    private String username;
    private String password ;

    @OneToOne
    @JoinColumn(name = "SecretKey_id")
    private SecretKey secretKey ;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_id_serviceOTP_id",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "serviceOTP_id"))
    private Set<ServiceOTP> serviceOTP = new HashSet<>();

    public User() {
    }

    public User( String name, int age, String gender, String city, String email, String tel, String username, String password) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.city = city;
        this.email = email;
        this.tel = tel;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ServiceOTP> getServiceOTP() {
        return serviceOTP;
    }

    public void setServiceOTP(Set<ServiceOTP> serviceOTP) {
        this.serviceOTP = serviceOTP;
    }
}
