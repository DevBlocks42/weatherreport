package com.weatherreport.controller;

import com.weatherreport.DAL.ForecastRepository;
import com.weatherreport.DAL.Repository;
import com.weatherreport.model.ForecastDaySum;
import com.weatherreport.model.Location;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Contrôleur de la fenêtre de synthèse météo hebdomadaire
 *
 * @author devblocks42
 */
public class SummaryController {

    @FXML
    private AnchorPane day1AnchorPane;

    @FXML
    private AnchorPane day2AnchorPane;

    @FXML
    private AnchorPane day3AnchorPane;

    @FXML
    private AnchorPane day4AnchorPane;

    @FXML
    private AnchorPane day5AnchorPane;

    @FXML
    private AnchorPane day6AnchorPane;

    @FXML
    private AnchorPane day7AnchorPane;
    
    private ForecastRepository forecastRepo;
    
    public void initialize(Location location) {
        forecastRepo = new ForecastRepository(Repository.getInstance());
        List<ForecastDaySum> forecastSums = forecastRepo.getForecastDaySum(location, LocalDate.now(), LocalDate.now().plusDays(6));
        System.out.println("test");
        /*ObservableList<Node> day1Nodes = day1AnchorPane.getChildren();
        Label maxTemp = (Label)day1Nodes.get(0);
        maxTemp.setText(string);*/
    }    
    
}
