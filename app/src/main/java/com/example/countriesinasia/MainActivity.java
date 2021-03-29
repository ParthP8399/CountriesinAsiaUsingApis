package com.example.countriesinasia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    List<Example> country_list;
    ArrayList<Language> languages;
    private ArrayList<Example> arrayList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.postlist_countries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting() && arrayList != null) {
            fetchfromServer();
        } else {


            fetchfromRoom();
        }








    }

    private void fetchfromRoom() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                List<Countries> countrylist = DatabaseClient.getInstance(MainActivity.this).getAppDatabase().countriesDao().getAll();
               arrayList.clear();
                for (Countries p1: countrylist) {
                    Example example = new Example(p1.getId(),p1.getName(),
                            p1.getCapital(),
                            p1.getFlag_image(),
                    p1.getRegion(),
                    p1.getSubregion(),
                    p1.getPopulation());
//                    p1.getBorders(),
//                    p1.getLanguages());
                    arrayList.add(example);
                }
                // refreshing recycler view
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // adapter.notifyDataSetChanged();
                    }
                });
            }
        });
        thread.start();

    }

    private void fetchfromServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JSONPlaceHolder jsonPlaceHolder=retrofit.create(JSONPlaceHolder.class);
        // Call<List<Country>> call=jsonPlaceHolder.getPosts();
        Call<List<Example>> call=jsonPlaceHolder.getPosts();
        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response Code: ", String.valueOf(response.code()));
                    return;
                }

                List<Example> countries=response.body();
                country_list=countries;

                recyclerView.setAdapter(new CountryAdapter(getApplicationContext(), countries,MainActivity.this));




                saveTask();

            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Log.e("Code: ", t.getMessage());
            }
        });
    }

    private void saveTask() {

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task

                for (int i = 0; i < country_list.size(); i++) {
                    Countries c1= new Countries();
                    c1.setName(country_list.get(i).getName());
                    c1.setCapital(country_list.get(i).getCapital());
                    c1.setRegion(country_list.get(i).getRegion());
                    c1.setSubregion(country_list.get(i).getSubregion());
                   // c1.setBorders(country_list.get(i).setBorders());
                    //c1.setFlag_image(country_list.get(i).setFlag());
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().countriesDao().insert(c1);
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

}


