package com.example.cookbook.AppModule.ingredient;

import com.example.cookbook.ErrorHandeling.ErrorMessage;


public enum IngredientError implements ErrorMessage{
    INGREDIENT_NOT_FOUND(1001, "Ingredient Id not found"),
    INGREDIENT_ALREADY_EXIST(1002, "Ingredient Id already exist");
  
    private final int code;
    private final String message;
    
    IngredientError(int i, String string) {
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
