package com.qingluan.darkh.wificontroll.Services;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.ArrayMap;

import com.qingluan.darkh.wificontroll.MainActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by darkh on 3/15/15.
 */
public class BroadcastNotifer {
    private  String tag = BroadcastNotifer.class.getName();
    private  Context locateContext ;

    public static  Map<String,BroadReciver> broadReciverMap = new HashMap<String, BroadReciver>();

    public BroadcastNotifer(Context context){
        this.locateContext = context;
    }

    public boolean sendIntent(Intent intent){
        if (this.locateContext != null){
            this.locateContext.sendBroadcast(intent);
            return  true;
        }
        return false;
    }


    public BroadReciver setReceiveFilter(String tag,BroadReciver.BroadcastReceiverListener listener){
        IntentFilter filter = new IntentFilter();
        filter.addAction(tag);

        if (locateContext != null){
            BroadReciver reciver = new BroadReciver(locateContext,listener);
            locateContext.registerReceiver(reciver,filter);
            broadReciverMap.put(MainActivity.class.getName(),reciver);
            return reciver;
        }
        return  null;
    }

    public void unSetReciverFilter(BroadReciver reciver){
        locateContext.unregisterReceiver(reciver);
    }



}
