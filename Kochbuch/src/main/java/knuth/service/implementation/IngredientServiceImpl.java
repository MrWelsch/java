package knuth.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import knuth.facades.DaoFacade;
import knuth.model.entities.IngredientEntity;
import knuth.model.interfaces.Ingredient;
import knuth.service.interfaces.IngredientService;

/**
 * Ingredient Service class. 
 * @author Hannes Schumacher
 * @version 1.0
 */
@Service
public class IngredientServiceImpl implements IngredientService{

    @Autowired
    private DaoFacade daoFacade;


    @Override
    public Ingredient getIngredientByName (String keyword){
        List<IngredientEntity> ingredientlist = daoFacade.getIngredientByName(keyword);
        if (!ingredientlist.isEmpty()) {
            return ingredientlist.get(0);
        } else {
            return null;
        }
    }

    /**
     * adds an ingredient to database via daoFacade
     * @param ingredient
     * @return
     */ 
    @Override
    public Ingredient addIngredient(final IngredientEntity ingredient){
         return daoFacade.insertIngredient(ingredient);
    }      

    /**
     * deletes an ingredient via daoFacade
     */ 
    @Override
     public void deleteIngredient(final IngredientEntity ingredient){
         daoFacade.deleteIngredient(ingredient);
     }    
 
     /**
      * changes an ingredient.
      * besser wäre eine change Methode für jedes Ingredient Attribut. Was genau soll hier veraendert werden?
      */
    @Override
    public Ingredient changeIngredient(IngredientEntity ingredient, String name, String category, float calories, float protein, float carbohydrates, float fat){
        ingredient.setCalories(calories);
        ingredient.setCarbohydrates(carbohydrates);
        ingredient.setCategory(category);
        ingredient.setName(name);
        ingredient.setFat(fat);
        ingredient.setProtein(protein);

        daoFacade.updateIngredient(ingredient);
        return ingredient;
        
    }







    
}
