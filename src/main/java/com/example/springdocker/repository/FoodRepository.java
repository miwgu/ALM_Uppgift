package com.example.springdocker.repository;

import com.example.springdocker.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends MongoRepository<Food, String> {

    List<Food> findFoodByCanICookIt(boolean canCook);
    // Add the food exists or not
    boolean existsFoodByNameAndDeliciousAndCanICookIt(String name, boolean delicious, boolean canICookIt);
}
