package com.example.countriesinasia;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountriesDao {
    @Query("SELECT * FROM Countries")
    List<Countries> getAll();

    @Insert
    void insert(Countries countries);
}

