package com.example.eventmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Splash extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        text = findViewById(R.id.text);
       /* Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);*/

        Animation myanim1 = AnimationUtils.loadAnimation(this,R.anim.zoomout);

        //imageView.setAnimation(myanim);
        text.setAnimation(myanim1);

        final Intent intent = new Intent(Splash.this, Login.class);
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

    }


}
