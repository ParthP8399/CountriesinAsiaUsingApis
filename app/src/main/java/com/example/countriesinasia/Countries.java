package com.example.countriesinasia;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Countries implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo
    String name;

    @ColumnInfo
    String flag_image;

    @ColumnInfo
    String capital;

    @ColumnInfo
    String region;

    @ColumnInfo
    String subregion;

    @ColumnInfo
    int population;




    //ArrayList<String> borders;

//    public ArrayList<String> getBorders() {
//        return borders;
//    }
//
//    public void setBorders(ArrayList<String> borders) {
//        this.borders = borders;
//    }

   // ArrayList<String> languages;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFlag_image(String flag_image) {
        this.flag_image = flag_image;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public void setPopulation(int population) {
        this.population = population;
    }




    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFlag_image() {
        return flag_image;
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


//    public ArrayList<String> getLanguages() {
//        return languages;
//    }
//
//    public void setLanguages(ArrayList<String> languages) {
//        this.languages = languages;
//    }
}
