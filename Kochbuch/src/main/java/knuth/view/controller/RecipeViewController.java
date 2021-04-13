package knuth.view.controller;
//java importe
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

//javafx importe
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;

/**
 * Klasse RecipeViewController
 * Die klasse implementier das Interface Initializable
 * 
 * Die Klasse RecipeViewController steuert Alle graphischen Anwendungen 
 * der RecipeView.java Klasse. Es werden hier die Buttons, Panes, Bilder
 * ihrer zu tätigen Funktion zugeordnet, sodass Sie die richtigen Ereignisse
 * erfüllen. 
 *
 */
@Component
@FxmlView("RecipeView.fxml")
public class RecipeViewController implements Initializable {
    //fxml Initialisierungen
    @FXML
    private Button backButton;

    /**
     * MainView
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
    //evtl. andere Lösung doppelter Code mit RecipeCreatViewController.MainView()
    void MainView(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(new File("src/main/resources/view/MainView.fxml").toURI().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("view/style.css");
        stage.setScene(scene);
        

    }

    /**
     * public void intialize 
     * 
     * Hier wird die Methode aus der Interface Initializable aufgerufen und ueberschrieben (@Override).
     * Diese Methode wird Benötigt um einen Controller zu initialisieren, nach dem sein Root-Element volständig
     * verabeitet wurde. 
     * 
     * Hier werden verschieden Initialisierungen getaetig was beim Aufruf der Seite geschehen soll.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }
    
}
