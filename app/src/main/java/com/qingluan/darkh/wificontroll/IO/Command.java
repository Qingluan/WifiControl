package com.qingluan.darkh.wificontroll.IO;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by darkh on 3/17/15.
 */
public class Command {

    List<Byte> command = new ArrayList<Byte>();
    public static final byte POSITON = (byte) 1;
    public static final byte SCALE = (byte) 2;
    public  Command (){
        command.add((byte)170);
        command.add((byte)0);


    }



    public  Byte[]  VerticalHorizontal(int vertical_length,
                                             int horizontal_length){

        Log.d("point ",String.valueOf(vertical_length)+"| "+ String.valueOf(horizontal_length));
        this.command.add((byte)0x06);
        this.command.add((byte)2 );
        byte l_vertical_length = (byte) (vertical_length & 0x00ff);
        byte h_vertical_length = (byte) ( (vertical_length & 0xff00) >> 8 );
        byte l_horizontal_length = (byte) (horizontal_length & 0x00ff);
        byte h_horizontal_length = (byte) ( (horizontal_length & 0xff00) >> 8 );
        this.command.add(l_horizontal_length);
        this.command.add(h_horizontal_length);
        this.command.add(l_vertical_length);
        this.command.add(h_vertical_length);
        this.command.add((byte)7);
        this.command.add((byte)0x0d);


        Byte[] result= this.command.toArray(new Byte[]{});
        byte odd = Command.getCheck(result);
        result[8] =(byte )(odd);

        Log.d("tag ",String.valueOf((int)odd));
        return  result;
    }

    public byte[] ScreenScale(int vertical_length,int horizontal_length){
        this.command.add((byte)0x07);
        this.command.add((byte)2 );
        byte l_vertical_length = (byte) (vertical_length & 0x00ff);
        byte h_vertical_length = (byte) ( (vertical_length & 0xff00) >> 8 );
        byte l_horizontal_length = (byte) (horizontal_length & 0x00ff);
        byte h_horizontal_length = (byte) ( (horizontal_length & 0xff00) >> 8 );
        this.command.add(l_horizontal_length);
        this.command.add(h_horizontal_length);
        this.command.add(l_vertical_length);
        this.command.add(h_vertical_length);
        this.command.add((byte)0);


    }

    public  Byte[] ScaleOrPosition(int TYPE,int vertical_length,int horizontal_length){
        Byte[] command = this.VerticalHorizontal(vertical_length, horizontal_length);
        command[3] = (byte) TYPE;
        byte oo = Command.getCheck(command);
        command[8] = (byte)oo;
//        command[8] = (byte)(7 % TYPE);
        return  command;

    }

    public static byte getCheck(Byte[] command){
        int check_i = 0;
        byte[] all_bytes = Command.toPrimitives(command);
        if (all_bytes.length > 1){
            for (int i =1 ; i < 8 ; i++ ){
                check_i ^= all_bytes[i];
                Log.d("tag",String.valueOf(check_i));
            }
        }
        Log.d("% = ",String.valueOf(check_i));
        return  (byte)(check_i);
    }
    public static byte[] toPrimitives(Byte[] oBytes){

        byte[] bytes = new byte[oBytes.length];
        for(int i = 0; i < oBytes.length; i++){
            bytes[i] = oBytes[i];
        }
        return bytes;

    }
}
