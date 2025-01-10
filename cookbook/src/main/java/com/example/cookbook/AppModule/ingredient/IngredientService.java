package com.example.cookbook.AppModule.ingredient;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cookbook.ErrorHandeling.AppException;

@Service
public interface IngredientService {
    IngredientDTO addIngredient(IngredientDTO ingredient) throws AppException; //ExistId
    Ingredient getSingleIngredient(int id) throws AppException;//Id Not exist
    void updateIngredient(int id, IngredientDTO ingredient) throws AppException; //non exist Id, non exist name
    void deleteIngredient(int id)throws AppException; //non exist ID
    List<IngredientDTO> getAllIngredients();

    void setAndCalculatePricePer1gr(int ingredientId, double pricePerPackage, double packageWeight, String weightUnit) throws AppException;
    IngredientDTO getSingleIngredientDTO(int id) throws AppException;
}
