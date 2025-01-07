package com.example.cookbook.AppModule.recipe;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface RecipeService {
    Recipe addRecipe(Recipe recipe);
    Recipe getSingleRecipe(Recipe recipe);
    void updateRecipe(int id, Recipe recipe);
    void deleteRecipe(int id);
    List<Recipe> getAllRecipies();

}
 