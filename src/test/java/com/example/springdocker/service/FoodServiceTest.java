package com.example.springdocker.service;

import com.example.springdocker.model.Food;
import com.example.springdocker.repository.FoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


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
    void getAll(){
        Food mockFood = new Food();
        mockFood.setId("1");
        mockFood.setName("Sushi");
        String expectedName ="Sushi";
        
        mockFood.setId(expectedName);
        
        when(mockF_Repository.findAll())
                .thenReturn(Arrays.asList(mockFood));

        List<Food> actual = foodService.getFoods();

    //------------------
        assertEquals(mockFood.getId(), actual.get(0).getId());
        assertEquals(expectedName, actual.get(0).getName());
        assertEquals(1,actual.size());

        verify(mockF_Repository).findAll();

    }




}
