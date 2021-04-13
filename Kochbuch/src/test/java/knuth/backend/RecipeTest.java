package knuth.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import knuth.dao.repositories.RecipeRepository;
import knuth.facades.ServiceFacade;
import knuth.model.entities.IngredientEntity;
import knuth.model.entities.RecipeEntity;

@SpringBootTest
public class RecipeTest {

    String name = "Bolognese";

    /**
     * Vor dem Test in DatabaseApplication.java die Zeilen mit save auskommentieren
     */
    
    @Autowired
	private RecipeRepository recipeJpa;

    @Autowired 
    private ServiceFacade serviceFacade;

    
    @Test
    @DisplayName("findBy Test")
    void findByNameTest(){
        // funktioniert!
        serviceFacade.addRecipe(getRecipe()); 
        assertEquals(name, serviceFacade.getRecipeByName(name).getName());
        //assertSame(getRecipe(), recipeJpa.findByName(name));
    }

    @Test
    @DisplayName("Insert Recipe Test ")
    void addRecipeTest() {
        // funktioniert!
        serviceFacade.addRecipe(getRecipe());
        assertNotNull(serviceFacade.getRecipeByName(name));
    }

    @Test
    @DisplayName("Delete Recipe Test ")
    void deleteRecipeTest() {
        // funktioniert!
        serviceFacade.addRecipe(getRecipe()); 
        serviceFacade.deleteRecipe((RecipeEntity)serviceFacade.getRecipeByName(name));    
    
        assertEquals(0, recipeJpa.count());
    }
    
    @Test
    @DisplayName("GUI creates Recipe test")
    void createRecipeByGUITest() {
        List<String> ingredients = new ArrayList<>();
        List<Float> amounts = new ArrayList<>();
        List<String> amounttypes = new ArrayList<>();

        ingredients.add("Paprika");
        ingredients.add("Käse");

        amounts.add(200f);
        amounts.add(100f);

        amounttypes.add("g");
        amounttypes.add("l");

        RecipeEntity recipe = serviceFacade.createRecipeByGUI("Pizza", "Vegetarisch", 5, 30, 1, ingredients, amounts, amounttypes, "make pizza, profit", "imagePath", true);
        serviceFacade.addRecipe(recipe);

        //check if saved to db
        assertEquals("Pizza", serviceFacade.getRecipeByName("Pizza").getName());
    }

    @Test
    @DisplayName("Change Recipe Test ")
    void changeRecipeTest() {
        // funktioniert noch nicht!
        List<IngredientEntity> ingredients = new ArrayList<>();
        List<Float> amounts = new ArrayList<>();
        List<String> amounttypes = new ArrayList<>();
        
        IngredientEntity zucchini = new IngredientEntity();

		zucchini.setName("Zucchini");
		zucchini.setCategory("Gemuese");
		zucchini.setCalories(99F);
		zucchini.setProtein(99F);
		zucchini.setCarbohydrates(99F);
        zucchini.setFat(1F);
        
        ingredients.add(zucchini);
        amounts.add(2000f);
        amounttypes.add("t");
        
        serviceFacade.addRecipe(getRecipe()); 
        serviceFacade.changeRecipe((RecipeEntity)serviceFacade.getRecipeByName(name), "Burger", "Fast Food", 1, 30, 1, ingredients, amounts, amounttypes, "directions", "imagePath", true);
    
        assertNotNull(serviceFacade.getRecipeByName("Burger"));
        assertEquals("Fast Food", serviceFacade.getRecipeByName("Burger").getCategory());
    }

    
    
    private RecipeEntity getRecipe() {
		
		final RecipeEntity recipe = new RecipeEntity();

        List<IngredientEntity> ingredients = new ArrayList<IngredientEntity>();
        List<Float> amounts = new ArrayList<Float>();
		List<String> amounttypes = new ArrayList<String>();

		IngredientEntity tomato = new IngredientEntity();

		tomato.setName("Tomate");
		tomato.setCategory("Gemuese");
		tomato.setCalories(1F);
		tomato.setProtein(1F);
		tomato.setCarbohydrates(2F);
		tomato.setFat(3F);

		IngredientEntity spagetthi = new IngredientEntity();

		spagetthi.setName("Spagetthi");
		spagetthi.setCategory("Nudeln");
		spagetthi.setCalories(1F);
		spagetthi.setProtein(1F);
		spagetthi.setCarbohydrates(2F);
		spagetthi.setFat(3F);

		ingredients.add(tomato);
		ingredients.add(spagetthi);
		amounts.add(200f);
		amounts.add(100f);
		amounttypes.add("kg");
		amounttypes.add("l");
        
        recipe.setName("Bolognese");
        recipe.setCategory("Nudeln");
        recipe.setHealthRating(3);
        recipe.setTime(30);
        recipe.setPersonCount(1);
        recipe.setIngredients(ingredients);
        recipe.setAmounts(amounts);
        recipe.setAmounttypes(amounttypes);
        recipe.setDirections("directions");
        recipe.setImagePath("imagePath");
        recipe.setFavorized(true);

		return recipe;
	}

    /*
    @Test
    @DisplayName("Search for name Test ")
    void searchNameTest() {
        
         recipeController.getRecipeByName("Bolo");
        
        assertEquals(test, getRecipe().getName());
    
    }
    */


}
