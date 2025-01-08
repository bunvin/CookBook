package com.example.cookbook.AppModule.recipe;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cookbook.AppModule.recipeIngredient.RecipeIngredientServiceImp;
import com.example.cookbook.ErrorHandeling.AppException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeServiceImp implements RecipeService{
    private final RecipeRepository recipeRepository;
    private final RecipeIngredientServiceImp recipeIngredientServiceImp;

	@Override
	public Recipe addRecipe(Recipe recipe) throws AppException {
		if(this.recipeRepository.existsById(recipe.getId())){
            throw new AppException(RecipeError.RECIPE_ALREADY_EXIST);
        }
        if(this.recipeRepository.findByTitle(recipe.getTitle()) != null){
            throw new AppException(RecipeError.RECIPE_TITLE_ALREADY_EXIST);
        }	
        return this.recipeRepository.save(recipe);
    }

	@Override
	public Recipe getSingleRecipe(int id) throws AppException {
		return this.recipeRepository.findById(id)
        .orElseThrow(() -> new AppException(RecipeError.RECIPE_NOT_FOUND));	
    }

	@Override
	public void updateRecipe(int id, Recipe recipe) throws AppException {
        if(this.recipeRepository.findByTitle(recipe.getTitle()) != null){
            throw new AppException(RecipeError.RECIPE_TITLE_ALREADY_EXIST);
        }	        
        Recipe recipeFromDB = this.getSingleRecipe(id);
        recipe.setId(recipeFromDB.getId());
        this.recipeRepository.save(recipe);
	}

	@Override
	public void deleteRecipe(int id) throws AppException {
		getSingleRecipe(id); //Exception id not found
        this.recipeRepository.deleteById(id);	
    }

	@Override
	public List<Recipe> getAllRecipies() {
        return this.recipeRepository.findAll();
	}

    @Override
    public double getRecipeTotalPrice(int recipeId) throws AppException {
        this.getSingleRecipe(recipeId);
        return this.recipeIngredientServiceImp.getTotalPriceByRecipeId(recipeId);        
    }


}
