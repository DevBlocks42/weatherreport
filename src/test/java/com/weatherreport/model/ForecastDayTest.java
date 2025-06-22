package com.weatherreport.model;

import com.weatherreport.DAL.ForecastRepository;
import com.weatherreport.DAL.LocationRepository;
import com.weatherreport.DAL.Repository;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test sur l'objet ForecastDay
 * @author devblocks42
 */
public class ForecastDayTest {
    
    private static ForecastDay forecastDay;
    private static Location location;
    
    public ForecastDayTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        ForecastRepository forecastRepo = new ForecastRepository(Repository.getInstance());
        LocationRepository locationRepo = new LocationRepository(Repository.getInstance());
        location = locationRepo.getLocationsLike("Paris").get(0);
        forecastDay = forecastRepo.getCurrentForecast(location, LocalDate.now());
        
    }
    
    /**
     * Test of getTemperatures method, of class ForecastDay.
     */
    @Test
    public void testGetTemperatures() {
        System.out.println("getTemperatures");
        List<Float> result = forecastDay.getTemperatures();
        int expResult = 24;
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getApparentTemperatures method, of class ForecastDay.
     */
    @Test
    public void testGetApparentTemperatures() {
        System.out.println("getApparentTemperatures");
        List<Float> result = forecastDay.getApparentTemperatures();
        int expResult = 24;
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getRain method, of class ForecastDay.
     */
    @Test
    public void testGetRain() {
        System.out.println("getRain");
        List<Float> result = forecastDay.getRain();
        int expResult = 24;
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getCloudCover method, of class ForecastDay.
     */
    @Test
    public void testGetCloudCover() {
        System.out.println("getCloudCover");
        List<Integer> result = forecastDay.getCloudCover();
        int expResult = 24;
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getWindSpeed10m method, of class ForecastDay.
     */
    @Test
    public void testGetWindSpeed10m() {
        System.out.println("getWindSpeed10m");
        List<Float> result = forecastDay.getWindSpeed10m();
        int expResult = 24;
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getWeatherCodes method, of class ForecastDay.
     */
    @Test
    public void testGetWeatherCodes() {
        System.out.println("getWeatherCodes");
        List<Integer> result = forecastDay.getWeatherCodes();
        int expResult = 24;
        assertEquals(expResult, result.size());
    }
   
}
