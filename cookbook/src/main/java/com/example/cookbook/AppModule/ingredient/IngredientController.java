package com.example.cookbook.AppModule.ingredient;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cookbook.AppModule.recipeIngredient.RecipeIngredientServiceImp;
import com.example.cookbook.ErrorHandeling.AppException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ingredient")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IngredientController {
    private final IngredientServiceImp ingredientServiceImp;
    private final RecipeIngredientServiceImp recipeIngredientServiceImp;

    @PostMapping
    public IngredientDTO addIngredient(@RequestBody IngredientDTO ingredient) throws AppException {
        return this.ingredientServiceImp.addIngredient(ingredient);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable int id) throws AppException{
        this.ingredientServiceImp.deleteIngredient(id);
    }

    @PutMapping
    public void updateIngredient(@RequestBody IngredientDTO ingredient, @RequestParam int id) throws AppException {
        this.ingredientServiceImp.updateIngredient(id, ingredient);
    }

    //calculate and update ingredient price per gr
    @PutMapping("/calculate-price/{ingredientId}")
    public void setAndCalculatePricePer1gr(
                @PathVariable int ingredientId,
                @RequestParam double pricePerPackageNIS,
                @RequestParam double packageWeight,
                @RequestParam String UnitKgOrGr) throws AppException{
            
                this.ingredientServiceImp.setAndCalculatePricePer1gr(ingredientId, pricePerPackageNIS, packageWeight, UnitKgOrGr);
                }
        //in use: /api/ingredients/calculate-price/1?pricePerPackageNIS=100&packageWeight=2.5&UnitKgOrGr=kg
    
    @GetMapping("/{id}")
    public IngredientDTO getSingleIngredient(@PathVariable int id) throws AppException{
        return this.ingredientServiceImp.getSingleIngredientDTO(id);
    }

    @GetMapping("/all")
    public List<IngredientDTO> getAllIngredientDTO() throws AppException{
        return this.ingredientServiceImp.getAllIngredients();
    }

    


}
