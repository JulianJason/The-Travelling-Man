package com.lejit.thetravellingman.OptimalSolver;

import java.util.List;

/**
 * Created by USER on 11/15/2016.
 */

public class OptimalSolver {
    static String INITIAL_LOCATION;
    static List<String> DESTINATIONS;

    public static SolutionClass initiate(Attraction attraction, double budget, List<String> destinations) {
        DESTINATIONS = destinations;
        INITIAL_LOCATION = attraction.name;
        SolutionClass solution = new SolutionClass();
        solution.time = 999999999;
        double time = solve(attraction, budget, 0, "Start", destinations.size(), solution);
        solution.time = time;
        return solution;
    }

    public static double solve(Attraction attraction, double budget, double time, String route, int destination_left, SolutionClass solution) {

        solution.tries++;
        if (destination_left == 0) {
            if (solution.cost <= budget && solution.time >= time) {
                solution.cost = budget;
                solution.route = route + "\n end of route!";
                solution.time = time;
            }
            return solution.time;
        } else {
            List<Edge> queue = attraction.edgeList;
            double bound = Integer.MAX_VALUE;
            for (Edge e : queue) {
                if (route.contains(e.attraction.name) || !DESTINATIONS.contains(e.attraction.name)) { //No repeating destination
                    continue;
                }
                if (e.cost <= budget && e.time < bound) {
                    if (destination_left > 1 && e.attraction.name.equals(INITIAL_LOCATION)) {
                        continue;
                    }
                    if (destination_left == 1 && !e.attraction.name.equals(INITIAL_LOCATION)) { // Go back to starting point (MBS)
                        continue;
                    }
                    String newRoute = route + " to " + e.attraction.name + " cost $" + e.cost + ", time " + e.time + "min using " + e.transport + "\n";
                    double request_boundary = solve(e.attraction, budget - e.cost, time + e.time, newRoute, destination_left - 1, solution);
                    if (bound > request_boundary) {
                        bound = request_boundary;
                    }
                }
            }
            return solution.time;
        }
    }
}

