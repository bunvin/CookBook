package com.example.cookbook.AppModule.ingredient;


public enum Type{
    PANTRY("pantry"),
    FRESH("fresh"),
    FRESHorFROZEN("fresh or frozen"),
    FROZEN("frozen");

    private final String description;

        Type(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

}