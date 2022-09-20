package edu.miu.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    private String password;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Product product;

}
