package com.ednach.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
@Getter
@Setter

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany (mappedBy = "role" , fetch = FetchType.EAGER)
    private Set<User> users;

    private String roleName;

    public Role() {
    }
    public Role(Long id , String roleName){
        this.id = id;
        this.roleName = roleName;
    }
}
