package knuth.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import knuth.dao.repositories.IngredientRepository;
import knuth.model.entities.IngredientEntity;

@SpringBootTest
class DatabaseTest {

	@Autowired
	private IngredientRepository ingredientJpa;
	
	/**
	 * Following is the simple Junit 5 tests case to save the Ingredient
	 * entity in the H2 database and test the results.
	 */
	@Test
	@DisplayName("Create Ingredient Test ")
	void createIngredientTest() {
		
		IngredientEntity created = ingredientJpa.save(getIngredient());
		
		assertNotNull(created);
	}
	
	/*
	void updateIngredientTest() {
		IngredientEntity changeme = ingredientJpa.save(getIngredient());

	}*/


	private IngredientEntity getIngredient() {
		
		IngredientEntity ingredient = new IngredientEntity();

		ingredient.setName("Tomate");
		ingredient.setCategory("Gemuese");
		ingredient.setCalories(1F);
		ingredient.setProtein(1F);
		ingredient.setCarbohydrates(2F);
		ingredient.setFat(3F);

		return ingredient;
	}
}
