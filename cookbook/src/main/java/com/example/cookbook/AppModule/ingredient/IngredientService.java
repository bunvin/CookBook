package com.example.cookbook.AppModule.ingredient;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);
    Ingredient getSingleIngredient(int id);
    void updateIngredient(int id, Ingredient ingredient);
    void deleteIngredient(int id);
    List<Ingredient> getAllIngredients();

}
