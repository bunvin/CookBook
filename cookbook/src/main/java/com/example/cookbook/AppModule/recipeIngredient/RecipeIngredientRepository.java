package com.example.cookbook.AppModule.recipeIngredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cookbook.AppModule.recipe.Recipe;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<Recipe, Integer>{

}
