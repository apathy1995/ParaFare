package com.example.logintricyfare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DriversRecyclerViewAdapter extends RecyclerView.Adapter<DriversRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<DriversInformation> mDriversList;

    public DriversRecyclerViewAdapter (Context mContext, List<DriversInformation> mDriversList){
        this.mContext = mContext;
        this.mDriversList = mDriversList;

    }

    @NonNull
    @Override
    public DriversRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.drivers_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DriversRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.txtDriversName.setText(mDriversList.get(position).getDriversName());
        holder.imgTricycle.setImageResource(mDriversList.get(position).getTricycleIcon());

    }

    @Override
    public int getItemCount() {
        return mDriversList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtDriversName;
        private ImageView imgTricycle;
        private CardView driversMainCard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            txtDriversName = itemView.findViewById(R.id.driversName);
            imgTricycle = itemView.findViewById(R.id.tricycleIcon);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
