package com.qingluan.darkh.wificontroll.Widgets;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qingluan.darkh.wificontroll.Config.DATA;
import com.qingluan.darkh.wificontroll.IO.AsySocket;
import com.qingluan.darkh.wificontroll.IO.Talking;
import com.qingluan.darkh.wificontroll.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by darkh on 3/15/15.
 */
public class SingalSwitchListViewAdapter extends BaseAdapter {
    Map<String,byte[]> data = new HashMap<String, byte[]>();
    Context external_context;
    Button bt_switch_signal;

    String [] keys;

    public SingalSwitchListViewAdapter(Context context ){
        external_context = context;
        data.put(DATA.AV1,DATA.B_AV1);
        data.put(DATA.AV2,DATA.B_AV2);
        data.put(DATA.AV3,DATA.B_AV3);
        data.put(DATA.VGA1,DATA.B_VGA1);
        data.put(DATA.VGA2,DATA.B_VGA2);
        data.put(DATA.DVI1,DATA.B_DVI1);
        data.put(DATA.DVI2,DATA.B_DVI2);
        data.put(DATA.HDMI1,DATA.B_HDMI1);
        data.put(DATA.HDMI2,DATA.B_HDMI2);
        data.put(DATA.CVBS4,DATA.B_CVBS4);
        data.put(DATA.VPBPR,DATA.B_VPBPR);
        data.put(DATA.USB1,DATA.B_USB1);
        data.put(DATA.USB2,DATA.B_USB2);
        keys = data.keySet().toArray(new String[] {});
    }

    public void resetDisplayItem(){
        data.put(DATA.AV1,DATA.B_AV1);
        data.put(DATA.AV2,DATA.B_AV2);
        data.put(DATA.AV3,DATA.B_AV3);
        data.put(DATA.VGA1,DATA.B_VGA1);
        data.put(DATA.VGA2,DATA.B_VGA2);
        data.put(DATA.DVI1,DATA.B_DVI1);
        data.put(DATA.DVI2,DATA.B_DVI2);
        data.put(DATA.HDMI1,DATA.B_HDMI1);
        data.put(DATA.HDMI2,DATA.B_HDMI2);
        data.put(DATA.CVBS4,DATA.B_CVBS4);
        data.put(DATA.VPBPR,DATA.B_VPBPR);
        data.put(DATA.USB1,DATA.B_USB1);
        data.put(DATA.USB2,DATA.B_USB2);
    }

    public void hideItems(String[] keys){
        if (keys == null){
            Log.d("Tag 8 88 8 8 8 8 ","no keys pass ");
            return;
        }
        for (String key : keys){
            data.remove(key);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public byte[] getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater) external_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_listview_singal_switch,null);
        bt_switch_signal = (Button) convertView.findViewById(R.id.bt_switch_singal);
        Log.d("Singal ", keys[position]);
        bt_switch_signal.setText(keys[position]);
        bt_switch_signal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetDisplayItem();
                byte[] B_data = data.get(((TextView) v).getText().toString());
                Log.d("Tag - - - - -",String.valueOf(data));
                Talking.sendInfo(external_context, B_data, new AsySocket.AsyReadListener() {
                    @Override
                    public void onRead(String data) {
                        Toast.makeText(external_context, data, Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        return convertView;
    }

}
