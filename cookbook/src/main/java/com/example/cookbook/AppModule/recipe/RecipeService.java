package com.example.cookbook.AppModule.recipe;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cookbook.AppModule.recipeIngredient.RecipeIngredientDTO;
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

    //Recipe ingredient control
    RecipeIngredientDTO addRecipeIngredient(RecipeIngredientDTO recipeIngredient) throws AppException;
    RecipeIngredientDTO getSingleRecipeIngredient(int id) throws AppException;
    void updateRecipeIngredient(int id, RecipeIngredientDTO recipeIngredient) throws AppException;
    void deleteRecipeIngredient(int id) throws AppException;
    
    List<RecipeIngredientDTO> getAllRecipeIngredients();
    List<RecipeIngredientDTO> getAllRecipeIngredientByRecipeId(int recipeId) throws AppException;
    
    List<RecipeDTO> getAllRecipeWithIngredient(int ingredientId);
    List<RecipeDTO> getAllRecipeWithIngredients(int... ingredientIds) throws AppException;


}
 