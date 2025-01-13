package com.example.cookbook.AppModule.recipeIngredient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.cookbook.AppModule.ingredient.IngredientDTO;
import com.example.cookbook.AppModule.recipe.RecipeDTO;
import com.example.cookbook.ErrorHandeling.AppException;
import com.example.cookbook.AppModule.recipe.Recipe;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeIngredientServiceImp implements RecipeIngredientService{
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final ModelMapper modelMapper;

    @Override
    public RecipeIngredientDTO addRecipeIngredient(RecipeIngredientDTO recipeIngredientDTO) throws AppException {
        if(this.recipeIngredientRepository.existsById(recipeIngredientDTO.getId())) {
            throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_ALREADY_EXIST);
        }
        //check if ingredient already in recipe
        if(this.recipeIngredientRepository.existsByRecipe_IdAndIngredient_Id(
            recipeIngredientDTO.getRecipe().getId(), recipeIngredientDTO.getIngredient().getId())){
                throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_ALREADY_EXIST_IN_RECIPE);
        }
        if(recipeIngredientDTO.getIngredientAmountInGr() <= 0.0) {
            throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_MISSING_AMOUNT);
        }
        IngredientDTO ingredient = recipeIngredientDTO.getIngredient();
        if(ingredient.getPriceper1grNIS() <= 0.0) {
            throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_MISSING_PRICE);
        }
        RecipeIngredient recipeIngredient = this.modelMapper.map(recipeIngredientDTO, RecipeIngredient.class);
        recipeIngredient = this.recipeIngredientRepository.save(recipeIngredient);

        return this.modelMapper.map(recipeIngredient, RecipeIngredientDTO.class);
    }

    @Override
    public RecipeIngredient getSingleRecipeIngredient(int id) throws AppException {
        return this.recipeIngredientRepository.findById(id)
        .orElseThrow(() -> new AppException(RecipeIngredientError.RECIPE_INGREDIENT_NOT_FOUND));
    }

    @Override
    public RecipeIngredientDTO getSingleRecipeIngredientDTO(int id) throws AppException {
        RecipeIngredient recipeIngredient =  this.getSingleRecipeIngredient(id);
        return this.modelMapper.map(recipeIngredient,RecipeIngredientDTO.class);
    }

    @Override
    public void updateRecipeIngredient(int id, RecipeIngredientDTO recipeIngredientDTO) throws AppException {
        RecipeIngredient recipeIngredientDB = this.getSingleRecipeIngredient(id);
        if(!this.recipeIngredientRepository.existsById(id)){
            throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_NOT_FOUND);
        }
        if(recipeIngredientDTO.getIngredientAmountInGr() <= 0.0) {
            throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_MISSING_AMOUNT);
        }
        IngredientDTO ingredient = recipeIngredientDTO.getIngredient();
        if(ingredient.getPriceper1grNIS() <= 0.0) {
            throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_MISSING_PRICE);
        }
        //if ingredient already in recipe
        if(this.recipeIngredientRepository.existsByRecipe_IdAndIngredient_Id(
            recipeIngredientDTO.getRecipe().getId(), ingredient.getId())){
                throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_ALREADY_EXIST_IN_RECIPE);
            }

        recipeIngredientDTO.setId(recipeIngredientDB.getId());
        RecipeIngredient recipeIngredient = this.modelMapper.map(recipeIngredientDTO, RecipeIngredient.class);
        
        this.recipeIngredientRepository.save(recipeIngredient);
    }

    @Override
    public void deleteRecipeIngredient(int id) throws AppException {
        this.getSingleRecipeIngredient(id);
        this.recipeIngredientRepository.deleteById(id);
        }

    @Override
    public List<RecipeIngredientDTO> getAllRecipeIngredients(){
        List<RecipeIngredient> recipesIngredients = this.recipeIngredientRepository.findAll();
        List<RecipeIngredientDTO> recipesIngredientsDTO = recipesIngredients.stream()
        .map(recipe -> this.modelMapper.map(recipe, RecipeIngredientDTO.class)).toList();
        return recipesIngredientsDTO;
    }

    @Override
    public List<RecipeIngredientDTO> getAllRecipeIngredientByRecipeId(int recipeId) {
        List<RecipeIngredient> recipesIngredients = this.recipeIngredientRepository.findAllIngredientsByRecipe_Id(recipeId);
        List<RecipeIngredientDTO> recipesIngredientsDTO = recipesIngredients.stream()
        .map(recipe -> this.modelMapper.map(recipe, RecipeIngredientDTO.class)).toList();
        return recipesIngredientsDTO;        
    }

	@Override
	public double getTotalPriceByRecipeId(int recipeId) {
        return this.recipeIngredientRepository.calculateTotalPriceForRecipe(recipeId);	
    }

    @Override
    public List<RecipeDTO> getAllRecipeWithIngredients(int... ingredientIds) throws AppException {
        return this.getAllRecipeWithIngredients(ingredientIds);
    }

    @Override
    public List<RecipeDTO> getAllRecipeWithIngredient(int ingredientId) {
        List<Recipe> recipes = this.recipeIngredientRepository.findAllRecipesByIngredientId(ingredientId);
        List<RecipeDTO> recipeDTOList = recipes.stream().map(recipe -> this.modelMapper.map(recipe, RecipeDTO.class)).collect(Collectors.toList());
        return recipeDTOList;
    }
}
