package com.weatherreport.controller;

import com.weatherreport.DAL.ForecastRepository;
import com.weatherreport.DAL.Repository;
import com.weatherreport.model.Location;
import com.weatherreport.model.Forecast;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

/**
 * Contrôleur de la fenêtre de météo en temps réel pour un lieu
 * @author DevBlocks42 <devblocks42 at keemail.me>
 */
public class ForecastController {
    
    private Location location;
    private ForecastRepository forecastRepo; 
    private Forecast forecast;
    
    @FXML
    private LineChart<Float, Float> temperatureChart;
    
    public void initialize(Location location) {
        this.location = location; 
        forecastRepo = new ForecastRepository(Repository.getInstance());
        forecast = forecastRepo.getCurrentForecast(location);
        List<Float> temperatures = forecast.getTemperatures();
        Series series = new Series();
        for(int i = 0; i < temperatures.size(); i++) {
            series.getData().add(new Data(i, temperatures.get(i)));
        }
//series.getData().addAll(forecast.getTemperatures());
       temperatureChart.getData().add(series);
    }
    
}
