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
       
        System.out.println(robot.lookup("#mnbShowGraphs").query().isDisabled());
        //robot.clickOn("#miTemp");
    }
    
    @Test
    public void testTableView(FxRobot robot) throws Exception {
        setCurrentView(robot);
        robot.clickOn("Détails");
        verifyThat("#lblTitle", isVisible());
    }
    
}
