package com.example.cookbook.AppModule.recipeIngredient;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface RecipeIngredientService {
    RecipeIngredient addRecipeIngredient(RecipeIngredient recipeIngredient);
    RecipeIngredient getSingleRecipeIngredient(int id);
    void updateRecipeIngredient(int id, RecipeIngredient recipeIngredient);
    void deleteRecipeIngredient(int id);
    List<RecipeIngredient> getAllRecipeIngredients();

}
