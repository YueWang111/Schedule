package com.example.schedule;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;



public class WebActivity extends AppCompatActivity {
    Button start_up, restart, shutdown, link,msgsent;

    TextView show_chat;
    TextView L_ip,link_status ;
    EditText web_ip,chat;
    Socket socket = null;
    String buffer = "";
    String ip = "192.168.212.19";
    String status;
    String proform;
    String S_chat;
    private Handler mMainHandler;
    String response;

    public Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x11) {
                Bundle bundle = msg.getData();

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        //This is mobile terminal (1.PC shutdown 2.PC restrat 3.PC start-up)
        restart = (Button) findViewById(R.id.wp_restart);
        shutdown = (Button) findViewById(R.id.wp_shutdown);
        web_ip = (EditText) findViewById(R.id.wp_Linkip);
        link_status = (TextView) findViewById(R.id.wp_linkstatus);
        show_chat = (TextView) findViewById(R.id.wp_show);

        msgsent = (Button) findViewById(R.id.wp_sent);
        chat = (EditText) findViewById(R.id.wp_msg);


        Message msg = new Message();
        msg.what = 0x11;

        socket = new Socket();


        //link
        link = (Button) findViewById(R.id.wp_btnlink);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //socket-linking
                Socket_linking();

            }
        });


        //1.PC shutdown
        shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sending infos to the sever
                try {
                    Message msg = new Message();
                    msg.what = 0x11;
                    Bundle bundle = new Bundle();
                    bundle.clear();
                    OutputStream out = socket.getOutputStream();
                    out.write("shutdown".getBytes("gbk"));
                    out.flush();
                    bundle.putString("msg", buffer.toString());
                    msg.setData(bundle);
                    //sending msg to change ui-thread
                    myHandler.sendMessage(msg);
                    Log.d("ScheduleManager", "shutdown");
                    show_chat.setText("client：shutdown");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //2.PC restart
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sending infos to the sever
                try {
                    Message msg = new Message();
                    msg.what = 0x11;
                    Bundle bundle = new Bundle();
                    bundle.clear();
                    OutputStream out = socket.getOutputStream();
                    out.write("restart".getBytes("gbk"));
                    out.flush();
                    bundle.putString("msg", buffer.toString());
                    msg.setData(bundle);
                    //sending msg to change ui-thread
                    myHandler.sendMessage(msg);
                    show_chat.setText("client：restart");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //3.PC start-up

        mMainHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        show_chat.setText(response);
                        break;
                }
            }
        };
        //sent
        msgsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sending infos to the sever
                try {
                    Message msg = new Message();
                    msg.what = 0x11;
                    Bundle bundle = new Bundle();
                    bundle.clear();
                    OutputStream out = socket.getOutputStream();
                    S_chat = chat.getText().toString();
                    out.write(S_chat.getBytes("gbk"));
                    out.flush();
                    bundle.putString("msg", buffer.toString());
                    msg.setData(bundle);
                    //sending msg to change ui-thread
                    myHandler.sendMessage(msg);
                    show_chat.setText("client：" + S_chat);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }




    public void Socket_linking(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ip = web_ip.getText().toString();

                Bundle bundle = new Bundle();
                bundle.clear();
                //Linking-serve

                try {
                    socket.connect(new InetSocketAddress(ip,5150), 5000);
                    status = "连接成功";
                    Log.d("ScheduleManager","连接成功");
                }catch (IOException e){
                    e.printStackTrace();
                    status = "连接失败";
                    Log.d("ScheduleManager","连接失败");
                }
//                while(true) {
//                    try {
//                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                        response = br.readLine();
//
//                        Message msg = Message.obtain();
//                        msg.what = 0;
//                        mMainHandler.sendMessage(msg);
//
//                        Log.i("dasdasd", "读取数据：" + br);
//
//                        //关闭连接
//                        //br.close();
//                        //socket.close();
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
   //             }
            }

        }

        ).start();
    }
}
