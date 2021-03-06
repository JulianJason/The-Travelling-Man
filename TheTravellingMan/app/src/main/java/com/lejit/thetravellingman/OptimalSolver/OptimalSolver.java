package com.lejit.thetravellingman.OptimalSolver;

import android.util.Log;

import java.util.List;

/**
 * Created by USER on 11/15/2016.
 */

class OptimalSolver {
    static String INITIAL_LOCATION;
    static List<String> DESTINATIONS;

    SolutionClass initiate(Attraction attraction, double budget, List<String> destinations, boolean exhaustive) {
        DESTINATIONS = destinations;
        INITIAL_LOCATION = attraction.name;
        Log.d("TEST", "destinations = " + DESTINATIONS.toString());
        SolutionClass solution = new SolutionClass();
        solution.time = 999999999;

        double time = solve(attraction, budget, 0, "BEGIN", destinations.size(), solution, exhaustive);
        solution.time = time;
        solution.cost = (Math.ceil(budget*100 - solution.cost*100))/100;
        return solution;
    }

    double solve(Attraction attraction, double budget, double time, String route, int destination_left, SolutionClass solution, boolean exhaustive) {
        solution.tries++;
        if (destination_left == 0) {
            if (solution.cost <= budget && solution.time >= time) {
                solution.cost = budget;
                solution.route = route;
                solution.time = time;
            }
            return solution.time;
        } else {
            List<Edge> queue = attraction.edgeList;
            double bound = Integer.MAX_VALUE;
            for (Edge edge : queue) {
                if (route.contains(edge.attraction.name) || !DESTINATIONS.contains(edge.attraction.name)) {
                    continue;
                }
                if (edge.cost <= budget && edge.time < bound) {
                    if (destination_left > 1 && edge.attraction.name.equals(INITIAL_LOCATION)) {
                        continue;
                    }
                    if (destination_left == 1 && !edge.attraction.name.equals(INITIAL_LOCATION)) {
                        continue;
                    }
//                    Log.d("TEST", "EDGE NAME =" + edge.attraction.name + " INITIAL = " + INITIAL_LOCATION);

                    String computerizedRoute = route + "ROUTE"  + edge.attraction.name + "endROUTE COST" + edge.cost + "endCOST TIME" + edge.time + " minendTIME USING" + edge.transport + " endITEM\n";
                    if (destination_left == 1) {
                        computerizedRoute = route;
                    }
                    solution.end = edge.attraction.name;
                    double request_boundary;
                    if (destination_left != 1) {
                        request_boundary = solve(edge.attraction, budget - edge.cost, time + edge.time, computerizedRoute, destination_left - 1, solution, exhaustive);
                    } else {
                        request_boundary = solve(edge.attraction, budget, time, computerizedRoute, destination_left - 1, solution, exhaustive);
                    }
                    if (!exhaustive && bound > request_boundary) {
                        bound = request_boundary;
                    }
                }
            }
            return solution.time;
        }
    }
}

