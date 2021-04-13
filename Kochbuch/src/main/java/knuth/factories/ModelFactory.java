package knuth.factories;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import knuth.model.entities.IngredientEntity;
import knuth.model.entities.RecipeEntity;

/**
 * Object Factory that creates object of the dao level.
 * @author Nico Welsch
 * @version 0.1
 */
@Component
public class ModelFactory {

    /**
     * Creates and returns an Ingredient object.
     * @return Ingredient object
     */
    public static IngredientEntity createIngredient(String name, String category, float calories, float protein, float carbohydrates, float fat){
        
        final IngredientEntity ingredient = new IngredientEntity();

        ingredient.setId(UUID.randomUUID());
        ingredient.setName(name);
        ingredient.setCategory(category);
        ingredient.setCalories(calories);
        ingredient.setProtein(protein);
        ingredient.setCarbohydrates(carbohydrates);
        ingredient.setFat(fat);

        return ingredient;

    }


    public static RecipeEntity createRecipe(String name, String category, int healthRating, int time, int personCount, List<IngredientEntity> ingredients, List<Float> amounts, List<String> amounttypes, String directions, String imagePath, boolean favorized){

        final RecipeEntity recipe = new RecipeEntity();

        recipe.setId(UUID.randomUUID());
        recipe.setName(name);
        recipe.setCategory(category);
        recipe.setHealthRating(healthRating);
        recipe.setTime(time);
        recipe.setPersonCount(personCount);
        recipe.setIngredients(ingredients);
        recipe.setAmounts(amounts);
        recipe.setAmounttypes(amounttypes);
        recipe.setDirections(directions);
        recipe.setImagePath(imagePath);
        recipe.setFavorized(favorized);

        return recipe;

    }
    
}
