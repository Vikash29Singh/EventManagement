package com.example.eventmanager;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Events extends Fragment implements OnBackPressed {

    View v;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Dashboard_item_model> modelist;
    private Dashboard_adapter dashboard_adapter;

    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.events, container, false);


        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = v.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        databaseReference = FirebaseDatabase.getInstance().getReference("date");

        modelist = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                modelist.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Dashboard_item_model modl = dataSnapshot1.getValue(Dashboard_item_model.class);
                    modelist.add(modl);
                }

                dashboard_adapter = new Dashboard_adapter(getActivity(), modelist);
                recyclerView.setAdapter(dashboard_adapter);

                /*ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT , Dashboard_Frag1.this);
                new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);


                ItemTouchHelper.SimpleCallback itemTouchHelperCallback1 = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        // Row is swiped from recycler view
                        // remove it from adapter
                    }

                    @Override
                    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                    }
                };

                // attaching the touch helper to recycler view
                new ItemTouchHelper(itemTouchHelperCallback1).attachToRecyclerView(recyclerView);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Error....!!", Toast.LENGTH_SHORT).show();
            }
        });

        return v;

    }

    public void onBackPressed() {}
}
