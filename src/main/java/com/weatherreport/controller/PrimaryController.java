package com.weatherreport.controller;

import com.weatherreport.DAL.LocationRepository;
import com.weatherreport.http.ApiClient;
import com.weatherreport.model.Location;
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

public class PrimaryController {
    @FXML
    private Button btnSearchLocation;

    @FXML
    private TextField txfSearchLocation;
   
    @FXML
    private TableView<Location> tbvResults;
    
    public void initialize() {
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
    }
    
    
    @FXML
    void onBtnSearchLocation_clicked(ActionEvent event) {
        
        if(txfSearchLocation.getText().length() > 3) {
            ApiClient apiClient = new ApiClient();
            LocationRepository locationRepo = new LocationRepository(apiClient);
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
}
