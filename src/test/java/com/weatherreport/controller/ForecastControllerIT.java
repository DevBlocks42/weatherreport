package com.weatherreport.controller;

import com.weatherreport.App;
import com.weatherreport.model.Location;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.api.FxRobot;
import org.junit.jupiter.api.Test;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de test sur les fonctionnalités du détail météo heure par heure.
 * @author devadmin
 */
@ExtendWith(ApplicationExtension.class)
public class ForecastControllerIT extends ApplicationTest {
    
    public ForecastControllerIT() {
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        new App().start(stage);
    }
    
    public void setCurrentView(FxRobot robot) {
        robot.clickOn("#txfSearchLocation");  
        robot.write("Paris");
        robot.clickOn("#btnSearchLocation");
        robot.doubleClickOn("#tbvResults .table-row-cell");
    }
    
    @Test
    public void testChartSwitch(FxRobot robot) throws Exception {
        setCurrentView(robot);
        WaitForAsyncUtils.waitForFxEvents();
        robot.clickOn("Détails");
        WaitForAsyncUtils.waitForFxEvents();
        robot.clickOn("#mnbShowGraphs");
        robot.clickOn("#miTemp");
        WaitForAsyncUtils.waitForFxEvents();
        verifyThat(robot.lookup("#tempChart"), isVisible());
        robot.sleep(2000);
        WaitForAsyncUtils.waitForFxEvents();
        robot.clickOn("#mnbShowGraphs");
        robot.clickOn("#miAppTemp");
        verifyThat(robot.lookup("#appTempChart"), isVisible());
        WaitForAsyncUtils.waitForFxEvents();
        robot.clickOn("#mnbShowGraphs");
        robot.clickOn("#miWindSpeed");
        verifyThat(robot.lookup("#windSpeedChart"), isVisible());
        WaitForAsyncUtils.waitForFxEvents();
        robot.clickOn("#mnbShowGraphs");
        robot.clickOn("#miRain");
        verifyThat(robot.lookup("#rainChart"), isVisible());
        WaitForAsyncUtils.waitForFxEvents();
        robot.clickOn("#mnbShowGraphs");
        robot.clickOn("#miRainProbs");
        verifyThat(robot.lookup("#rainProbsChart"), isVisible());
        WaitForAsyncUtils.waitForFxEvents();
        robot.clickOn("#mnbShowGraphs");
        robot.clickOn("#miCloudCover");
        verifyThat(robot.lookup("#cloudCoverChart"), isVisible());
        robot.sleep(3000);
    }
}
