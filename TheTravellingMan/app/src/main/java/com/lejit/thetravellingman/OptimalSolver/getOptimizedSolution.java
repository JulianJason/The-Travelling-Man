package com.lejit.thetravellingman.OptimalSolver;

import android.util.Log;

import com.lejit.thetravellingman.Attraction_Resources.DestinationMatrix_HASH;
import com.lejit.thetravellingman.Model.ItineraryRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static HashMap<String,Integer> map = DestinationMatrix_HASH.destination_matrix;
    SolutionClass solution;
    private double BUDGET = 5;
    public List<ItineraryRow> breakdownSolution;

    public getOptimizedSolution() {
    }

    public List<ItineraryRow> findOptimalPath(List<String> destinationArray, double budget) {
        destinationStatic.clear();
        this.BUDGET = budget;
        map = DestinationMatrix_HASH.destination_matrix;
        int size = map.size();
        initialize(size);

        for (String destination: destinationArray) {
            destinationStatic.add(destination);
        }
        Log.d("ASYN", "DESTIN = " + destinationStatic.toString());
        Attraction start = attractions[0];
        OptimalSolver optimalSolver = new OptimalSolver();
        setupReducedHashMap(size);
        SolutionClass solution = optimalSolver.initiate(start, budget, destinationStatic);
        Log.d("ASYN", "rout =" + solution.route);
        breakdownSolution = findBreakDownSolution(solution);
        this.solution = solution;
        return breakdownSolution;
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

    public List<ItineraryRow> findBreakDownSolution(SolutionClass solution) {
        List<ItineraryRow> breakdown = new ArrayList<ItineraryRow>();

        // add initial solution
        breakdown.add(new ItineraryRow("Start", solution.getEnd(), "" + solution.getTime(), "" + solution.getCost(), "NA"));
        if (solution.route != null && !solution.route.isEmpty()) {
            String[] splitted = solution.route.split("[\\r?\\n]+");
            String regex = "ROUTE(.*?)endROUTE COST(.*?)endCOST TIME(.*?)endTIME USING(.*?)endITEM";
            Pattern pattern = Pattern.compile(regex);
            for (String item : splitted) {
//            System.out.println("item = " + item);
                Matcher matcher = pattern.matcher(item);
                ItineraryRow row = new ItineraryRow();
                while (matcher.find()) {
//                System.out.println("GROUP 0 = " +matcher.group(0));
                    row.setTo(matcher.group(1));
                    row.setCost(matcher.group(2));
                    row.setTime(matcher.group(3));
                    row.setMethod(matcher.group(4));
                }
                breakdown.add(row);
            }
        } else {
//            Log.d("ASYN,","ERROR route is empty"  );
        }

        return breakdown;
    }
}
