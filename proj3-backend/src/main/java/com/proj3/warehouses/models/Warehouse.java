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
@DynamicUpdate
@DynamicInsert
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id", updatable = false)
    private int id;

    @Column(name = "name", updatable = false)
    private String name;

    @Column(name = "location", updatable = false)
    private String location;

    @Column(name = "capacity", updatable = false)
    private int capacity = 100;

//	@Column(name = "current_total")
//	private int currentTotal = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

//	public int getCurrentTotal() {
//		return currentTotal;
//	}
//
//	public void setCurrentTotal(int currentTotal) {
//		this.currentTotal = currentTotal;
//	}

    @Override
    public String toString() {
        return "Warehouse [id=" + id + ", name=" + name + ", location=" + location + ", capacity=" + capacity
                + "]";
    }

}