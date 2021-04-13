package knuth.dao.repositories;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import knuth.model.entities.IngredientEntity;
import knuth.model.entities.RecipeEntity;

// DEPENDENCY INJECTION:
// With the JPA in the Parenthesis we can actually change databases with one
// line of code by addid a @Qualifier("JPA") into the parameter of a service
// method.
@Repository //("JPA")
public interface RecipeRepository extends JpaRepository<RecipeEntity, UUID>{

    List<RecipeEntity> findByName(String name);

    List<RecipeEntity> findByCategory (String keyword);

	//the query below does not work, but neither did the old one
	//i updated the recipe values though
	//seems like there is no way to execute multiple native queries with one @Query
	//but we need to make 4 UPDATEs
	//i think native queries are the wrong approach here
	//if all else fails we could just delete the old recipe and create a new one
    @Transactional
	@Modifying
	@Query(value = "UPDATE RECIPE SET NAME = ?1, CATEGORY = ?2, HEALTHRATING = ?3, TIME = ?4, PERSONCOUNT = ?5, DIRECTIONS = ?9, IMAGE_PATH = ?10, FAVORIZED = ?11 WHERE ID = ?12\n" +
				   "UPDATE RECIPE_INGREDIENTS SET INGREDIENTS = (?6) WHERE RECIPE_ID = ?12\n" +
				   "UPDATE RECIPE_AMOUNTS SET AMOUNTS = (?7) WHERE RECIPE_ID = ?12\n" +
				   "UPDATE RECIPE_AMOUNTTYPES SET AMOUNTTYPES = (?8) WHERE RECIPE_ID ?12", nativeQuery=true)
	public int updateRecipeQuery(String name, String category, int healthRating, int time, int personCount, List<IngredientEntity> ingredients, List<Float> amounts, List<String> amounttypes, String directions, String imagePath, boolean favorized, UUID id);
	//@Query("SELECT r FROM RECIPE r WHERE r.name LIKE %?1%")
	//public List<RecipeEntity> getRecipeByName(String name);
	
}
