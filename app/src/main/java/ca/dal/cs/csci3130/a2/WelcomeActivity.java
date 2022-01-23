package ca.dal.cs.csci3130.a2;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    static String extractedNetID = new String();
    static String extractedEmailAddress = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //access the intents & show the welcome message

        //attach an event handler to the retrieve button

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