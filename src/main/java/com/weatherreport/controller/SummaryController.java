package com.weatherreport.controller;

import com.weatherreport.DAL.ForecastRepository;
import com.weatherreport.DAL.Repository;
import com.weatherreport.DAL.WeatherIconRepository;
import com.weatherreport.model.ForecastDaySum;
import com.weatherreport.model.Location;
import com.weatherreport.model.WeatherIcon;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
    
    @FXML
    private Label lblTitle;
    
    private ForecastRepository forecastRepo;
    
    private WeatherIconRepository weatherIconRepo;
    
    private List<ForecastDaySum> forecastDaySums;
    
    public void initialize(Location location) {
        forecastRepo = new ForecastRepository(Repository.getInstance());
        weatherIconRepo = new WeatherIconRepository(Repository.getInstance());
        forecastDaySums = forecastRepo.getForecastDaySum(location, LocalDate.now(), LocalDate.now().plusDays(6));
        lblTitle.setText(lblTitle.getText() + " " + location.getName());
        List<AnchorPane> panes = new ArrayList<>();
        panes.add(day1AnchorPane);
        panes.add(day2AnchorPane);
        panes.add(day3AnchorPane);
        panes.add(day4AnchorPane);
        panes.add(day5AnchorPane);
        panes.add(day6AnchorPane);
        panes.add(day7AnchorPane);
        for(int i = 0; i < panes.size(); i++) {
            ObservableList<Node> dayNodes = panes.get(i).getChildren();
            VBox vbox = (VBox)dayNodes.get(0);
            for(Node node : vbox.getChildren()) {
                if(node instanceof Label) {
                    Label label = (Label) node;
                    initLabelValue(label, i);
                } else if(node instanceof ImageView) {
                    initImageViews(vbox, i);
                }
                
            }
        }
    }

    private void initLabelValue(Label label, int anchorIndex) {
        switch(label.getId()) {
            case "day":
                label.setText(label.getText() + forecastDaySums.get(anchorIndex).getSunrise().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRANCE));
                break;
            case "maxTemp":
                label.setText(label.getText() + forecastDaySums.get(anchorIndex).getMaxTemp() + "°C");
                break;
            case "minTemp":
                label.setText(label.getText() + forecastDaySums.get(anchorIndex).getMinTemp() + "°C");
                break;
            case "sunrise":
                label.setText(label.getText() + forecastDaySums.get(anchorIndex).getSunrise().toLocalTime());
                break;
            case "sunset":
                label.setText(label.getText() + forecastDaySums.get(anchorIndex).getSunset().toLocalTime());
                break;
            }
    }
    
    private void initImageViews(VBox vbox, int anchorIndex) {
        WeatherIcon icon = weatherIconRepo.getWeatherIcon(forecastDaySums.get(anchorIndex).getWeatherCode());
        ObservableList<Node> nodes = vbox.getChildren();
        for(Node node : nodes) {
            if(node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                imageView.setImage(icon.getImage());
            }
        }
    }   
}