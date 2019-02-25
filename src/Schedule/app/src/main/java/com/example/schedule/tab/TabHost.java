package com.example.schedule.tab;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.schedule.R;

/**
 * Created by buynow1 on 2018/10/13.
 */

public class  TabHost {

    private Context mContext;
    private String texts[] = { "用户", "报表", "计划表","空间", "教育" };
    private int imageButton[] = { R.drawable.bt_home_user,
            R.drawable.bt_home_reporter, R.drawable.bt_home_schedule,R.drawable.bt_home_room,R.drawable.bt_home_edu};
    private Class fragmentArray[] = {ScheduleUser.class,FragmentPage2.class,ScheduleShow.class,FragmentPage3.class,ScheduleManager.class};

    public TabHost (Context context){
        mContext = context;
    }

    public void onCreate(FragmentTabHost fragmentTabHost, FragmentManager mFragmentManager) {

        fragmentTabHost.setup(mContext, mFragmentManager,
                R.id.maincontent);

        for (int i = 0; i < texts.length; i++) {
            android.widget.TabHost.TabSpec spec=fragmentTabHost.newTabSpec(texts[i]).setIndicator(getView(i));

            fragmentTabHost.addTab(spec, fragmentArray[i], null);

            //设置背景(必须在addTab之后，由于需要子节点（底部菜单按钮）否则会出现空指针异常)
            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_bg);
        }

    }

    private View getView(int i) {
        //取得布局实例
        View view=View.inflate(mContext, R.layout.tab_item_view, null);
        //取得布局对象
        ImageView imageView=(ImageView) view.findViewById(R.id.image);
        TextView textView=(TextView) view.findViewById(R.id.text);

        //设置图标
        imageView.setImageResource(imageButton[i]);
        //设置标题
        textView.setText(texts[i]);
        return view;
    }
}
