package com.example.cookbook.Testing;

import org.springframework.stereotype.Component;

import com.example.cookbook.AppModule.ingredient.IngredientServiceImp;
import com.example.cookbook.AppModule.recipe.RecipeServiceImp;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FactoryUtils {
    private final RecipeServiceImp recipeServiceImp;
    private final IngredientServiceImp ingredientServiceImp;

    //create ingredient
    public static void initIngredients(){

        
    }

    public void createRandomIngredient(int ingredientCount){

    }

    //create recipe //assign ingredients to recipe



}
