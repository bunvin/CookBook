package com.example.cookbook.AppModule.recipe;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cookbook.ErrorHandeling.AppException;

@Service
public interface RecipeService {
    RecipeDTO addRecipe(RecipeDTO recipeDTO) throws AppException; //Exist id, exist Title
    Recipe getSingleRecipe(int id) throws AppException;//non exist id
    void updateRecipe(int id, RecipeDTO recipe) throws AppException; //non exist id, exist title
    void deleteRecipe(int id) throws AppException;
    List<Recipe> getAllRecipies();
    List<RecipeDTO> getAllRecipiesDTO();

    double getRecipeTotalPrice(int recipeId) throws AppException; //only in recipe ingredient ?
    RecipeDTO getSingleRecipeDTO(int id) throws AppException;

}
 