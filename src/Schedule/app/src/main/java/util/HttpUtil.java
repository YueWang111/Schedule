package util;

import android.util.Log;

import com.example.schedule.Info.PlanInfo;
import com.example.schedule.Info.UserInfo;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by buynow1 on 2018/12/18.
 */

public class HttpUtil {
    static String address = "http://192.168.43.80/ScheduleJDBC";

    public static void addByOkHttp(final PlanInfo plan){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 声明客户端
                    OkHttpClient client = new OkHttpClient();

                    //数据解析；
                    int idint = plan.getId();
                    String id = String.valueOf(idint);
                    int imageIdint = plan.getImageId();
                    String imageId = String.valueOf(imageIdint);
                    String content = plan.getContent();
                    String type = plan.getType();
                    String alarm = plan.getAlarm();
                    // 上传参数
                    RequestBody requestBody = new FormBody.Builder()
                            .add("id",id)
                            .add("imageId", imageId) // 新增参数
                            .add("content", content) // 新增参数
                            .add("type", type) // 新增参数
                            .add("alarm", alarm) // 新增参数
                            .build();
                    // 声明请求
                    Request request = new Request.Builder()
                            .url(address+"/add_plan.jsp")
                            .post(requestBody) //post
                            .build();
                    // 客户端发起请求
                    Response response = client.newCall(request).execute();
//                    // 返回值
//                    final String data = response.body().string();
//                    // 解析ＸＭＬ
//                    final String result = parseXml(data);
//                    // 显示
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            _tvResult.setText(result);
//                        }
//                    });
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        }).start();
    }

    public static void delByOkHttp(final String id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 声明客户端
                    OkHttpClient client = new OkHttpClient();



                    // 上传参数
                    RequestBody requestBody = new FormBody.Builder()
                            .add("id", id) // 新增参数
                            .build();
                    // 声明请求
                    Request request = new Request.Builder()
                            .url(address+"/del_plan.jsp")
                            .post(requestBody) //post
                            .build();
                    // 客户端发起请求
                    Response response = client.newCall(request).execute();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public static void updateByOkHttp(final String id,final PlanInfo plan){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 声明客户端
                    OkHttpClient client = new OkHttpClient();

                    //数据解析
                    int imageIdint = plan.getImageId();
                    String imageId = String.valueOf(imageIdint);
                    String content = plan.getContent();
                    String type = plan.getType();
                    String alarm = plan.getAlarm();
                    // 上传参数
                    RequestBody requestBody = new FormBody.Builder()
                            .add("id",id)
                            .add("imageId", imageId) // 新增参数
                            .add("content", content) // 新增参数
                            .add("type", type) // 新增参数
                            .add("alarm", alarm) // 新增参数
                            .build();
                    // 声明请求
                    Request request = new Request.Builder()
                            .url(address+"/update_plan.jsp")
                            .post(requestBody) //post
                            .build();
                    // 客户端发起请求
                    Response response = client.newCall(request).execute();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    //完成
    public static void planfinishedByOkHttp(final String id,final PlanInfo plan){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 声明客户端
                    OkHttpClient client = new OkHttpClient();

                    //数据解析
                    int finishedint = plan.getFinished();
                    String finished = String.valueOf(finishedint);
                    // 上传参数
                    RequestBody requestBody = new FormBody.Builder()
                            .add("id",id)
                            .add("finished", finished) // 新增参数
                            .build();
                    // 声明请求
                    Request request = new Request.Builder()
                            .url(address+"/finished_plan.jsp")
                            .post(requestBody) //post
                            .build();
                    // 客户端发起请求
                    Response response = client.newCall(request).execute();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }).start();
    }


    public static void register(final UserInfo info){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 声明客户端
                    OkHttpClient client = new OkHttpClient();
                    //解析
                    String username = info.getUsername();
                    String password = info.getPassword();

                    // 上传参数
                    RequestBody requestBody = new FormBody.Builder()
                            .add("username", username) // 新增参数
                            .add("password", password) // 新增参数
                            .build();
                    // 声明请求
                    Request request = new Request.Builder()
                            .url(address+"/register.jsp")
                            .post(requestBody) //post
                            .build();
                    // 客户端发起请求
                    Response response = client.newCall(request).execute();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public static void login(final UserInfo info){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 声明客户端
                    OkHttpClient client = new OkHttpClient();
                    //解析
                    String username = info.getUsername();
                    String password = info.getPassword();

                    // 上传参数
                    RequestBody requestBody = new FormBody.Builder()
                            .add("username", username) // 新增参数
                            .add("password", password) // 新增参数
                            .build();
                    // 声明请求
                    Request request = new Request.Builder()
                            .url(address+"/login.jsp")
                            .post(requestBody) //post
                            .build();
//                    // 客户端发起请求
//                    Response response = client.newCall(request).execute();
//
//                    // 返回值
//                    final String data = response.body().string();
//
//                    final String result = data;

                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String res = response.body().string();
                        Log.d("res",res);
                        if(res == "true") {

                        }
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }

                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }).start();
    }
}
