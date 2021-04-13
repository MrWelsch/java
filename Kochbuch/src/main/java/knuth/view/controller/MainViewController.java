package knuth.view.controller;
// Java importe
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

// javafx importe
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;

/**
 * Klasse MainViewController
 * Die klasse implementier das Interface Initializable
 * 
 * Die Klasse MainViewController steuert Alle graphischen Anwendungen 
 * der MainView.java Klasse. Es werden hier die Buttons, Panes, Bilder
 * ihrer zu tätigen Funktion zugeordnet, sodass Sie die richtigen Ereignisse
 * erfüllen. 
 *
 */
@Component
@FxmlView("MainView.fxml")
public class MainViewController implements Initializable {
    //Initialisierung von @FXML-Variablen
    @FXML
    private Button searchBarOpen;

    @FXML
    private Pane searchBar_input;

    @FXML
    private Button recipeButton;

    @FXML
    private AnchorPane leftMenu;

    @FXML
    private GridPane buttonGrid;

    @FXML
    private Button buttonCategory;

    @FXML
    private ImageView logoImg;

    @FXML
    private GridPane recipeGrid;

    @FXML
    private Button searchBarOpenLeftMenu;

    @FXML
    private Button buttonRecipeAdd;

    /**
     * void closeSearchBar
     * 
     * Hier wird der Pane searchBar_Input und dem Butten searchBarOpen zugeordnet, 
     * was sie bei Betätigung zu tun hab, mittels eines Actione Events. Bei betätigung 
     * des Buttons, soll der Butten verschwinden und die suchleiste mit neuem Butten auftauche. 
     * Durch das Betätigen des Buttons in der Suchleiste soll diese wieder verschwinden und der 
     * Initialisierte Butten wieder erscheinen.
     * 
     * @param event
     */
   
    @FXML
    void closeSearchBar(ActionEvent event) {
        searchBar_input.setVisible(false);
        searchBarOpen.setVisible(true);
    }

    /**
     * void openSearchBar
     * 
     * Hier passiert die Umgekehrte Reihenfolge der Methode void closesearchBar
     * @param event
     */

    @FXML
    void openSearchBar(ActionEvent event) {
        searchBarOpen.setVisible(false);
        searchBar_input.setVisible(true);
    }

    /**
     * void openRecipe 
     * 
     * Hier wird mittels Action event dem Button recipeButton zugeordnet was er zu taetigen hat.
     * 
     * Der Button wird mit einem passenden Rezeptbild in der MainView.fxml gefuellt. Das Bild ist der 
     * eigentliche Button. Durch betaetigen des Buttons soll nun die graphische Oberfläche auf die neue
     * Seite RecipeView.fxml wechseln und das dazugehörige Rezept mit all seinen Komponenten anzeigen. 
     * 
     * Zur Realisierung wird hier eine Stage Objekt erstellt (=stage) der der Button zugeordnet wird. 
     * Recipe Button wird vorher zu einem Stage object umgewandelt. 
     * Wetierhin wird mittels der Klasse Parent ein Objekt root erstellt. Diese wird mittels FXMLLoader
     * die Seite RecipeView.fxml zugordnet. Anschliesend wir ein von der Klasse Scene ein neues Objekt mit der 
     * Variablen root erstellt. Via URL url und String css wird die Nutzung der Seite style.css ermoeglicht. 
     *
     * 
     * @param event
     * @throws IOException
     */

    @FXML
    void openRecipe(ActionEvent event) throws IOException {
        Stage stage = (Stage) recipeButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(new File("src/main/resources/view/RecipeView.fxml").toURI().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("view/style.css");
        stage.setScene(scene);
    }

    /**
     * openRecipeCreate
     * 
     * Hier wird mittels Action event dem Button buttonRecipeAdd zugeordnet was er zu taetigen hat.
     * 
     * Der Button wird mit einem passender Bennenung in der RecipeCreat.fxml gefuellt.Durch betaetigen des 
     * Buttons soll nun die graphische Oberfläche auf die neue Seite RecipeCreatView.fxml wechseln und das 
     * dazugehörige Rezept mit all seinen Komponenten anzeigen.  
     * Zur Realisierung wird hier eine Stage Objekt erstellt (=stage) der der Button zugeordnet wird. 
     * Reciepe Button wird vorher zu einem Stage object umgewandelt. 
     * Wetierhin wird mittels der Klasse Parent eine Objekt root erstellt. Diese wird mittels FXMLLoader
     * die Seite RecipeCreatView.fxml zugordnet. Anschliessend wird von der Klasse Scene ein neues Objekt mit der 
     * Variablen root erstellt. Via URL url und String css wird die Nutzung der Seite style.css ermoeglicht. 
     * @param event
     * @throws IOException
     */
    @FXML
    void openRecipeCreate(ActionEvent event) throws IOException {
        Stage stage = (Stage) buttonRecipeAdd.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(new File("src/main/resources/view/RecipeCreate.fxml").toURI().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("view/style.css");
        stage.setScene(scene);
    }

     /**
     * void leftMenuToggle
     * 
     * Die Methode leftMenuToggle regelt die Verschiebung der AnchorPane leftMenu.
     * Durch Betaetigen des in der MainView.fxml leftmenuButoon(gefuellt mit MenuButton.png)
     * wird in dies Methode realisiert, das Sich leftMenue verschiebt und die Augelistetn Buttons verschwinden.
     * Beim erneuten betaatigen, wird die AchnorPane leftMenu erneut verschoben und die Aufgelisteten Buttons 
     * werden wieder sichtbar.
     * @param event
     */

    @FXML
    void leftMenuToggle(ActionEvent event){
        if(leftMenu.getMaxWidth() == 100){
            leftMenu.setMaxWidth(500);
            leftMenu.setMinWidth(500);
            buttonGrid.setVisible(true);
            buttonCategory.setVisible(true);
            buttonRecipeAdd.setVisible(true);
            logoImg.setVisible(true);            
            recipeGrid.setMaxWidth(1412);
            recipeGrid.setMinWidth(1412);
            searchBarOpenLeftMenu.setVisible(false);
        }else{
            leftMenu.setMaxWidth(100);
            leftMenu.setMinWidth(100);
            buttonGrid.setVisible(false);
            buttonCategory.setVisible(false);
            buttonRecipeAdd.setVisible(false);
            logoImg.setVisible(false);
            recipeGrid.setMaxWidth(1812);
            recipeGrid.setMinWidth(1812);
            searchBarOpenLeftMenu.setVisible(true);
           }
    }

     /**
     * public void intialize 
     * 
     * Hier wird die Methode aus der Interface Initializable aufgerufen und ueberschrieben (@Override).
     * Diese Methode wird Benötigt um einen Controller zu initialisieren, nach dem sein Root-Element volständig
     * verabeitet wurde. 
     * Beim ersten Aufruf der MainView.fxml Seit soll die Searchbar nicht angzeigt werden.
     * 
     * @param location 
     * @param resources
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchBar_input.setVisible(false);
        searchBarOpenLeftMenu.setVisible(false);
        //TODO 
        //GridPane soll Datenbank auslesen und entsprechend AnchorPanes anlegen
    }
    

}
