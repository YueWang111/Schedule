package util;

import com.example.schedule.Info.PlanInfo;

/**
 * Created by buynow1 on 2018/12/20.
 */

public class PlansUtil {
    public static void planfinished(final PlanInfo plan){
        int finished = 1;

        plan.setFinished(1);
        //Toast.makeText(this,id.toString(),Toast.LENGTH_LONG).show();
        String id_s = String.valueOf(plan.getId());

        try {

            plan.updateAll("id =  ?", id_s);
            HttpUtil httpUtil = new HttpUtil();
            httpUtil.planfinishedByOkHttp(id_s,plan);
        }catch (Exception e) {

            e.printStackTrace();
        }
    }
}
