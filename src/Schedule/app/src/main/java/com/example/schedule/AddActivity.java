package com.example.schedule;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schedule.Info.PlanInfo;
import com.example.schedule.recyclerview.PlanAdapter;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;
import util.HttpUtil;

public class AddActivity extends AppCompatActivity {
    private EditText imageId, type ,content,alarm;
    private Button save;
    private Button exit;
    private Intent intent;
    private Integer id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //hidding the sys_title
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }


        save = (Button) findViewById(R.id.Save);
        imageId = (EditText) findViewById(R.id.Edit_imageId);
        type = (EditText) findViewById(R.id.Edit_type);
        content = (EditText) findViewById(R.id.Edit_content);
        alarm = (EditText) findViewById(R.id.Edit_alarm);

        intent = getIntent();
        final Integer judge = intent.getIntExtra("Judge",-1);
        Toast.makeText(getApplicationContext(),judge.toString(),Toast.LENGTH_SHORT).show();

        if(judge > 0) {
            imageId.setText(intent.getIntExtra("ImageId",-1));
            content.setText(intent.getStringExtra("Content"));
            type.setText(intent.getStringExtra("Type"));
            alarm.setText(intent.getStringExtra("Alarm"));
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( judge>0 )
                    updata_save();
                else
                    add_save();

            }
        });



        //Exit
        exit = (Button) findViewById(R.id.Add_Exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void add_save(){
        PlanInfo plan = new PlanInfo(android.R.drawable.ic_input_add,
                type.getText().toString(),
                content.getText().toString(),
                alarm.getText().toString()
        );

        Boolean k = plan.save();
        if(k == true)Toast.makeText(getApplicationContext(),"OKOK",Toast.LENGTH_SHORT).show();
        else Toast.makeText(getApplicationContext(),"xx",Toast.LENGTH_SHORT).show();
        HttpUtil httpUtil = new HttpUtil();

        httpUtil.addByOkHttp(plan);

        finish();
    }

    private void updata_save(){
        Integer id = intent.getIntExtra("Id",-1);

        PlanInfo plan = new PlanInfo();

        plan.setImageId(android.R.drawable.ic_input_add);
        plan.setType(type.getText().toString());
        plan.setContent(content.getText().toString());
        plan.setAlarm(alarm.getText().toString());
        Toast.makeText(this,id.toString(),Toast.LENGTH_LONG).show();
        String id_s = id.toString();
        try {

            plan.updateAll("id =  ?", id_s);
            HttpUtil httpUtil = new HttpUtil();
            httpUtil.updateByOkHttp(id_s,plan);
            Log.d("AddActivity","ok");
        }catch (Exception e) {
            Log.d("AddActivity","sorry");
            e.printStackTrace();
        }
        finish();

    };

}
