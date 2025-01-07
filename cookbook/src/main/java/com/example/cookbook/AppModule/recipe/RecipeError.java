package com.example.cookbook.AppModule.recipe;
import com.example.cookbook.ErrorHandeling.ErrorMessage;


public enum RecipeError implements ErrorMessage{
    RECIPE_NOT_FOUND(2001, "Recipe Id not found"),
    RECIPE_ALREADY_EXIST(2002, "Recipe Id already exist"),
    RECIPE_TITLE_ALREADY_EXIST(2003, "Recipe Title already in use");

    private final int code;
    private final String message;

    RecipeError(int i, String string) {
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
