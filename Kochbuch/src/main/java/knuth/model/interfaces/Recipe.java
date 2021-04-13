package knuth.model.interfaces;

import java.util.List;
import java.util.UUID;

import knuth.model.entities.IngredientEntity;

/**
 * Interface responsible for methods of the Recipe class.
 * @author Nico Welsch
 * @version 0.1
 */
public interface Recipe {

    public UUID getId();

    public void setId(UUID id);

    public String getName();

    public void setName(String name);

    public String getCategory();

    public void setCategory(String category);

    public int getHealthRating();

    public void setHealthRating(int healthRating);

    public int getTime();

    public void setTime(int time);

    public int getPersonCount();

    public void setPersonCount(int personCount);

    public void setIngredients(List<IngredientEntity> ingredients);

    public List<IngredientEntity> getIngredients();

    public void setAmounts(List<Float> amounts);

    public List<Float> getAmounts();

    public void setAmounttypes(List<String> amounttypes);

    public List<String> getAmounttypes();

    public String getDirections();

    public void setDirections(String directions);

    public String getImagePath();

    public void setImagePath(String imagePath);

    public boolean getFavorized();

    public void setFavorized(boolean favorized);

}