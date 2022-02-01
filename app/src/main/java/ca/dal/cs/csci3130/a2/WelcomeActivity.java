package ca.dal.cs.csci3130.a2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    static String extractedNetID = new String();
    static String extractedEmailAddress = new String();
    private FirebaseDatabase database;
    private String FirebaseURL = "https://csci-3130-a2-4ebd5-default-rtdb.firebaseio.com/";
    private DatabaseReference email;
    private DatabaseReference netID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //access the intents & show the welcome message
        Intent intent = getIntent();
        String email = intent.getStringExtra(MainActivity.EMAIL);
        extractedEmailAddress = email;
        String netID = intent.getStringExtra(MainActivity.NETID);
        extractedNetID = netID;

        //show the message
        TextView welcomeLabel = findViewById(R.id.welcomeLabel);
        String message = "Welcome " + extractedNetID + "! An email was sent to " + extractedEmailAddress;
        welcomeLabel.setText(message);

        //initializing the database instance and others!
        this.initializeDatabase();
    }


    protected void initializeDatabase() {
        //initialize the database and the two references related to net ID and email address.
        database = FirebaseDatabase.getInstance(FirebaseURL);
        email = database.getReference("email");
        netID = database.getReference("netID");

        //read email from the database
        email.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                extractedEmailAddress = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                //failed to read value
                System.out.println("Failed to read email value");
            }
        });

        //read netID from the database
        netID.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                extractedNetID = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                //failed to read value
                System.out.println("Failed to read netID value");
            }
        });
    }

    @Override
    public void onClick(View view) {
        String message = extractedNetID + ",\t" + extractedEmailAddress;
        //show the retrieved credentials using Toast or AlertBox
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}