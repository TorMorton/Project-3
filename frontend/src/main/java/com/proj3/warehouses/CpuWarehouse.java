package com.proj3.warehouses;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cpu_warehouse")
public class CpuWarehouse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private String id;
		private String manufacturer;
		private String model;
		private int cost;
		
		public String getId() {
			return id;
		}
		public void setId(String id2) {
			this.id = id2;
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
	
		
		
}
