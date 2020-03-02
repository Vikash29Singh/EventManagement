package com.example.eventmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {

    TextView home, payment, offer, order, logout, about, call, support;
    FirebaseAuth firebaseAuth;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();

        home = findViewById(R.id.home);
        payment = findViewById(R.id.payment);
        offer = findViewById(R.id.offer);
        order = findViewById(R.id.order);
        logout = findViewById(R.id.logout);
        about = findViewById(R.id.about);
        call = findViewById(R.id.call);
        support = findViewById(R.id.support);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Dashboard.class));

            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),AboutUs.class));
                finish();

            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_CALL);

                    i.setData(Uri.parse("tel:9435312345"));    // default dial number

                if (ActivityCompat.checkSelfPermission(Profile.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(Profile.this,"Please grant the permission to call",Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(Profile.this, new String[]{Manifest.permission.CALL_PHONE},1);
                }
                else {
                    startActivity(i);
                }

            }
        });

        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(Intent.ACTION_SEND);
                intent3.setData(Uri.parse("email"));
                String[] s={"email_us@gmail.com"};
                intent3.putExtra(Intent.EXTRA_EMAIL,s);
                intent3.putExtra(Intent.EXTRA_SUBJECT,"Please Enter the Subject");
                intent3.putExtra(Intent.EXTRA_TEXT,"Please Enter the Body of the Email");
                intent3.setType("message/rfc822");
                Intent chooser=Intent.createChooser(intent3,"Launch Email");
                startActivity(chooser);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                final Intent intent = new Intent(Profile.this, Login.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(Profile.this,Dashboard.class));
        finish();

    }

}
