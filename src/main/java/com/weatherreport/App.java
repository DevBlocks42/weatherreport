package com.weatherreport;

import com.weatherreport.DAL.LocationRepository;
import com.weatherreport.http.ApiClient;
import com.weatherreport.model.Location;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
        ApiClient apiClient = new ApiClient();
        LocationRepository locationRepo = new LocationRepository(apiClient);
        List<Location> locations = locationRepo.getLocationsLike(URLEncoder.encode("St-Étienne", StandardCharsets.UTF_8));
        for(Location location : locations) {
            System.out.println(location.toString());
        }
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}