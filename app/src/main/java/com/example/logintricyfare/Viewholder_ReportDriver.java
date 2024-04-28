package com.example.logintricyfare;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class Viewholder_ReportDriver extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView rdtime_result, repordriverName_result, reportDriverIssue_result, reportBodyNumber_result;


public Viewholder_ReportDriver(@NonNull View itemView) {
        super(itemView);

        }

public void setitem(FragmentActivity activity, String reportDriverName, String reportDriverIssue, String reportBodyNumber,
                    String fname, String key, String email, String phonenumber, String rdtime, String imgreportdriver){

        imageView = itemView.findViewById(R.id.imgreportdriver);
        rdtime_result = itemView.findViewById(R.id.current_Date);
        repordriverName_result = itemView.findViewById(R.id.reportDriverName);
        reportDriverIssue_result = itemView.findViewById(R.id.report_issue);
        reportBodyNumber_result = itemView.findViewById(R.id.rd_bodynumber);

        Picasso.get().load(imgreportdriver).into(imageView);
        rdtime_result.setText(rdtime);
        repordriverName_result.setText(reportDriverName);
        reportDriverIssue_result.setText(reportDriverIssue);
        reportBodyNumber_result.setText(reportBodyNumber);

        }
}

