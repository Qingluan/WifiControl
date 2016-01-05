package com.qingluan.darkh.wificontroll;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.qingluan.darkh.wificontroll.Config.DBHandler;
import com.qingluan.darkh.wificontroll.Config.arguments;
import com.qingluan.darkh.wificontroll.IO.ToastShow;
import com.qingluan.darkh.wificontroll.Widgets.DBSettingListAdapter;

import java.util.List;


public class NotConnectActivity extends Activity {
    Context context;
    ToastShow toast;
    Button bt_db_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_connect);
        context = NotConnectActivity.this;
        String msg = context.getString(R.string.try_connect);
        ProgressDialog.show(NotConnectActivity.this,"Info",msg,true,true);
        toast = new ToastShow(context);
        bt_db_setting = (Button)findViewById(R.id.bt_db_ips);

        final DBHandler dbHandler = new DBHandler(context);
        final List<String[]> ip_items = dbHandler.getAllIpList();

        bt_db_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.item_db_setting,null);
                final AlertDialog dialog = new AlertDialog.Builder(context).setView(linearLayout).setTitle("DB settings").show();

                        /*
                         init UI in alert dialog
                         */
                ListView lv_db_settings = (ListView) linearLayout.findViewById(R.id.lv_db);
                Button bt_add_db_item = (Button) linearLayout.findViewById(R.id.bt_db_add_one);
                final EditText et_tag = (EditText) linearLayout.findViewById(R.id.et_db_add_tag);
                final EditText et_ip = (EditText) linearLayout.findViewById(R.id.et_db_add_ip);
                final DBSettingListAdapter adapter = new DBSettingListAdapter(context,ip_items);
                lv_db_settings.setAdapter(adapter);
//                toast.show(String.valueOf(adapter.getCount()));

                        /*
                          add one handler for add item in db ;
                         */
                bt_add_db_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ip = et_ip.getText().toString();
                        String tag = et_tag.getText().toString();
                        if (ip.length() > 12 && tag.length() > 1){
                            long size = dbHandler.addOne(tag,ip);

                            adapter.addOne(new String[]{tag,ip});
                            toast.show("add one ip to DB :"+tag+":"+ip+String.valueOf(size));
                        }else{
                            toast.show(context.getString(R.string.NULL_ERROR));
                        }
                    }
                });
                lv_db_settings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String[] items = (String[]) adapter.getItem(position);
                        arguments.HOST = items[1];
                        toast.show(context.getString(R.string.changed_result)+ arguments.HOST);
                        dialog.dismiss();
                        Intent myIntent = new Intent(NotConnectActivity.this, MainActivity.class);
                        startActivity(myIntent);
                        finish();
                    }
                });
//                lv_db_settings.setOnItemClickListener(new .OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        toast.show(view.getTag().toString());
//                    }
//                });


            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent myIntent = new Intent(NotConnectActivity.this, MainActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_not_connect, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
