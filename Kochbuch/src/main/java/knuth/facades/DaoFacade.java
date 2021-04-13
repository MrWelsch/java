package knuth.facades;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import knuth.dao.interfaces.IngredientDao;
import knuth.dao.interfaces.RecipeDao;
import knuth.factories.DaoFactory;
import knuth.model.entities.IngredientEntity;
import knuth.model.entities.RecipeEntity;
import knuth.model.interfaces.Ingredient;
import knuth.model.interfaces.Recipe;

/**
 * Class that connects the database level to the service level of the program architecture.
 * @author Nico Welsch, 
 * @version 0.1
 */
@RestController
public class DaoFacade {

    @Autowired
    private IngredientDao ingredientDao;

    @Autowired
    private RecipeDao recipeDao;

    public DaoFacade() {

        ingredientDao = DaoFactory.createIngredientController();
        recipeDao = DaoFactory.createRecipeController();
        
    }

    public List<IngredientEntity> getAllIngredients() {
        return ingredientDao.getAllIngredients();
    }

    public Ingredient getIngredientById(final UUID id){
        return (Ingredient)ingredientDao.getIngredientById(id);
    }

    public List<IngredientEntity> getIngredientByName(final String keyword) {
        return ingredientDao.getIngredientByName(keyword);
    }

    public Ingredient insertIngredient(final IngredientEntity ingredient) {
        return ingredientDao.insertIngredient(ingredient);
    }    
    
    public void deleteIngredient(final IngredientEntity ingredient) {
        ingredientDao.deleteIngredient(ingredient);
    }    

    public void updateIngredient(final IngredientEntity updatedIngredient) {
        //deleteIngredient((IngredientEntity)getIngredientById(updatedIngredient.getId()));
        //insertIngredient(updatedIngredient);
        ingredientDao.updateIngredient(updatedIngredient);
    }    

    public Iterable<RecipeEntity> getAllRecipes() {
        return recipeDao.getAllRecipes();
    }    

    public Recipe getRecipeById(final UUID id){
        return (Recipe)recipeDao.getRecipeById(id);
    }    

    public List<RecipeEntity> getRecipeByName(final String keyword) {
        return recipeDao.getRecipeByName(keyword);
    }

    public Recipe insertRecipe(final RecipeEntity recipe) {
        return recipeDao.insertRecipe(recipe);
    }
    
    public void deleteRecipe(final RecipeEntity recipe) {
        recipeDao.deleteRecipe(recipe);
    }

    public void updateRecipe(final RecipeEntity updatedRecipe) {
        //deleteRecipe((RecipeEntity)getRecipeById(updatedRecipe.getId()));
        //insertRecipe(updatedRecipe);
        recipeDao.updateRecipe(updatedRecipe);
    }

}
