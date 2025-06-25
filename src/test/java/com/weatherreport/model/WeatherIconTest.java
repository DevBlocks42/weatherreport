package com.weatherreport.model;

import com.weatherreport.DAL.Repository;
import com.weatherreport.DAL.WeatherIconRepository;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test sur l'objet WeatherIcon
 * @author devblocks42
 */
public class WeatherIconTest {
    
    private static WeatherIcon instance0 = null;
    private static WeatherIcon instance1 = null;
    private static WeatherIcon instance2 = null;
    private static WeatherIcon instance3 = null;
    private static WeatherIcon instance45 = null;
    
    public WeatherIconTest() {
        
    }
    
    
   @BeforeAll
    public static void setUpClass() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        if (!latch.await(5, TimeUnit.SECONDS)) {
            throw new RuntimeException("JavaFX platform failed to start.");
        }
        WeatherIconRepository weatherIconRepo = new WeatherIconRepository(Repository.getInstance());
        instance0 = weatherIconRepo.getWeatherIcon(0);
        instance1 = weatherIconRepo.getWeatherIcon(1);
        instance2 = weatherIconRepo.getWeatherIcon(2);
        instance3 = weatherIconRepo.getWeatherIcon(3);
        instance45 = weatherIconRepo.getWeatherIcon(45);
       
    }

    /**
     * Test of getDescription method, of class WeatherIcon.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        //0
        WeatherIcon instance_0 = instance0;
        String expResult = "Ensoleillé";
        String result = instance_0.getDescription();
        assertEquals(expResult, result);
        //1
        WeatherIcon instance_1 = instance1;
        String expResult_1 = "Principalement ensoleillé";
        String result_1 = instance_1.getDescription();
        assertEquals(expResult_1, result_1);
        //2
        WeatherIcon instance_2 = instance2;
        String expResult_2 = "Partiellement nuageux";
        String result_2 = instance_2.getDescription();
        assertEquals(expResult_2, result_2);
        //3
        WeatherIcon instance_3 = instance3;
        String expResult_3 = "Nuageux";
        String result_3 = instance_3.getDescription();
        assertEquals(expResult_3, result_3);
        //45
        WeatherIcon instance_45 = instance45;
        String expResult_45 = "Brumeux";
        String result_45 = instance_45.getDescription();
        assertEquals(expResult_45, result_45);
    }

    /**
     * Test of getImage method, of class WeatherIcon.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage");
        //0
        WeatherIcon instance_0 = instance0;
        boolean expResult_0 = false;
        Image result_0 = instance_0.getImage();
        assertEquals(expResult_0, result_0.isError());
        //1
        WeatherIcon instance_1 = instance1;
        boolean expResult_1 = false;
        Image result_1 = instance_1.getImage();
        assertEquals(expResult_1, result_1.isError());
        //2
        WeatherIcon instance_2 = instance2;
        boolean expResult_2 = false;
        Image result_2 = instance_2.getImage();
        assertEquals(expResult_2, result_2.isError());
        //3
        WeatherIcon instance_3 = instance3;
        boolean expResult_3 = false;
        Image result_3 = instance_3.getImage();
        assertEquals(expResult_3, result_3.isError());
        //45
        WeatherIcon instance_45 = instance45;
        boolean expResult_45 = false;
        Image result_45 = instance_45.getImage();
        assertEquals(expResult_45, result_45.isError());
    }
    
}
