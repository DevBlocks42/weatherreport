package com.weatherreport.controller;

import com.weatherreport.App;
import com.weatherreport.DAL.ForecastRepository;
import com.weatherreport.DAL.Repository;
import com.weatherreport.DAL.WeatherIconRepository;
import com.weatherreport.model.Location;
import com.weatherreport.model.Forecast;
import com.weatherreport.model.ForecastRow;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Contrôleur de la fenêtre de météo en temps réel pour un lieu
 * @author DevBlocks42 <devblocks42 at keemail.me>
 */
public class ForecastController {
    private Location location;
    private ForecastRepository forecastRepo; 
    private WeatherIconRepository weatherIconRepo;
    private Forecast forecast;
    private LineChart<?, ?> temperatureChart;
    private LineChart<?, ?> apparentTemperatureChart;
    private LineChart<?, ?> rainChart;
    private LineChart<?, ?> rainProbsChart;
    private LineChart<?, ?> windSpeedChart;
    private LineChart<?, ?> cloudCoverChart;
    
    @FXML
    private AnchorPane anchor;
    
    @FXML
    private TableView<ForecastRow> tbvMeteo;
    
    @FXML
    private Label lblTitle;

    @FXML
    private MenuButton mnbShowGraphs;
    
    @FXML
    private Button btnBack;
    
    public void initialize(Location location) {
        this.location = location; 
        forecastRepo = new ForecastRepository(Repository.getInstance());
        weatherIconRepo = new WeatherIconRepository(Repository.getInstance());
        forecast = forecastRepo.getCurrentForecast(location);
        lblTitle.setText("Météo de " + location.getName() + ", aujourd'hui.");
        initializeCharts();
        initializeTable();
    }
    
    private void initializeTable() {
        //Heures
        TableColumn<ForecastRow, LocalTime> dailyClockColumn = new TableColumn("Heure");
        dailyClockColumn.setCellValueFactory(cellData -> {
            return cellData.getValue().getHour();
        });
        //Températures
        TableColumn<ForecastRow, Float> dailyTemperatureColumn = new TableColumn("Température (°C)");
        dailyTemperatureColumn.setCellValueFactory(cellData -> {
            return cellData.getValue().getTemperature();
        });
        //Températures ressenties
        TableColumn<ForecastRow, Float> apparentDailyTemperaturesColumn = new TableColumn("Ressenti (°C)");
        apparentDailyTemperaturesColumn.setCellValueFactory(cellData -> {
            return cellData.getValue().getApparentTemperature();
        });
        //Probabilitées de pluie
        TableColumn<ForecastRow, Integer> rainProbsColumn = new TableColumn("Probabilité de pluie (%)");
        rainProbsColumn.setCellValueFactory(cellData -> {
            return cellData.getValue().getPrecipitationProbability();
        });
        //Pluie 
        TableColumn<ForecastRow, Float> rainColumn = new TableColumn("Pluie (mm)");
        rainColumn.setCellValueFactory(cellData -> {
            return cellData.getValue().getRain();
        });
        //Couverture nuageuse 
        TableColumn<ForecastRow, Integer> cloudCoverColumn = new TableColumn("Nuages");
        cloudCoverColumn.setCellValueFactory(cellData -> {
            return cellData.getValue().getCloudCover();
        });
        //Vitesse du vent
        TableColumn<ForecastRow, Float> windSpeedColumn = new TableColumn("Vitesse du vent (km/h)");
        windSpeedColumn.setCellValueFactory(cellData -> {
            return cellData.getValue().getWindSpeed10m();
        });
        //Code/Icone météo
        tbvMeteo.setRowFactory(tv -> {
            TableRow<ForecastRow> row = new TableRow<>();
            row.setStyle("-fx-background-color: grey");
            return row;
        });
        TableColumn<ForecastRow, Image> weatherCodeColumn = new TableColumn("Code météo");
        weatherCodeColumn.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<Image>(weatherIconRepo.getWeatherIcon(cellData.getValue().getWeatherCode().get()).getImage());
        });
        weatherCodeColumn.setCellFactory(col -> new TableCell<>() { 
            private final ImageView imgView = new ImageView();
                    
            @Override 
            protected void updateItem(Image item, boolean empty) {
                super.updateItem(item, empty);
                if(empty || item == null) {
                    imgView.setImage(null);
                    setGraphic(null);
                } else {
                    imgView.setImage(item);
                    imgView.setFitHeight(50);
                    imgView.setFitWidth(50);
                    setGraphic(imgView);
                }
            }
        });
        tbvMeteo.getColumns().addAll(dailyClockColumn, weatherCodeColumn, dailyTemperatureColumn, apparentDailyTemperaturesColumn, rainProbsColumn, rainColumn, cloudCoverColumn, windSpeedColumn);
        //Peuplement de tbvMeteo
        ObservableList<ForecastRow> ol = FXCollections.observableArrayList();
        for(int i = 0; i < forecast.getDailyClock().size(); i++) {
            ol.add(new ForecastRow(forecast.getDailyClock().get(i), forecast.getTemperatures().get(i), forecast.getApparentTemperatures().get(i), forecast.getPrecipitationProbs().get(i), forecast.getRain().get(i), forecast.getCloudCover().get(i), forecast.getWindSpeed10m().get(i), forecast.getWeatherCodes().get(i)));
        }
        tbvMeteo.setItems(ol);
        tbvMeteo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    
    private LineChart initChart(NumberAxis xAxis, NumberAxis yAxis, String xAxisDescr, String yAxisDescr, List<?> datas) {
        LineChart lineChart = new LineChart(xAxis, yAxis);
        xAxis.setLabel(xAxisDescr);
        yAxis.setLabel(yAxisDescr);
        Series series = new Series<Number, Number>();
        for(int i = 0; i < datas.size(); i++) {
            Data<Number, Number> data = new Data<>(i, (Number)datas.get(i));
            series.getData().add(data);
        }
        lineChart.getData().add(series);
        AnchorPane.setTopAnchor(lineChart, 10.0);
        AnchorPane.setRightAnchor(lineChart, 0.0);
        AnchorPane.setLeftAnchor(lineChart, 0.0);
        AnchorPane.setBottomAnchor(lineChart, 150.0);
        anchor.getChildren().add(lineChart);
        lineChart.setVisible(false);
        return lineChart;
    }
    
    private void initializeCharts() {
        //Température
        int maxTemp = (int)(forecast.getMaxTemperature() + 1);
        NumberAxis xTempAxis = new NumberAxis(0, 23.59, 1);
        NumberAxis yTempAxis = new NumberAxis(0, maxTemp, 5);
        temperatureChart = initChart(xTempAxis, yTempAxis, "Heure", "Température (°C)", forecast.getTemperatures());
        //Ressenti
        int maxAppTemp = (int)(forecast.getMaxApparentTemperature() + 1);
        NumberAxis xAppTempAxis = new NumberAxis(0, 23.59, 1);
        NumberAxis yAppTempAxis = new NumberAxis(0, maxAppTemp, 5);
        apparentTemperatureChart = initChart(xAppTempAxis, yAppTempAxis, "Heure", "Température ressentie (°C)", forecast.getApparentTemperatures());
        //Vitesse du vent
        int maxWindSpeed = (int)(forecast.getMaxWindSpeed() + 1);
        NumberAxis xWindSpeedAxis = new NumberAxis(0, 23.59, 1);
        NumberAxis yWindSpeedAxis = new NumberAxis(0, maxWindSpeed, 10);
        windSpeedChart = initChart(xWindSpeedAxis, yWindSpeedAxis, "Heure", "Vitesse du vent (km/h)", forecast.getWindSpeed10m());
        //Précipitations
        int maxRain = (int)(forecast.getMaxRain() + 1);
        NumberAxis xRainAxis = new NumberAxis(0, 23.59, 1);
        NumberAxis yRainAxis = new NumberAxis(0, maxRain, 5);
        rainChart = initChart(xRainAxis, yRainAxis, "Heure", "Précipitations (mm)", forecast.getRain());
        //Probabilité de pluie 
        int maxProbs = forecast.getMaxPrecipitationProbs();
        NumberAxis xProbsAxis = new NumberAxis(0, 23.59, 1);
        NumberAxis yProbsAxis = new NumberAxis(0, maxProbs, 5);
        rainProbsChart = initChart(xProbsAxis, yProbsAxis, "Heure", "Probabilités de pluie (%)", forecast.getPrecipitationProbs());
        //Couverture nuageuse
        int maxCover = forecast.getMaxCloudCover();
        NumberAxis xCoverAxis = new NumberAxis(0, 23.59, 1);
        NumberAxis yCoverAxis = new NumberAxis(0, maxCover, 5);
        cloudCoverChart = initChart(xCoverAxis, yCoverAxis, "Heure", "Couverture nuageuse (%)", forecast.getCloudCover());
    }
    
    @FXML
    void showGraph(ActionEvent event) {
        MenuItem item = (MenuItem)event.getSource();
        if(item != null) {
            System.out.println(item.getId());
            switch(item.getId()) {
                case "miTable":
                    temperatureChart.setVisible(false);
                    apparentTemperatureChart.setVisible(false);
                    windSpeedChart.setVisible(false);
                    rainChart.setVisible(false);
                    cloudCoverChart.setVisible(false);
                    rainProbsChart.setVisible(false);
                    tbvMeteo.setVisible(true);
                    break;
                case "miTemp":
                    temperatureChart.setVisible(true);
                    apparentTemperatureChart.setVisible(false);
                    windSpeedChart.setVisible(false);
                    rainChart.setVisible(false);
                    cloudCoverChart.setVisible(false);
                    rainProbsChart.setVisible(false);
                    tbvMeteo.setVisible(false);
                    break;
                case "miAppTemp":
                    temperatureChart.setVisible(false);
                    apparentTemperatureChart.setVisible(true);
                    windSpeedChart.setVisible(false);
                    rainChart.setVisible(false);
                    cloudCoverChart.setVisible(false);
                    rainProbsChart.setVisible(false);
                    tbvMeteo.setVisible(false);
                    break;
                case "miWindSpeed":
                    temperatureChart.setVisible(false);
                    apparentTemperatureChart.setVisible(false);
                    windSpeedChart.setVisible(true);
                    rainChart.setVisible(false);
                    cloudCoverChart.setVisible(false);
                    rainProbsChart.setVisible(false);
                    tbvMeteo.setVisible(false);
                    break;
                case "miRain": 
                    temperatureChart.setVisible(false);
                    apparentTemperatureChart.setVisible(false);
                    windSpeedChart.setVisible(false);
                    rainChart.setVisible(true);
                    cloudCoverChart.setVisible(false);
                    rainProbsChart.setVisible(false);
                    tbvMeteo.setVisible(false);
                    break;
                case "miRainProbs": 
                    temperatureChart.setVisible(false);
                    apparentTemperatureChart.setVisible(false);
                    windSpeedChart.setVisible(false);
                    rainChart.setVisible(false);
                    cloudCoverChart.setVisible(false);
                    rainProbsChart.setVisible(true);
                    tbvMeteo.setVisible(false);
                    break;
                case "miCloudCover": 
                    temperatureChart.setVisible(false);
                    apparentTemperatureChart.setVisible(false);
                    windSpeedChart.setVisible(false);
                    rainChart.setVisible(false);
                    cloudCoverChart.setVisible(true);
                    rainProbsChart.setVisible(false);
                    tbvMeteo.setVisible(false);
                    break;
            }
        }
    }
    
    @FXML
    void returnToPrimary(ActionEvent event) {
        try {
            App.setRoot("primary");
            PrimaryController controller = App.fxmlLoader.<PrimaryController>getController();
            controller.initialize();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}