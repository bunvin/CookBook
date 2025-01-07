package com.example.cookbook.AppModule.recipeIngredient;

import com.example.cookbook.ErrorHandeling.ErrorMessage;


public enum RecipeIngredientError implements ErrorMessage{
    RECIPE_INGREDIENT_NOT_FOUND(3001, "Recipe's Ingredient Id not found"),
    RECIPE_INGREDIENT_ALREADY_EXIST(3002, "Recipe's Ingredient Id already exist");
    
    private final int code;
    private final String message;

    RecipeIngredientError(int i, String string) {
        this.code = i;
        this.message = string;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
