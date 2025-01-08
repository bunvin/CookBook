package com.example.cookbook.AppModule.ingredient;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cookbook.ErrorHandeling.AppException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientServiceImp implements IngredientService{
    private final IngredientRepository ingredientRepository;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) throws AppException {
        if(this.ingredientRepository.existsById(ingredient.getId())){
            throw new AppException(IngredientError.INGREDIENT_ALREADY_EXIST);
        }
        return this.ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient getSingleIngredient(int id) throws AppException{
        return 
        this.ingredientRepository.findById(id)
        .orElseThrow(() -> new AppException(IngredientError.INGREDIENT_NOT_FOUND));    
    }

    @Override
    public void updateIngredient(int id, Ingredient ingredient) throws AppException{
        Ingredient ingredientFromDB = getSingleIngredient(id); //AppException Not found
        ingredient.setId(ingredientFromDB.getId());
        ingredientRepository.save(ingredient);    
    }

    @Override
    public void deleteIngredient(int id) throws AppException{
        getSingleIngredient(id); //AppException Not found
        this.ingredientRepository.deleteById(id);            
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return this.ingredientRepository.findAll();
    }


    @Override
    public void setAndcalculatePricePer1gr(int ingredientId, double pricePerPackage, double packageWeight, String weightUnit) throws AppException {
        Ingredient ingredient = this.getSingleIngredient(ingredientId);

        if((weightUnit.toLowerCase()).equals("kg")){
            ingredient.setPriceper1gr(pricePerPackage/(1000*packageWeight));
        }
        if((weightUnit.toLowerCase()).equals("gr")){
            ingredient.setPriceper1gr(pricePerPackage/packageWeight);
    }
    this.updateIngredient(ingredientId, ingredient);
    System.out.println(ingredient.getName()+" newly calculated price per 1 gr is: "+ ingredient.getPriceper1gr()+"₪");
}

}
