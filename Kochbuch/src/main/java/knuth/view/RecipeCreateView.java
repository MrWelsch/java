package knuth.view;
//java importe
import java.io.File;

import javafx.application.Application;
// javafx importe
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Klasse RecipeCreateView
 * 
 * Die Klasse RecipeCreateView erbt von der Klasse Apllication
 * Durch die Klasse RecipeCreateView, soll der Anweder beim Aufruf des Kochbuches die Startseite sehen.
 * Die Klasse RecipeCreateView wird ueber die Klasse RecipeCreateViewController.java gesteuert.
 * Es befindet sich jediglich die main und start Methode in dieser Klasse.
 * 
 */

public class RecipeCreateView extends Application {

     /**
     * void main Methode
     * 
     * In dieser Methode wird die RecipeCreateView.java gestartet, über die Methode launch() aus der 
     * geerbten Klasse Application.
     * @param args
     */
    public static void main(String[] args) {
        Application.launch();
    }

     /**
     * void Start 
     * 
     * In dieser Methode wird der Zugriff auf die Hauptseite RecipeCreate.fxml gewährt.
     * Zur Realisierung wird ein neues Objekte der Klasse FXMLLoader erstellet, der man eine 
     * Neue Datein mit passendem Pfad zur RecipeCreate.fxml erstellt und uebergibt.
     * Wetierhin wird mittels der Klasse Parent eine Objekt root erstellt siwe wie ein Objekt der Klasse Scene, dem 
     * root uebergeben wird. Via URL url und String css wird die Nutzung der Seite style.css ermoeglicht. 
     * Die uebergebende Paranthese stage gibt hier verschiedene Methodenaufrufe der Klasse Stage wieder.
     * @param stage
     * @throws Exception
     */

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("src/main/resources/view/RecipeCreate.fxml").toURI().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("view/style.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
