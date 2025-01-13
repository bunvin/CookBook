package com.example.cookbook.AppModule.recipe;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cookbook.AppModule.recipeIngredient.RecipeIngredientDTO;
import com.example.cookbook.AppModule.recipeIngredient.RecipeIngredientServiceImp;
import com.example.cookbook.ErrorHandeling.AppException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipe")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecipeController {
    private final RecipeServiceImp recipeServiceImp;
    private final RecipeIngredientServiceImp recipeIngredientServiceImp;

    @PostMapping
    public RecipeDTO addRecipe(@RequestBody RecipeDTO recipe) throws AppException{
        return this.recipeServiceImp.addRecipe(recipe);
    }

    @PutMapping
    public void updateRecipe(@RequestBody int id, RecipeDTO recipe) throws AppException{
        this.recipeServiceImp.updateRecipe(id, recipe);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable int id) throws AppException{
        this.recipeServiceImp.deleteRecipe(id);
    } 

    @GetMapping("/{id}")
    public RecipeDTO getSingleRecipe(@PathVariable int id) throws AppException{
        return this.recipeServiceImp.getSingleRecipeDTO(id);
    } 

    @GetMapping("/all")
    public List<RecipeDTO> getAllRecipies() {
        return this.recipeServiceImp.getAllRecipiesDTO();
    }

    @GetMapping("/total-price/{id}")
    public double getRecipeTotalPrice(@PathVariable int id) throws AppException{
        return this.recipeIngredientServiceImp.getTotalPriceByRecipeId(id); 
    }

    @GetMapping("/all-recipe-by-ingredient/{ingredientId}")
    public List<RecipeDTO> getAllRecipeWithIngredient(@PathVariable int ingredientId) {
        return this.recipeIngredientServiceImp.getAllRecipeWithIngredient(ingredientId);
    }

    @GetMapping("/all-recipe-by-ingredients/{ingredientIds}")
    public List<RecipeDTO> getAllRecipeWithIngredients(
            @PathVariable int[] ingredientIds) throws AppException {
        // Call the service method with ingredientId1 and the rest of the ingredientIds
        return this.recipeIngredientServiceImp.getAllRecipeWithIngredients(ingredientIds);
    }
    //in use: /api/recipe-ingredients/all/1,2,3,4


    //Recipe ingredient 

    @PostMapping("/ingredient")
    public RecipeIngredientDTO addRecipeIngredient(@RequestBody RecipeIngredientDTO recipeIngredient) throws AppException{
        return this.recipeIngredientServiceImp.addRecipeIngredient(recipeIngredient);
    }

    @GetMapping("/ingredient/{id}")
    public RecipeIngredientDTO getSingleRecipeIngredient(@PathVariable int recipeIngredientId) throws AppException{
        return this.recipeIngredientServiceImp.getSingleRecipeIngredientDTO(recipeIngredientId);
    }
    
    //update RecipeIngredient //change amount, AppError: id, ingredient,recipe 
    @PutMapping("/ingredient")
    public void updateRecipeIngredientAmount(@RequestBody int id, @RequestBody RecipeIngredientDTO recipeIngredientDTO) throws AppException {
        this.recipeIngredientServiceImp.updateRecipeIngredient(id, recipeIngredientDTO);
    }

    @DeleteMapping("/ingredient/{id}")
    public void deleteRecipeIngredient(@PathVariable int id) throws AppException{
        this.recipeIngredientServiceImp.deleteRecipeIngredient(id);
    }

    @GetMapping("/ingredient/all")
    public List<RecipeIngredientDTO> getAllRecipesIngredients() {
        return this.recipeIngredientServiceImp.getAllRecipeIngredients();
    }

    //getAllRecipeIngredientByRecipeId
    @GetMapping("/ingredient/byRecipeId/{recipeId}")
    public List<RecipeIngredientDTO> getAllRecipeIngredientsById(@PathVariable int recipeId) {
        return this.recipeIngredientServiceImp.getAllRecipeIngredientByRecipeId(recipeId);
    }

    





}
