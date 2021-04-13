package knuth.factories;

import knuth.dao.implementation.IngredientDaoImpl;
import knuth.dao.implementation.RecipeDaoImpl;
import knuth.dao.interfaces.IngredientDao;
import knuth.dao.interfaces.RecipeDao;

/**
 * Object Factory that creates object of the dao level.
 * @author Nico Welsch
 * @version 0.1
 */
public class DaoFactory {

    private DaoFactory(){};

    /**
     * Creates an IngredientController object.
     * @return IngredientDao object
     */
    public static IngredientDao createIngredientController() {

        return new IngredientDaoImpl();

    }

    /**
     * Creates a RecipeController object.
     * @return RecipeDao object
     */
    public static RecipeDao createRecipeController() {
        
        return new RecipeDaoImpl();

    }

}
