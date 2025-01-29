package com.capstoneproject.fooddelivery.RestaurantListing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstoneproject.fooddelivery.RestaurantListing.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
	

}
