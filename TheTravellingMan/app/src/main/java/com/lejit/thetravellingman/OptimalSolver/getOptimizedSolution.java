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
    private double budget = 100;
    public List<ItineraryRow> breakdownSolution = new ArrayList<ItineraryRow>();

    public getOptimizedSolution() {
    }

    public List<ItineraryRow> findOptimalPath(List<String> destinationArray, double budget) {
        destinationStatic.clear();
        breakdownSolution.clear();
        this.budget = budget;
        map = DestinationMatrix_HASH.destination_matrix;
        int size = map.size();
        initialize(size);
        String initialLocation = destinationArray.get(0);
        for (String destination: destinationArray) {
            destinationStatic.add(destination);
        }

        Attraction start = attractions[matchString(initialLocation, map)];
        OptimalSolver optimalSolver = new OptimalSolver();
        setupReducedMap(size);
        SolutionClass solution = optimalSolver.initiate(start, budget, destinationStatic, true);
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
    void setupReducedMap(int size) {
        for (MethodOfTransport t : transportations) {
            int transportCode = -1;
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
            // for each destination
            for (String target : destinationStatic) {
                int currentKey = matchString(target, map); // get the key from hashmap
                // then for every edge
                for (int edge = 0; edge < size; edge++) {
                    double cost;
                    // if walking by foot, set cost to 0, else get trnansport cost from array
                    if (transportCode == 0) {
                        cost = 0.0;
                    } else {
                        cost = DestinationMatrix_HASH.costArray[transportCode][currentKey][edge];
                    }
                    // get time
                    double time = DestinationMatrix_HASH.timeArray[transportCode][currentKey][edge];
                    // get the attraction node and add the weighted edge to the new attraction
                    attractions[currentKey].addEdge(attractions[edge], time, cost, t);
                }
            }
        }
    }

    private int matchString(String input, HashMap<String, Integer> map) {
        int entry = map.get(input);
        if (entry < 0) {
            return -1; // throw error here
        }
        return entry;
    }

    public List<ItineraryRow> findBreakDownSolution(SolutionClass solution) {
        List<ItineraryRow> breakdown = new ArrayList<ItineraryRow>();
//        Log.d("TEST", solution.route);
        // add initial solution
        breakdown.add(new ItineraryRow("Start", solution.getEnd(), "" + solution.getTime(), "" + solution.getCost(), "Overall trip cost"));
        if (solution.route != null && !solution.route.isEmpty()) {
            String[] splitted = solution.route.split("[\\r?\\n]+");
            String regex = "ROUTE(.*?)endROUTE COST(.*?)endCOST TIME(.*?)endTIME USING(.*?)endITEM";
            Pattern pattern = Pattern.compile(regex);
            for (String item : splitted) {
                Matcher matcher = pattern.matcher(item);
                ItineraryRow row = new ItineraryRow();
                while (matcher.find()) {
                    row.setTo(matcher.group(1));
                    row.setCost(matcher.group(2));
                    row.setTime(matcher.group(3));
                    row.setMethod(matcher.group(4));
                }
                breakdown.add(row);
                Log.d("ASYN", row.toString());
            }
        } else {
//            Log.d("ASYN,","ERROR route is empty"  );
        }

        return breakdown;
    }
}
