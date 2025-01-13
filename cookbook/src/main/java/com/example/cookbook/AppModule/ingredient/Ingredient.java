package com.example.cookbook.AppModule.ingredient;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(updatable=false)
    private String name;
    
    @Column(name = "price_per_1gr_nis")
    private double priceper1grNIS = 0.0;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Builder.Default
    private LocalDateTime createdDateTime = LocalDateTime.now();

    @Builder.Default
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedDateTime = LocalDateTime.now();

}
