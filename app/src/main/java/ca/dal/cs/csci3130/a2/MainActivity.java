package ca.dal.cs.csci3130.a2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String WELCOME_MESSAGE = "ca.dal.csci3130.a2.welcome";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        initializeDatabase();
    }

    protected void initializeDatabase() {
        //initialize the database and the two references related to net ID and email address.
    }


    protected String getNetID() {
        /**This method is complete**/
        EditText netIDBox = findViewById(R.id.netID);
        return netIDBox.getText().toString().trim();
    }

    protected String getEmailAddress() {
        EditText emailAddress = findViewById(R.id.emailAddress);
        return emailAddress.getText().toString().trim();
    }

    protected boolean isEmptyNetID(String netID) {
        return netID.isEmpty();
    }

    protected boolean isValidNetID(String netID) {
        //buggy method, implement it.
        return false;
    }

    protected boolean isValidEmailAddress(String emailAddress) {
        //buggy method, implement it.
        return false;
    }

    protected boolean isDALEmailAddress(String emailAddress) {
        //buggy method implement it.
        return false;
    }

    protected void setStatusMessage(String message) {
        /**This method is complete**/
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }

    protected void switch2WelcomeWindow(String netID, String emailAddress) {
        //your business logic goes here!
    }

    protected Task<Void> saveNetIDToFirebase(String netID) {
        //buggy method, implement it.
        return null;
    }

    protected Task<Void> saveEmailToFirebase(String emailAddress) {
        //buggy method, implement it.
        return null;
    }

    @Override
    public void onClick(View view) {

        String netID = getNetID();
        String emailAddress = getEmailAddress();
        String errorMessage = new String("ERROR MESSAGE");

        if (isEmptyNetID(netID)) {
            errorMessage = getResources().getString(R.string.EMPTY_NET_ID).trim();
            System.out.println(errorMessage);
        }
        //This method is incomplete, your business logic goes here!
        setStatusMessage(errorMessage);
    }
}