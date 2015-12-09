package com.qingluan.darkh.wificontroll.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by darkh on 3/15/15.
 */
public class DATA {



    private static Map<String,byte[]> settings = new HashMap<String, byte[]>();

    // -- - - - - -- - - - -  key area - - - - - - - - -- - - - - - - - - - - -

    public static final String RESOLUTION = "resolution";
    public static final String SCREEN_OPTIONS_SETTING = "screen_option_select";
    public static final String HORIZON_POINT = "horizontal_point";
    public static final String HORIZON_POSITION = "horizontal_position";
    public static final String VERTICAL_POINT = "vertical_point";
    public static final String VERTICAL_POSITION = "vertical_position";

    //  - - - - - - - - - - - - - bytes area - - - - - - - - - - - - - -- - - -

    public static final byte[] B_RESOLUTION = {0x00 };
    public static final byte[] B_SCREEN_OPTIONS_SETTING = {0x00};
    public static final byte[] B_HORIZON_POINT = { 0x00 } ;
    public static final byte[] B_HORIZON_POSITION = {0x00};
    public static final byte[] B_VERTICAL_POINT = {0x00};
    public static final byte[] B_VERTICAL_POSITION = {0x00 };

    public static final String N_SCREEN_ONE = "缩放画面 （1）";
    public static final String N_SCREEN_TWO = "缩放画面 （2）";
    public static final String N_SCREEN_DEFAULT = "屏幕参数";

    public static final int LIGHT_TYPE = 1;
    public static final int COM_TYPE = 2;
    public static final int SATURATION_TYPE = 1;

    public static final int SCREEN_ONE = 1 ;
    public static final int SCREEN_TWO = 2 ;
    public static final int SCREEN_DEFAULT = 0 ;

//    XP330切换信号源命令
//    CMD: 0x03   设置通道输入源
//    AA 00 03 01 01 00 00 00 03 0D  //AV1
//    AA 00 03 01 02 00 00 00 00 0D  //AV2
//    AA 00 03 01 03 00 00 00 01 0D  //AV3
//    AA 00 03 01 05 00 00 00 07 0D  //VGA1
//    AA 00 03 01 0A 00 00 00 08 0D  //VGA2
//    AA 00 03 01 06 00 00 00 04 0D  //DVI1
//    AA 00 03 01 0B 00 00 00 09 0D  //DVI2
//    AA 00 03 01 07 00 00 00 05 0D  //HDMI1
//    AA 00 03 01 08 00 00 00 0A 0D  //HDMI2

    public static final String AV1 = "AV1" ;
    public static final String AV2 = "AV2" ;
    public static final String AV3 = "AV3" ;
    public static final String VGA1 = "VGA1" ;
    public static final String VGA2 = "VGA2" ;
    public static final String DVI1 = "DVI1" ;
    public static final String DVI2 = "DVI2" ;
    public static final String HDMI1 = "HDMI1" ;
    public static final String HDMI2 = "HDMI2" ;
    public static final String VPBPR = "YPBPR";
    public static final String USB1 = "USB1";
    public static final String USB2 = "USB2";
    public static final String CVBS4 = "CVBS4";

    public static final byte[] B_CVBS4 = { (byte)0xaa,(byte) 0x00 ,(byte)0x03 , (byte)0x01 ,(byte)0x0C ,(byte)0x00,(byte)0x00 ,(byte)0x00 ,(byte)0x03 ,(byte)0x0D  };
    public static final byte[] B_USB1 = { (byte)0xaa,(byte) 0x00 ,(byte)0x03 , (byte)0x01 ,(byte)0x0D ,(byte)0x00,(byte)0x00 ,(byte)0x00 ,(byte)0x03 ,(byte)0x0D  };
    public static final byte[] B_USB2 = { (byte)0xaa,(byte) 0x00 ,(byte)0x03 , (byte)0x01 ,(byte)0x0E ,(byte)0x00,(byte)0x00 ,(byte)0x00 ,(byte)0x03 ,(byte)0x0D  };
    public static final byte[] B_VPBPR = { (byte)0xaa,(byte) 0x00 ,(byte)0x03 , (byte)0x01 ,(byte)0x04 ,(byte)0x00,(byte)0x00 ,(byte)0x00 ,(byte)0x03 ,(byte)0x0D  };
    public static final byte[] B_TEST = { (byte)0xaa,(byte) 0x00 ,(byte)0x03 , (byte)0x01 ,(byte)0x01 ,(byte)0x00,(byte)0x00 ,(byte)0x00 ,(byte)0x03 ,(byte)0x0D };
    public static final byte[] B_AV1 = { (byte)0xAA,(byte)0x00,(byte)0x03,(byte)0x01,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x03,(byte)0x0D };
    public static final byte[] B_AV2 = { (byte)0xAA,(byte)0x00,(byte)0x03,(byte)0x01,(byte)0x02,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x0D };
    public static final byte[] B_AV3 = { (byte)0xAA,(byte)0x00,(byte)0x03,(byte)0x01,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x0D };
    public static final byte[] B_VGA1 = { (byte)0xAA,(byte)0x00,(byte)0x03,(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x07,(byte)0x0D };
    public static final byte[] B_VGA2 = { (byte)0xAA,(byte)0x00,(byte)0x03,(byte)0x01,(byte)0x0A,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x0D };
    public static final byte[] B_DVI1 = { (byte)0xAA,(byte)0x00,(byte)0x03,(byte)0x01,(byte)0x06,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x0D };
    public static final byte[] B_DVI2 = { (byte)0xAA,(byte)0x00,(byte)0x03,(byte)0x01,(byte)0x0B,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x09,(byte)0x0D };
    public static final byte[] B_HDMI1 = { (byte)0xAA,(byte)0x00,(byte)0x03,(byte)0x01,(byte)0x07,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x05,(byte)0x0D };
    public static final byte[] B_HDMI2 = { (byte)0xAA,(byte)0x00,(byte)0x03,(byte)0x01,(byte)0x08,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x0A,(byte)0x0D };

//    XP330切换输出分辨率命令
//    CMD: 0x05   设置输出分辨率
//    AA 00 05 01 00 00 00 00 04 0D	//640x480
//    AA 00 05 02 00 00 00 00 07 0D	//800x600
//    AA 00 05 03 00 00 00 00 06 0D	//1024x768
//    AA 00 05 04 00 00 00 00 01 0D	//1280x1024
//    AA 00 05 05 00 00 00 00 00 0D	//1366x768
//    AA 00 05 06 00 00 00 00 03 0D	//1440x900
//    AA 00 05 07 00 00 00 00 02 0D	//1600x1200
//    AA 00 05 08 00 00 00 00 0D 0D	//1680x1050
//    AA 00 05 09 00 00 00 00 0C 0D	//1920x1080
//    AA 00 05 0A 00 00 00 00 0F 0D	//1600x900

    public static final String K640_480 = "640x480" ;
    public static final String K800_600 = "800x600" ;
    public static final String K1024_768 = "1024x768" ;
    public static final String K1280_1024 = "1280x1024" ;
    public static final String K1366_768 = "1366x768" ;
    public static final String K1440_900 = "1440x900" ;
    public static final String K1600_1200 = "1600x1200" ;
    public static final String K1680_1050 = "1680x1050" ;
    public static final String K1920_1080 = "1920x1080" ;
    public static final String K1600_900 = "1600x900" ;

    public static final byte[] B_640_480 = { (byte)0xAA,(byte)0x00,(byte)0x05,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x0D };
    public static final byte[] B_800_600 = { (byte)0xAA,(byte)0x00,(byte)0x05,(byte)0x02,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x07,(byte)0x0D };
    public static final byte[] B_1024_768 = { (byte)0xAA,(byte)0x00,(byte)0x05,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x06,(byte)0x0D };
    public static final byte[] B_1280_1024 = { (byte)0xAA,(byte)0x00,(byte)0x05,(byte)0x04,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x0D };
    public static final byte[] B_1366_768 = { (byte)0xAA,(byte)0x00,(byte)0x05,(byte)0x05,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x0D };
    public static final byte[] B_1440_900 = { (byte)0xAA,(byte)0x00,(byte)0x05,(byte)0x06,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x03,(byte)0x0D };
    public static final byte[] B_1600_1200 = { (byte)0xAA,(byte)0x00,(byte)0x05,(byte)0x07,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x02,(byte)0x0D };
    public static final byte[] B_1680_1050 = { (byte)0xAA,(byte)0x00,(byte)0x05,(byte)0x08,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x0D,(byte)0x0D };
    public static final byte[] B_1920_1080 = { (byte)0xAA,(byte)0x00,(byte)0x05,(byte)0x09,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x0C,(byte)0x0D };
    public static final byte[] B_1600_900 = { (byte)0xAA,(byte)0x00,(byte)0x05,(byte)0x0A,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x0F,(byte)0x0D };

//    CMD: 0x06   设置屏幕参数
//    AA 00 06 02 00 02 00 02 04 0D   //screen_scale512x512
//    AA 00 06 01 00 01 00 01 07 0D   //screen_position256x256
    public static final String SCREEN_SCALE512_512 = "screen_scale512x512" ;
    public static final String SCREEN_POSITION256_256 = "screen_position256x256" ;


    public static final byte[] B_SCREEN_SCALE512_512 = { (byte)0xAA,(byte)0x00,(byte)0x06,(byte)0x02,(byte)0x00,(byte)0x02,(byte)0x00,(byte)0x02,(byte)0x04,(byte)0x0D };
    public static final byte[] B_SCREEN_POSITION256_256 = { (byte)0xAA,(byte)0x00,(byte)0x06,(byte)0x01,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x01,(byte)0x07,(byte)0x0D };

    public static void  init(){
        DATA.settings.put(RESOLUTION,B_RESOLUTION);
        DATA.settings.put(SCREEN_OPTIONS_SETTING,B_SCREEN_OPTIONS_SETTING);
        DATA.settings.put(HORIZON_POINT,B_HORIZON_POINT);
        DATA.settings.put(HORIZON_POSITION,B_HORIZON_POSITION);
        DATA.settings.put(VERTICAL_POINT,B_VERTICAL_POINT);
        DATA.settings.put(VERTICAL_POSITION,B_VERTICAL_POSITION);

        DATA.settings.put("B_TEST",B_TEST);


        DATA.settings.put(K640_480,B_640_480);
        DATA.settings.put(K800_600,B_800_600);
        DATA.settings.put(K1024_768,B_1024_768);
        DATA.settings.put(K1280_1024,B_1280_1024);
        DATA.settings.put(K1366_768,B_1366_768);
        DATA.settings.put(K1440_900,B_1440_900);
        DATA.settings.put(K1600_1200,B_1600_1200);
        DATA.settings.put(K1680_1050,B_1680_1050);
        DATA.settings.put(K1920_1080,B_1920_1080);
        DATA.settings.put(K1600_900,B_1600_900);

        DATA.settings.put(SCREEN_SCALE512_512,B_SCREEN_SCALE512_512);
        DATA.settings.put(SCREEN_POSITION256_256,B_SCREEN_POSITION256_256);

        DATA.settings.put(AV1,B_AV1);
        DATA.settings.put(AV2,B_AV2);
        DATA.settings.put(AV3,B_AV3);
        DATA.settings.put(VGA1,B_VGA1);
        DATA.settings.put(VGA2,B_VGA2);
        DATA.settings.put(DVI1,B_DVI1);
        DATA.settings.put(DVI2,B_DVI2);
        DATA.settings.put(HDMI1,B_HDMI1);
        DATA.settings.put(HDMI2,B_HDMI2);
    }

    public static String[]  XP320 = new String[]{ VGA2,DVI2,HDMI2,USB1,USB2,CVBS4};
    public static String[]  XP330 = new String[]{ VPBPR,USB1,USB2,CVBS4};
    public static String[]  XP350 = new String[]{ VGA2,DVI2,USB1,USB2,CVBS4};
    public static String[]  XP360 = new String[]{ VGA2,DVI2,USB1,USB2,CVBS4};
    public static String[]  XP380 = new String[]{ VGA2,VPBPR,HDMI2,CVBS4};
    public static String[]  XP520 = new String[]{ AV3,VGA2,DVI2,VPBPR,USB1,USB2,HDMI2,CVBS4};
    public static String[]  XP530 = new String[]{ VPBPR,USB1,USB2};
    public static String[]  XP550 = new String[]{ VPBPR,AV3,VGA2,DVI2,USB1,USB2,HDMI2,CVBS4};
    public static String[]  XP720 = new String[]{ AV3,VPBPR,HDMI1,HDMI2,VPBPR};
    public static String[]  XP723 = new String[]{ AV3,VGA2,VPBPR,DVI2,HDMI2,VPBPR};
    public static String[]  XP726 = new String[]{ AV3,HDMI2,VPBPR,USB1,USB2,CVBS4};

    public static String  N_XP320 = "XP320";
    public static String  N_XP330 = "XP330";
    public static String  N_XP350 = "XP350";
    public static String  N_XP360 = "XP360";
    public static String  N_XP380 = "XP380";
    public static String  N_XP520 = "XP520";
    public static String  N_XP530 = "XP530";
    public static String  N_XP550 = "XP550";
    public static String  N_XP720 = "XP720";
    public static String  N_XP723 = "XP723";
    public static String  N_XP726 = "XP726";

    public static String TEST_DATA_STRING = "Hello Silly B!";
    public static byte[] TEST_DATA = TEST_DATA_STRING.getBytes();

    public static byte[] getSettings(String key){
        return DATA.settings.get(key);
    }

    public static  void setSettings(String key ,byte[] data){
        DATA.settings.put(key,data);
    }


}
