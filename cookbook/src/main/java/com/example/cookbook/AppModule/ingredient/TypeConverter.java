package com.example.cookbook.AppModule.ingredient;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TypeConverter implements AttributeConverter<Type, String> {

    @Override
    public String convertToDatabaseColumn(Type type) {
        if (type == null) {
            return null;
        }
        return type.getDescription();  // Store description in the database
    }

    @Override
    public Type convertToEntityAttribute(String description) {
        if (description == null) {
            return null;
        }
        for (Type type : Type.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown description: " + description);
    }
}