package com.example.cookbook.AppModule.ingredient;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cookbook.ErrorHandeling.AppException;

@Service
public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient) throws AppException; //ExistId
    Ingredient getSingleIngredient(int id) throws AppException;//Id Not exist
    void updateIngredient(int id, Ingredient ingredient) throws AppException; //non exist Id, non exist name
    void deleteIngredient(int id)throws AppException; //non exist ID
    List<Ingredient> getAllIngredients();

    void setAndcalculatePricePer1gr(int ingredientId, double pricePerPackage, double packageWeight, String weightUnit) throws AppException;


}
