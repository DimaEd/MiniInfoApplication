package com.ednach.model;

import com.ednach.dto.response.UserResponseDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
@Getter
@Setter

@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @ManyToOne
    @JoinColumn(name="role_id", nullable = false )
    private Role role;

    @OneToMany(mappedBy = "user" , fetch = FetchType.EAGER)
    private Set<Product> products;

    public User() {
    }

    public User(Long id, String name, String email, Role role){
        this.id = id;
        this.name = name;
        this.email = email;
        this.role =role;
    }
    public User(Long id , String name){
        this.id = id;
        this.name = name;
    }
}
