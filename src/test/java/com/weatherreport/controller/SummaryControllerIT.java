package com.weatherreport.controller;

import com.weatherreport.App;
import com.weatherreport.model.ForecastRow;
import com.weatherreport.model.Location;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.api.FxRobot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Classe de test sur les fonctionnalités de recherche de lieux
 * @author devblocks42
 */
@ExtendWith(ApplicationExtension.class)
public class SummaryControllerIT extends ApplicationTest {
    
    public SummaryControllerIT() {
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        new App().start(stage);
        
    }
        
    /**
     * Test visant à vérifier que les informations météo s'affichent correctement pour la synthèse hebdomadaire 
     * @param robot
     * @throws Exception 
     */
    @Test
    public void testSummaryAndDetails(FxRobot robot) throws Exception {
        robot.clickOn("#txfSearchLocation");  
        robot.write("Paris");
        robot.clickOn("#btnSearchLocation");
        robot.doubleClickOn("#tbvResults .table-row-cell");
        robot.clickOn("Détails");
        WaitForAsyncUtils.waitForFxEvents();
        TableView<ForecastRow> tbv = robot.lookup("#tbvMeteo").queryTableView();
        ForecastRow firstRow = tbv.getItems().get(0);
        int weatherCode = firstRow.getWeatherCode().getValue().intValue();
        assertNotEquals(-1, weatherCode);
    }
}
