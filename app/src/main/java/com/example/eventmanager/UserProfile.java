package com.example.eventmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

class UserProfile extends Fragment {

    View view;
    TextView home, payment, offer, order, logout;
    FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.userprofile, container, false);

        //firebase connectivity
        firebaseAuth = FirebaseAuth.getInstance();


        home = view.findViewById(R.id.home);
        payment = view.findViewById(R.id.payment);
        offer = view.findViewById(R.id.offer);
        order = view.findViewById(R.id.order);
        logout = view.findViewById(R.id.logout);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(getActivity(), Events.class);
                startActivity(intent);*/
                //Fragment fragment = new tasks();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new Events());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                final Intent intent = new Intent(getActivity(), Login.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return view;
    }
}
