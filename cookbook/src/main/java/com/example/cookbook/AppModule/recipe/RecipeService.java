package com.example.cookbook.AppModule.recipe;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cookbook.ErrorHandeling.AppException;

@Service
public interface RecipeService {
    Recipe addRecipe(Recipe recipe) throws AppException; //Exist id, exist Title
    Recipe getSingleRecipe(int id) throws AppException;//non exist id
    void updateRecipe(int id, Recipe recipe) throws AppException; //non exist id, exist title
    void deleteRecipe(int id) throws AppException;
    List<Recipe> getAllRecipies();

    double getRecipeTotalPrice(int recipeId) throws AppException;

}
 