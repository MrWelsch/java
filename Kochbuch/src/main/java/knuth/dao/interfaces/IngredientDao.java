package knuth.dao.interfaces;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import knuth.model.entities.IngredientEntity;
import knuth.model.interfaces.Ingredient;

public interface IngredientDao {

    public List<IngredientEntity> getAllIngredients();

    public ResponseEntity<Ingredient> getIngredientById(@PathVariable(value = "id") UUID id);
    
    public List<IngredientEntity> getIngredientByName(@PathVariable("name") String keyword);
    
    public Ingredient insertIngredient(@Valid @RequestBody IngredientEntity ingredient);

    public void deleteIngredient(IngredientEntity ingredient);

    public ResponseEntity<Ingredient> updateIngredient(@Valid @RequestBody IngredientEntity updatedIngredient);
    
}
