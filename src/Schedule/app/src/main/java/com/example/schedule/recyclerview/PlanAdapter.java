package com.example.schedule.recyclerview;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schedule.AddActivity;
import com.example.schedule.Info.PlanInfo;
import com.example.schedule.ItemTouchHelper.ItemTouchHelperAdapter;
import com.example.schedule.R;

import org.litepal.crud.DataSupport;

import java.util.Collections;
import java.util.List;

import util.HttpUtil;

import static util.PlansUtil.planfinished;

/**
 * Created by buynow1 on 2018/10/13.
 */

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> implements ItemTouchHelperAdapter{
    private Context context;
    private List<PlanInfo> mPlanList;

    //ItemTouchHelperAdpater
    @Override
    public void onItemMove(int fromPosition, int toPosition){
        //change
        Collections.swap(mPlanList,fromPosition,toPosition);
    }

    @Override
    public void onItemDissmiss(int positon) {
        //detele data
        mPlanList.remove(positon);
        notifyItemRemoved(positon);
    }

    //Define ViewHolder and Get the RV_Layout
    static class ViewHolder extends RecyclerView.ViewHolder {
        //plan_item1 (image, content, times, alarm)
        ImageView type_Image;
        TextView p_content;
        TextView p_type;
        TextView p_alarm;
        Button item_delete;
        View p_view;

        public ViewHolder(View itemView) {
            super(itemView);
            p_view = itemView;

            //p_view.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.gray));

            item_delete =  (Button) itemView.findViewById(R.id.tv_delete);
            type_Image = (ImageView) itemView.findViewById(R.id.tv_Image);
            p_content = (TextView) itemView.findViewById(R.id.tv_content);
            p_type = (TextView) itemView.findViewById(R.id.tv_type);
            p_alarm = (TextView) itemView.findViewById(R.id.tv_alarm);
        }
    }

    //Get the dataList adapted  (constructor)
    public PlanAdapter(Context context, List<PlanInfo> PlanList){
        this.context = context;
        mPlanList = PlanList;
    }

    public PlanAdapter() {

    }


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item1,parent,false);

        final ViewHolder holder = new ViewHolder(view);

        holder.p_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                PlanInfo info = mPlanList.get(position);
                Intent intent = new Intent(v.getContext(),AddActivity.class);
                intent.putExtra("Id",info.getId());
                intent.putExtra("Alarm",info.getAlarm());
                intent.putExtra("Content",info.getContent());
                intent.putExtra("ImageId",info.getImageId());
                intent.putExtra("Type",info.getType());
                intent.putExtra("Judge",1);//add -- alertnative -- Necessary;
                context.startActivity(intent);
            }
        });

        holder.p_view.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view){
                int position = holder.getAdapterPosition();
                final PlanInfo info = mPlanList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("您确定完成了项计划?");
                builder.setTitle("提示");

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        planfinished(info);

                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();


                return true;
            }
        });

        //item - delete
        holder.item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                PlanInfo info = mPlanList.get(position);
                Integer id = info.getId();
                DataSupport.deleteAll(PlanInfo.class,"id = ?", id.toString());

                HttpUtil httpUtil = new HttpUtil();
                httpUtil.delByOkHttp(id.toString());
            }
        });

        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlanInfo planInfo = mPlanList.get(position);
        holder.type_Image.setImageResource(planInfo.getImageId());
        holder.p_content.setText(planInfo.getContent());
        holder.p_type.setText(planInfo.getType());
        holder.p_alarm.setText(planInfo.getAlarm());
        if(planInfo.getFinished() == 1) {
            holder.p_view.setBackgroundColor(holder.p_view.getContext().getResources().getColor(R.color.finished_green));
        }
        //String finished = String.valueOf(planInfo.getFinished());
        //Log.d("MainAcitvity",finished+planInfo.getContent());
    }

    public void change(){
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPlanList.size();
    }
}
