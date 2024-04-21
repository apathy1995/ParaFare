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
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Routes> mRoutesList;

    AlertDialog alertDialog;


    public RecyclerViewAdapter (Context mContext, List<Routes> mRoutesList){
        this.mContext = mContext;
        this.mRoutesList = mRoutesList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.txtRoutesName.setText(mRoutesList.get(position).getRoutesName());
        holder.txtRoutesDistance.setText(mRoutesList.get(position).getRoutesDistance());
        holder.txtRoutesPrice.setText(mRoutesList.get(position).getRoutesPrice());
        holder.imgLocation.setImageResource(mRoutesList.get(position).getLocationIcon());
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
        return mRoutesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtRoutesName;
        private TextView txtRoutesDistance;
        private TextView txtRoutesPrice;
        private ImageView imgLocation;
        private Button btnOnlinePayment;
        private CardView mainCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            txtRoutesName = itemView.findViewById(R.id.routesName);
            txtRoutesDistance = itemView.findViewById(R.id.routesDistance);
            txtRoutesPrice = itemView.findViewById(R.id.routesPrice);
            imgLocation = itemView.findViewById(R.id.location);
            mainCard = itemView.findViewById(R.id.mainCard);
            btnOnlinePayment = itemView.findViewById(R.id.online_payment);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
