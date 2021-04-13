package knuth.service;
import static org.junit.Assert.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
//Beta
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import knuth.facades.ServiceFacade;
import knuth.factories.ModelFactory;
import knuth.model.entities.IngredientEntity;
import knuth.model.entities.RecipeEntity;
import knuth.model.interfaces.Recipe;


@SpringBootTest
public class RecipeServiceTest {

    @Autowired 
    private ServiceFacade serviceFacade;



    @Test
    public void testGetRecipe(){
        List<IngredientEntity> ingredients = new ArrayList<IngredientEntity>();
    
        List<Float> amounts = new ArrayList<Float>();
        List<String> amounttypes = new ArrayList<String>();
        
        IngredientEntity spaghetti = ModelFactory.createIngredient("Spaghetti", "category", 0, 6, 20, 5);
        IngredientEntity tomaten = ModelFactory.createIngredient("Tomaten", "gesund", 0, 0, 0, 0);
        ingredients.add(spaghetti);
        ingredients.add(tomaten);
        amounts.add(500f);
        amounts.add(200f);
        amounttypes.add("g");
        amounttypes.add("l");
 
        RecipeEntity napoli = ModelFactory.createRecipe("Spaghetti Napoli", "Nudelgericht", 5, 20, 3, ingredients, amounts, amounttypes, "Nudeln kochen Tomaten passieren und würzen", "", true);
        serviceFacade.addRecipe(napoli);
        Recipe napoliFromDatabase = serviceFacade.getRecipeByName("Spaghetti Napoli");
        List<IngredientEntity> ingredientsFromDatabase = napoliFromDatabase.getIngredients();
        String spaghettiNameFromDatabase = ingredientsFromDatabase.get(0).getName();
        

        assertEquals("Spaghetti", spaghettiNameFromDatabase);

    }

    //test convertes image from resource directory to string
    //then back to image-path
    //and finally checks if the new image is the same as the original
    @Test
    public void testImageConversion(){
        //test with BackArrow image from resources
        Path imagePath = Paths.get("src/main/resources/view/BackArrow.png").toAbsolutePath();
        String imageString = serviceFacade.imageToString(imagePath);
        Path newPath = serviceFacade.stringToImage(imageString);

        byte[] oldFile = null;
        byte[] newFile = null;

        try {
            oldFile = Files.readAllBytes(imagePath);
            newFile = Files.readAllBytes(newPath);   
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertArrayEquals(oldFile, newFile);
    }
}
