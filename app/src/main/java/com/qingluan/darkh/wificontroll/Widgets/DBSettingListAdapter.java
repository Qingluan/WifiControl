package com.qingluan.darkh.wificontroll.Widgets;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qingluan.darkh.wificontroll.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by darkh on 1/5/16.
 */
public class DBSettingListAdapter extends BaseAdapter {
    List<String[]> all_ips;
    Context context;
    TextView tv_tag;
    TextView tv_ip;

    public DBSettingListAdapter(Context context, List<String[]> init_values){
        all_ips = init_values;

        this.context = context;
    }

    public int addOne(String[] item){
        all_ips.add(item);
        this.notifyDataSetChanged();
        return  all_ips.size();
    }

    @Override
    public int getCount() {
        return all_ips.size();
    }

    @Override
    public Object getItem(int position) {
        return all_ips.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_db_list,null);
        tv_tag = (TextView) convertView.findViewById(R.id.tv_db_item_tag);
        tv_ip = (TextView) convertView.findViewById(R.id.tv_db_item_ip);


        String[] items = this.all_ips.get(position);
        tv_tag.setText(items[0]);
        tv_ip.setText(items[1]);

        return convertView;
    }

//    public interface onClickItemListener {
//        public void onClickItem(String [] items);
//    }
}
