package com.example.schedule.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.schedule.LoginActivity;
import com.example.schedule.R;

/**
 * Created by buynow1 on 2018/10/13.
 */

public class ScheduleUser extends Fragment{
    private  View view;
    private Button To_Login;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.schedule_user, null);
        To_Login = (Button) view.findViewById(R.id.to_login);
        To_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);//startActivityForResult(intent,1);
            }
        });




        return view;
    }
}
