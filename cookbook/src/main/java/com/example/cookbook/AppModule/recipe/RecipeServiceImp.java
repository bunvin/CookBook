package com.example.cookbook.AppModule.recipe;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeServiceImp implements RecipeService{
    private final RecipeRepository recipeRepository;

    @Override
    public Recipe addRecipe(Recipe recipe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addRecipe'");
    }

    @Override
    public Recipe getSingleRecipe(Recipe recipe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSingleRecipe'");
    }

    @Override
    public void updateRecipe(int id, Recipe recipe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRecipe'");
    }

    @Override
    public void deleteRecipe(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRecipe'");
    }

    @Override
    public List<Recipe> getAllRecipies() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRecipies'");
    }
    

}
