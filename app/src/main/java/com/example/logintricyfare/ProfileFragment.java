package com.example.logintricyfare;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ProfileFragment extends Fragment implements View.OnClickListener{

    TextView fullname, username, email, contact_num;

    private TabLayout tabLayout;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);


        tabLayout = requireActivity().findViewById(R.id.tab_layout);
        tabLayout.setVisibility(View.GONE);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fullname = getActivity().findViewById(R.id.user_fullname);
        username = getActivity().findViewById(R.id.user_username);
        email = getActivity().findViewById(R.id.user_email);
        contact_num = getActivity().findViewById(R.id.user_contactnumber);


    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserId = user.getUid();
        DocumentReference reference;
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        reference = firestore.collection("users").document(currentUserId);

        reference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.getResult().exists()){

                            String fullnameResult = task.getResult().getString("fname");
                            String nameResult = task.getResult().getString("username");
                            String emailResult = task.getResult().getString("email");
                            String contactResult = task.getResult().getString("phoneNumber");

                            fullname.setText(fullnameResult);
                            username.setText(nameResult);
                            email.setText(emailResult);
                            contact_num.setText(contactResult);

                        }
                    }
                });


    }
}
