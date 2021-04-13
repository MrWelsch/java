package knuth.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import knuth.dao.repositories.IngredientRepository;
import knuth.facades.ServiceFacade;
import knuth.model.entities.IngredientEntity;

@SpringBootTest
public class IngredientTest {

    String name = "Erbse";
    
    /**
     * Vor dem Test in DatabaseApplication.java die Zeilen mit save auskommentieren
     */
    @Autowired
	private IngredientRepository ingredientJpa;

    @Autowired 
    private ServiceFacade serviceFacade;   

    @Test
    @DisplayName("findBy Test")
    void findByNameTest(){
        // funktioniert!
        
        serviceFacade.addIngredient(getIngredient());
        //assertSame(getIngredient(), ingredientJpa.findByName(name));
        assertEquals(name, serviceFacade.getIngredientByName(name).getName());
        //return null if ingredient not found
        assertEquals(null, serviceFacade.getIngredientByName("asdfljasfljaf"));
    }

    @Test
	@DisplayName("Insert Ingredient Test ")
    void addIngredientTest() {
       //funktioniert!
        serviceFacade.addIngredient(getIngredient());
        assertNotNull(serviceFacade.getIngredientByName(name));
        //assertEquals(11, ingredientJpa.count());
    }

    
    @Test
	@DisplayName("Delete Ingredient Test ")
    void deleteIngredientTest() {
        //funktioniert!
        serviceFacade.addIngredient(getIngredient());
        long numIngredients = ingredientJpa.count();
        serviceFacade.deleteIngredient((IngredientEntity)serviceFacade.getIngredientByName(name));
        assertEquals(numIngredients - 1, ingredientJpa.count());
        //assertNull(serviceFacade.getIngredientByName(name));
    }
    
    @Test
	@DisplayName("Change Ingredient Test ")
    void changeIngredientTest() {
        // funktioniert!
        serviceFacade.addIngredient(getIngredient());   
        serviceFacade.changeIngredient((IngredientEntity)serviceFacade.getIngredientByName(name), "Apfel", "Obst", 1f, 2f, 3f, 4f);

        assertNotNull(serviceFacade.getIngredientByName("Apfel"));
        assertEquals("Obst", serviceFacade.getIngredientByName("Apfel").getCategory());

    }
    
    
    private IngredientEntity getIngredient() {
		
		IngredientEntity ingredient = new IngredientEntity();

		ingredient.setName(name);
		ingredient.setCategory("Gemuese");
		ingredient.setCalories(1F);
		ingredient.setProtein(1F);
		ingredient.setCarbohydrates(2F);
		ingredient.setFat(3F);

		return ingredient;
	}



}
