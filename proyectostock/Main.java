package proyectostock;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage stage) throws Exception {
       this.stage = stage;
        initRootLayout();
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vista/GestionStock.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    public static void main(String[] args) {
        launch(args);
    }

}
