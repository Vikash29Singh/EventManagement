package com.example.eventmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText password, useremail;
    Button btn_login;
    TextView registraion;
    ProgressDialog progressBar;
    int count = 0, count1 = 3;

    AlertDialog.Builder builder;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private ProgressDialog mProgress;
    //Session session;
    private BroadcastReceiver MyReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //check connectivity
        MyReceiver = new MyReceiver();

        //firebase connectivity
        firebaseAuth = FirebaseAuth.getInstance();

        useremail = (EditText) findViewById(R.id.useremail);
        password = (EditText) findViewById(R.id.Password);

        btn_login = (Button) findViewById(R.id.btn_login);
        registraion  = findViewById(R.id.btn_registration);
        registraion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Registration.class));
            }
        });


        // Session Manager
        //session = new Session(getApplicationContext());


        // final ProgressBar simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(getApplicationContext(), "User logged in ", Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(getApplicationContext(), Dashboard.class);
                    startActivity(I);
                } else {
                    Toast.makeText(getApplicationContext(), "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };
        //check status of internet
        broadcastIntent();

        //progress bar
        mProgress = new ProgressDialog(Login.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        mProgress.setIndeterminate(true);
        //mProgress.setTitle("Processing...");
        mProgress.setMessage("Authenticating");
        mProgress.setCancelable(false);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgress.show();
                final String userEmail = useremail.getText().toString();
                String userPaswd = password.getText().toString();
                //_btn_login.setEnabled(false);


                if (userEmail.isEmpty()) {
                    useremail.setError("Provide your Email first!");
                    useremail.requestFocus();
                } else if (userPaswd.isEmpty()) {
                    password.setError("Enter Password!");
                    password.requestFocus();
                } else if (userEmail.isEmpty() && userPaswd.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(userEmail.isEmpty() && userPaswd.isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(userEmail, userPaswd).addOnCompleteListener(Login.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                mProgress.dismiss();
                                //Toast.makeText(getApplicationContext(), "Not sucessfull", Toast.LENGTH_SHORT).show();
                                //check status of internet
                                broadcastIntent();
                                builder = new AlertDialog.Builder(Login.this);
                                builder.setTitle("Alert")
                                        .setIcon(R.drawable.alerticon)
                                        .setMessage("Wrong Credential")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                password.setText("");
                                              /*  useremail.getText().toString();
                                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                                finish();
                                                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);*/
                              /* finish();


                                finish();*/

                                               /* FirebaseAuth.getInstance().signOut();
                                                Intent I = new Intent(Login.this, Login.class);
                                                startActivity(I);
                                                //System.exit(0);
                                                finish();*/

                                            }
                                        });
                                //authentication();
                                AlertDialog alertDialog1 = builder.create();
                                alertDialog1.show();

                                Toast.makeText(getApplicationContext(), "Not sucessfull", Toast.LENGTH_SHORT).show();
                            } else {

                                mProgress.dismiss();
                                //authentication();
                                // FirebaseAuth.getInstance().signOut();

                                String user_email = useremail.getText().toString();
                                // Creating user login session
                                // For testing i am stroing name, email as follow
                                // Use user real data
                                //session.createLoginSession(user_email);
                                Intent i = new Intent(getApplicationContext(), Dashboard.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();
                                overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(MyReceiver);

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}


