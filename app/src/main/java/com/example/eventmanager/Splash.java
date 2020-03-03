package com.example.eventmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class Splash extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        text = findViewById(R.id.text);
        /* Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);*/

        Animation myanim1 = AnimationUtils.loadAnimation(this, R.anim.zoomout);

        //imageView.setAnimation(myanim);
        text.setAnimation(myanim1);


        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {


                    final Intent intent = new Intent(Splash.this, Login.class);
                    Thread timer = new Thread() {
                        public void run() {
                            try {
                                sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                startActivity(intent);
                                finish();
                            }
                        }
                    };
                    timer.start();

                    /*Intent I = new Intent(getApplicationContext(), Login.class);
                    startActivity(I);*/
                } else {
                    final Intent intent = new Intent(Splash.this, Dashboard.class);
                    Thread timer = new Thread() {
                        public void run() {
                            try {
                                sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                startActivity(intent);
                                finish();
                            }
                        }
                    };
                    timer.start();


                }
            }
        };
       /* final Intent intent = new Intent(Splash.this, Login.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}
