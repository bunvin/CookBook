package com.example.cookbook.AppModule.recipe;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.cookbook.AppModule.recipeIngredient.RecipeIngredientServiceImp;
import com.example.cookbook.ErrorHandeling.AppException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeServiceImp implements RecipeService{
    private final RecipeRepository recipeRepository;
    private final RecipeIngredientServiceImp recipeIngredientServiceImp;
    private final ModelMapper modelMapper;

	@Override
	public RecipeDTO addRecipe(RecipeDTO recipeDTO) throws AppException {
        Recipe recipe = this.modelMapper.map(recipeDTO, Recipe.class);
		if(this.recipeRepository.existsById(recipeDTO.getId())){
            throw new AppException(RecipeError.RECIPE_ALREADY_EXIST);
        }
        if(this.recipeRepository.findByTitle(recipeDTO.getTitle()) != null){
            throw new AppException(RecipeError.RECIPE_TITLE_ALREADY_EXIST);
        }	
        recipe = this.recipeRepository.save(recipe);
        return this.modelMapper.map(recipe,RecipeDTO.class);
    }

	@Override
	public Recipe getSingleRecipe(int id) throws AppException {
		return this.recipeRepository.findById(id)
        .orElseThrow(() -> new AppException(RecipeError.RECIPE_NOT_FOUND));	
    }

	@Override
	public void updateRecipe(int id, RecipeDTO recipe) throws AppException {
        Recipe recipeFromDB = this.getSingleRecipe(id);
        if(this.recipeRepository.findByTitle(recipe.getTitle()) != null){
            throw new AppException(RecipeError.RECIPE_TITLE_ALREADY_EXIST);
        }	        
        recipe.setId(recipeFromDB.getId());
        Recipe updatedRecipe = this.modelMapper.map(recipe, Recipe.class);
        this.recipeRepository.save(updatedRecipe);
	}

	@Override
	public void deleteRecipe(int id) throws AppException {
		this.getSingleRecipe(id); //Exception id not found
        this.recipeRepository.deleteById(id);	
    }

    @Override
	public List<RecipeDTO> getAllRecipiesDTO() {
        List<Recipe> recipes = this.recipeRepository.findAll();
        List<RecipeDTO> recipesDTO = recipes.stream().map(recipe -> this.modelMapper.map(recipe, RecipeDTO.class)).toList();
        return recipesDTO;
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

    @Override
    public RecipeDTO getSingleRecipeDTO(int id) throws AppException {
        Recipe recipe = this.getSingleRecipe(id);
        return this.modelMapper.map(recipe, RecipeDTO.class);
    }


}
