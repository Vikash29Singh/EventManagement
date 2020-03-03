package com.example.eventmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static java.sql.Types.NULL;

public class Bookactivity extends AppCompatActivity {

    // private BroadcastReceiver MyReceiver = null;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    TextView center_name, event_name, stime, date, tac, no_of_tickets, amount, tickets;
    private static ImageView more;
    /*private int current_image;
    int[] images = {R.drawable.ic_keyboard_arrow_up_black_24dp,R.drawable.ic_keyboard_arrow_down_black_24dp};
    *///LinearLayout tac;
    String [] permission;
    //    TextView tv, tv1;
    private ProgressBar progressBar;
    DatabaseReference databaseReference;
    ImageView imageView, image, download;
    String center_name1;
    String event_name1;
    String stime1;
    String date1;
    String image_view;
    int price1;
    Button book, add, sub, ok, cancel;
    private Dialog myDialog;

    String price;
    private static final int PERMISSION_STORAGE_CODE = 1000;


    LinearLayout amount_click;

    String pricex;
    int count=1;

    //String price;
    int grand_tot;

    //int count=1;

    int total;

    Toolbar toolbar;
String firebaseURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookactivity);

        toolbar = findViewById(R.id.toolbar);

        myDialog = new Dialog(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        pricex = getIntent().getExtras().getString("price");

        price1 = Integer.parseInt(pricex);

        //int p = price1 + 5;

        download = findViewById(R.id.download);
        //price1 = Integer.parseInt(price);


        price1 = Integer.parseInt(pricex);


        progressBar = findViewById(R.id.progressbar);
        event_name = findViewById(R.id.event_name);
        center_name = findViewById(R.id.center_name);
        stime = findViewById(R.id.stime);
        date = findViewById(R.id.date);
        more = findViewById(R.id.more);
        book = findViewById(R.id.book);
        amount = findViewById(R.id.amount);
        tickets = findViewById(R.id.tickets);
        amount_click = findViewById(R.id.amount_click);

        //amount.setText(p);

        /*add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        no_of_tickets = findViewById(R.id.no_of_ticket);*/

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    Toast.makeText(getApplicationContext(), "Please Login", Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(getApplicationContext(), Login.class);
                    startActivity(I);
                } else {
                }
            }
        };


        /*add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = count + 1;
                no_of_tickets.setText(String.valueOf(count));
                tickets.setText(no_of_tickets.getText().toString());



                //amount.setText(price);

                total = count * price1;
                //amount.setText(total);
                amount.setText(Integer.toString(total));
                //Toast.makeText(Bookactivity.this, total, Toast.LENGTH_SHORT).show();

                sub.setEnabled(true);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sub.setEnabled(true);
                if (count >= 2) {
                    count = count - 1;
                    no_of_tickets.setText(String.valueOf(count));
                    tickets.setText(no_of_tickets.getText().toString());

                    total = count * price1;
                    amount.setText(Integer.toString(total));

                    //amount.setText(price);
                } else {

                    total = count * price1;
                    amount.setText(Integer.toString(total));
                    sub.setEnabled(false);
                    //amount.setText(price);
                }


            }
        });*/



        // amount.setText(total);


        tac = findViewById(R.id.tac);
        imageView = findViewById(R.id.imageView);
        tac.setVisibility(View.INVISIBLE);


        //amount.setText(price);

        amount_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDialog.setContentView(R.layout.ticket_count);
                add = myDialog.findViewById(R.id.add);
                sub = myDialog.findViewById(R.id.sub);
                ok = myDialog.findViewById(R.id.ok);
                cancel = myDialog.findViewById(R.id.cancel);
                no_of_tickets = myDialog.findViewById(R.id.no_of_ticket);

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(count>=15)
                        {
                            add.setEnabled(false);
                        }
                        else {
                            //add.setEnabled(true);
                            count = count + 1;
                            no_of_tickets.setText(String.valueOf(count));
                            tickets.setText(no_of_tickets.getText().toString());


                            //amount.setText(price);

                            total = count * price1;
                            //amount.setText(total);
                            amount.setText(Integer.toString(total));
                            //Toast.makeText(Bookactivity.this, total, Toast.LENGTH_SHORT).show();

                            sub.setEnabled(true);
                        }
                    }
                });
                sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sub.setEnabled(true);
                        add.setEnabled(true);
                        if (count >= 2) {
                            count = count - 1;
                            no_of_tickets.setText(String.valueOf(count));
                            tickets.setText(no_of_tickets.getText().toString());

                            total = count * price1;
                            amount.setText(Integer.toString(total));

                            //amount.setText(price);
                        } else {

                            total = count * price1;
                            amount.setText(Integer.toString(total));
                            sub.setEnabled(false);
                            //amount.setText(price);
                        }


                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        myDialog.dismiss();

                    }
                });

                myDialog.setCancelable(false);
                myDialog.show();

            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Bookactivity.this, PaymentActivity.class);
                intent.putExtra("amount", amount.getText().toString());
                startActivity(intent);
                finish();

            }
        });

        //amount.setText(grand_tot);


        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int status = tac.getVisibility();
                if (status == View.VISIBLE) {
                    tac.setVisibility(View.INVISIBLE);
                    more.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                   /* current_image++;
                    current_image=current_image & images.length;
                    more.setImageResource(images[current_image]);*/
                } else {
                    tac.setVisibility(View.VISIBLE);
                    more.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                   /* current_image--;
                    current_image=current_image & images.length;
                    more.setImageResource(images[current_image]);*/
                }
            }
        });


        event_name1 = getIntent().getExtras().getString("event_name");
        event_name.setText(event_name1);
       /* center_name1 = getIntent().getExtras().getString("center_name");
        center_name.setText(center_name1);*/
        date1 = getIntent().getExtras().getString("date");
        date.setText(date1);
        stime1 = getIntent().getExtras().getString("stime");
        stime.setText(stime1);
        /*price1 = getIntent().getExtras().getString("price");*/
        /*String center_name1 = getIntent().getExtras().getString("center_name");
        String img =  getIntent().getExtras().getString("imageView");
        Picasso.get().load(img).into(imageView);*/

        //center_name.setText(center_name1);

        databaseReference = FirebaseDatabase.getInstance().getReference("event").child(event_name1);
        /*Query query= databaseReference.child(event_name)*/

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager
                            .PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission,PERMISSION_STORAGE_CODE);

                    } else {
                        startDownloading();
                    }
                } else {

                }
                //  downloadBroucher(getApplicationContext(),event_name1,".pdf",DIRECTORY_DOWNLOADS,url);
            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                image_view = dataSnapshot.child("imageView").getValue(String.class);
                Picasso.get().load(image_view).placeholder(R.drawable.home).into(imageView);
                String center_name1 = dataSnapshot.child("center_name").getValue(String.class);
                center_name.setText(center_name1);

                firebaseURL = dataSnapshot.child("brochoure").getValue(String.class);
                progressBar.isIndeterminate();
                progressBar.setVisibility(View.INVISIBLE);
                //Picasso.get().load(model.getImageView()).into(holder.imageView);
               /*String center_name1 = dataSnapshot.child("center_name").getValue().toString();
               center_name.setText(center_name1);
              */  /* image = dataSnapshot.getValue(String.class);
                Picasso.get().load(image).into(imageView);*/
                //Dashboard_item_model model = dataSnapshot.getValue(Dashboard_item_model.class);
                /*String center_name1 = dataSnapshot.getValue(String.class);
                center_name.setText(center_name1);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error....!!", Toast.LENGTH_SHORT).show();
                progressBar.isIndeterminate();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void startDownloading() {
        String url = firebaseURL;
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("Download")
                .setDescription("Download File...");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis());
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
switch (requestCode){
    case PERMISSION_STORAGE_CODE : {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
startDownloading();
        }
        else {
            Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
        }
    }
}
    }

    private void downloadBroucher() {
        // databaseReference.child("imageView").addOnSu
    }


    public void downloadfile(Context context, String filename, String fileExtension, String destinationDirectory, String url) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, filename + fileExtension);
        downloadManager.enqueue(request);
    }



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


    /*public void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(MyReceiver);
    }
*/
/*

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.orderByChild("event_name").equalTo(event_name1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //image = dataSnapshot.child("imageView").getValue().toString();
                // Picasso.get().load(image).into(imageView);
                //Picasso.get().load(model.getImageView()).into(holder.imageView);
               *//*String center_name1 = dataSnapshot.child("center_name").getValue().toString();
               center_name.setText(center_name1);
              *//*  *//* image = dataSnapshot.getValue(String.class);
                Picasso.get().load(image).into(imageView);*//*
                //Dashboard_item_model model = dataSnapshot.getValue(Dashboard_item_model.class);
                *//*String center_name1 = dataSnapshot.getValue(String.class);
                center_name.setText(center_name1);*//*
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error....!!", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

}
