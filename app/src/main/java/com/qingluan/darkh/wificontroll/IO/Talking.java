package com.qingluan.darkh.wificontroll.IO;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.qingluan.darkh.wificontroll.Config.DATA;
import com.qingluan.darkh.wificontroll.Config.arguments;
import com.qingluan.darkh.wificontroll.NotConnectActivity;
import com.qingluan.darkh.wificontroll.R;

import java.io.UnsupportedEncodingException;

/**
 * Created by darkh on 3/15/15.
 */
public class Talking {

    public  static  void  sendInfo(Context context,byte[] data,AsySocket.AsyReadListener asyReadListener){
        AsySocket asySocket = new AsySocket(context,arguments.HOST, arguments.NETWORK_PORT);

        asySocket.AsySend(data);

        asySocket.setAsyReadListener(asyReadListener);
    }


//    public  static  void  sendInfo(Context context,byte[] data,AsySocket.AsyReadListener asyReadListener,AsySocket.ErrorCallbackListener listener){
//        AsySocket asySocket = new AsySocket(context,arguments.HOST, arguments.NETWORK_PORT);
//
//        asySocket.AsySend(data,listener);
//
//        asySocket.setAsyReadListener(asyReadListener);
//    }

    public  static  void  ConnectTest(final Context context){
        byte[] data = DATA.TEST_DATA;
        Talking.sendInfo(context,data,new AsySocket.AsyReadListener() {
            @Override
            public void onRead(String data) {

                if(data.equals("Socket is not connected")){
                    if (! context.getClass().equals(NotConnectActivity.class)) {
                        Toast.makeText(context, "Net work Error", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(context, NotConnectActivity.class);
                        context.startActivity(i);
                    }
                }else{
                    Toast.makeText(context,context.getString(R.string.connect_ok),Toast.LENGTH_SHORT).show();
                }
            }


        });
    }



}
