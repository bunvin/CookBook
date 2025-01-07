package com.example.cookbook.AppModule.ingredient;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientServiceImp implements IngredientService{
    private final IngredientRepository ingredientRepository;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addIngredient'");
    }

    @Override
    public Ingredient getSingleIngredient(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSingleIngredient'");
    }

    @Override
    public void updateIngredient(int id, Ingredient ingredient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateIngredient'");
    }

    @Override
    public void deleteIngredient(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteIngredient'");
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllIngredients'");
    }

    

}
