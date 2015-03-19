package com.qingluan.darkh.wificontroll.Widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.qingluan.darkh.wificontroll.Config.DATA;
import com.qingluan.darkh.wificontroll.IO.AsySocket;
import com.qingluan.darkh.wificontroll.IO.Talking;
import com.qingluan.darkh.wificontroll.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by darkh on 3/19/15.
 */
public class ChooseScreenOptionsListAdapter extends BaseAdapter {

    Map<String,Integer> data = new HashMap<String, Integer>();
    Context external_context;
    TextView tv_item_resolution;

    String [] keys;
    onAlertListViewClickListener listener ;

    public ChooseScreenOptionsListAdapter (Context context) {
        external_context = context;
        data.put(DATA.N_SCREEN_ONE, DATA.SCREEN_ONE);
        data.put(DATA.N_SCREEN_TWO, DATA.SCREEN_TWO);
        data.put(DATA.N_SCREEN_DEFAULT,DATA.SCREEN_DEFAULT);


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
    public Integer getItem(int position) {
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

                int number = data.get(((TextView)v).getText().toString());
                if (listener != null){
                    listener.afterClick(number,((TextView)v).getText().toString());

                }


            }
        });
        return convertView;
    }

    public interface onAlertListViewClickListener{
        public void afterClick(int key,String name);
    }
}
