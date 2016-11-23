package com.lejit.thetravellingman.Model;

/**
 * Created by USER on 11/20/2016.
 */

public class ItineraryRow {
    public String from;
    public String to;
    public String time;
    public String cost;
    public String method;

    public ItineraryRow() {
    }

    public ItineraryRow(String from, String to, String time, String cost, String method) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.cost = cost;
        this.method = method;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "ItineraryRow{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", time='" + time + '\'' +
                ", cost='" + cost + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
