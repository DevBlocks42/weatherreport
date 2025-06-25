package com.weatherreport.model;

import com.weatherreport.DAL.LocationRepository;
import com.weatherreport.DAL.Repository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test sur l'objet Location
 * @author devblocks42
 */
public class LocationTest {
    
    private static List<Location> locations = new ArrayList<>();
    private Location instance = null;
   
    
    public LocationTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        LocationRepository locationRepo = new LocationRepository(Repository.getInstance());
        locations = locationRepo.getLocationsLike("Paris");
        assertEquals(20, locations.size());
    }
    

    /**
     * Test of getName method, of class Location.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Location instance = locations.get(0);
        String expResult = "Paris";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCountry method, of class Location.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        Location instance = locations.get(0);
        String expResult = "France";
        String result = instance.getCountry();
        assertEquals(expResult, result);
    }

    /**
     * Test of getElevation method, of class Location.
     */
    @Test
    public void testGetElevation() {
        System.out.println("getElevation");
        Location instance = locations.get(0);
        double expResult = 42.0;
        double result = instance.getElevation();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getLatitude method, of class Location.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        Location instance = locations.get(0);
        double expResult = 48.85341;
        double result = instance.getLatitude();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getLongitude method, of class Location.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        Location instance = locations.get(0);
        double expResult = 2.3488;
        double result = instance.getLongitude();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getAdmin1 method, of class Location.
     */
    @Test
    public void testGetAdmin1() {
        System.out.println("getAdmin1");
        Location instance = locations.get(0);
        String expResult = "Île-de-France";
        String result = instance.getAdmin1();
        assertEquals(expResult, result);
    }  
}
