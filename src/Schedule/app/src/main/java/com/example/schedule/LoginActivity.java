package com.example.schedule;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.schedule.Info.PlanInfo;
import com.example.schedule.Info.UserInfo;
import com.example.schedule.R;
import com.example.schedule.tab.ScheduleUser;

import util.HttpUtil;

public class LoginActivity extends AppCompatActivity {
    EditText Edit_uersname;
    EditText Edit_password;
    Button Login,Register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //My app dont need title(Hidding title)
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }

        Edit_uersname = (EditText) findViewById(R.id.username);
        Edit_password = (EditText) findViewById(R.id.password);









        Login = (Button) findViewById(R.id.Login);
        Register = (Button) findViewById(R.id.Register);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = String.valueOf(Edit_uersname.getText());
                String password = String.valueOf(Edit_password.getText());
                UserInfo info = new UserInfo(username,password);



                Boolean k = info.save();

                HttpUtil httpUtil = new HttpUtil();
                httpUtil.register(info);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(Edit_uersname.getText());
                String password = String.valueOf(Edit_password.getText());
                UserInfo info = new UserInfo(username, password);

                Boolean k = info.save();

                HttpUtil httpUtil = new HttpUtil();
                httpUtil.login(info);

                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);//startActivityForResult(intent,1);
            }
        });


    }
}
