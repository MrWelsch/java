package knuth.dao.interfaces;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import knuth.model.entities.RecipeEntity;
import knuth.model.interfaces.Recipe;

public interface RecipeDao {

    public Iterable<RecipeEntity> getAllRecipes();

    public ResponseEntity<Recipe> getRecipeById(@PathVariable(value = "id") UUID id);

    public List<RecipeEntity> getRecipeByName(@PathVariable("name") String keyword);

    public List<RecipeEntity> filterByCategory(String keyword);

    public Recipe insertRecipe(@Valid @RequestBody RecipeEntity recipe);

    public void deleteRecipe(RecipeEntity recipe);

    public ResponseEntity<Recipe> updateRecipe(@Valid @RequestBody RecipeEntity updatedRecipe);
    
}
    