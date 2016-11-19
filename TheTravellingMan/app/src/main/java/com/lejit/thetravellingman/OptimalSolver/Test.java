package com.lejit.thetravellingman.OptimalSolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 11/17/2016.
 */

public class Test {
    public static void main(String[] args) {
        getOptimizedSolution initialize = new getOptimizedSolution();
        List<String> destination= new ArrayList<String>();
        destination.add("Marina Bay Sands");
        destination.add("Singapore Flyer");
        destination.add("The Singapore Zoo");
        initialize.findOptimalPath(destination, 100);
    }
}
