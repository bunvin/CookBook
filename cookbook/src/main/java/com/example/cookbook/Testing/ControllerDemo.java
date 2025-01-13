package com.example.cookbook.Testing;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.cookbook.AppModule.ingredient.IngredientDTO;
import com.example.cookbook.AppModule.ingredient.IngredientServiceImp;
import com.example.cookbook.AppModule.ingredient.Type;
import com.example.cookbook.AppModule.recipe.MethodType;
import com.example.cookbook.AppModule.recipe.RecipeDTO;
import com.example.cookbook.AppModule.recipe.RecipeServiceImp;
import com.example.cookbook.AppModule.recipeIngredient.RecipeIngredientDTO;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class ControllerDemo implements CommandLineRunner{
    private final RestTemplate restTemplate;

    private final IngredientServiceImp ingredientServiceImp; 
    private final RecipeServiceImp RecipeServiceImp;
    
    private String url = "http://localhost:8080/api";

//start with Ingredient controller

    @Override
    public void run(String... args) throws Exception {
        //Ingredient Controller testing
        String ingredientURL = url+"/ingredient";

        IngredientDTO newIngredient1 = IngredientDTO.builder()
        .name("Tomato")
        .type(Type.FRESH).build();

        //add
        newIngredient1 = restTemplate.postForObject(ingredientURL,newIngredient1,IngredientDTO.class);

        //update price per gr
        String newIngredientURL = ingredientURL+"/calculate-price/"+newIngredient1.getId()+
        "?"+"pricePerPackageNIS="+11.9
        +"&"+"packageWeight="+1
        +"&"+"UnitKgOrGr="+"kg";
        
        restTemplate.put(newIngredientURL, newIngredient1);

        restTemplate.delete(ingredientURL+"/"+newIngredient1.getId());

        FactoryUtils.initIngredientsShakshuKa(ingredientServiceImp); //with tomato

        //Recipe Controller testing
        String recipeURL = url+"/recipe";

        RecipeDTO recipe = RecipeDTO.builder()
        .title("Shakshuka")
        .cookingTime("20 minutes")
        .methodType(MethodType.PAN_FRY)
        .description("1. oil your pan and sautee the onion and hot green peppers on low-medium heat untill the onion is golden"
        +"\n2. add the tomatoes sautee untill they lose shape and becaaume a sauce"
        +"\n3. add salt pepper and sugar to taste"
        +"\n4. add hot water to loosen up the sauce (if too much let it time to reduce)"
        +"\n5. break the eggs on top and keep cooking on low heat untill they are to your liking")
        .numofServing(2)
        .build();

        //add
        recipe = restTemplate.postForObject(recipeURL, recipe, RecipeDTO.class);
        System.out.println(recipe);
        //get all ingredients
        List<IngredientDTO> ingredients = restTemplate.exchange(
            ingredientURL + "/all", 
            HttpMethod.GET, 
            null, 
            new ParameterizedTypeReference<List<IngredientDTO>>() {}
        ).getBody();        

        //add ingredients to recipe with amount
        String recipeIngredientURL = recipeURL+"/ingredient";
        RecipeIngredientDTO recipeIngredient = RecipeIngredientDTO.builder()
        .recipe(recipe).ingredient(ingredients.get(0)).ingredientAmountInGr(750).build();

        //add
        recipeIngredient = restTemplate.postForObject(recipeIngredientURL, recipeIngredient, RecipeIngredientDTO.class);

        //delete
        restTemplate.delete(recipeIngredientURL+"/"+recipeIngredient.getId());
        
        //get all recipe ingredients
        List<IngredientDTO> allIngredients = restTemplate.exchange(
            ingredientURL + "/all", 
            HttpMethod.GET, 
            null, 
            new ParameterizedTypeReference<List<IngredientDTO>>() {}
        ).getBody();
       
        //add all ingredients to recipe using service
        FactoryUtils.addIngredientsToRecipeShakshuka(allIngredients, recipe.getId(), RecipeServiceImp);        

        //get recipe total price
        Double recipeTotalPrice = restTemplate.exchange(
            recipeURL + "/total-price/" + recipe.getId(),
            HttpMethod.GET,
            null,
            Double.class
        ).getBody();
        
        System.out.println("Shakshuka total-price is : " + recipeTotalPrice);

        //get all recipe ingredients by recipe ID
        List<RecipeIngredientDTO> recipeIngredientsList = restTemplate.exchange(
            recipeIngredientURL+"/byRecipeId/"+recipe.getId(), 
            HttpMethod.GET,null, 
            new ParameterizedTypeReference<List<RecipeIngredientDTO>>() {}).getBody();
        System.out.println("### shopping list ###");
        for(RecipeIngredientDTO recipeIng : recipeIngredientsList){
            System.out.println(recipeIng.getIngredient().getName());
        }

        //Add another recipe
        FactoryUtils.addPastTomatoRecipe(ingredientServiceImp, RecipeServiceImp);

        //findAllRecipesByIngredientId
        List<RecipeDTO> tomatoRecipes = restTemplate.exchange(recipeURL+"/all-recipe-by-ingredient/"+2, HttpMethod.GET, null, new ParameterizedTypeReference<List<RecipeDTO>>() {}).getBody();
        //findAllRecipesByIngredientIds
        System.out.println("### recipies with tomato ###");
        for(RecipeDTO recipebying : tomatoRecipes){
            System.out.println(recipebying.getTitle());
        }

        // int[] ingredientsId = {2,7};
        // List<RecipeDTO> tomatoGarlicRecipes = restTemplate.exchange(
        //     recipeURL+"/all-recipe-by-ingredients/"+"2,7", HttpMethod.GET, null, new ParameterizedTypeReference<List<RecipeDTO>>() {}).getBody();
        // System.out.println("### recipies with tomato and garlic ###");
        // for(RecipeDTO recipebying : tomatoGarlicRecipes){
        //     System.out.println(recipebying.getTitle());
        // }







    }

}
