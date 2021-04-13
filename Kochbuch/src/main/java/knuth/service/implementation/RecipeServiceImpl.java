package knuth.service.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import knuth.facades.DaoFacade;
import knuth.factories.ModelFactory;
import knuth.model.entities.IngredientEntity;
import knuth.model.entities.RecipeEntity;
import knuth.model.interfaces.Recipe;
import knuth.service.interfaces.RecipeService;

/**
 * RecipeService Implementation
 * 
 * @author Hannes Schumacher, Yannik Strittmatter
 * @version 1.0
 */
@Service
public class RecipeServiceImpl implements RecipeService {
    
    @Autowired
    private DaoFacade daoFacade;

    public RecipeServiceImpl(){
        this.daoFacade = new DaoFacade();
    }


    //helper method to convert a string-list of ingredients into an ingredient-entity-list
    public List<IngredientEntity> convertIngredientList(List<String> ingredients){
        //create empty ingredient-entity-list
        List<IngredientEntity> convertedingredients = new ArrayList<>();
        //loop and check for existing entities in db
        for (String ingredient : ingredients) {
            List<IngredientEntity> convertedingredient = daoFacade.getIngredientByName(ingredient);
            if (!convertedingredient.isEmpty()){
                convertedingredients.add(convertedingredient.get(0));
            } else {
                //create new ingredient
                IngredientEntity newingredient = ModelFactory.createIngredient(ingredient, "", 0, 0, 0, 0);
                convertedingredients.add(newingredient);
            }
        }
        //convertedingredients should have same length as ingredients
        return convertedingredients;
    }

    
    @Override
    public RecipeEntity createRecipeByGUI(String name, String category, int healthRating, int time, int personCount, List<String> ingredients, List<Float> amounts, List<String> amounttypes, String directions, String imagePath, boolean favorized){

        final RecipeEntity recipe = new RecipeEntity();

        //convert string-list of ingredients to ingredient-entity list
        List<IngredientEntity> convertedingredients = convertIngredientList(ingredients);

        recipe.setId(UUID.randomUUID());
        recipe.setName(name);
        recipe.setCategory(category);
        recipe.setHealthRating(healthRating);
        recipe.setTime(time);
        recipe.setPersonCount(personCount);
        recipe.setIngredients(convertedingredients);
        recipe.setAmounts(amounts);
        recipe.setAmounttypes(amounttypes);
        recipe.setDirections(directions);
        recipe.setImagePath(imagePath);
        recipe.setFavorized(favorized);

        return recipe;

    }


    @Override
    public Recipe getRecipeByName (String keyword){
        List<RecipeEntity> recipelist = daoFacade.getRecipeByName(keyword);
        if (!recipelist.isEmpty()) {
            return recipelist.get(0);
        } else {
            return null;
        }
    }

    /**
     * adds Recipe to database via daoFacade
     * @param 
     */
    @Override
    public Recipe addRecipe(final RecipeEntity recipe) {
        return daoFacade.insertRecipe(recipe);

    }

    @Override
    public void deleteRecipe(final RecipeEntity recipe) {
        daoFacade.deleteRecipe(recipe);

    }

    //@Override
    //public Recipe getRecipeByName(String name){
        
        //return daoFacade.getRecipeByName(name);
    //    return null;
    //}


    @Override
    public Recipe changeRecipe(RecipeEntity recipe, String name, String category, int healthRating, int time,
            int personCount, List<IngredientEntity> ingredients, List<Float> amounts, List<String> amounttypes, String directions, String imagePath,
            boolean favorized) {

                recipe.setCategory(category);
                recipe.setName(name);
                recipe.setDirections(directions);
                recipe.setFavorized(favorized);
                recipe.setImagePath(imagePath);
                recipe.setHealthRating(healthRating);
                recipe.setTime(time);
                recipe.setPersonCount(personCount);
                recipe.setIngredients(ingredients);
                recipe.setAmounts(amounts);
                recipe.setAmounttypes(amounttypes);

                daoFacade.updateRecipe(recipe);

                return recipe;
            }
    
     @Override
     public Recipe changePersonCount(int personCount, String recipeName){
        Recipe recipe = getRecipeByName(recipeName);
        List<Float> amounts = recipe.getAmounts();
        
        for(int i=0; i<amounts.size(); i++){
            float newAmount = amounts.get(i)/recipe.getPersonCount()*personCount;  //Rechnet neuen Person Count aus. Wird nicht in die Datenbank zurückgeschrieben
            amounts.set(i, newAmount);
        }

        recipe.setAmounts(amounts);

        return recipe;

     }

     //converts file-path to base-64 string
     @Override
     public String imageToString(Path path) {
        //gui sends path, so here it converts to file object
        File file = path.toFile();
        String stringImage = null;
        // try-with to automatically close stream
        try (FileInputStream fileInputStreamReader = new FileInputStream(file)){
            //create byte-array, read from stream to byte-array, return as base64 string
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            stringImage = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringImage;
    }

    //converts base-64 string to file-path
    @Override
    public Path stringToImage(String string) {
        //since we want to save the image to the db
        //i create a temp file for the image,
        //which gets removed at boot
        //on linux this file is located at /tmp
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile("kochbuch-", ".tmp"); //suffix and prefix
            byte[] bytes = Base64.getDecoder().decode(string);
            Files.write(tempFile, bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempFile;
    }
}
