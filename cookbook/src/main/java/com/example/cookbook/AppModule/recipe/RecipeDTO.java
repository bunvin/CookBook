package com.example.cookbook.AppModule.recipe;

import java.util.List;

import com.example.cookbook.AppModule.recipeIngredient.RecipeIngredientDTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    private int id;
    private String title; 
    private MethodType methodType;
    private String description;    
    private String cookingTime;
    private int numofServing;
    private boolean isPrefered = true;
    private List<RecipeIngredientDTO> recipeIngredients; 

}
