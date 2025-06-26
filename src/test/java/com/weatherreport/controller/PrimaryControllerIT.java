package com.weatherreport.controller;

import com.weatherreport.App;
import com.weatherreport.model.Location;
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
 * Classe de test sur les fonctionnalités de recherche de lieux
 * @author devblocks42
 */
@ExtendWith(ApplicationExtension.class)
public class PrimaryControllerIT extends ApplicationTest {
    
    public PrimaryControllerIT() {
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        new App().start(stage);
    }
    
    /**
     * Test visant à vérifier que la recherche par nom de lieu approximatif fonctionne de manière attendue. 
     * @param robot
     * @throws Exception 
     */
    @Test
    public void testSearchBarAndClick(FxRobot robot) throws Exception {
        //Recherche du terme "Paris" puis clic sur le bouton de recherche
        robot.clickOn("#txfSearchLocation");  
        robot.write("Paris");
        robot.clickOn("#btnSearchLocation");
        //Récupération du premier élément de la table 
        TableView<Location> tbv = robot.lookup("#tbvResults").queryTableView();
        Location paris = (Location)tbv.getItems().get(0);
        Location paris2 = (Location)tbv.getItems().get(19);
        //Le lieu choisi doit avoir le nom "Paris"
        assertEquals(paris.getName(), "Paris");
        assertEquals(paris2.getCountry(), "Philippines");
        //On valide la séléction et on change de vue
        robot.doubleClickOn("#tbvResults .table-row-cell");
        //robot.lookup()
        WaitForAsyncUtils.waitForFxEvents();
        verifyThat("#day", isVisible());
    }    
}
