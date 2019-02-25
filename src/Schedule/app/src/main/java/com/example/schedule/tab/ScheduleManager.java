package com.example.schedule.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.schedule.R;
import com.example.schedule.WebActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by buynow1 on 2018/10/13.
 */

public class ScheduleManager extends Fragment {
    View view;
    Button GtoWP, btn_HttpURLconn,btn_okHttp;
    TextView res;
    Socket socket = null;
    String buffer = "";





    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.schedule_manager, null);

        GtoWP = (Button) view.findViewById(R.id.GotoWebP);

        GtoWP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WebActivity.class);
                startActivity(intent);//startActivityForResult(intent,1);
            }
        });

        //using HttpURLConnection
        res = (TextView) view.findViewById(R.id.httpURLcon_result);
        btn_HttpURLconn = (Button) view.findViewById(R.id.HURLC);
        btn_HttpURLconn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestByHttpUrlConn();
            }
        });

        btn_okHttp = (Button) view.findViewById(R.id.okHURLC);
        btn_okHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestByokHttp();
            }
        });


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    public void sendRequestByokHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://liu-jiong.com")
                            .build();
                    Response response = client.newCall(request).execute();
                    final String resposeData = response.body().string();

                    // show the msg
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            res.setText(resposeData);
                        }
                    });

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void sendRequestByHttpUrlConn() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                BufferedReader reader = null;

                try {
                    //1)link
                    URL url = new URL("http://liu-jiong.com");
                    conn = (HttpURLConnection) url.openConnection();
                    //setting
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    //2)reading msg
                    InputStream in = conn.getInputStream(); //get binary - stream

                    // binary-->character-->buffered
                    reader = new BufferedReader(new InputStreamReader(in));

                    final StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    // show the msg
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            res.setText(response.toString());
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(reader != null) {
                        try {
                            reader.close();

                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }
}