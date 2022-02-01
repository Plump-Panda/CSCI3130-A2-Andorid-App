package ca.dal.cs.csci3130.a2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.Task;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        //Is the netID the right length
        if(netID.length() == 8){
            //Check if we have 2 letters at the beginning
            int c1 = netID.charAt(0);
            int c2 = netID.charAt(1);
            boolean firstCharacterIsValid = ((c1 >= 65) && (c1 <=90)) || ((c1 >= 97) && (c1 <= 122));
            boolean secondCharacterIsValid = ((c2 >= 65) && (c2 <=90)) || ((c2 >= 97) && (c2 <= 122));

            //We don't have 2 letters at the beginning
            if(!firstCharacterIsValid || !secondCharacterIsValid){
                return false;
            }

            //Check if we have 6 numbers at the end
            try{
                Integer.parseInt(netID.substring(2,7));
            }catch (Exception e){
                return false;
            }
            return true;
        }
        return false;
    }

    protected boolean isValidEmailAddress(String emailAddress) {
        //Use regex to check if the email is valid
        Pattern regex = Pattern.compile("^[A-Za-z0-9\\.]+@[A-Za-z0-9]+\\.[A-Za-z]{2,}$");
        Matcher email = regex.matcher(emailAddress);
        return email.matches();
    }

    protected boolean isDALEmailAddress(String emailAddress) {
        return emailAddress.contains("dal.ca");
    }

    protected void setStatusMessage(String message) {
        /**This method is complete**/
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }

    protected void switch2WelcomeWindow(String netID, String emailAddress) {
        Intent intent = new Intent(getBaseContext(), WelcomeActivity.class);

        //Send data to intent
        intent.putExtra(EMAIL, emailAddress);
        intent.putExtra(NETID, netID);

        startActivity(intent);
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
        String errorMessage = "";

        if(!isValidNetID(netID)){
            errorMessage = getResources().getString(R.string.INVALID_NET_ID).trim();
            System.out.println(errorMessage);
        }

        if(isEmptyNetID(netID)){
            errorMessage = getResources().getString(R.string.EMPTY_NET_ID).trim();
            System.out.println(errorMessage);
        }

        if(!isValidEmailAddress(emailAddress)){
            errorMessage = getResources().getString(R.string.INVALID_EMAIL_ADDRESS).trim();
            System.out.println(errorMessage);
        }

        if(!isDALEmailAddress(emailAddress)){
            errorMessage = getResources().getString(R.string.INVALID_DAL_EMAIL).trim();
            System.out.println(errorMessage);
        }

        //No errors, we can move forward
        if(errorMessage.length() == 0){
            //Switch to new activity
            switch2WelcomeWindow(netID,emailAddress);
        }else{
            //Show error message
            setStatusMessage(errorMessage);
        }
    }
}