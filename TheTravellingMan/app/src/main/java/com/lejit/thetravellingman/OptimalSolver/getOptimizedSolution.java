package com.lejit.thetravellingman.OptimalSolver;

import com.lejit.thetravellingman.Attraction_Resources.DestinationMatrix_HASH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 11/15/2016.
 */

/**
 * HOW TO USE: (look at Test.java)
 * Initialize the class using getOptimizedSolution fastSolution = new getOptimizedSolution
 * then run findOPtimalPath(List of attractions to go, budget)
 * then finally call fastSolution.getOptimalCost() or fastSolution.getRoute to get relevant Data
 */
public class getOptimizedSolution {
    private static Attraction[] attractions;
    private static MethodOfTransport[] transportations = {MethodOfTransport.FOOT, MethodOfTransport.TRAIN, MethodOfTransport.TAXI};

    private static List<String> destinationStatic = new ArrayList<String>();
    private static HashMap<String,Integer> map;
    SolutionClass solution;
    private double BUDGET = 5;

    public void findOptimalPath(List<String> destinationArray, double budget) {
        this.BUDGET = budget;
        map = DestinationMatrix_HASH.destination_matrix;
        int size = map.size();
        initialize(size);

        for (String destination: destinationArray) {
            destinationStatic.add(destination);
        }
        Attraction start = attractions[0];
        OptimalSolver optimalSolver = new OptimalSolver();
        setupReducedHashMap(size);

        SolutionClass solution = optimalSolver.initiate(start, budget, destinationStatic);
        this.solution = solution;
//        System.out.println("route is : \n" + solution.route);
//        System.out.println("total cost : $" + Math.ceil(BUDGET - solution.cost));
//        System.out.println("total time: " + solution.time + "min");
//        System.out.println("machine tries: " + solution.tries);
        System.out.println(getOptimalTime());
    }


    void initialize(int size) {
        attractions = new Attraction[size];
       for (String key: map.keySet()) {
           int currentKey = matchString(key, map);
           attractions[currentKey] = new Attraction(key);
        }
    }
    void setupReducedHashMap(int size) {
        for (MethodOfTransport t : transportations) {
            int transportCode = -1; // should never happen
            switch (t) {
                case FOOT:
                    transportCode = 0;
                    break;
                case TRAIN:
                    transportCode = 1;
                    break;
                case TAXI:
                    transportCode = 2;
                    break;
            }
            for (String target : destinationStatic) {
                int currentKey = matchString(target, map);
                for (int edge = 0; edge < size; edge++) {
                    double cost;
//                            System.out.println("accessing array [" + transportCode + "] [" + currentKey + "] [" + edge + "]");
                    if (transportCode != 0) {
                        cost = DestinationMatrix_HASH.costArray[transportCode][currentKey][edge];
                    } else {
                        cost = 0.0;
                    }
                    double time = DestinationMatrix_HASH.timeArray[transportCode][currentKey][edge];
//                            System.out.println(" + " + attractions[edge] + " time = "  + time + " cost = + " + cost + " " + t + "");
                    attractions[currentKey].addEdge(attractions[edge], time, cost, t);
                }
            }
        }
    }
    public double getOptimalTime() {
        return this.solution.time;
    }

    public double getOptimalCost() {
        return this.BUDGET - this.solution.cost;
    }

    public String getRoute() {
        return this.solution.route;
    }

    public int getTries() {
        return this.solution.tries;
    }

    private int matchString(String input, HashMap<String, Integer> map) {
        int entry = map.get(input);
        if (entry < 0) {
            //do something with value
            return -1;
        }
        return entry;
    }

}
