package knuth.service.interfaces;


import knuth.model.entities.IngredientEntity;
import knuth.model.interfaces.Ingredient;

/**
 * 
 * Interface of IngredientService
 * 
 * author Hannes Schumacher
 * 
 * version 1.0
 * 
 */

public interface IngredientService{


    public Ingredient getIngredientByName (String keyword);


    /**
   
    * Method that adds an Ingredient to DAO
   
    * ?void oder Ingredient Zurückgeben?
   
    * @param ingredient
   
    * @return added Ingredient
   
    */
   
   public Ingredient addIngredient(IngredientEntity ingredient);
   
    
   
    /**
   
    * Method that deletes an Ingredient from DAO
   
    * ?void oder ingredient zurükgeben?
   
    * @param ingredient
   
    * @return deleted Ingredient
   
    */
   
   public void deleteIngredient(IngredientEntity ingredient);
   
   
   
 
   /**
    * 
    * @param inredient
    * @param name
    * @param category
    * @param calories
    * @param protein
    * @param carbohydrates
    * @param fat
    * @return changed ingredient
    */
   
   public Ingredient changeIngredient(IngredientEntity ingredient, String name, String category, float calories, float protein, float carbohydrates, float fat);
   
    
   
   
   
   
   
   }