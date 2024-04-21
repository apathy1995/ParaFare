package com.example.logintricyfare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyTripsRecyclerViewAdapter extends RecyclerView.Adapter<MyTripsRecyclerViewAdapter.MyViewHolder>{

    Context mContext;
    List<MyTrips> mMyTripsList;

    public MyTripsRecyclerViewAdapter (Context mContext, List<MyTrips> mMyTripsList){
        this.mContext = mContext;
        this.mMyTripsList = mMyTripsList;
    }


    @NonNull
    @Override
    public MyTripsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.mytrips_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyTripsRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.txtMyTripsRoutesName.setText(mMyTripsList.get(position).getMyTripsRoutesName());
        holder.txtMyTripsRoutesDistance.setText(mMyTripsList.get(position).getMyTripsRoutesDistance());
        holder.txtMyTripsRoutesPrice.setText(mMyTripsList.get(position).getMyTripsRoutesPrice());
        holder.imgMyTripsLocation.setImageResource(mMyTripsList.get(position).getMyTripsLocationIcon());

    }

    @Override
    public int getItemCount() {
        return mMyTripsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtMyTripsRoutesName;
        private TextView txtMyTripsRoutesDistance;
        private TextView txtMyTripsRoutesPrice;
        private ImageView imgMyTripsLocation;
        private CardView mainCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            txtMyTripsRoutesName = itemView.findViewById(R.id.myTrips_routesName);
            txtMyTripsRoutesDistance = itemView.findViewById(R.id.myTrips_routesDistance);
            txtMyTripsRoutesPrice = itemView.findViewById(R.id.myTrips_routesPrice);
            imgMyTripsLocation = itemView.findViewById(R.id.myTrips_location);
            mainCard = itemView.findViewById(R.id.mainCard);
        }
        @Override
        public void onClick(View v) {

        }
    }
}
