package com.example.cookbook.AppModule.recipeIngredient;

import com.example.cookbook.AppModule.ingredient.Ingredient;
import com.example.cookbook.AppModule.recipe.Recipe;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private double ingredientAmountInGr;
    private boolean isOptional;

    public int getIngredientId() {
        return this.ingredient.getId();
    }

    public int getRecipeId() {
        return this.recipe.getId();
    }
}
