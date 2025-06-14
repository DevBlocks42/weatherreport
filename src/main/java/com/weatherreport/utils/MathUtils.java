package com.weatherreport.utils;

import java.util.List;

/**
 * Classe contenant des méthodes auxilliaires pour des opérations mathématiques
 * @author DevBlocks42
 */
public class MathUtils {
    
    public static Float getMaxOfFloatList(List<Float> list) {
        Float max = list.get(0);
        for(Float val : list) {
            if(val > max) {
                max = val;
            }
        }
        return max;
    }
    
    public static Integer getMaxOfIntegerList(List<Integer> list) {
        Integer max = list.get(0);
        for(Integer val : list) {
            if(val > max) {
                max = val;
            }
        }
        return max;
    }
}
