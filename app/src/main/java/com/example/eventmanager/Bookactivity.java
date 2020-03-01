package com.example.eventmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Bookactivity extends AppCompatActivity {

    // private BroadcastReceiver MyReceiver = null;
    TextView tv, center_name;
    DatabaseReference databaseReference;
    ImageView imageView;
    String event_name, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookactivity);
        tv = findViewById(R.id.event_name);
        center_name = findViewById(R.id.center_name);

        imageView = findViewById(R.id.imageView);
        event_name = getIntent().getExtras().getString("event_name");
        /*String center_name1 = getIntent().getExtras().getString("center_name");
        String img =  getIntent().getExtras().getString("imageView");
        Picasso.get().load(img).into(imageView);*/
        //tv.setText(event_name);
        //center_name.setText(center_name1);

        databaseReference = FirebaseDatabase.getInstance().getReference("date");
        /*Query query= databaseReference.child(event_name)*/



      /* // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       // String current_uid = user.getUid(); // user.getUid() will return null if you are not log in
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        Query query = db.child("Leads").child("Generated").orderByChild("userid").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // do something

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });*/

        /*MyReceiver = new MyReceiver();

        broadcastIntent();
*/
    }


    /*public void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(MyReceiver);
    }
*/


    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.orderByChild("event_name").equalTo(event_name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //image = dataSnapshot.child("imageView").getValue().toString();
                // Picasso.get().load(image).into(imageView);
                //Picasso.get().load(model.getImageView()).into(holder.imageView);
               /*String center_name1 = dataSnapshot.child("center_name").getValue().toString();
               center_name.setText(center_name1);
              */  /* image = dataSnapshot.getValue(String.class);
                Picasso.get().load(image).into(imageView);*/
                //Dashboard_item_model model = dataSnapshot.getValue(Dashboard_item_model.class);
                String center_name1 = dataSnapshot.child("center_name").getValue(String.class);
                center_name.setText(center_name1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error....!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
