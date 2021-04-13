package knuth.facades;

import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import knuth.factories.ServiceFactory;
import knuth.model.entities.IngredientEntity;
import knuth.model.entities.RecipeEntity;
import knuth.model.interfaces.Ingredient;
import knuth.model.interfaces.Recipe;
import knuth.service.interfaces.IngredientService;
import knuth.service.interfaces.RecipeService;



/* 
 * Service Facade connects Service level to view level
 * @author Hannes Schumacher, Yannik Strittmatter
 * @version 1.0
 */
@RestController
public class ServiceFacade {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private RecipeService recipeService;
    
    public ServiceFacade(){
        ingredientService = ServiceFactory.createIngredientService();
        recipeService = ServiceFactory.createRecipeService();
    }

    public RecipeEntity createRecipeByGUI(String name, String category, int healthRating, int time, int personCount, List<String> ingredients, List<Float> amounts, List<String> amounttypes, String directions, String imagePath, boolean favorized){
        return recipeService.createRecipeByGUI(name, category, healthRating, time, personCount, ingredients, amounts, amounttypes, directions, imagePath, favorized);
    }

    public void addRecipe(final RecipeEntity recipe) {
        recipeService.addRecipe(recipe);
    }

    public void deleteRecipe(final RecipeEntity recipe) {
        recipeService.deleteRecipe(recipe);
    }

    public Recipe getRecipeByName(String name){
        return recipeService.getRecipeByName(name);
    }

    public Recipe changePersonCount(int personCount, String name){
        return recipeService.changePersonCount(personCount, name);
    }

    public Recipe changeRecipe(RecipeEntity recipe, String name, String category, int healthRating, int time,
    int personCount, List<IngredientEntity> ingredients, List<Float> amounts, List<String> amounttypes, String directions, String imagePath,
    boolean favorized) {
        return recipeService.changeRecipe(recipe, name, category, healthRating, time,
        personCount, ingredients, amounts, amounttypes, directions, imagePath,
        favorized);
    }

    public Ingredient addIngredient(final IngredientEntity ingredient){
        return ingredientService.addIngredient(ingredient);
    }

    public void deleteIngredient(final IngredientEntity ingredient){
        ingredientService.deleteIngredient(ingredient);

    }

    public Ingredient changeIngredient(IngredientEntity ingredient, String name, String category, float calories, float protein, float carbohydrates, float fat){
        return ingredientService.changeIngredient(ingredient, name, category, calories, protein, carbohydrates, fat);
    }

    public Ingredient getIngredientByName(String name){
        return ingredientService.getIngredientByName(name);
    }

    public String imageToString(Path path) {
        return recipeService.imageToString(path);
    }

    public Path stringToImage(String string) {
        return recipeService.stringToImage(string);
    }

}
