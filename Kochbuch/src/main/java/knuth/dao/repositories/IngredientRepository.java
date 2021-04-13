package knuth.dao.repositories;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import knuth.model.entities.IngredientEntity;

// DEPENDENCY INJECTION:
// With the JPA in the Parenthesis we can actually change databases with one
// line of code by addid a @Qualifier("JPA") into the parameter of a service
// method.
@Repository //("JPA")
public interface IngredientRepository extends JpaRepository<IngredientEntity, UUID>{

    List<IngredientEntity> findByName (String name);
    
    @Transactional
	@Modifying
	@Query(value = "UPDATE INGREDIENT SET CALORIES = ?1,CARBOHYDRATES = ?2,CATEGORY = ?3, FAT = ?4, NAME = ?5, PROTEIN = ?6 WHERE id = ?7", nativeQuery=true)
	public int updateIngredientQuery(float calories,float carbohydrates,String category,float fat,String name,float protein,UUID id);

	//@Query("SELECT i FROM INGREDIENT i WHERE i.name LIKE %?1%")
	//public List<IngredientEntity> getIngredientByName(String name);

}
