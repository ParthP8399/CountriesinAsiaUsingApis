package com.example.countriesinasia;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Countries.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CountriesDao countriesDao();
}
