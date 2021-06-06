package com.firestudio.projectasia.adapter;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.firestudio.projectasia.R;
import com.firestudio.projectasia.models.Example;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideApp;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class AsiaCountryAdapter extends RecyclerView.Adapter<AsiaCountryAdapter.AsiaCountryHolder> {
    List<Example> asiaCountryList;
    Context context;


    public AsiaCountryAdapter(List<Example> asiaCountryList, Context context) {
        this.asiaCountryList = asiaCountryList;
        this.context = context;
    }

    @NonNull
    @Override
    public AsiaCountryAdapter.AsiaCountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.countary_card_design, parent, false);
        return new AsiaCountryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsiaCountryAdapter.AsiaCountryHolder holder, int position) {
        Example example = asiaCountryList.get(position);
        holder.countryName.setText("Country: "+example.getName());
        holder.countryCapital.setText("Capital: "+example.getCapital());
        holder.countryPopulation.setText("Population: "+example.getPopulation().toString());
       GlideApp.with(context).load(example.getFlag()).into(holder.countryFlagImage);


    }

    @Override
    public int getItemCount() {
        return asiaCountryList.size();
    }

    public  void getAllCountry(List<Example>examples){
        asiaCountryList=examples;
    }

    public class AsiaCountryHolder extends RecyclerView.ViewHolder {
        CircleImageView countryFlagImage;
        TextView countryName, countryCapital, countryPopulation;

        public AsiaCountryHolder(@NonNull View itemView) {
            super(itemView);
            countryFlagImage = itemView.findViewById(R.id.asia_country_flag);
            countryName = itemView.findViewById(R.id.country_name);
            countryCapital = itemView.findViewById(R.id.country_capital);
            countryPopulation = itemView.findViewById(R.id.country_population);


        }
    }
}
