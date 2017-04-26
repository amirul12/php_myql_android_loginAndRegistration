package com.edupointbd.amirul.webapplogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText NAME, USER_NAME,USER_PASS;
    String name, user_name, user_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        NAME = (EditText)findViewById(R.id.user);
        USER_NAME = (EditText)findViewById(R.id.userName);
        USER_PASS= (EditText)findViewById(R.id.userPass);

    }

    public  void  userReg(View view){

        name = NAME.getText().toString();
        user_name = USER_NAME.getText().toString();
        user_pass = USER_PASS.getText().toString();

        BackgroundTask backgroundTask = new BackgroundTask(this);
        String method = "register";

        backgroundTask.execute(method,name,user_name,user_pass);
        finish();

    }
}
