package com.example.cookbook.AppModule.ingredient;

import java.util.List;

import com.example.cookbook.AppModule.recipe.Recipe;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    
    @Column(name = "price_per_1gr")
    private double priceper1gr = 0.0;

    @Enumerated(EnumType.STRING)
    Type type;

    @ManyToMany(mappedBy = "ingredients", cascade={CascadeType.ALL}) //fetch = FetchType.LAZY ?
    List<Recipe> recipes;

    public void setAndcalculatePricePer1gr(double pricePerPackage, double packageWeight, String weightUnit) {
        if((weightUnit.toLowerCase()).equals("kg")){
            this.priceper1gr = pricePerPackage/(1000*packageWeight);
        }
        if((weightUnit.toLowerCase()).equals("gr")){
            this.priceper1gr = pricePerPackage/packageWeight;
    }
}

}
