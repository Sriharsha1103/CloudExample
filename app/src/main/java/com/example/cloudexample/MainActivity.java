package com.example.cloudexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText uname, pwd, phone;
    Button register;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        uname = (EditText) findViewById(R.id.id_uname);
        pwd = findViewById(R.id.id_pwd);
        phone = findViewById(R.id.id_mobile);
        register = findViewById(R.id.id_register);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = uname.getText().toString();
                String password = pwd.getText().toString();
                String mobile = phone.getText().toString();

                Person person = new Person(name, password, mobile);
                databaseReference.child(mobile).setValue(person);
                //databaseReference.setValue(person);
                Toast.makeText(MainActivity.this, "Data Stored", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });


    }
}
