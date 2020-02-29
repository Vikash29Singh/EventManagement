package com.example.eventmanager;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Dashboard_adapter extends RecyclerView.Adapter<Dashboard_adapter.MyViewHolder> {

    private ArrayList<Dashboard_item_model> modelist;
    private Context ctx;
    private ClickAdapterListener listener;
    SparseBooleanArray selectedItem;
    public static int currentSelectedIndex;



    public class MyViewHolder extends RecyclerView.ViewHolder {

       // public CookieHandler Picasso;
        TextView event_name, address, date, stime, center_name;
        ImageView imageView;
        ProgressBar progressBar;
        LinearLayout ll1;


        public MyViewHolder(View view) {
            super(view);

            event_name = (TextView) view.findViewById(R.id.event_name);
            address = (TextView) view.findViewById(R.id.address);
            date = (TextView) view.findViewById(R.id.time);
            stime = view.findViewById(R.id.stime);
            imageView = view.findViewById(R.id.imageView);

            //center_name = view.findViewById(R.id.center_name);
            //progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

            ll1 = view.findViewById(R.id.ll1);

            //view.setOnClickListener(this);

        }

       /* @Override
        public void onClick(View v) {
        }*/

    }

    public Dashboard_adapter(Context ctx, ArrayList<Dashboard_item_model> modelist, ClickAdapterListener listener) {
        this.ctx = ctx;
        this.modelist = modelist;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull android.view.ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_list_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Dashboard_item_model model = modelist.get(position);

        holder.event_name.setText(model.getEvent_name()+"\n"+model.getCenter_name());
        //String var = model.getI_alarm_weight() + " KG";

        holder.address.setText(model.getAddress());
        holder.date.setText(model.getDate());
        holder.stime.setText(model.getStime()+" Onwards");
        Picasso.get().load(model.getImageView()).into(holder.imageView);
        //holder.center_name.setText(model.getCenter_name());
        // holder.progressBar.setProgress(model.getLess_weight() * 20);

        applyClickEvents(holder, position);

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

    private void applyClickEvents(MyViewHolder holder, final int position) {
        holder.ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRowClicked(position, view);
            }
        });
    }

    public interface ClickAdapterListener {

        void onRowClicked(int position, View v);
    }

    }

