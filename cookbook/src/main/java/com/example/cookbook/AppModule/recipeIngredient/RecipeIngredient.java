package com.example.cookbook.AppModule.recipeIngredient;

import com.example.cookbook.AppModule.ingredient.Ingredient;
import com.example.cookbook.AppModule.recipe.Recipe;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "recipe_ingredient")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", updatable = false)
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", updatable = false)
    private Ingredient ingredient;

    @Column(name = "ingredient_amount_in_gr")
    private double ingredientAmountInGr;
    private boolean isOptional;

    public int getIngredientId() {
        return this.ingredient.getId();
    }

    public int getRecipeId() {
        return this.recipe.getId();
    }
}
