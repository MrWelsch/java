package knuth.factories;

import knuth.service.implementation.IngredientServiceImpl;
import knuth.service.implementation.RecipeServiceImpl;

/**
 * @author Hannes Schumacher, Yannick Strittmatter
 * @version 1.0
 * 
 */

public class ServiceFactory {

    /**
     * empty constructor?
     */
    public ServiceFactory(){};

    /**
     * 
     * @return IngredientService
     */
    public static IngredientServiceImpl createIngredientService(){
        return new IngredientServiceImpl();
    }
    
    /**
     * 
     * @return RecipeService
     */
    public static RecipeServiceImpl createRecipeService(){
        return new RecipeServiceImpl();
    }    
}
