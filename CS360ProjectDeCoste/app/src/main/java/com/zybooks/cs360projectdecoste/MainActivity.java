package com.zybooks.cs360projectdecoste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Temporary variables until count is in database
    int item1count = 0;
    int item2count = 0;
    int item3count = 0;
    int item4count = 0;



    private final int REQUEST_TEXT_CODE = 0;
    UserDatabase userDatabase;
    inventoryDatabase inventoryDatabase;
    EditText myEditTextUser;
    EditText myEditTextPass;

    //Runs when main activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Checks for texting permission
        hasFilePermissions();

        //Creates database of users
        UserDatabase userDatabase = new UserDatabase(getApplicationContext());
        //Creates database of inventory
        inventoryDatabase inventoryDatabase = new inventoryDatabase(getApplicationContext());
    }

    private boolean hasFilePermissions() {
        String textPermission = Manifest.permission.SEND_SMS;
        if (ContextCompat.checkSelfPermission(this, textPermission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, textPermission)) {
                showPermissionRationaleDialog();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{textPermission}, REQUEST_TEXT_CODE);
            }
            return false;
        }
        return true;
    }

    private void showPermissionRationaleDialog() {
    }

    //Button actions
    public void switchScreen(View view) {
        setContentView(R.layout.inventory_grid);
    }

    public void sendText(View view){
        String textPermission = Manifest.permission.SEND_SMS;
        //Only attempts to send if permissions allow
        if(ContextCompat.checkSelfPermission(this, textPermission) == PackageManager.PERMISSION_GRANTED) {
            String message = "test message";
            //Number from which the message will come
            String number = "5555555555";
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number,null, message,null,null);
        }
    }


    public void addUser(View view) {
        myEditTextUser = (EditText) findViewById(R.id.username);
        myEditTextPass = (EditText) findViewById(R.id.password);

        String username = myEditTextUser.getText().toString();
        String password = myEditTextPass.getText().toString();

        //!!!
        //This crashes, even if the method is empty??
        userDatabase.addUser(username, password);
    }


    public void loginUser(View view) {
        myEditTextUser = (EditText) findViewById(R.id.username);
        myEditTextPass = (EditText) findViewById(R.id.password);

        String username = myEditTextUser.getText().toString();
        String password = myEditTextPass.getText().toString();

        //Crashes when call is made to userDatabase.checkPasswordByUsername(username, password)??
        boolean credCheck = true;//userDatabase.checkPasswordByUsername(username, password);

        //if user exists
        if (credCheck == true) {
            setContentView(R.layout.inventory_grid);
        }
        //if user does not exist, alert user
        else{
            Toast.makeText(MainActivity.this, "User not found!", Toast.LENGTH_LONG).show();
        }
    }


    public void askPermission(View view) {
        String textPermission = Manifest.permission.SEND_SMS;
        ActivityCompat.requestPermissions(this, new String[]{textPermission}, REQUEST_TEXT_CODE);
    }

    //Manages item count until I get the database to stop crashing the app
    public void addCount1(View view){
        Button button = (Button)findViewById(R.id.item1);
        item1count = item1count + 1;
        button.setText("Item 1: " + item1count);
    }
    public void minusCount1(View view){
        Button button = (Button)findViewById(R.id.item1);
        item1count = item1count - 1;
        button.setText("Item 1: " + item1count);
    }
    public void addCount2(View view){
        Button button = (Button)findViewById(R.id.item2);
        item2count = item2count + 1;
        button.setText("Item 2: " + item2count);
    }
    public void minusCount2(View view){
        Button button = (Button)findViewById(R.id.item2);
        item2count = item2count - 1;
        button.setText("Item 2: " + item1count);
    }
    public void addCount3(View view){
        Button button = (Button)findViewById(R.id.item3);
        item3count = item3count + 1;
        button.setText("Item 3: " + item3count);
    }
    public void minusCount3(View view){
        Button button = (Button)findViewById(R.id.item3);
        item3count = item3count - 1;
        button.setText("Item 3: " + item3count);
    }
    public void addCount4(View view){
        Button button = (Button)findViewById(R.id.item4);
        item4count = item4count + 1;
        button.setText("Item 4: " + item4count);
    }
    public void minusCount4(View view){
        Button button = (Button)findViewById(R.id.item4);
        item4count = item4count - 1;
        button.setText("Item 4: " + item4count);
    }


}

