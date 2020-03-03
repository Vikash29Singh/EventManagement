package com.example.eventmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    EditText email, contact, organization, password;
    Button signup;



    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        email = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        organization = findViewById(R.id.organization);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);

        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    private void registerUser() {
        final String organization1 = organization.getText().toString().trim();
        final String email1 = email.getText().toString().trim();
        final String password1 = password.getText().toString().trim();
        final String phone = contact.getText().toString().trim();

        if (organization1.isEmpty()) {
            organization.setError("Enter organization");
            organization.requestFocus();
            return;
        }

        if (email1.isEmpty()) {
            email.setError("Enter email ID");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            email.setError("Enter a valid email ID");
            email.requestFocus();
            return;
        }

        if (password1.isEmpty()) {
            password.setError("Password required");
            password.requestFocus();
            return;
        }

        if (password1.length() < 6) {
            password.setError("password should be atleast 6 characters long");
            password.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            contact.setError("Phone number required");
            contact.requestFocus();
            return;
        }

        if (phone.length() != 10) {
            contact.setError("Enter a valid phone number");
            contact.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email1, password1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            User user = new User(email1, phone, organization1);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Registration Succesfull", Toast.LENGTH_LONG).show();
                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


}
