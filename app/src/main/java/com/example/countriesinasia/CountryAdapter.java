package com.example.countriesinasia;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgDrawableTranscoder;
import com.ahmadrosid.svgloader.SvgLoader;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

private Context context;
    private List<Example> items;
    private Activity mactivity;

    public CountryAdapter(Context context,List<Example> items, Activity activity) {
        this.context = context;
        this.items=items;
        this.mactivity=activity;


    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.post_countriesdata, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
      Example example=items.get(position);
        holder.ecountry_name.setText(example.getName());
        holder.ecapital.setText(example.getCapital());
        holder.eregion.setText(example.getRegion());
        holder.esubregion.setText(example.getSubregion());
        holder.epopulation.setText(String.valueOf(example.getPopulation()));
        String temp="";
        //ArrayList<List> temp1;
        for(String i:example.getBorders()){
            temp+=i+",";
        }
        holder.eborders.setText(temp.toString());

        String temp2="";
        for(Language language:example.getLanguages()){
                temp2+=language.getName()+",";
        }

        holder.elanuages.setText(temp2);















//        Picasso.get()
//                .load("https://restcountries.eu/data/afg.svg")
//                .into(holder.eflag);

        String url=example.getFlag();
        Utils.fetchSvg(mactivity, url,holder.eflag);


////        SvgLoader.pluck()
////                .with(mactivity) // ur activity
////                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)//use ur place holder
////                .load(country.flag,holder.eflag);
//        Glide.with(context)
//                .load(String.valueOf(country.flag))
//                .into(holder.eflag);





    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class CountryViewHolder extends RecyclerView.ViewHolder{
        ImageView eflag;
        TextView ecountry_name;
        TextView ecapital;
        TextView eregion;
        TextView esubregion;
        TextView epopulation;
        TextView eborders;
        TextView elanuages;
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            ecountry_name=itemView.findViewById(R.id.name_country);
            ecapital=itemView.findViewById(R.id.capital_id);
            eregion=itemView.findViewById(R.id.region_id);
            esubregion=itemView.findViewById(R.id.subregion_id);
            epopulation=itemView.findViewById(R.id.population_id);
            eflag=(ImageView) itemView.findViewById(R.id.flag_image);
            eborders=itemView.findViewById(R.id.borders_id);
            elanuages=itemView.findViewById(R.id.languages_id);

        }
    }

}
