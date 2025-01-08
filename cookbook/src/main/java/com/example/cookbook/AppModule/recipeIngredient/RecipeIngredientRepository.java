package com.example.cookbook.AppModule.recipeIngredient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer>{

    List<RecipeIngredient> findAllIngredientsByRecipe_Id(int id);
    // boolean existsByRecipeIdAndIngredientId(int recipeId, int ingredientId);
    boolean existsByRecipe_IdAndIngredient_Id(int recipeId, int ingredientId);

    @Query(value="SELECT SUM(ri.ingredient.priceper1gr * ri.ingredientAmountInGr) FROM RecipeIngredient ri WHERE ri.recipe.id = :recipeId", nativeQuery = true)
    Double calculateTotalPriceForRecipe(@Param("recipeId") int recipeId);
    
}
