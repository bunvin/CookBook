package com.example.cookbook.AppModule.ingredient;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.cookbook.ErrorHandeling.AppException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientServiceImp implements IngredientService{
    private final IngredientRepository ingredientRepository;
    private final ModelMapper modelMapper;

    @Override
    public IngredientDTO addIngredient(IngredientDTO ingredient) throws AppException {
        if(this.ingredientRepository.existsById(ingredient.getId())){
            throw new AppException(IngredientError.INGREDIENT_ALREADY_EXIST);
        }
        Ingredient newIngredient = this.modelMapper.map(ingredient, Ingredient.class);
        newIngredient = this.ingredientRepository.save(newIngredient);
        return this.modelMapper.map(newIngredient, IngredientDTO.class);
    }

    @Override
    public Ingredient getSingleIngredient(int id) throws AppException{
        return 
        this.ingredientRepository.findById(id)
        .orElseThrow(() -> new AppException(IngredientError.INGREDIENT_NOT_FOUND));    
    }

    @Override
    public IngredientDTO getSingleIngredientDTO(int id) throws AppException{
        Ingredient ingredient = this.getSingleIngredient(id);
        return this.modelMapper.map(ingredient, IngredientDTO.class);
    }

    @Override
    public void updateIngredient(int id, IngredientDTO ingredient) throws AppException{
        Ingredient ingredientFromDB = getSingleIngredient(id); //AppException Not found
        Ingredient updatedIngredient = this.modelMapper.map(ingredient,Ingredient.class);
        updatedIngredient.setId(ingredientFromDB.getId());
        this.ingredientRepository.save(updatedIngredient);    
    }

    @Override
    public void deleteIngredient(int id) throws AppException{
        getSingleIngredient(id); //AppException ID Not found
        this.ingredientRepository.deleteById(id);            
    }

    @Override
    public List<IngredientDTO> getAllIngredients() {
        List<Ingredient> ingredients = this.ingredientRepository.findAll();
        List<IngredientDTO> ingredientsDTO = ingredients.stream().map(ingredient -> this.modelMapper.map(ingredient,IngredientDTO.class)).toList();
        return ingredientsDTO;
    }

    @Override
    public void setAndCalculatePricePer1gr(int ingredientId, double pricePerPackage, double packageWeight, String weightUnit) throws AppException {
        Ingredient ingredient = this.getSingleIngredient(ingredientId); //exception if Id not found

        if((weightUnit.toLowerCase()).equals("kg")){
            ingredient.setPriceper1grNIS(pricePerPackage/(1000*packageWeight));
        }
        if((weightUnit.toLowerCase()).equals("gr")){
            ingredient.setPriceper1grNIS(pricePerPackage/packageWeight);
    }
    IngredientDTO updatedIngredient = this.modelMapper.map(ingredient, IngredientDTO.class);
    this.updateIngredient(ingredientId, updatedIngredient);
    System.out.println(ingredient.getName()+" newly calculated price per 1 gr is: "+ ingredient.getPriceper1grNIS()+"₪");
}



}
