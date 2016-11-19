package com.lejit.thetravellingman.ExhaustiveEnumeration;

import java.util.ArrayList;

/**
 * Created by USER on 11/17/2016.
 */

public class Path implements Comparable<Path> {
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
