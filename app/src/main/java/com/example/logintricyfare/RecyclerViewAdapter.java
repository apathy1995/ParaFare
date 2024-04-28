package com.example.logintricyfare;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Route;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Routes> routesList;

    AlertDialog alertDialog;


    public RecyclerViewAdapter (List<Routes> routesList, Context mContext){
        this.mContext = mContext;
        this.routesList = routesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new RecyclerViewAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Routes model = routesList.get(position);
        holder.txtRoutesName.setText(model.getRoutesName());
        holder.txtRoutesDistance.setText(model.getRoutesDistance());
        holder.txtRoutesPrice.setText(model.getRoutesPrice());
        Picasso.get().load(model.getImageLoc()).placeholder(R.drawable.location_icon)
                .into(holder.imgLocation);
        holder.btnOnlinePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.custom_dialog,null);
                TextView paymentTitle;
                Button payNow;
                Button payLater;
                paymentTitle = dialogView.findViewById(R.id.paymentTitle);
                payNow = dialogView.findViewById(R.id.PayNow);
                payLater = dialogView.findViewById(R.id.PayLater);
                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();


                payNow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = v.getContext().getPackageManager().getLaunchIntentForPackage("com.gcash");
                        if (intent != null) {
                            v.getContext().startActivity(intent);
                        } else {
                            // Handle case where app is not installed
                            Toast.makeText(v.getContext(), "GCASH is not installed", Toast.LENGTH_SHORT).show();
                        }
                        // Dismiss the AlertDialog after launching the app
                        alertDialog.dismiss();
                    }
                });
                payLater.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return routesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtRoutesName, txtRoutesDistance, txtRoutesPrice;
        CircleImageView imgLocation;
        private Button btnOnlinePayment;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtRoutesName = itemView.findViewById(R.id.routesName);
            txtRoutesDistance = itemView.findViewById(R.id.routesDistance);
            txtRoutesPrice = itemView.findViewById(R.id.routesPrice);
            imgLocation = itemView.findViewById(R.id.location);
            btnOnlinePayment = itemView.findViewById(R.id.online_payment);
        }

    }
}
