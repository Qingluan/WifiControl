package com.qingluan.darkh.wificontroll.IO;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import com.qingluan.darkh.wificontroll.Config.arguments;
import com.qingluan.darkh.wificontroll.Services.BroadReciver;
import com.qingluan.darkh.wificontroll.Services.BroadcastNotifer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by darkh on 3/14/15.
 */
public class AsySocket  {
    public String LOG = this.getClass().getName();

    int port = 0 ;
    String ip = "127.0.0.1";
    SocketAddress NetworkAddress ;
    Socket NetworkHandler;
    List<DataInputStream> InStreams = new ArrayList<DataInputStream>();
    private static Map<Integer,Thread> SendThreadPools = new HashMap<Integer,Thread>();
    private static InputStream ReadStream = null;
    private Context context;
    BroadcastNotifer notifer;

    public  AsySocket (Context context,String ip ,int port ) {
        NetworkHandler = new Socket();
        this.context = context;
        this.ip = ip;
        this.port = port;
        Log.d(LOG,"init ...");
        NetworkAddress = new InetSocketAddress(this.ip,this.port);



    }



    private boolean send(byte[] data ) {
        try {

            NetworkHandler.connect(NetworkAddress,5000);
            Log.d(LOG,"connect to ");
            DataOutputStream  DOS = new DataOutputStream(NetworkHandler.getOutputStream());
//            DataInputStream DIS = new DataInputStream(NetworkHandler.getInputStream());
//            InStreams.add(0,DIS);

            if (DOS != null){

                DOS.write(data,0,data.length);
                Log.d(LOG,"write "+ data.toString());
                DOS.flush();
            }else{
                throw  new NullPointerException("DataOutputStream created failed !!");
            }


            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  false;
    }

    public   void AsySend(final byte[] data){
        Runnable sendRunable = new Runnable() {
            @Override
            public void run() {
                send(data);
//                while (! NetworkHandler.isConnected()) {
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Log.d(LOG,"not connected .. try again in 200 millis");
//                }
//                AsyRead();
                try {
                    DataInputStream  DIS = new DataInputStream(NetworkHandler.getInputStream());
                    if (DIS!= null){
                        Log.d(LOG,"Stream get ... ");
                    }
                    readFromStream(DIS);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };

        Thread sendThread = new Thread(sendRunable);

        addThread(sendThread);

        Log.d(LOG,"stat thread ...");
//        sendThread.start();
    }
    private void AsyRead(){
        Runnable readRunable = new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(LOG,"start read...");
                    DataInputStream  DIS = new DataInputStream(NetworkHandler.getInputStream());



                    int Counter = 0;
                    while (DIS == null){
                        DIS = new DataInputStream(NetworkHandler.getInputStream());

                        if (DIS != null){


                            break;
                        }
                        Counter ++ ;
                        Thread.sleep(700);
                        if (Counter >10){
                            Log.d(LOG,"try 10 times , but no response , check internet");
                            break;
                        }
                        Log.d(LOG,"try to get read buffer in "+String.valueOf(Counter)+" time");
                    }

                    readFromStream(DIS); //recv string then send to broadcast
                    closeNetwork();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }
        };

        Thread readThread = new Thread(readRunable);
        readThread.start();
    }

    private void  readFromStream(DataInputStream DIS){

        try {
            String get;
            int len = DIS.available();
            Log.d(LOG,String.valueOf(len)+" lenght");
            byte[] buf = new byte[4096];
            DIS.read(buf);

            get= new String(buf,"UTF-8");

            Intent intent = new Intent();
            intent.putExtra("DATA",get);
            intent.setAction(arguments.BROADCAST_RECV_FROM_SOCKET);
            context.sendBroadcast(intent);
            Log.d(LOG,"get data >"+get);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public  void setAsyReadListener(final AsyReadListener asyReadListener){
        notifer = new BroadcastNotifer(context);
        notifer.setReceiveFilter(arguments.BROADCAST_RECV_FROM_SOCKET,new BroadReciver.BroadcastReceiverListener() {
            @Override
            public void onString(String data) {
                asyReadListener.onRead(data);
            }
        });


    }

    private  int addThread(Thread thread){
        int threads_count = getCountOfSendPools();
        if (threads_count >= 20){
            AsySocket.SendThreadPools.clear();
            threads_count = 0;
        }
        SendThreadPools.put(threads_count,thread);
        thread.start();
        Log.d(LOG,"running thread : "+String.valueOf(threads_count));

        return  getCountOfSendPools();
    }

    public static void removeThread(int index){
        AsySocket.SendThreadPools.remove(index);
    }

    public static Thread getSendThread(int index){
        return AsySocket.SendThreadPools.get(index);
    }

    public int getCountOfSendPools (){
        return  AsySocket.SendThreadPools.size();
    }




    public void closeNetwork(){
        AsySocket.SendThreadPools.clear();
        try {
            NetworkHandler.close();
            NetworkHandler = null;
            NetworkAddress = null;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public interface AsyReadListener {
        public  void onRead(String data);
    }


}
