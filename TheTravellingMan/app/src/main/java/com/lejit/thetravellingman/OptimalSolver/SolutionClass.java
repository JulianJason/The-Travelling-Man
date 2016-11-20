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
    public String end;
    public SolutionClass(double cost, double time, int tries, String start, String route) {
        this.cost = cost;
        this.time = time;
        this.tries = tries;
        this.start = start;
        this.route = route;

    }

    public SolutionClass() {
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
