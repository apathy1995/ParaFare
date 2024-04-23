package com.example.logintricyfare;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends Fragment {

    Button confirmButton;

    Button cancelButton;

    public LogoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logout, container, false);

        // Handle click on confirm button
        confirmButton = view.findViewById(R.id.btn_confirm);
        cancelButton = view.findViewById(R.id.btn_cancel);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call logout method or perform logout action
                logout();
            }
        });

        // Handle click on cancel button

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the dialog or navigate back
                getParentFragmentManager().popBackStack();
            }
        });

        return view;
    }

    // Method to perform logout action
    private void logout() {
        // Implement your logout logic here
        // For example, sign out the user and navigate back to the login screen
        FirebaseAuth.getInstance().signOut();
        Intent loginActivity = new Intent(requireContext(), LoginActivity.class);
        startActivity(loginActivity);
        requireActivity().finish();
    }
}