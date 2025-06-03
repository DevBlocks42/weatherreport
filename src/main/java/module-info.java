module com.weatherreport {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires org.apache.httpcomponents.core5.httpcore5;
    requires org.json;

    opens com.weatherreport to javafx.fxml, org.apache.httpcomponents.client5.httpclient5, org.apache.httpcomponents.core5.httpcore5;
    opens com.weatherreport.controller to javafx.fxml;
    exports com.weatherreport;
    exports com.weatherreport.controller;
    exports com.weatherreport.http;
}
