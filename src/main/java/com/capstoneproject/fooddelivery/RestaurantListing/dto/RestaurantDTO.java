package com.capstoneproject.fooddelivery.RestaurantListing.dto;

import java.util.Objects;

public class RestaurantDTO {
	
	private int id; 
	
	private String name; 
	
	private String address; 
	
	private String city; 
	
	private String restaurantDescription;

	public RestaurantDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestaurantDTO(int id, String name, String address, String city, String restaurantDescription) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.restaurantDescription = restaurantDescription;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRestaurantDescription() {
		return restaurantDescription;
	}

	public void setRestaurantDescription(String restaurantDescription) {
		this.restaurantDescription = restaurantDescription;
	}

	@Override
	public String toString() {
		return "RestaurantDTO [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city
				+ ", restaurantDescription=" + restaurantDescription + "]";
	} 
	
	
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null || getClass() != obj.getClass()) return false;
	        RestaurantDTO that = (RestaurantDTO) obj;
	        return id == that.id &&
	               Objects.equals(name, that.name) &&
	               Objects.equals(address, that.address) &&
	               Objects.equals(city, that.city) &&
	               Objects.equals(restaurantDescription, that.restaurantDescription);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id, name, address, city, restaurantDescription);
	    }
	}

	
	
	


