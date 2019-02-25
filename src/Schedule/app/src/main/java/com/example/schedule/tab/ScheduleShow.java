package com.example.schedule.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schedule.AddActivity;
import com.example.schedule.Info.PlanInfo;
import com.example.schedule.ItemTouchHelper.ItemTouchHelperCallback;
import com.example.schedule.R;
import com.example.schedule.recyclerview.PlanAdapter;
import com.example.schedule.recyclerview.SimpleDividerDecoration;

import org.litepal.crud.DataSupport;

import java.nio.Buffer;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by buynow1 on 2018/10/13.
 */

public class ScheduleShow  extends Fragment{
    private  View view;
    private  View n_view;
    private List<PlanInfo> planList = new ArrayList<>();
    private TextView Account;
    private TextView FinishedAccount;
    private TextView Pect;
    private TextView Calendar;
    PlanAdapter adapter;

    RecyclerView recyclerView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.schedule_show, container, false);

        //Using RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        initPlanInfo();
        adapter = new PlanAdapter(getActivity(), planList);

        adapter.change();

//        Integer a = android.R.drawable.ic_input_add;
//        Toast.makeText(getContext(),a.toString() ,Toast.LENGTH_LONG).show();

        //先实例化CallbacIl
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        //用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new SimpleDividerDecoration(getContext()));

        //add imageView clickevent;
        Button to_add = (Button) view.findViewById(R.id.to_add);

       to_add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(v.getContext(), AddActivity.class);
               startActivity(intent);//startActivityForResult(intent,1);
           }
       });

        //Account dealing
        Account = (TextView) view.findViewById(R.id.Account);
        Integer account = planList.size();
        Account.setText("今日计划数："+ account);

        //FinishedAccount dealing
        FinishedAccount = (TextView) view.findViewById(R.id.finshedAccount);
        int faccount = 0;
        List<PlanInfo> infos = DataSupport.findAll(PlanInfo.class);
        for(PlanInfo info : infos){
            if(info.getFinished() == 1)
                faccount++;
        }
        FinishedAccount.setText("今日计划数："+ faccount);

        //百分比
        Pect = (TextView) view.findViewById(R.id.percentage);
        double pect = (double)( ((double) faccount*100) / ((double) account));
        String pectS = String.format("%.2f", pect);
        Pect.setText(pectS+"%" );

        //The calender show
        Calendar = (TextView) view.findViewById(R.id.Calendar);
        Calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PlanInfo> infos = DataSupport.findAll(PlanInfo.class);
                for(PlanInfo info : infos) {

                    Toast.makeText(getContext(),info.getType()+info.getAlarm()+info.getContent(),Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }



    private void initPlanInfo() {

        //planList.clear();
        planList = DataSupport.select("imageid","type","content","alarm","finished").find(PlanInfo.class);
        for (PlanInfo p: planList) {
            Log.d("MainAcitvity","imageid:" + p.getImageId());
            Log.d("MainAcitvity","type:" + p.getType());
            Log.d("MainAcitvity","content:" + p.getContent());
            Log.d("MainAcitvity","alarm:" + p.getAlarm());
            Log.d("MainAcitvity","finshed:" + p.getAlarm());
        }

    }



    @Override
    public void onResume() {
        super.onResume();
    }
}
