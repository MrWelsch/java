package knuth.service.interfaces;

import java.nio.file.Path;
import java.util.List;

import knuth.model.entities.IngredientEntity;
import knuth.model.entities.RecipeEntity;
import knuth.model.interfaces.Recipe;

/**
 * Interface for RecipeService
 * 
 * @author Hannes Schumacher, Yannik Strittmatter
 * @version 1.0
 */
public interface RecipeService {

    public List<IngredientEntity> convertIngredientList(List<String> ingredients);

    public RecipeEntity createRecipeByGUI(String name, String category, int healthRating, int time, int personCount, List<String> ingredients, List<Float> amounts, List<String> amounttypes, String directions, String imagePath, boolean favorized);

    public Recipe changePersonCount(int personCount, String name);

    //public Recipe getRecipeByName(String name);

    //public float readNutrition(List<IngredientEntity> ingredients, List<String> amounts);
    public Recipe getRecipeByName (String keyword);

    public  Recipe addRecipe(RecipeEntity recipe);

    public void deleteRecipe(RecipeEntity recipe);

    //public void changeAmounts(RecipeEntity recipe, int personCount);

    public Recipe changeRecipe(RecipeEntity recipe, String name, String category, int healthRating, int time, int personCount, List<IngredientEntity> ingredients, List<Float> amounts, List<String> amounttypes, String directions, String imagePath, boolean favorized);

    //public int rateHealth(int calories);

    //public List<RecipeEntity> filterRecipes(Predicate<T> predicate);

    //public List<RecipeEntity> sortRecipes(List<RecipeEntity> recipes, Comparator<T> comparator);

    public String imageToString(Path path);

    public Path stringToImage(String string);
    
}