package com.qingluan.darkh.wificontroll.Widgets;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.qingluan.darkh.wificontroll.Config.DATA;
import com.qingluan.darkh.wificontroll.Config.arguments;
import com.qingluan.darkh.wificontroll.IO.AsySocket;
import com.qingluan.darkh.wificontroll.IO.Talking;
import com.qingluan.darkh.wificontroll.MainActivity;
import com.qingluan.darkh.wificontroll.R;

import java.io.UnsupportedEncodingException;

/**
 * Created by darkh on 3/13/15.
 */
public class PlaceholderFragment  extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";


    Context context;
    ViewPager viewPager;
    private View rootView;
    private  int fragment_layout_id;


    /*
        All View Widgets Here
     */

    Button bt_send;

    // - - - - - fragment_network_test - - - - - - - - - //
    Button bt_ping;
    EditText et_ip;
    TextView tv_dis;
    EditText et_test_command;
    // - - - - - fragment_screen_setting - - - - - - - - - //
    Button bt_send_info;
    Button bt_set_resolution;
    Button bt_options_select;
    EditText et_horizontal_point;
    EditText et_horizontal_position;
    EditText et_vertical_point;
    EditText et_vertical_position;

    // - - - - -  fragment_switch_signal - - - - - - - - - -//
    Button bt_choose_resolution;

    // - - - - - - fragment_function_setting - - - - - - - - - //
    RadioGroup Selects;
    /*
        - - - - - - END - - - - - - -
     */

    public static PlaceholderFragment newInstance(int sectionNumber,Context context,ViewPager viewPager) {

        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);

        fragment.setViewPager(viewPager);
        fragment.setContext(context);
        fragment.setArguments(args);
        return fragment;
    }


    public void  setViewPager(ViewPager viewPager){
        this.viewPager = viewPager;
    }

    public  void setContext(Context context){
        this.context =context;
    }


    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    private  void turnPage(int pageNumber){
        this.viewPager.setCurrentItem(pageNumber);
        this.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                //this is just a example
                byte[] data = DATA.getSettings(DATA.SCREEN_OPTIONS_SETTING);
                Talking.sendInfo(context,data,new AsySocket.AsyReadListener() {
                    @Override
                    public void onRead(String data) {

                    }
                });
                Log.d("change ","send to server");
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get page number
        int pageNumber = getArguments().getInt(ARG_SECTION_NUMBER);
        if (pageNumber == 0){
            fragment_layout_id = R.layout.fragment_screen_setting;
        }else if(pageNumber == 1) {
            fragment_layout_id = R.layout.fragment_signal_switch;

        }else if (pageNumber == 2){
            fragment_layout_id = R.layout.fragment_function_setting;
        }else if (pageNumber == 3){
            fragment_layout_id = R.layout.fragment_network_test;
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(fragment_layout_id,container,false);

        switch ( fragment_layout_id){
            case  R.layout.fragment_screen_setting :
                bt_send_info = (Button)rootView.findViewById(R.id.bt_send_info);
                bt_set_resolution = (Button)rootView.findViewById(R.id.bt_choose_resolution);
                bt_set_resolution.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LinearLayout linearLayout  ;
                        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        linearLayout = (LinearLayout) inflater.inflate(R.layout.item_resoltion_setting_list,null);

                        final AlertDialog dialog = new AlertDialog.Builder(context).setView(linearLayout).setTitle("选择分辨率").show();

                        ListView lv = (ListView) linearLayout.findViewById(R.id.lv_resolution);

                        ResolutionOptionsListAdapter adapter = new ResolutionOptionsListAdapter(context);
                        lv.setAdapter(adapter);
                        adapter.setOnAlertListViewClickListener(new ResolutionOptionsListAdapter.onAlertListViewClickListener() {
                            @Override
                            public void afterClick() {
                                dialog.dismiss();
                            }
                        });




                    }
                });
                bt_send_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        /*
                        //get byte[] just like this .
                        */
                        byte[] data = DATA.getSettings(DATA.RESOLUTION);

                        /*
                            send data to server
                         */
                        Talking.sendInfo(context,data,new AsySocket.AsyReadListener(){

                            @Override
                            public void onRead(String data) {

                            }

                        });
                        Log.d("change ","send to server");
                    }
                });



                break;
            case R.layout.fragment_signal_switch:
                ListView lv_switch_signal = (ListView)rootView.findViewById(R.id.lv_singal_switch);
                SingalSwitchListViewAdapter adapter = new SingalSwitchListViewAdapter(context);
                lv_switch_signal.setAdapter(adapter);

                bt_choose_resolution = (Button ) rootView.findViewById(R.id.bt_choose_resolution);
                bt_choose_resolution.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LinearLayout linearLayout  ;
                        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        linearLayout = (LinearLayout) inflater.inflate(R.layout.item_resoltion_setting_list,null);

                        final AlertDialog dialog = new AlertDialog.Builder(context).setView(linearLayout).setTitle("选择分辨率").show();

                        ListView lv = (ListView) linearLayout.findViewById(R.id.lv_resolution);

                        ResolutionOptionsListAdapter adapter = new ResolutionOptionsListAdapter(context);
                        lv.setAdapter(adapter);
                        adapter.setOnAlertListViewClickListener(new ResolutionOptionsListAdapter.onAlertListViewClickListener() {
                            @Override
                            public void afterClick() {
                                dialog.dismiss();
                            }
                        });



                    }
                });

                break;

            case R.layout.fragment_network_test:
                bt_ping = (Button)rootView.findViewById(R.id.bt_test_ip);
                et_ip = (EditText) rootView.findViewById(R.id.et_test_ip);
                tv_dis = (TextView) rootView.findViewById(R.id.tv_dis_test);
                et_test_command = (EditText)rootView.findViewById(R.id.et_test_command);


                bt_ping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ip = et_ip.getText().toString();
                        Toast.makeText(context,ip,Toast.LENGTH_SHORT).show();
                        if (ip.length() > 1){
                            AsySocket asySocket = new AsySocket(context,ip, arguments.NETWORK_PORT);
                            String key = et_test_command.getText().toString();
                            byte[] cmd = DATA.getSettings(key);

                            asySocket.AsySend(cmd);

                            asySocket.setAsyReadListener(new AsySocket.AsyReadListener() {
                                @Override
                                public void onRead(String data) {
                                    tv_dis.setText(data);
                                }
                            });
                        }
                    }
                });


                break;

        }

        return  rootView;

    }


}