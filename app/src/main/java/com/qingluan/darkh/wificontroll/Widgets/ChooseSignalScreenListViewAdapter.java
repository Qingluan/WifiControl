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
import com.qingluan.darkh.wificontroll.IO.Command;
import com.qingluan.darkh.wificontroll.IO.Talking;
import com.qingluan.darkh.wificontroll.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by darkh on 3/19/15.
 */
public class ChooseSignalScreenListViewAdapter extends BaseAdapter{
    Map<String,Integer> data = new HashMap<String, Integer>();
    Context external_context;
    TextView tv_item_resolution;

    String [] keys;
    onAlertListViewClickListener listener ;
    public ChooseSignalScreenListViewAdapter (Context context){
        external_context = context;
        data.put("画面1（单画面）",1);
        data.put("画面2",2);
        keys = data.keySet().toArray(new String[]{});
    }


    public void setOnAlertListViewClickListener(onAlertListViewClickListener onAlertListViewClickListener){
        this.listener = onAlertListViewClickListener;
    }

    public  void hideItems(SingalSwitchListViewAdapter adapter ,String[] keys){
        adapter.resetDisplayItem();
        adapter.hideItems(keys);
    }

    public Integer getData(String key){
        return  data.get(key);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater) external_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_listview_textview,null);
        tv_item_resolution = (TextView) convertView.findViewById(R.id.item_tv_resolution);
        tv_item_resolution.setText(keys[position]);
        tv_item_resolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog dialog =  ProgressDialog.show(external_context,"Info","Loading ...",true,false);
                Command cmd = new Command();
                int screenNumber = getData( ((TextView )v).getText().toString());

                Toast.makeText(external_context,"选中 "+((TextView )v).getText().toString(),Toast.LENGTH_SHORT).show();
                PlaceholderFragment.visual_number = screenNumber;

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
