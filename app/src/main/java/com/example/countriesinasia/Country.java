package com.example.countriesinasia;

import java.util.ArrayList;

public class Country {

    String name;
    String capital;
    String region;
    String subregion;
    int population;
    String flag;
    ArrayList<String> borders;

    public ArrayList<String> getBorders() {
        return borders;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public int getPopulation() {
        return population;
    }

    public String getFlag() {
        return flag;
    }
}
