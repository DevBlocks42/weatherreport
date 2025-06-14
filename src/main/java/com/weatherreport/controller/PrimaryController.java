package com.weatherreport.controller;

import com.weatherreport.DAL.LocationRepository;
import com.weatherreport.DAL.Repository;
import com.weatherreport.http.ApiClient;
import com.weatherreport.model.Location;
import com.weatherreport.App;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Contrôleur de la fenêtre de recherche par lieux
 * @author DevBlocks42 
 */
public class PrimaryController {
    @FXML
    private Button btnSearchLocation;

    @FXML
    private TextField txfSearchLocation;
   
    @FXML
    private TableView<Location> tbvResults;
    
    private ApiClient apiClient;
    
    private LocationRepository locationRepo;
    
    public void initialize() {
        tbvResults.getColumns().clear();
        locationRepo = new LocationRepository(Repository.getInstance());
        //Lieu/ville
        TableColumn<Location, String> nameCol = new TableColumn<>("Lieu");
        nameCol.setCellValueFactory(cellData -> 
            new ReadOnlyStringWrapper(cellData.getValue().getName())
        );
        //Pays
        TableColumn<Location, String> countryCol = new TableColumn<>("Pays");
        countryCol.setCellValueFactory(cellData -> 
            new ReadOnlyStringWrapper(cellData.getValue().getCountry())
        );
        //Altitude
        TableColumn<Location, Double> elevationCol = new TableColumn<>("Altitude");
        elevationCol.setCellValueFactory(cellData -> 
            new ReadOnlyObjectWrapper<Double>(cellData.getValue().getElevation())
        );
        //Latitude
        TableColumn<Location, Double> latitudeCol = new TableColumn<>("Latitude");
        latitudeCol.setCellValueFactory(cellData -> 
            new ReadOnlyObjectWrapper<Double>(cellData.getValue().getLatitude())
        );
        //Longitude
        TableColumn<Location, Double> longitudeCol = new TableColumn<>("Longitude");
        longitudeCol.setCellValueFactory(cellData -> 
            new ReadOnlyObjectWrapper<Double>(cellData.getValue().getLongitude())
        );
        //Zone administrative
        TableColumn<Location, String> admin1Col = new TableColumn<>("Administration");
        admin1Col.setCellValueFactory(cellData -> 
            new ReadOnlyStringWrapper(cellData.getValue().getAdmin1())
        );
        tbvResults.getColumns().addAll(nameCol, countryCol, elevationCol, latitudeCol, longitudeCol, admin1Col);
        tbvResults.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    
    @FXML
    void onBtnSearchLocation_clicked(ActionEvent event) {
        if(txfSearchLocation.getText().length() > 3) {          
            List<Location> locations = locationRepo.getLocationsLike(txfSearchLocation.getText());
            ObservableList<Location> ol = FXCollections.observableArrayList();
            ol.addAll(locations);
            tbvResults.setItems(ol);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Critère de recherche trop court");
            alert.setHeaderText("Vous devez saisir au moins 3 caractères dans la barre de recherche.");
            alert.show();
        }
    }
    
    @FXML
    void onLocationItemChanged(MouseEvent event) {
        if(event.getClickCount() == 2) {
            Location currentLocation = tbvResults.getSelectionModel().getSelectedItem();
            if(currentLocation != null) {
                try {
                    App.setRoot("forecast");
                    ForecastController forecastController = App.fxmlLoader.<ForecastController>getController();
                    LocalDate date = LocalDate.now(); // Ajuster en fonction du jour choisi
                    forecastController.initialize(currentLocation, date);
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
