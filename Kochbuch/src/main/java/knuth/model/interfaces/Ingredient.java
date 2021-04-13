package knuth.model.interfaces;

import java.util.UUID;

public interface Ingredient {

    public UUID getId();

    public void setId(UUID id);

    public String getName();

    public void setName(String name);

    public String getCategory();

    public void setCategory(String category);

    public float getCalories();

    public void setCalories(float calories);

    public float getProtein();

    public void setProtein(float protein);

    public float getCarbohydrates();

    public void setCarbohydrates(float carbohydrates);

    public float getFat();

    public void setFat(float fat);
    
}
