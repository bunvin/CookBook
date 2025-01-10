package com.example.cookbook.AppModule.ingredient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {
    private int id;
    private String name;
    private double priceper1grNIS = 0.0;
    private Type type;
}
