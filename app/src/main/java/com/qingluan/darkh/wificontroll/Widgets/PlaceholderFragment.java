package com.qingluan.darkh.wificontroll.Widgets;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.text.TextPaint;
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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qingluan.darkh.wificontroll.Config.DATA;
import com.qingluan.darkh.wificontroll.Config.arguments;
import com.qingluan.darkh.wificontroll.IO.AsySocket;
import com.qingluan.darkh.wificontroll.IO.Command;
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

    private static int ScreenStatus = DATA.SCREEN_DEFAULT;


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
    Button bt_send_info_scale;
    Button bt_set_resolution;
    Button bt_options_select;
    EditText et_horizontal_point;
    EditText et_horizontal_position;
    EditText et_vertical_point;
    EditText et_vertical_position;


    // - - - - -  fragment_switch_signal - - - - - - - - - -//
    Button bt_choose_resolution;
    Button bt_choose_screen_options;
    Button bt_choose_type_device;
    // - - - - - - fragment_function_setting - - - - - - - - - //
    RadioGroup Selects;
    SeekBar sb_setting_contrast;
    SeekBar sb_setting_light;
    SeekBar sb_setting_saturation;
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
                et_horizontal_point  = (EditText) rootView.findViewById(R.id.edit_horizontal_point);
                et_horizontal_position = (EditText)rootView.findViewById(R.id.edit_horizontal_position);
                et_vertical_point = (EditText) rootView.findViewById(R.id.edit_vertical_point);
                et_vertical_position = (EditText) rootView.findViewById(R.id.edit_vertical_position);


                bt_options_select = (Button) rootView.findViewById(R.id.bt_choose_screen_options);
                bt_send_info = (Button)rootView.findViewById(R.id.bt_send_info);
                bt_send_info_scale = (Button)rootView.findViewById(R.id.bt_send_info_point);
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
                bt_options_select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LinearLayout linearLayout  ;
                        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        linearLayout = (LinearLayout) inflater.inflate(R.layout.item_resoltion_setting_list,null);

                        final AlertDialog dialog = new AlertDialog.Builder(context).setView(linearLayout).setTitle("选择分辨率").show();

                        ListView lv = (ListView) linearLayout.findViewById(R.id.lv_resolution);
                        ChooseScreenOptionsListAdapter adapter = new ChooseScreenOptionsListAdapter(context);
                        lv.setAdapter(adapter);
                        adapter.setOnAlertListViewClickListener(new ChooseScreenOptionsListAdapter.onAlertListViewClickListener() {
                            @Override
                            public void afterClick(int number,String selectedName) {
                                PlaceholderFragment.ScreenStatus = number;
                                bt_options_select.setText(selectedName);
                                dialog.dismiss();
                            }
                        });

                    }
                });

                bt_send_info_scale.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int vertical_point = Integer.valueOf(et_vertical_point.getText().toString() );
                        int horizontal_point = Integer.valueOf(et_horizontal_point.getText().toString() );


                        if (ScreenStatus == DATA.SCREEN_DEFAULT) {
                            Command command = new Command();
                            byte[] b_data = command.ScaleOrPosition(DATA.SCREEN_DEFAULT,Command.SCALE, vertical_point, horizontal_point);

                            Talking.sendInfo(context,b_data,new AsySocket.AsyReadListener(){

                                @Override
                                public void onRead(String data) {
                                    Toast.makeText(context,data,Toast.LENGTH_SHORT).show();
                                }

                            });
                            Log.d("change ","send to server");
                        }else if (ScreenStatus == DATA.SCREEN_ONE){
                            Command cmd = new Command();

                            byte[]  bb_DATA = cmd.ScaleOrPosition(DATA.SCREEN_ONE,Command.SCALE,vertical_point, horizontal_point);
                            Talking.sendInfo(context,bb_DATA,new AsySocket.AsyReadListener(){

                                @Override
                                public void onRead(String data) {
                                    Toast.makeText(context,data,Toast.LENGTH_SHORT).show();
                                }

                            });
                        }else if(ScreenStatus == DATA.SCREEN_TWO){
                            Command cmd = new Command();

                            byte[]  bb_DATA = cmd.ScaleOrPosition(DATA.SCREEN_TWO,Command.SCALE,vertical_point, horizontal_point);
                            Talking.sendInfo(context,bb_DATA,new AsySocket.AsyReadListener(){

                                @Override
                                public void onRead(String data) {
                                    Toast.makeText(context,data,Toast.LENGTH_SHORT).show();
                                }

                            });
                        }

                        /*
                            send data to server
                         */


                    }
                });

                bt_send_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        /*
                        //get byte[] just like this .
                        */
                        int vertical_position =  Integer.valueOf(et_vertical_position.getText().toString() );
                        int horizontal_position = Integer.valueOf(et_horizontal_position.getText().toString() );
//                        Command command = new Command();
//                        Byte[] b_data = command.ScaleOrPosition(Command.POSITON,vertical_position,horizontal_position);
//                        Log.d("Position ","Position");
//                        byte[] bb_DATA = Command.toPrimitives(b_data);
//                        /*
//                            send data to server
//                         */
//                        Talking.sendInfo(context,bb_DATA,new AsySocket.AsyReadListener(){
//
//                            @Override
//                            public void onRead(String data) {
//                                Toast.makeText(context,data,Toast.LENGTH_SHORT).show();
//                            }
//
//                        });
//                        Log.d("change ","send to server");

                        if (ScreenStatus == DATA.SCREEN_DEFAULT) {
                            Command command = new Command();
                            byte[] b_data = command.ScaleOrPosition(DATA.SCREEN_DEFAULT,Command.POSITON, vertical_position, horizontal_position);

                            Talking.sendInfo(context,b_data,new AsySocket.AsyReadListener(){

                                @Override
                                public void onRead(String data) {
                                    Toast.makeText(context,data,Toast.LENGTH_SHORT).show();
                                }

                            });
                            Log.d("change ","send to server");
                        }else if (ScreenStatus == DATA.SCREEN_ONE){
                            Command cmd = new Command();

                            byte[]  bb_DATA = cmd.ScaleOrPosition(DATA.SCREEN_ONE,Command.POSITON,vertical_position, horizontal_position);
                            Talking.sendInfo(context,bb_DATA,new AsySocket.AsyReadListener(){

                                @Override
                                public void onRead(String data) {
                                    Toast.makeText(context,data,Toast.LENGTH_SHORT).show();
                                }

                            });
                        }else if(ScreenStatus == DATA.SCREEN_TWO){
                            Command cmd = new Command();

                            byte[]  bb_DATA = cmd.ScaleOrPosition(DATA.SCREEN_TWO,Command.POSITON,vertical_position, horizontal_position);
                            Talking.sendInfo(context,bb_DATA,new AsySocket.AsyReadListener(){

                                @Override
                                public void onRead(String data) {
                                    Toast.makeText(context,data,Toast.LENGTH_SHORT).show();
                                }

                            });
                        }
                    }
                });



                break;
            case R.layout.fragment_signal_switch:
                ListView lv_switch_signal = (ListView)rootView.findViewById(R.id.lv_singal_switch);
                final SingalSwitchListViewAdapter adapter_signal = new SingalSwitchListViewAdapter(context);
                lv_switch_signal.setAdapter(adapter_signal);
                bt_choose_type_device = (Button)rootView.findViewById(R.id.bt_choose_type_device);
//                bt_choose_resolution = (Button ) rootView.findViewById(R.id.bt_choose_resolution);
                bt_choose_type_device.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LinearLayout linearLayout  ;
                        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        linearLayout = (LinearLayout) inflater.inflate(R.layout.item_resoltion_setting_list,null);

                        final AlertDialog dialog = new AlertDialog.Builder(context).setView(linearLayout).setTitle("选择分辨率").show();

                        ListView lv = (ListView) linearLayout.findViewById(R.id.lv_resolution);

//                        ResolutionOptionsListAdapter adapter = new ResolutionOptionsListAdapter(context);
                        final ChooseTypeDeviceListAdapter adapter = new ChooseTypeDeviceListAdapter(context);
                        lv.setAdapter(adapter);
                        adapter.setOnAlertListViewClickListener(new ChooseTypeDeviceListAdapter.onAlertListViewClickListener() {
                            @Override
                            public void afterClick(String [] removedItmes) {
                                adapter.hideItems(adapter_signal,removedItmes);
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
            case R.layout.fragment_function_setting:
                sb_setting_contrast = (SeekBar)rootView.findViewById(R.id.SB_contrast);
                sb_setting_light = (SeekBar)rootView.findViewById(R.id.SB_light);
                sb_setting_saturation = (SeekBar) rootView.findViewById(R.id.SB_saturation);
                sb_setting_contrast.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        Command cmd = new Command();
                        Log.d("contrast :",String.valueOf(progress));
                        byte [] data = cmd.ColorSetting(DATA.COM_TYPE,progress);
                        Talking.sendInfo(context,data, new AsySocket.AsyReadListener(){

                            @Override
                            public void onRead(String data) {
                                Toast.makeText(context,data,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                sb_setting_saturation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        Log.d("saturation :",String.valueOf(progress));
                        Command cmd = new Command();
                        byte [] data = cmd.ColorSetting(DATA.SATURATION_TYPE,progress);
                        Talking.sendInfo(context,data, new AsySocket.AsyReadListener(){

                            @Override
                            public void onRead(String data) {
                                Toast.makeText(context,data,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                sb_setting_light.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        Command cmd = new Command();
                        Log.d("light :",String.valueOf(progress));
                        byte [] data = cmd.ColorSetting(DATA.LIGHT_TYPE,progress);
                        Talking.sendInfo(context,data, new AsySocket.AsyReadListener(){

                            @Override
                            public void onRead(String data) {
                                Toast.makeText(context,data,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                break;

        }

        return  rootView;

    }


}
