package com.example.cookbook.AppModule.recipeIngredient;

import com.example.cookbook.AppModule.ingredient.IngredientDTO;
import com.example.cookbook.AppModule.recipe.RecipeDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientDTO {
    private int id;
    private RecipeDTO recipe;
    private IngredientDTO ingredient;
    private double ingredientAmountInGr;
    private boolean isOptional;
    
}
