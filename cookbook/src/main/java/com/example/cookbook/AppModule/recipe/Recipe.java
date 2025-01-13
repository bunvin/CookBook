package com.example.cookbook.AppModule.recipe;

import java.time.LocalDateTime;
import java.util.List;

import com.example.cookbook.AppModule.recipeIngredient.RecipeIngredient;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //creator/editor >> UserId

    private String title;
    private boolean isPrefered = true; 

    @Enumerated(EnumType.STRING)
    private MethodType methodType;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private String cookingTime;
    private int numofServing;


    // @OneToMany(mappedBy = "recipe", cascade = {CascadeType.ALL})
    // private List<RecipeIngredient> recipeIngredients; 

    @Builder.Default
    private LocalDateTime createdDateTime = LocalDateTime.now();

    @Builder.Default
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedDateTime = LocalDateTime.now();
}
