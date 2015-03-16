package com.qingluan.darkh.wificontroll.Config;

/**
 * Created by darkh on 3/13/15.
 */
public class arguments {

    public static final int FRAGMENT_SCREEN_SETTING  = 0;
    public static final int FRAGMENT_SIGNAL_SWITCH  = 1;
    public static final int FRAGMENT_FUNCTION_SETTING  = 2;
    public static final int FRAGMENT_NETWORK_TEST  = 3;


    public static final byte[] TEST_BYTES = {0x00, 0x01, 0x02 , 0x03, 0xf };

    public static final int NETWORK_PORT = 8080;
    public static final String HOST = "192.168.59.3";
    public static final String BROADCAST_RECV_FROM_SOCKET = "com.qingluan.darkh.recv_from_socket";
}
