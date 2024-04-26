package com.example.logintricyfare;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class Viewholder_MyTrips extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView time_result, routesname_result, routesdistance_result, routesprice_result;


    public Viewholder_MyTrips(@NonNull View itemView) {
        super(itemView);

    }

        public void setitem(FragmentActivity activity, String routesName, String numberOfPerson,String routesPrice,
                String fname, String key,String email,String phonenumber,String time, String locationIcon){

            imageView = itemView.findViewById(R.id.myTrips_location);
            time_result = itemView.findViewById(R.id.currentDate);
            routesname_result = itemView.findViewById(R.id.myTrips_routesName);
            routesdistance_result = itemView.findViewById(R.id.myTrips_routesDistance);
            routesprice_result = itemView.findViewById(R.id.myTrips_routesPrice);

            Picasso.get().load(locationIcon).into(imageView);
            time_result.setText(time);
            routesname_result.setText(routesName);
            routesdistance_result.setText(numberOfPerson);
            routesprice_result.setText(routesPrice);

        }
    }

