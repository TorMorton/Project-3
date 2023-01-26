package com.proj3.warehouses.models;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "Category.getAllCategories", query = "SELECT c FROM Category c WHERE c.id in (SELECT p.category.id FROM Product p WHERE p.status='true')")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "categories")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

}
