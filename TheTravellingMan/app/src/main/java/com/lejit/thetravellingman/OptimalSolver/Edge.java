package com.lejit.thetravellingman.OptimalSolver;

/**
 * Created by USER on 11/15/2016.
 */

class Edge {
    double time;
    double cost;
    MethodOfTransport transport;
    Attraction attraction;

    Edge (Attraction attraction, MethodOfTransport transport, double time, double cost){
        this.time = time;
        this.cost = cost;
        this.attraction = attraction;
        this.transport = transport;
    }
}

