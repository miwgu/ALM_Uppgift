package com.example.springdocker.repository;

import com.example.springdocker.model.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Miwa Guhrés
 * Date: 15/06/2021
 * Time: 13:34
 * Project: springdocker
 * Copyright: MIT
 */
//@DataJpaTest
@DataMongoTest
@ExtendWith(SpringExtension.class)
public class FoodRepositoryTest {
    List<Food> foods = new ArrayList<>();

    //Klass som jag vill testa
    @Autowired
    FoodRepository foodRepository;

    @BeforeEach
    void setUpFood(){
        // Add food to the foods List
        foods.add(new Food("1","Ozouni",true,true));
        foods.add(new Food("2","Onigiri",true,true));
        foodRepository.saveAll(foods);
    }

    @DisplayName("Test1 findFoodByCanICookIt(true)")
    @Test
    void findFoodByCanICookIt_True(){
        //Expected List is foods List
        List<Food> expected = foods;

        //------------
        List<Food> actual = foodRepository.findFoodByCanICookIt(true);
        //------------
        assertEquals(expected,actual);

    }

    @DisplayName("Test2 findFoodByCanICookIt(false)")
    @Test
    void findFoodByCanICookIt_False(){

        //Test with empty List--------------------------------------------------
        List<Food> expected = new ArrayList<>();// The list is empty
        //------------
        List<Food> actual = foodRepository.findFoodByCanICookIt(false);
        //------------
        assertEquals(expected,actual);


    }
    @DisplayName("Test3 the food exists")
    @Test
    void existsFoodByNameAndDeliciousAndCanICookIt (){
        Food expectedFood= new Food("3","Ramen",true,true);
        //Save the expectedFood to the foodRepository
        foodRepository.save(expectedFood);


        //If you get the same name, booleans which inisiated now and is saved, the actual become true
        //-------------------------

        boolean actual = foodRepository.existsFoodByNameAndDeliciousAndCanICookIt(expectedFood.getName(),expectedFood.isDelicious(),expectedFood.isCanICookIt());
        //----------------------------

        assertEquals(true,actual);
    }

    @DisplayName("Test3 the food dosen´t exists")
    //@Disabled
    @Test
    void existsFoodByNameAndDeliciousAndCanICookIt_notsaved (){

        // I didint dave the food
        String expectedName="Okonomiyaki";
        boolean expectedIsDelicious= true;
        boolean expectedIsCanICookIt=false;

        //If you get the same name, booleans which inisiated now and is saved, the actual become true
        //-------------------------
        boolean actual = foodRepository.existsFoodByNameAndDeliciousAndCanICookIt(expectedName,expectedIsDelicious,expectedIsCanICookIt);
        //----------------------------

        assertEquals(false,actual);
    }
}
