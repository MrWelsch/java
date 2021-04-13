package knuth.dao.implementation;

import static knuth.constants.Constants.NO_INGREDIENT_FOUND;

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

import knuth.dao.interfaces.IngredientDao;
import knuth.dao.repositories.IngredientRepository;
import knuth.model.entities.IngredientEntity;
import knuth.model.interfaces.Ingredient;

@RestController
@RequestMapping("/ingredients")
public class IngredientDaoImpl implements IngredientDao{
	
	@Autowired
	private IngredientRepository ingredientJpa;
	
	@Override
	@GetMapping("/getIngredients")
	public List<IngredientEntity> getAllIngredients() {
		return ingredientJpa.findAll();
	}


	@Override
	@GetMapping("/getIngredientById")
	public ResponseEntity<Ingredient> getIngredientById(@PathVariable(value = "id") UUID id){
			
		Ingredient ingredient = ingredientJpa.findById(id).orElseThrow(
			() -> new NoSuchElementException(NO_INGREDIENT_FOUND + id));
				
		return ResponseEntity.ok().body(ingredient);
	}
	
    @Override
    @GetMapping("/getIngredientByName")
    public List<IngredientEntity> getIngredientByName(@Valid @RequestBody String keyword) {
        return ingredientJpa.findByName(keyword);
    }

    /**
     * Checks whether the ID exists or not.
     * If the entity is already there, only update will happen 
     * by merge(entity) method and if else, a new record is inserted 
     * by persist(entity) method.
     */
	@Override
	@PostMapping("/saveIngredient")
	public Ingredient insertIngredient(@Valid @RequestBody IngredientEntity ingredient) {
		return ingredientJpa.save(ingredient);
	}

	@Override
    @PostMapping("/deleteIngredient")
	public void deleteIngredient(@Valid @RequestBody IngredientEntity ingredient) {
		ingredientJpa.delete(ingredient);
	}

	@Override
	@PostMapping("/updateIngredient")
	public ResponseEntity<Ingredient> updateIngredient(@Valid @RequestBody IngredientEntity updatedIngredient) {

		ingredientJpa.findById(updatedIngredient.getId()).orElseThrow(
			() -> new NoSuchElementException(NO_INGREDIENT_FOUND + updatedIngredient.getId()));

		ingredientJpa.updateIngredientQuery(updatedIngredient.getCalories(),
											updatedIngredient.getCarbohydrates(),
											updatedIngredient.getCategory(),
											updatedIngredient.getFat(),
											updatedIngredient.getName(),
											updatedIngredient.getProtein(),
											updatedIngredient.getId());

		//ingredientJpa.deleteById(updatedIngredient.getId());
		//Ingredient savedIngredient = ingredientJpa.save(updatedIngredient);
		Ingredient savedIngredient = ingredientJpa.findById(updatedIngredient.getId()).orElseThrow(
			() -> new NoSuchElementException(NO_INGREDIENT_FOUND + updatedIngredient.getId()));
		return ResponseEntity.ok().body(savedIngredient);
    }

	/*
	Gibt falls das Keyword existiert alle Ergebnisse zu dem Keyword zurueck.
	falls nicht gibt die Methode alle Objekte zurueck.
	*/
	//@Override
	//@GetMapping("Ingredient/{name}")
	//public List<IngredientEntity> getIngredientByName(@PathVariable("name") String keyword) {
	//	if (keyword != null) {
	//		return ingredientJpa.getIngredientByName(keyword);
	//	}
	//	return ingredientJpa.findAll();
	//}





}
