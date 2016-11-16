package com.lejit.thetravellingman.OptimalSolver;

/**
 * Created by USER on 11/15/2016.
 */

public class SolutionClass {
    public double cost = -1;
    public double time = -1;
    public int tries = 0;
    public String start;
    public String route;

    public SolutionClass(double cost, double time, int tries, String start, String route) {
        this.cost = cost;
        this.time = time;
        this.tries = tries;
        this.start = start;
        this.route = route;
    }

    public SolutionClass() {
    }

}
