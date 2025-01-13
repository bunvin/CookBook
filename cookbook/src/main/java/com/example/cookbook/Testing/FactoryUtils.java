package com.example.cookbook.Testing;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.cookbook.AppModule.ingredient.IngredientDTO;
import com.example.cookbook.AppModule.ingredient.IngredientServiceImp;
import com.example.cookbook.AppModule.ingredient.Type;
import com.example.cookbook.AppModule.recipe.MethodType;
import com.example.cookbook.AppModule.recipe.RecipeDTO;
import com.example.cookbook.AppModule.recipe.RecipeServiceImp;
import com.example.cookbook.AppModule.recipeIngredient.RecipeIngredientDTO;
import com.example.cookbook.ErrorHandeling.AppException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FactoryUtils {

    //create ingredients
    public static void initIngredientsShakshuKa(IngredientServiceImp ingredientServiceImp) throws AppException {
        IngredientDTO newIngredient1 = IngredientDTO.builder()
        .name("Tomato")
        .type(Type.FRESH).build();
        newIngredient1 = ingredientServiceImp.addIngredient(newIngredient1);
        ingredientServiceImp.setAndCalculatePricePer1gr(newIngredient1.getId(),11.9, 1, "kg");

        IngredientDTO newIngredient2 = IngredientDTO.builder()
        .name("Onion")
        .type(Type.FRESH).build();
        newIngredient2 = ingredientServiceImp.addIngredient(newIngredient2);
        ingredientServiceImp.setAndCalculatePricePer1gr(newIngredient2.getId(),7.9, 1, "kg");

        IngredientDTO newIngredient3 = IngredientDTO.builder()
        .name("Green Hot Papper")
        .type(Type.FRESH).build();
        newIngredient3 = ingredientServiceImp.addIngredient(newIngredient3);
        ingredientServiceImp.setAndCalculatePricePer1gr(newIngredient3.getId(),12.9, 1, "kg");

        IngredientDTO newIngredient4 = IngredientDTO.builder()
        .name("Egg")
        .type(Type.FRESH).build();
        newIngredient4 = ingredientServiceImp.addIngredient(newIngredient4);
        //1 egg is 60gr X 12 = 720
        ingredientServiceImp.setAndCalculatePricePer1gr(newIngredient4.getId(),14.09, 720, "gr"); 
    }

    public static void addIngredientsToRecipeShakshuka(List<IngredientDTO> ingredients, int recipeId, RecipeServiceImp recipeServiceImp) throws AppException{
        // Scanner scanner = new Scanner(System.in);
        int[] ing_amount = {750, 120, 100, 240};
        RecipeDTO recipe = recipeServiceImp.getSingleRecipeDTO(recipeId);
         for(int i = 0; i < ingredients.size(); i++){
            System.out.println(ingredients.get(i).getName()+" amount in gr: ");
            int ingredientAmountInGr = ing_amount[i];
            // int ingredientAmountInGr = scanner.nextInt();
            RecipeIngredientDTO recipeIngredientDTO = RecipeIngredientDTO.builder()
            .recipe(recipe).ingredient(ingredients.get(i)).ingredientAmountInGr(ingredientAmountInGr).build();
            recipeServiceImp.addRecipeIngredient(recipeIngredientDTO);
         }
    }

    public static void addPastTomatoRecipe(IngredientServiceImp ingredientServiceImp, RecipeServiceImp recipeServiceImp) throws AppException{
        IngredientDTO newIngredient = IngredientDTO.builder()
        .name("Pasta")
        .type(Type.PANTRY).build();
        newIngredient = ingredientServiceImp.addIngredient(newIngredient);
        ingredientServiceImp.setAndCalculatePricePer1gr(newIngredient.getId(),1.7, 100, "gr");
        newIngredient = ingredientServiceImp.getSingleIngredientDTO(newIngredient.getId());

        IngredientDTO newIngredient1 = IngredientDTO.builder()
        .name("garlic")
        .type(Type.PANTRY).build();
        newIngredient1 = ingredientServiceImp.addIngredient(newIngredient1);
        ingredientServiceImp.setAndCalculatePricePer1gr(newIngredient1.getId(),3.63, 100, "gr");
        newIngredient1 = ingredientServiceImp.getSingleIngredientDTO(newIngredient1.getId());

        //add new Recipe
        RecipeDTO pastaRecipe = RecipeDTO.builder()
        .cookingTime("25 minutes")
        .title("Tomato Pasta")
        .numofServing(2)
        .description("1. boil water with salt, when boil add the pasta and cook for 8-12 minutes as written on package + 2 minutes"+
        "\n2. in a pan with oil of your choise add the garlic untill garlic smell is noticable, then add the diced tomatoes"+
        "\n3. when the tomatoes lose their shape add the pasta and a half cup of the pasta water")
        .methodType(MethodType.PAN_FRY).build();

        pastaRecipe = recipeServiceImp.addRecipe(pastaRecipe);

        //add to recipe . 1 clove 7 gr, 500gr pasta 2 serving
        List<IngredientDTO> ingredients = new ArrayList<>();
        ingredients.add(newIngredient);
        ingredients.add(newIngredient1);
        IngredientDTO tomato = ingredientServiceImp.getSingleIngredientDTO(2);
        ingredients.add(tomato);
        
        //add to ingredients to recipe
        int[] ing_amount = {500,21,500};
         for(int i = 0; i < ingredients.size(); i++){
            System.out.println(ingredients.get(i).getName()+" amount in gr: ");
            int ingredientAmountInGr = ing_amount[i];
            RecipeIngredientDTO recipeIngredientDTO = RecipeIngredientDTO.builder()
            .recipe(pastaRecipe).ingredient(ingredients.get(i)).ingredientAmountInGr(ingredientAmountInGr).build();
            recipeServiceImp.addRecipeIngredient(recipeIngredientDTO);
    }
}




}
