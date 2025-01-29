package com.capstoneproject.fooddelivery.RestaurantListing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.capstoneproject.fooddelivery.RestaurantListing.dto.RestaurantDTO;
import com.capstoneproject.fooddelivery.RestaurantListing.model.Restaurant;

@Mapper
public interface RestaurantMapper {
	
	RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);

    RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);
	

}
