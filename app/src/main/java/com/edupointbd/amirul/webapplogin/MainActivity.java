package com.edupointbd.amirul.webapplogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText USER_NAME, USER_PASS;
    String user_name, user_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        USER_NAME = (EditText)findViewById(R.id.etName);
        USER_PASS = (EditText)findViewById(R.id.etPass);
    }


    public void userLogin(View v){

        user_name = USER_NAME.getText().toString();
        user_pass  = USER_PASS.getText().toString();

        String method = "login";

        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,user_name,user_pass);
        USER_NAME.setText("");
        USER_PASS.setText("");
    }
    public void userRegistration(View v){
        startActivity(new Intent(this,Register.class));


    }
}
