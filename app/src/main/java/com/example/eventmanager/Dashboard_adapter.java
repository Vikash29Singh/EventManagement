package com.example.eventmanager;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Dashboard_adapter extends RecyclerView.Adapter<Dashboard_adapter.MyViewHolder> {

    private ArrayList<Dashboard_item_model> modelist;
    private Context ctx;
    SparseBooleanArray selectedItem;
    public static int currentSelectedIndex;

    public Dashboard_adapter(Context ctx, ArrayList<Dashboard_item_model> modelist) {
        this.ctx = ctx;
        this.modelist = modelist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView event_name, address, date, stime, etime;
        ProgressBar progressBar;
        LinearLayout ll1;

        public MyViewHolder(View view) {
            super(view);

            event_name = (TextView) view.findViewById(R.id.event_name);
            address = (TextView) view.findViewById(R.id.address);
            date = (TextView) view.findViewById(R.id.time);
            stime = view.findViewById(R.id.stime);
            //etime = view.findViewById(R.id.etime);
            //progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

            ll1 = view.findViewById(R.id.ll1);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
        }

    }

    @NonNull
    @java.lang.Override
    public MyViewHolder onCreateViewHolder(@NonNull android.view.ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_list_row, parent, false);
        return new MyViewHolder(view);
    }

    @java.lang.Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Dashboard_item_model model = modelist.get(position);
        holder.event_name.setText(model.getEvent_name()+"/n"+model.getCname());
        //String var = model.getI_alarm_weight() + " KG";
        holder.address.setText(model.getAddress());
        holder.date.setText(model.getDate());
        holder.stime.setText(model.getStime()+" Onwards");
        //holder.etime.setText(model.getCname());
        // holder.progressBar.setProgress(model.getLess_weight() * 20);
    }

    @java.lang.Override
    public int getItemCount() {
        return modelist.size();
    }

/*
    public void removeItem(int position) {
        modelist.remove(position);
        notifyItemRemoved(position);
    }*/

}
