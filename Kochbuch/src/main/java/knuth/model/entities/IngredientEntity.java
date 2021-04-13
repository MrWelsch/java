package knuth.model.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import knuth.model.interfaces.Ingredient;

@Entity(name = "INGREDIENT")
@javax.persistence.Table(name = "INGREDIENT")
public class IngredientEntity implements Ingredient{

    @Id
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID") 
    @Column(name = "ID", insertable = false,updatable = false, nullable = false)
    private UUID id;

	@Column(name = "NAME")
    @NotNull
	private String name;

	@Column(name = "CATEGORY")
    @NotNull
	private String category;

	@Column(name = "CALORIES")
    @Min(0)
	private float calories;

    @Column(name = "PROTEIN")
    @Min(0)
	private float protein;

    @Column(name = "CARBOHYDRATES")
    @Min(0)
    private float carbohydrates;

    @Column(name = "FAT")
    @Min(0)
    private float fat;

    @Override
    public UUID getId() {
		return id;
	}

    @Override
	public void setId(UUID id) {
		this.id = id;
	}

    @Override
	public String getName() {
		return name;
	}

    @Override
	public void setName(String name) {
		this.name = name;
	}

    @Override
	public String getCategory() {
		return category;
	}

    @Override
	public void setCategory(String category) {
		this.category = category;
	}

    @Override
	public float getCalories() {
        return this.calories;
    }

    @Override
    public void setCalories(float calories) {
        this.calories = calories;
    }

    @Override
    public float getProtein() {
        return this.protein;
    }

    @Override
    public void setProtein(float protein) {
        this.protein = protein;
    }

    @Override
    public float getCarbohydrates() {
        return this.carbohydrates;
    }

    @Override
    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }
	
    @Override
    public float getFat() {
        return this.fat;
    }

    @Override
    public void setFat(float fat) {
        this.fat = fat;
    }

}
