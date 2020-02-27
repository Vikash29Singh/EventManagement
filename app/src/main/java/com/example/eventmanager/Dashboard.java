package com.example.eventmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class Dashboard extends AppCompatActivity {

    Toolbar toolbar;
    Session session;
    private BroadcastReceiver MyReceiver = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_dashboard);


        // Session class instance
        session = new Session(getApplicationContext());


        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        session.checkLogin();

       /* // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(session.KEY_NAME);*/

        //check connectivity
        MyReceiver = new MyReceiver();


        //check status of internet
        broadcastIntent();


        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Events()).commit();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.home:
                            selectedFragment = new Events();
                            break;
                        case R.id.profile:
                            session.logoutUser();

                            //alert.showAlertDialog(LoginActivity.this, "Login failed..", "Please enter username and password", false);
                            Toast.makeText(Dashboard.this, "Logged out", Toast.LENGTH_SHORT).show();
                            //selectedFragment = new Train_book();
                            break;
                        /*case R.id.bus:
                            selectedFragment = new Bus_book();
                            break;
                        case R.id.car:
                            selectedFragment = new Car_book();
                            break;*/
                    }

                    return true;
                }
            };

    public void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

}
