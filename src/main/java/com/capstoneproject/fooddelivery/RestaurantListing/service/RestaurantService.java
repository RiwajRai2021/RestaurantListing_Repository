package com.capstoneproject.fooddelivery.RestaurantListing.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstoneproject.fooddelivery.RestaurantListing.dto.RestaurantDTO;
import com.capstoneproject.fooddelivery.RestaurantListing.mapper.RestaurantMapper;
import com.capstoneproject.fooddelivery.RestaurantListing.model.Restaurant;
import com.capstoneproject.fooddelivery.RestaurantListing.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;

	public List<RestaurantDTO> findAllRestaurants() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		List<RestaurantDTO> restaurantDTOList = restaurants.stream()
				.map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant))
				.collect(Collectors.toList());
		return restaurantDTOList;
	}

	public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
		Restaurant savedRestaurant = restaurantRepository
				.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));
		return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
	}
	
	public RestaurantDTO updateRestaurantInDB(RestaurantDTO restaurantDTO) throws Exception {
		
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantDTO.getId());
		if(optionalRestaurant.isPresent()) {
			Restaurant savedRestaurant = optionalRestaurant.get();
			savedRestaurant.setAddress(restaurantDTO.getAddress());
			savedRestaurant.setCity(restaurantDTO.getCity());
			savedRestaurant.setName(restaurantDTO.getName());
			savedRestaurant.setRestaurantDescription(restaurantDTO.getRestaurantDescription());
			savedRestaurant = restaurantRepository.save(savedRestaurant);
			return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
		} else {
			throw new Exception("Restaurant is not available");
		}
		
	}

	public ResponseEntity<RestaurantDTO> fetchRestaurantById(Integer id) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
		if (restaurant.isPresent()) {
			return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant.get()),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

	}
}
