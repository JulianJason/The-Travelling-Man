package com.lejit.thetravellingman.OptimalSolver;

import com.lejit.thetravellingman.Attraction_Resources.DestinationMatrix;
import com.lejit.thetravellingman.Attraction_Resources.DestinationMatrix_HASH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 11/15/2016.
 */

public class Main {
    static Attraction[] attractions;
    static ArrayList<Integer> attractionCode;
    static MethodOfTransport[] transportations = {MethodOfTransport.FOOT, MethodOfTransport.TRAIN, MethodOfTransport.TAXI};
    static Attraction STARTING_POINT;
    static double BUDGET = 5;
    static List<String> destination = new ArrayList<String>();
    public static void main(String[] args) {
        try {
            HashMap<String,Integer> map = DestinationMatrix_HASH.destination_matrix;
            int size = map.size();
            attractions = new Attraction[size];
           for (String key: map.keySet()) {
               int currentKey = matchString(key, map);
               attractions[currentKey] = new Attraction(key);
            }


            destination.add("Marina Bay Sands");
            destination.add("Singapore Flyer");
            destination.add("The Singapore Zoo");


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
                for (String target : destination) {
                    int currentKey = matchString(target, map);
                        for (int edge = 0; edge < size; edge++) {
                            double cost;
//                            System.out.println("accessing array [" + transportCode + "] [" + currentKey + "] [" + edge + "]");
                            if (transportCode != 0) {
                                cost = DestinationMatrix.costArray[transportCode][currentKey][edge];
                            } else {
                                cost = 0.0;
                            }
                            double time = DestinationMatrix.timeArray[transportCode][currentKey][edge];
//                            System.out.println(" + " + attractions[edge] + " time = "  + time + " cost = + " + cost + " " + t + "");
                            attractions[currentKey].addEdge(attractions[edge], time, cost, t);
                        }
                    }
            }
            STARTING_POINT = attractions[0];
            OptimalSolver optimalSolver = new OptimalSolver();
            SolutionClass solution = optimalSolver.initiate(STARTING_POINT, BUDGET, destination);
            if (solution == null) {
                throw new IllegalAccessException();
            }
            System.out.println("route is : \n" + solution.route);
            System.out.println("total cost : $" + Math.ceil(BUDGET - solution.cost));
            System.out.println("total time: " + solution.time + "min");
            System.out.println("machine tries: " + solution.tries);

        } catch (IllegalAccessException e) {
            System.out.println("Unable to access data");
        }
    }
    public static int matchString(String input, HashMap<String, Integer> map) {
        int entry = map.get(input);
        if (entry < 0) {
            //do something with value
            return -1;
        }
        return entry;
    }

}
