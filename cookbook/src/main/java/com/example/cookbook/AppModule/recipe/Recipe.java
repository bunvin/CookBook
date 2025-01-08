package com.example.cookbook.AppModule.recipe;

import java.util.List;

import com.example.cookbook.AppModule.recipeIngredient.RecipeIngredient;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    //creator/editor >> UserId

    private String title;
    private boolean isPrivate; 
    //if recipe changed by user would save a copy with changes, totle + today date, 
    //would show privatly only to the user

    @Enumerated(EnumType.STRING)
    MethodType methodType;
    
    private String description;
    private String cookingTime;


    @OneToMany(mappedBy = "recipe")
    private List<RecipeIngredient> recipeIngredients; 
}
