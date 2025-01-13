package com.example.cookbook.AppModule.recipeIngredient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.cookbook.AppModule.recipe.Recipe;
import com.example.cookbook.AppModule.recipe.RecipeDTO;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer>{

    List<RecipeIngredient> findAllIngredientsByRecipe_Id(int id);
    boolean existsByRecipe_IdAndIngredient_Id(int recipeId, int ingredientId);

    // @Query(value="SELECT SUM(ri.ingredient.price_per_1gr_nis * ri.ingredient_amount_in_gr) FROM recipe_ingredient ri WHERE ri.recipe.id = :recipeId", nativeQuery = true)
    // Double calculateTotalPriceForRecipe(@Param("recipeId") int recipeId);
    
    @Query(value="SELECT SUM(CAST(ri.ingredient_amount_in_gr AS DOUBLE) * i.price_per_1gr_nis) " +
               "FROM recipe_ingredient ri " +
               "JOIN ingredient i ON ri.ingredient_id = i.id " +
               "WHERE ri.recipe_id = :recipeId", nativeQuery = true)
    Double calculateTotalPriceForRecipe(@Param("recipeId") int recipeId);

    @Query("SELECT ri.recipe FROM recipe_ingredient ri WHERE ri.ingredient.id = :ingredientId")
    List<Recipe> findAllRecipesByIngredientId(@Param("ingredientId") int ingredientId);

    @Query("SELECT ri.recipe FROM recipe_ingredient ri " +
    "WHERE ri.ingredient.id IN :ingredientIds " +
    "GROUP BY ri.recipe.id " +
    "HAVING COUNT(ri.recipe.id) = :ingredientCount")
    List<Recipe> findAllRecipesByIngredientIds(@Param("ingredientIds") int[] ingredientIds, @Param("ingredientCount") int ingredientCount);
}
