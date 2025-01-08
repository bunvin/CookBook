package com.example.cookbook.AppModule.recipeIngredient;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cookbook.ErrorHandeling.AppException;

@Service
public interface RecipeIngredientService {
    RecipeIngredient addRecipeIngredient(RecipeIngredient recipeIngredient) throws AppException;
    RecipeIngredient getSingleRecipeIngredient(int id) throws AppException;
    void updateRecipeIngredient(int id, RecipeIngredient recipeIngredient) throws AppException;
    void deleteRecipeIngredient(int id) throws AppException;
    List<RecipeIngredient> getAllRecipeIngredients();
    List<RecipeIngredient> getAllRecipeIngredientByRecipeId(int recipeId) throws AppException;
    double getTotalPriceByRecipeId(int recipeId);
        //get amount
        //get ingredient price
        //calculate recipe price
}
