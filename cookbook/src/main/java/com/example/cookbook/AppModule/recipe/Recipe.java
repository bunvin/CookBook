package com.example.cookbook.AppModule.recipe;

import java.util.List;

import org.springframework.data.repository.cdi.Eager;

import com.example.cookbook.AppModule.ingredient.Ingredient;
import com.example.cookbook.AppModule.recipeIngredient.RecipeIngredient;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String method;
    private String cookingTime;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeIngredient> recipeIngredients; 

}
