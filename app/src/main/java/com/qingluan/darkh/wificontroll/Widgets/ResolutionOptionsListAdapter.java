package com.qingluan.darkh.wificontroll.Widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

import com.qingluan.darkh.wificontroll.Config.DATA;
import com.qingluan.darkh.wificontroll.IO.AsySocket;
import com.qingluan.darkh.wificontroll.IO.Talking;
import com.qingluan.darkh.wificontroll.R;

/**
 * Created by darkh on 3/15/15.
 */
public class ResolutionOptionsListAdapter extends BaseAdapter {
    Map<String,byte[]> data = new HashMap<String, byte[]>();
    Context external_context;
    TextView tv_item_resolution;

    String [] keys;
    onAlertListViewClickListener listener ;
    public ResolutionOptionsListAdapter(Context context) {
        external_context = context;
        data.put(DATA.K640_480, DATA.B_640_480);
        data.put(DATA.K800_600, DATA.B_800_600);
        data.put(DATA.K1024_768, DATA.B_1024_768);
        data.put(DATA.K1280_1024, DATA.B_1280_1024);
        data.put(DATA.K1366_768, DATA.B_1366_768);
        data.put(DATA.K1440_900, DATA.B_1440_900);
        data.put(DATA.K1600_1200, DATA.B_1600_1200);
        data.put(DATA.K1680_1050, DATA.B_1680_1050);
        data.put(DATA.K1920_1080, DATA.B_1920_1080);
        data.put(DATA.K1600_900, DATA.B_1600_900);


        keys = data.keySet().toArray(new String[]{});
    }

    public void setOnAlertListViewClickListener(onAlertListViewClickListener onAlertListViewClickListener){
        this.listener = onAlertListViewClickListener;
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
        convertView = inflater.inflate(R.layout.item_listview_textview,null);
        tv_item_resolution = (TextView) convertView.findViewById(R.id.item_tv_resolution);
        tv_item_resolution.setText(keys[position]);
        tv_item_resolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] data = DATA.getSettings(((TextView)v).getText().toString());
                Talking.sendInfo(external_context,data,new AsySocket.AsyReadListener() {
                    @Override
                    public void onRead(String data) {
                        Toast.makeText(external_context,data,Toast.LENGTH_SHORT).show();
                    }
                });
                if (listener != null){
                    listener.afterClick();
                }


            }
        });
        return convertView;
    }

    public interface onAlertListViewClickListener{
        public void afterClick();
    }
}
