package com.qingluan.darkh.wificontroll;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.qingluan.darkh.wificontroll.Config.DATA;
import com.qingluan.darkh.wificontroll.IO.Talking;
import com.qingluan.darkh.wificontroll.Services.BroadcastNotifer;
import com.qingluan.darkh.wificontroll.Widgets.SectionsPagerAdapter;


public class MainActivity extends Activity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager main_pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Talking.ConnectTest(MainActivity.this);
        __init_UI(); // init UI
        __init_DATA();
    }


    /*
        inti UI Widgets here
     */

    private  void __init_UI(){
        main_pages = (ViewPager) this.findViewById(R.id.pager);



        mSectionsPagerAdapter = new SectionsPagerAdapter(main_pages,getFragmentManager(),this,4);

        main_pages.setAdapter(mSectionsPagerAdapter);
    }

    private void __init_DATA(){
        DATA.init();
    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(BroadcastNotifer.broadReciverMap.get(MainActivity.class.getName()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
