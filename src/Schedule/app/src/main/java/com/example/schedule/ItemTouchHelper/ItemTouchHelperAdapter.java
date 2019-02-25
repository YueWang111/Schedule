package com.example.schedule.ItemTouchHelper;

/**
 * Created by buynow1 on 2018/10/13.
 */

public interface ItemTouchHelperAdapter {
    //data_change
    void onItemMove(int fromPosition, int toPositon);

    //data_delete
    void onItemDissmiss(int positon);
}
