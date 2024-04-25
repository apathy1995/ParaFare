package com.example.logintricyfare;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;


import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DriversRecyclerViewAdapter extends RecyclerView.Adapter<DriversRecyclerViewAdapter.ViewHolder> {

    private ArrayList<DriversInformation> driversList;
    private Context mContext;

    public DriversRecyclerViewAdapter(ArrayList<DriversInformation> driversList, Context mContext) {
        this.driversList = driversList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.drivers_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d("Adapter", "onBindViewHolder called for position: " + position);

        DriversInformation model =  driversList.get(position);
        holder.txtDriversName.setText(model.getFullname());
        holder.txtDriversEmail.setText(model.getEmail());
        holder.txtDriversBodyNumber.setText(model.getBodynumber());
        holder.txtDriversPhoneNumber.setText(model.getPhonenumber());
        Picasso.get().load(model.getImage()).placeholder(R.drawable.profile_bg)
                .into(holder.imgDriversProfile);

    }

    @Override
    public int getItemCount() {
        return driversList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtDriversName, txtDriversEmail, txtDriversBodyNumber, txtDriversPhoneNumber;
        CircleImageView imgDriversProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgDriversProfile = itemView.findViewById(R.id.driversProfile);
            txtDriversName = itemView.findViewById(R.id.driversName);
            txtDriversBodyNumber = itemView.findViewById(R.id.driver_bodynumber);
            txtDriversEmail= itemView.findViewById(R.id.driversEmail);
            txtDriversPhoneNumber = itemView.findViewById(R.id.driversPhoneNumber);
        }
    }


}

