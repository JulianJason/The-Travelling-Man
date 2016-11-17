package com.lejit.thetravellingman.OptimalSolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 11/15/2016.
 */

public class Attraction {
    String name;
    List<Edge> edgeList = new ArrayList<Edge>();
    public Attraction(String name) {
        this.name = name;
    }

    public void addEdge(Attraction attraction, double time, double cost, MethodOfTransport transport){
        if(!attraction.name.equals(this.name)) {
            Edge newEdge = new Edge(attraction,transport,time,cost);
            System.out.println("Adding edge -> from " + this.name + " to " + attraction.name + " with time " + time + " and cost " + cost + " transport " + transport);
            sortEdge(newEdge);
        }
    }

    public void sortEdge(Edge edge) {
        boolean done = false;
        List<Edge> sortedList = new ArrayList<Edge>();
        if (edgeList.size() == 0) {
            edgeList.add(edge);
        } else {
            for (int i = 0; i < edgeList.size(); i++) {
                if (!done && edgeList.get(i).time > edge.time) {
                    sortedList.add(edge);
                    sortedList.add(edgeList.get(i));
                    done = true;
                } else {
                    sortedList.add(edgeList.get(i));
                }
            }
            if (!done) {
                sortedList.add(edge);
            }
            this.edgeList = sortedList;
        }
    }

    @Override
    public String toString() {
        return "Attraction " +
                "name='" + name;
    }


}
