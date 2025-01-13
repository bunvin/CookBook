package com.example.cookbook.AppModule.recipeIngredient;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cookbook.AppModule.recipe.RecipeDTO;
import com.example.cookbook.ErrorHandeling.AppException;

@Service
public interface RecipeIngredientService {
    RecipeIngredientDTO addRecipeIngredient(RecipeIngredientDTO recipeIngredientDTO) throws AppException;
    RecipeIngredient getSingleRecipeIngredient(int id) throws AppException;
    RecipeIngredientDTO getSingleRecipeIngredientDTO(int id) throws AppException;
    void updateRecipeIngredient(int id, RecipeIngredientDTO recipeIngredientDTO) throws AppException;
    void deleteRecipeIngredient(int id) throws AppException;
    List<RecipeIngredientDTO> getAllRecipeIngredients();
    List<RecipeIngredientDTO> getAllRecipeIngredientByRecipeId(int recipeId) throws AppException;
    double getTotalPriceByRecipeId(int recipeId);
    List<RecipeDTO> getAllRecipeWithIngredient(int ingredientId);
    List<RecipeDTO> getAllRecipeWithIngredients(int ingredientCount, int... ingredientIds) throws AppException;
}
