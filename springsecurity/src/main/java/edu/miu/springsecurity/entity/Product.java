package edu.miu.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private double price;

    @JsonIgnore
//    @JsonManagedReference
    @ManyToMany(mappedBy = "products")
    private List<Category> categories;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;



}
