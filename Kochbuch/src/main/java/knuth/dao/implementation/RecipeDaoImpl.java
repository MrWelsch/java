package knuth.dao.implementation;

import static knuth.constants.Constants.NO_RECIPE_FOUND;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import knuth.dao.interfaces.RecipeDao;
import knuth.dao.repositories.RecipeRepository;
import knuth.model.entities.RecipeEntity;
import knuth.model.interfaces.Recipe;

@RestController
@RequestMapping("/Recipes")
public class RecipeDaoImpl implements RecipeDao{
	
	@Autowired
	private RecipeRepository recipeJpa;
	
	@Override
	@GetMapping("/getRecipes")
	public Iterable<RecipeEntity> getAllRecipes() {
		return recipeJpa.findAll();
	}

	@Override
	@GetMapping("/getRecipeById")
	public ResponseEntity<Recipe> getRecipeById(@PathVariable(value = "id") UUID id){
			
		Recipe recipe = recipeJpa.findById(id).orElseThrow(
			() -> new NoSuchElementException(NO_RECIPE_FOUND + id));
				
		return ResponseEntity.ok().body(recipe);
	}
	
    @Override
    @GetMapping("/getRecipeByName")
    public List<RecipeEntity> getRecipeByName(@Valid @RequestBody String keyword) {
        return recipeJpa.findByName(keyword);
    }

    @Override
    public List<RecipeEntity> filterByCategory(@Valid String keyword){
        String categoryToFilterBy = null;
        switch (keyword) {
            case "Vegan":       categoryToFilterBy = "Vegan";
                                break;
            case "Vegetarian":  categoryToFilterBy = "Vegetarian";
                                break;
            case "Breakfast":   categoryToFilterBy = "Breakfast";
                                break;
            case "Lunch":       categoryToFilterBy = "Lunch";
                                break;
            case "Dinner":      categoryToFilterBy = "Dinner";
                                break;
            default:            break;
        }
        return recipeJpa.findByCategory(categoryToFilterBy);
    }
    
	@Override
	@PostMapping("/saveRecipe")
	public Recipe insertRecipe(@Valid @RequestBody RecipeEntity recipe) {
		return recipeJpa.save(recipe);
	}

	@Override
	@PostMapping("/deleteRecipe")
	public void deleteRecipe(@Valid @RequestBody RecipeEntity recipe) {
		recipeJpa.delete(recipe);
	}

	@Override
	@PostMapping("/updateRecipe")
	public ResponseEntity<Recipe> updateRecipe(@Valid @RequestBody RecipeEntity updatedRecipe) {
		/**
		recipeJpa.deleteById(updatedRecipe.getId());
		Recipe savedRecipe = recipeJpa.save(updatedRecipe);
		return ResponseEntity.ok().body(savedRecipe);
		**/
		recipeJpa.findById(updatedRecipe.getId()).orElseThrow(
			() -> new NoSuchElementException(NO_RECIPE_FOUND + updatedRecipe.getId()));

		//public int updateRecipeQuery(String category,String directions,boolean favorized,int health_rating,String image_path,String name,int person_count,UUID id);
		recipeJpa.updateRecipeQuery(updatedRecipe.getName(),
									updatedRecipe.getCategory(),
									updatedRecipe.getHealthRating(),
									updatedRecipe.getTime(),
									updatedRecipe.getPersonCount(),
									updatedRecipe.getIngredients(),
									updatedRecipe.getAmounts(),
									updatedRecipe.getAmounttypes(),
									updatedRecipe.getDirections(),
									updatedRecipe.getImagePath(),
									updatedRecipe.getFavorized(),
									updatedRecipe.getId());


		//ingredientJpa.deleteById(updatedIngredient.getId());
		//Ingredient savedIngredient = ingredientJpa.save(updatedIngredient);
		Recipe savedRecipe = recipeJpa.findById(updatedRecipe.getId()).orElseThrow(
			() -> new NoSuchElementException("No recipe found for Id :" + updatedRecipe.getId()));
		return ResponseEntity.ok().body(savedRecipe);
    }

	/*
	Gibt falls das Keyword existiert alle Ergebnisse zu dem Keyword zurueck.
	falls nicht gibt die Methode alle Objekte zurueck.
	*/
	//@Override
	//@GetMapping("Recipe/{name}")
	//public List<RecipeEntity> getRecipeByName(@PathVariable("name") String //keyword) {
	//	if (keyword != null) {
	//		return recipeJpa.getRecipeByName(keyword);
	//	}
	//	return recipeJpa.findAll();
	//}
	

}
