package com.qingluan.darkh.wificontroll.Widgets;

import android.app.ProgressDialog;
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
public class ChooseTypeDeviceListAdapter   extends BaseAdapter{
    Map<String,String[]> data = new HashMap<String, String[]>();
    Context external_context;
    TextView tv_item_resolution;

    String [] keys;
    onAlertListViewClickListener listener ;
    public ChooseTypeDeviceListAdapter (Context context){
        external_context = context;
        data.put(DATA.N_XP320,DATA.XP320);
        data.put(DATA.N_XP330,DATA.XP330);
        data.put(DATA.N_XP350,DATA.XP350);
        data.put(DATA.N_XP380,DATA.XP380);
        data.put(DATA.N_XP520,DATA.XP520);
        data.put(DATA.N_XP550,DATA.XP550);
        data.put(DATA.N_XP530,DATA.XP530);
        data.put(DATA.N_XP360,DATA.XP360);
        data.put(DATA.N_XP720,DATA.XP720);
        data.put(DATA.N_XP723,DATA.XP723);
        data.put(DATA.N_XP726,DATA.XP726);
        keys = data.keySet().toArray(new String[]{});
    }


    public void setOnAlertListViewClickListener(onAlertListViewClickListener onAlertListViewClickListener){
        this.listener = onAlertListViewClickListener;
    }

    public  void hideItems(SingalSwitchListViewAdapter adapter ,String[] keys){
        adapter.resetDisplayItem();
        adapter.hideItems(keys);
    }

    public String [] getData(String key){
        return  data.get(key);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String[] getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater) external_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_listview_textview,null);
        tv_item_resolution = (TextView) convertView.findViewById(R.id.item_tv_resolution);
        tv_item_resolution.setText(keys[position]);
        tv_item_resolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog dialog =  ProgressDialog.show(external_context,"Info","Loading ...",true,false);
                byte[] data = DATA.getSettings(((TextView) v).getText().toString());
//                Talking.sendInfo(external_context, data, new AsySocket.AsyReadListener() {
//                    @Override
//                    public void onRead(String data) {
//                        Toast.makeText(external_context, data, Toast.LENGTH_SHORT).show();
//                    }
//                });
                String[] keys =  getData(((TextView) v).getText().toString());
                if (listener != null){
                    listener.afterClick(keys);
                    dialog.dismiss();
                }


            }
        });
        return convertView;
    }

    public interface onAlertListViewClickListener{
        public void afterClick(String [] exclusionKeys);
    }
}
