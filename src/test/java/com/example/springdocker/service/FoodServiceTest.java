package com.example.springdocker.service;

import com.example.springdocker.model.Food;
import com.example.springdocker.repository.FoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Miwa Guhrés
 * Date: 09/06/2021
 * Time: 11:38
 * Project: springdocker
 * Copyright: MIT
 */
@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {

    //Klass som jag vill testa
    FoodService foodService;
    
    @Mock
    FoodRepository mockF_Repository;
    
    @BeforeEach
    public void init(){
        foodService= new FoodService(mockF_Repository);
    }
    
    @Test
    void getFoodsAll(){
        Food mockFood = new Food();
        mockFood.setId("1");

        String expectedName ="Sushi";
        mockFood.setName(expectedName);
        
        when(mockF_Repository.findAll())
                .thenReturn(Arrays.asList(mockFood));

        List<Food> actual = foodService.getFoods();

    //-------------------------------------------------------
        assertEquals(mockFood.getId(), actual.get(0).getId());
        assertEquals(expectedName, actual.get(0).getName());
        assertEquals(1,actual.size());

        verify(mockF_Repository).findAll();

        //foodService.getFoods();
        //verify(mockF_Repository).findAll();

    }


    @Test
    void saveNewFoodTest_successfull(){
        Food expectedFood = new Food ("2","Teuchi-Soba", true, false);

        // save expectedFood
        when(mockF_Repository.save(any()))
                .thenReturn(expectedFood);

        //---------------------------------------------------
        Food actual = foodService.saveNewFood(expectedFood);
        //---------------------------------------------------

        assertEquals(expectedFood, actual);
        assertEquals(expectedFood.getId(),actual.getId());
        assertEquals(expectedFood.getName(),actual.getName());
        assertEquals(expectedFood.isDelicious(), actual.isDelicious());
        assertEquals(expectedFood.isCanICookIt(),actual.isCanICookIt());


        verify(mockF_Repository).save(isA(Food.class));
        verify(mockF_Repository).save(any());
        // The food saved 1 time (save method is used 1 time)
        verify(mockF_Repository, times(1)).save(any());
    }

@Test
    void SaveNewFood_existing() {
    Food expectedFood = new Food("2","Teuchi-soba", true, false);

    // The food exists
    when(mockF_Repository.existsFoodByNameAndDeliciousAndCanICookIt(anyString(),anyBoolean(),anyBoolean()))
            .thenReturn(true);

    // An error occurr if you would like to save expectedFoood
    //---------------------
    assertThrows(ResponseStatusException.class,() -> foodService.saveNewFood(expectedFood));
    //---------------------

    // The food was not saved (save method was not used -> 0 time)
    verify(mockF_Repository, times(0)).save(any());
    verify(mockF_Repository).existsFoodByNameAndDeliciousAndCanICookIt(anyString(),anyBoolean(),anyBoolean());

}


}
