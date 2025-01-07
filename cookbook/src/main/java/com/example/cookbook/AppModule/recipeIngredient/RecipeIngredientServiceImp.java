package com.example.cookbook.AppModule.recipeIngredient;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeIngredientServiceImp implements RecipeIngredientService{
    private final RecipeIngredientRepository recipeIngredientRepository;

    @Override
    public RecipeIngredient addRecipeIngredient(RecipeIngredient recipeIngredient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addRecipeIngredient'");
    }

    @Override
    public RecipeIngredient getSingleRecipeIngredient(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSingleRecipeIngredient'");
    }

    @Override
    public void updateRecipeIngredient(int id, RecipeIngredient recipeIngredient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRecipeIngredient'");
    }

    @Override
    public void deleteRecipeIngredient(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRecipeIngredient'");
    }

    @Override
    public List<RecipeIngredient> getAllRecipeIngredients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRecipeIngredients'");
    }
    

}
