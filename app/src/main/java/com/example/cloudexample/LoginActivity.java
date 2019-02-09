package com.example.cloudexample;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText loginname,loginpassword;
    Button login;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.id_login);
        loginname = findViewById(R.id.id_login_uname);
        loginpassword = findViewById(R.id.id_login_pwd);

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = loginname.getText().toString();
                String pwd = loginpassword.getText().toString();


               databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       for(DataSnapshot snapshot :
                               dataSnapshot.getChildren()){
                           String text = snapshot.getKey();
                           Person person = dataSnapshot.child(text).getValue(Person.class);
                           System.out.println(person.getUsername());
                           if(loginname.getText().toString().
                                   equals(person.getUsername())
                           &&
                           loginpassword.getText().toString()
                                   .equals(person.getPassword())){
                               Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                               flag = true;
                               break;
                            }
                       }
                       if(!flag)
                           Toast.makeText(LoginActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });
            }
        });

    }
}
