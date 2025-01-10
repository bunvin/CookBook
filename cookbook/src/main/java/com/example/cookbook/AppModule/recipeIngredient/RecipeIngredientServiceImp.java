package com.example.cookbook.AppModule.recipeIngredient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.cookbook.AppModule.ingredient.Ingredient;
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
    public RecipeIngredient addRecipeIngredient(RecipeIngredient recipeIngredient) throws AppException {
        if(this.recipeIngredientRepository.existsById(recipeIngredient.getId())) {
            throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_ALREADY_EXIST);
        }
        //check if ingredient already in recipe
        if(this.recipeIngredientRepository.existsByRecipe_IdAndIngredient_Id(
            recipeIngredient.getRecipeId(), recipeIngredient.getIngredientId())){
                throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_ALREADY_EXIST_IN_RECIPE);
        }
        if(recipeIngredient.getIngredientAmountInGr() <= 0.0) {
            throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_MISSING_AMOUNT);
        }
        Ingredient ingredient = recipeIngredient.getIngredient();
        if(ingredient.getPriceper1grNIS() <= 0.0) {
            throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_MISSING_PRICE);
        }
        return this.recipeIngredientRepository.save(recipeIngredient);
    }

    @Override
    public RecipeIngredient getSingleRecipeIngredient(int id) throws AppException {
        return this.recipeIngredientRepository.findById(id)
        .orElseThrow(() -> new AppException(RecipeIngredientError.RECIPE_INGREDIENT_NOT_FOUND));
    }

    @Override
    public void updateRecipeIngredient(int id, RecipeIngredient recipeIngredient) throws AppException {
        RecipeIngredient recipeIngredientDB = this.getSingleRecipeIngredient(id);
        if(!this.recipeIngredientRepository.existsById(id)){
            throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_NOT_FOUND);
        }
        if(recipeIngredient.getIngredientAmountInGr() <= 0.0) {
            throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_MISSING_AMOUNT);
        }
        Ingredient ingredient = recipeIngredient.getIngredient();
        if(ingredient.getPriceper1grNIS() <= 0.0) {
            throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_MISSING_PRICE);
        }
        //if ingredient already in recipe
        if(this.recipeIngredientRepository.existsByRecipe_IdAndIngredient_Id(
            recipeIngredient.getRecipeId(), recipeIngredient.getIngredientId())){
                throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_ALREADY_EXIST_IN_RECIPE);
            }

        recipeIngredient.setId(recipeIngredientDB.getId());
        this.recipeIngredientRepository.save(recipeIngredient);
    }

    @Override
    public void deleteRecipeIngredient(int id) throws AppException {
        this.getSingleRecipeIngredient(id);
        this.recipeIngredientRepository.deleteById(id);
        }

    @Override
    public List<RecipeIngredient> getAllRecipeIngredients(){
        return this.recipeIngredientRepository.findAll();    
    }

    @Override
    public List<RecipeIngredient> getAllRecipeIngredientByRecipeId(int recipeId) {
        return this.recipeIngredientRepository.findAllIngredientsByRecipe_Id(recipeId);
            }

	@Override
	public double getTotalPriceByRecipeId(int recipeId) {
        return this.recipeIngredientRepository.calculateTotalPriceForRecipe(recipeId);	
    }

    @Override
    public List<RecipeDTO> getAllRecipeWithIngredients(int... ingredientIds) throws AppException {
    // Combine all ingredient IDs into a single list and validate Ids
    List<Integer> allIngredientIds = new ArrayList<>();
        for (int ingredientId : ingredientIds) {
            if(this.recipeIngredientRepository.existsById(ingredientId)){
                allIngredientIds.add(ingredientId);
            }else{
                throw new AppException(RecipeIngredientError.RECIPE_INGREDIENT_NOT_FOUND);
            }
        }

    List<Recipe> recipeList = this.recipeIngredientRepository.findAllRecipesByIngredientIds(allIngredientIds, allIngredientIds.size());
    List<RecipeDTO> recipeDTOList = recipeList.stream().map(recipe -> this.modelMapper.map(recipe, RecipeDTO.class)).collect(Collectors.toList());

    return recipeDTOList;
}

    @Override
    public List<RecipeDTO> getAllRecipeWithIngredient(int ingredientId) {
        List<Recipe> recipes = this.recipeIngredientRepository.findAllRecipesByIngredientId(ingredientId);
        List<RecipeDTO> recipeDTOList = recipes.stream().map(recipe -> this.modelMapper.map(recipe, RecipeDTO.class)).collect(Collectors.toList());
        return recipeDTOList;
    }
}
