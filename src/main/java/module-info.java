module com.weatherreport {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.weatherreport to javafx.fxml;
    opens com.weatherreport.controller to javafx.fxml;
    exports com.weatherreport;
    exports com.weatherreport.controller;
}
