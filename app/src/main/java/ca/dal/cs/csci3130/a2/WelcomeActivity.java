package ca.dal.cs.csci3130.a2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    static String extractedNetID = new String();
    static String extractedEmailAddress = new String();

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
        //initialize your database and store the retrieved values to the static members above.
    }

    @Override
    public void onClick(View view) {
        String message = extractedNetID + ",\t" + extractedEmailAddress;
        //show the retrieved credentials using Toast or AlertBox
    }
}