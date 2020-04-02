package com.ednach.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "producers")
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String email;
    private String country;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "products_producers",
            joinColumns = {@JoinColumn(name = "producer_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> products;

}
