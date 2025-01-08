package com.example.cookbook.AppModule.recipe;

public enum MethodType {
    GRILL("grill"),
    PAN_FRY("pan-fry"),
    ROAST("roast"),
    STEAMED("steamed"),
    DEEP_FRY_AIR_FRY("deep-fry or air-fry"),
    BAKE("bake"),
    FERMENT_PICKEL("ferment or pickel");

    private final String description;

        MethodType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

}
