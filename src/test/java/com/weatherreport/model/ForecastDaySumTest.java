package com.weatherreport.model;

import com.weatherreport.DAL.ForecastRepository;
import com.weatherreport.DAL.LocationRepository;
import com.weatherreport.DAL.Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests sur l'objet ForecastDaySum
 * @author devblocks42
 */
public class ForecastDaySumTest {
    
    private static ForecastDaySum instance;
    private static Location currentLocation;
    
    public ForecastDaySumTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        ForecastRepository repo = new ForecastRepository(Repository.getInstance());
        LocationRepository locRepo = new LocationRepository(Repository.getInstance());
        currentLocation = locRepo.getLocationsLike("Paris").get(0);
        List<ForecastDaySum> instances = repo.getForecastDaySum(currentLocation, LocalDate.now(), LocalDate.now().plusDays(7));
        instance = instances.get(0);
    }
    

    /**
     * Test of getWeatherCode method, of class ForecastDaySum.
     */
    @Test
    public void testGetWeatherCode() {
        System.out.println("getWeatherCode");
        boolean expResult = instance.getWeatherCode() >= 0;
        int result = instance.getWeatherCode();
        assertEquals(expResult, result >= 0);
    }

    /**
     * Test of getSunrise method, of class ForecastDaySum.
     */
    @Test
    public void testGetSunrise() {
        System.out.println("getSunrise");
        LocalDateTime expResult = LocalDateTime.parse(instance.getSunrise().toString());
        LocalDateTime result = instance.getSunrise();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSunset method, of class ForecastDaySum.
     */
    @Test
    public void testGetSunset() {
        System.out.println("getSunset");
        LocalDateTime expResult = LocalDateTime.parse(instance.getSunset().toString());
        LocalDateTime result = instance.getSunset();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxTemp method, of class ForecastDaySum.
     */
    @Test
    public void testGetMaxTemp() {
        System.out.println("getMaxTemp");
        Float expResult = Float.valueOf(instance.getMaxTemp().toString());
        Float result = instance.getMaxTemp();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinTemp method, of class ForecastDaySum.
     */
    @Test
    public void testGetMinTemp() {
        System.out.println("getMinTemp");
        Float expResult = Float.valueOf(instance.getMinTemp().toString());
        Float result = instance.getMinTemp();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLocation method, of class ForecastDaySum.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        Location expResult = currentLocation;
        Location result = instance.getLocation();
        assertEquals(expResult, result);
    }
    
}
