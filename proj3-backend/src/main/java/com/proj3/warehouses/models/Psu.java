package com.proj3.warehouses.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate  // these allow only updates in the columns that have updates to be changed
@DynamicInsert  // insert only columns that have values
@Table(name= "psu_inventory")
public class Psu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "psu_id")
    private int id;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "model")
    private String model;

    @Column(name = "cost")
    private int cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Psu [id=" + id + ", manufacturer=" + manufacturer + ", model=" + model + ", cost=" + cost + "]";
    }
}