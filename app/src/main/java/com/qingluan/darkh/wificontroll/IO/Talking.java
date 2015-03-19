package com.qingluan.darkh.wificontroll.IO;

import android.content.Context;
import android.widget.Toast;

import com.qingluan.darkh.wificontroll.Config.arguments;

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


    public class ToastShow {

    }


}
