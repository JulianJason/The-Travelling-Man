package com.lejit.thetravellingman;
import java.util.ArrayList;
import java.util.Collections;

import static com.lejit.thetravellingman.DestinationMatrix.costArray;
import static com.lejit.thetravellingman.DestinationMatrix.destination_matrix;
import static com.lejit.thetravellingman.DestinationMatrix.timeArray;

public class ExhaustiveEnumeration {
    ArrayList<ArrayList<String>> possibleRoutes = new ArrayList<>();
    ArrayList<Path> possiblePaths = new ArrayList<>();

    double getCost(ArrayList<ArrayList<Object>> path) {
        double cost = costArray[(int) path.get(0).get(0)][0][destination_matrix.indexOf(path.get(0).get(1))];
        for (int i = 1; i < path.size(); i++) {
            cost += costArray[(int) path.get(i).get(0)][destination_matrix.indexOf(path.get(i - 1).get(1))][destination_matrix.indexOf(path.get(i).get(1))];
        }
        return cost;
    }

    double getTime(ArrayList<ArrayList<Object>> path) {
        double time = timeArray[(int) path.get(0).get(0)][0][destination_matrix.indexOf(path.get(0).get(1))];
        for (int i = 1; i < path.size(); i++) {
            time += timeArray[(int) path.get(i).get(0)][destination_matrix.indexOf(path.get(i - 1).get(1))][destination_matrix.indexOf(path.get(i).get(1))];
        }
        return time;
    }

    Path findOptimalPath(double budget) {
        if (this.getCost(possiblePaths.get(0).path) <= budget) {
            return this.possiblePaths.get(0);
        } else {
            this.possiblePaths.remove(0);
            return findOptimalPath(budget);
        }
    }

    void enumerate(ArrayList<String> attractionList, int pointer) {
        if (pointer == attractionList.size()) {
            possibleRoutes.add(attractionList);
        } else {
            for (int i = pointer; i < attractionList.size(); i++) {
                ArrayList<String> enumeration = (ArrayList<String>) attractionList.clone();
                enumeration.set(pointer, attractionList.get(i));
                enumeration.set(i, attractionList.get(pointer));
                enumerate(enumeration, pointer + 1);
            }
        }
    }

    void enumerate(int n) {
        ArrayList<String> enumeration = new ArrayList<>();
        for (int i = 0; i < Math.pow(3, n); i++) {
            String s = Integer.toString(i, 3);
            while (s.length() < n) {
                s = "0" + s;
            }
            enumeration.add(s);
        }
        for (ArrayList<String> route : this.possibleRoutes) {
            for (String transportMode : enumeration) {
                ArrayList<ArrayList<Object>> path = new ArrayList<>();
                char[] transportModes = transportMode.toCharArray();
                for (int i = 0; i < n; i++) {
                    ArrayList<Object> transportToAttraction = new ArrayList<>();
                    transportToAttraction.add(Integer.valueOf(transportModes[i]));
                    transportToAttraction.add(route.get(i));
                    path.add(transportToAttraction);
                }
                Path p = new Path(path);
                possiblePaths.add(p);
            }
        }
        Collections.sort(this.possiblePaths);
    }
}

class Path implements Comparable<Path> {
    final ArrayList<ArrayList<Object>> path;

    Path(ArrayList<ArrayList<Object>> path) {
        this.path = path;
    }

    public int compareTo(Path p) {
        ExhaustiveEnumeration enumeration = new ExhaustiveEnumeration();
        if (enumeration.getTime(this.path) > enumeration.getTime(p.path)) {
            return 1;
        } else if (enumeration.getTime(p.path) < enumeration.getTime(this.path)) {
            return -1;
        }
        return 0;
    }
}
