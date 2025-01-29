package com.capstoneproject.fooddelivery.RestaurantListing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

import com.capstoneproject.fooddelivery.RestaurantListing.dto.RestaurantDTO;
import com.capstoneproject.fooddelivery.RestaurantListing.mapper.RestaurantMapper;
import com.capstoneproject.fooddelivery.RestaurantListing.model.Restaurant;
import com.capstoneproject.fooddelivery.RestaurantListing.repository.RestaurantRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    @Mock
    RestaurantRepository restaurantRepository; 

    @InjectMocks
    RestaurantService restaurantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); //in order for Mock and InjectMocks annotations to take effect, you need to call MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllRestaurants() {
        // Create mock restaurants
        List<Restaurant> mockRestaurants = Arrays.asList(
                new Restaurant(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"),
                new Restaurant(2, "Restaurant 2", "Address 2", "city 2", "Desc 2")
        );
        when(restaurantRepository.findAll()).thenReturn(mockRestaurants);

        // Call the service method
        List<RestaurantDTO> restaurantDTOList = restaurantService.findAllRestaurants();

        // Verify the result
        assertEquals(mockRestaurants.size(), restaurantDTOList.size());
        for (int i = 0; i < mockRestaurants.size(); i++) {
            RestaurantDTO expectedDTO = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(mockRestaurants.get(i));
            assertEquals(expectedDTO, restaurantDTOList.get(i));
        }

        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    public void testAddRestaurantInDB() {
        // Create a mock RestaurantDTO
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(1);
        restaurantDTO.setName("Restaurant 1");
        restaurantDTO.setAddress("Address 1");
        restaurantDTO.setCity("city 1");
        restaurantDTO.setRestaurantDescription("Desc 1");

        // Mock the repository save method
        when(restaurantRepository.save(any(Restaurant.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        // Call the service method
        RestaurantDTO resultDTO = restaurantService.addRestaurantInDB(restaurantDTO);

        // Verify the result
        assertNotNull(resultDTO);
        assertEquals(restaurantDTO, resultDTO);

        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }

    @Test
    public void testFetchRestaurantById_ExistingId() {
        // Create a mock restaurant ID
        Integer mockRestaurantId = 1;

        // Create a mock restaurant to be returned by the repository
        Restaurant mockRestaurant = new Restaurant(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");

        // Mock the repository behavior
        when(restaurantRepository.findById(mockRestaurantId)).thenReturn(Optional.of(mockRestaurant));

        // Call the service method
        ResponseEntity<RestaurantDTO> response = restaurantService.fetchRestaurantById(mockRestaurantId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurantId, response.getBody().getId());

        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).findById(mockRestaurantId);
    }

    @Test
    public void testFetchRestaurantById_NonExistingId() {
        // Create a mock non-existing restaurant ID
        Integer mockRestaurantId = 1;

        // Mock the repository behavior
        when(restaurantRepository.findById(mockRestaurantId)).thenReturn(Optional.empty());

        // Call the service method
        ResponseEntity<RestaurantDTO> response = restaurantService.fetchRestaurantById(mockRestaurantId);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());

        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).findById(mockRestaurantId);
    }
}
