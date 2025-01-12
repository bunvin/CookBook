package com.example.cookbook.AppModule.recipeIngredient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.cookbook.AppModule.recipe.Recipe;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer>{

    List<RecipeIngredient> findAllIngredientsByRecipe_Id(int id);
    boolean existsByRecipe_IdAndIngredient_Id(int recipeId, int ingredientId);

    @Query(value="SELECT SUM(ri.ingredient.priceper1gr * ri.ingredientAmountInGr) FROM RecipeIngredient ri WHERE ri.recipe.id = :recipeId", nativeQuery = true)
    Double calculateTotalPriceForRecipe(@Param("recipeId") int recipeId);
    
    @Query("SELECT ri.recipe FROM RecipeIngredient ri WHERE ri.ingredient.id = :ingredientId")
    List<Recipe> findAllRecipesByIngredientId(@Param("ingredientId") int ingredientId);

    @Query("SELECT ri.recipe FROM RecipeIngredient ri " +
    "WHERE ri.ingredient.id IN :ingredientIds " +
    "GROUP BY ri.recipe.id " +
    "HAVING COUNT(ri.recipe.id) = :ingredientCount")
    List<Recipe> findAllRecipesByIngredientIds(@Param("ingredientIds") List<Integer> ingredientIds, @Param("ingredientCount") long ingredientCount);
}
