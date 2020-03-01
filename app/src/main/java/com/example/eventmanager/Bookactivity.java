package com.example.eventmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Bookactivity extends AppCompatActivity {

    // private BroadcastReceiver MyReceiver = null;
    TextView tv, tv1;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookactivity);
        tv = findViewById(R.id.event_name);
        String id = getIntent().getExtras().getString("event_name");
        tv.setText(id);



        databaseReference = FirebaseDatabase.getInstance().getReference().child("date");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // String centername = dataSnapshot.child("center_name").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error....!!", Toast.LENGTH_SHORT).show();
            }
        });

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


}
