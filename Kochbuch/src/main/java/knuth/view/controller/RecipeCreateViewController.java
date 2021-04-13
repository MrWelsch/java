package knuth.view.controller;
// java importe
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//javafx importe
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import knuth.facades.ServiceFacade;
import net.rgielen.fxweaver.core.FxmlView;

/**
 * Klasse RecipeCreateViewController
 * Die klasse implementier das Interface Initializable
 * 
 * Die Klasse RecipeCreateViewController steuert Alle graphischen Anwendungen 
 * der RecipeCreateView.java Klasse. Es werden hier die Buttons, Panes, Bilder
 * ihrer zu tätigen Funktion zugeordnet, sodass Sie die richtigen Ereignisse
 * erfüllen. 
 *
 */
@Component
@FxmlView("RecipeCreate.fxml")
public class RecipeCreateViewController implements Initializable {
    // FXML Initialisierungen 
    @FXML
    private Button backButton;

    @FXML
    private Button addIngredientButton;

    @FXML
    private Button addImageButton;

    @FXML
    private TextField nameInput;

    @FXML
    private ChoiceBox<String> categoryInput;

    @FXML
    private ChoiceBox<String> recipeEinheit;

    @FXML
    private TextField dauerInput;

    @FXML
    private TextField portionenInput;

    @FXML
    private AnchorPane recipeIngredients;

    @FXML
    private URL recipeImageUrl;

    @FXML
    private Button saveRecipeButton;

    @FXML TextField directionsInput;

    // Variablen Initialisierungen
    private int ingredientCount;
    private ArrayList<Float> recipeMengeCount; 
    private ArrayList<String> recipeIngredientCount;
    private ArrayList<String> recipeUnitCount;

    @Autowired
    ServiceFacade servicefacade;

    /**
     * void MainView
     * 
     * Hier wird mittels Action event dem Button backButton zugeordnet was er zu taetigen hat.
     * 
     * Der Button wird mit einem passenden Bild (@BackArrow.png) in der RecipeCreat.fxml gefuellt. Das Bild ist der 
     * eigentliche Button. Durch betaetigen des Buttons soll nun die graphische Oberfläche auf die neue
     * Seite MainView.fxml wechseln, also somit die Hauptseite anzeigen. 
     * 
     * Zur Realisierung wird hier eine Stage Objekt erstellt (=stage) der der Button zugeordnet wird. 
     * backButton wird vorher zu einem Stage object umgewandelt. 
     * Wetierhin wird mittels der Klasse Parent ein Objekt root erstellt. Diese wird mittels FXMLLoader
     * die Seite RecipeView.fxml zugordnet. Anschliesend wir ein von der Klasse Scene ein neues Objekt mit der 
     * Variablen root erstellt. Via URL url und String css wird die Nutzung der Seite style.css ermoeglicht. 
     * @param event
     * @throws IOException
     */
    @FXML
    void MainView(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(new File("src/main/resources/view/MainView.fxml").toURI().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("view/style.css");
        stage.setScene(scene);

    }

    /**
     * void addIngredientLine
     * 
     * Hier wird mittels Action event dem Button addIngredientButton zugeordnet was er zu taetigen hat.
     * 
     * Der Button wird mit einem passenden Text ("Zutat hinzufügen") in der RecipeCreat.fxml gefuellt. 
     * Durch betaetigen des Buttons soll nun die graphische Oberfläche auf die neue Seite IngredientsLine.fxml wechseln
     * und anzeigen.
     * 
     * Zur Realisierung wird hier ein AchorPane Objekt erstellt (=ingredientsList) der der Button zugeordnet wird. 
     * addIngredientButton wird vorher zu einem AnchorPane object umgewandelt.  Diese wird mittels FXMLLoader
     * die Seite RecipeView.fxml zugordnet. Anschliesend wir ein von der Klasse AnchporPane ein neues Objekt (newLine) erstellt, 
     * dem der FXMLLoder zugeordnet wird. 
     * 
     * Weiterhin befinden sich in der Methode eine If schleife, welche realisiert, das die Texteingabefelder, sowie die Auswahl
     * der Einheiten ChoicBox(übernimmt vorgegebne Einheiten aus RecipeCreate.fxml) angezeigt wird. Nach dem der Anwender seine 
     * Zutat nun eingegeben hat und mittels des Button addIngredientButton seine Zutat hinzugefügt hat, sorgt die If-Schleife,dass
     * ein neuer Reiter ensteht, sodass der Anweder weitere Zutaten hinzufügen kann.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    void addIngredientLine(ActionEvent event) throws IOException {
        double margin = 100.0;
        AnchorPane ingredientsList = (AnchorPane) addIngredientButton.getParent();
        FXMLLoader loader = new FXMLLoader(new File("src/main/resources/view/IngredientsLine.fxml").toURI().toURL());
        AnchorPane newLine = loader.load();
        newLine.setLayoutY(margin + margin * ingredientCount);
        ingredientsList.getChildren().add(newLine);
        if(ingredientCount > 0){            
            Scene temp = addIngredientButton.getScene();
            TextField menge = (TextField) temp.lookup("#menge");        
            recipeMengeCount.add(Float.parseFloat(menge.getText()));
            menge.setId("mengeAlt");
            TextField zutat = (TextField) temp.lookup("#zutat");
            recipeIngredientCount.add(zutat.getText());
            zutat.setId("zutatAlt");

            ChoiceBox <String> einheit = (ChoiceBox) temp.lookup("#einheit");
            recipeUnitCount.add(einheit.getSelectionModel().getSelectedItem().toString());
        }

        ingredientCount++;
    }

    /**
     * openFileChooser
     * 
     * Hier wird mittels Action event dem Button fileChooser zugeordnet was er zu taetigen hat.
     * 
     * Der Button wird mit einem passenden Text ("Bild hinzufügen") in der RecipeCreat.fxml gefuellt. 
     * Durch betaetigen des Buttons soll nun die graphische Oberfläche dem Anwender seinen user.Home Verzeichnis
     * anzeigen. In diesem Verzeichnis kann der Anwender sich nun selber weiterleiten um ein passendes Bild fuer sein 
     * rezept auszuwaehlen und hinzuzufuegen.
     * 
     * Zur Realisierung wird hier ein FileChooser Objekt erstellt (=fileChooser) der der Button zugeordnet wird. 
     * Welcher durch das erstellen ein neuen File Objekts, dem aus der Klasse System eine passende Methode, zur
     * erreichen des Homeverzeichnes, übergeben wird. Weiterhin wird ein neues Objekt der Klasse Stage (=stage)
     * erstellt, welches filechooser zugordnet wird. Durch einen Try and Catch Block, wird hier realisierd, 
     * dass das ausgwaehlte Bild der Variablen recipImageUrl zugeordnet wird und dem Rezept so hinzugefügt wird. 
     * @param event
     */

    @FXML
    void openFileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                URL url = file.toURI().toURL();
                recipeImageUrl = url;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * void createRecipe
     * 
     * Hier wird mittels Action event dem Button createRecipeButton zugeordnet was er zu taetigen hat.
     * 
     * Der Button wird mit einem passenden Text ("Speichern") in der RecipeCreat.fxml gefuellt. 
     * Durch betaetigen des Buttons soll nun das neu eingegebene Rezept gespeichert und in der Datenbank 
     * hinterlegt werden.
     * 
     * @param event
     */
    @FXML
    void createRecipe(ActionEvent event) {
        String name = nameInput.getText();
        int dauer = Integer.parseInt(dauerInput.getText());
        int portionen = Integer.parseInt(portionenInput.getText());
        String imagePath = recipeImageUrl.toString();
        String directions = directionsInput.getText();
        servicefacade.addRecipe(
        servicefacade.createRecipeByGUI(name, categoryInput.getSelectionModel().getSelectedItem(), 0, dauer, portionen, recipeIngredientCount, recipeMengeCount, recipeUnitCount, directions, imagePath, false)
        );
    }

    /**
     * public void intialize 
     * 
     * Hier wird die Methode aus der Interface Initializable aufgerufen und ueberschrieben (@Override).
     * Diese Methode wird Benötigt um einen Controller zu initialisieren, nach dem sein Root-Element volständig
     * verabeitet wurde. 
     * 
     * Hier werden verschieden Initialisierungen getaatig was beim Aufruf der Seite geschehen soll.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ingredientCount = 0;
        recipeMengeCount = new ArrayList<Float>();
        recipeIngredientCount = new ArrayList <String> ();
        recipeUnitCount = new ArrayList <String> ();
    }

}
