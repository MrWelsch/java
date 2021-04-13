package knuth.model.entities;


import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import knuth.model.interfaces.Recipe;

/**
 * Container for recipes.
 * @implNote RecipeInterface contains get- and set-methods.
 * @implNote Seriazable connects the class to Hibernate.
 * @author Nico Welsch, 
 * @version 0.1
 */
@Entity(name = "RECIPE")
@javax.persistence.Table(name = "RECIPE")
public class RecipeEntity implements Recipe{

    @Id
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")  
    @Column(name = "ID", insertable = false, updatable = false, nullable = false)
    private UUID id;

    //Constrain to specific char count? 
    @Column(name = "NAME")
    @NotNull
	private String name;

    @Column(name = "CATEGORY")
    @NotNull
	private String category;
    
    @Column(name = "HEALTHRATING")
    @Min(0)
    @Max(5)
    private int healthRating;

    @Column(name = "TIME")
    @NotNull
    @Min(0)
    private int time;
    
    @Column(name = "PERSONCOUNT")
    @NotNull
    @Min(1)
    private int personCount;

    @Column(name = "INGREDIENTS")
    @ElementCollection
    @OneToMany(
        //targetEntity = IngredientEntity.class,
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER
    )
    @NotNull
    private List<IngredientEntity> ingredients;

    @Column(name = "AMOUNTS")
    @ElementCollection
    @NotNull
    private List<Float> amounts;

    @Column(name = "AMOUNTTYPES")
    @ElementCollection
    @NotNull
    private List<String> amounttypes;

    @Column(name = "DIRECTIONS")
    @NotNull
    private String directions;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    @Column(name = "FAVORIZED")
    private boolean favorized;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public UUID getId() {
		return id;
	}

    @Override
	public void setId(UUID id) {
		this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int getHealthRating() {
        return this.healthRating;
    }

    @Override
    public void setHealthRating(int healthRating) {
        this.healthRating = healthRating;
    }

    @Override
    public int getTime() {
        return this.time;
    }

    @Override
    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int getPersonCount() {
		return this.personCount;
	}

    @Override
    public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}

//  @Override
 //   @Transient
 //   @ElementCollection
 //   @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
 //   public Map<IngredientEntity, Float> getIngredients() {
 //       return this.ingredients;
 //   }

    @Override
    public void setIngredients(List<IngredientEntity> ingredients) {
       this.ingredients = ingredients;
    }

    @Override
    public List<IngredientEntity> getIngredients(){
        return this.ingredients;
    }

    @Override
    public void setAmounts(List<Float> amounts){
        this.amounts = amounts;
    }

    @Override
    public List<Float> getAmounts(){
        return this.amounts;
    }

    @Override
    public void setAmounttypes(List<String> amounttypes){
        this.amounttypes = amounttypes;
    }

    @Override
    public List<String> getAmounttypes(){
        return this.amounttypes;
    }

    @Override
    public String getDirections() {
        return this.directions;
    }

    @Override
    public void setDirections(String directions) {
        this.directions = directions;
    }

    @Override
    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean getFavorized() {
        return this.favorized;
    }

    @Override
    public void setFavorized(boolean favorized) {
        this.favorized = favorized;
    }

}