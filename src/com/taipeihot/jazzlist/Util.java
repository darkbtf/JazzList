package com.taipeihot.jazzlist;

import android.annotation.SuppressLint;

import java.security.MessageDigest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Deque;
import java.util.Queue;

import java.util.Calendar;


public class Util {

	public static Boolean errorReport(String msg) {
		System.out.println(msg);
		return false;
	}

	public static byte[] intToByteArray(int value) {
		return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16),
				(byte) (value >>> 8), (byte) value };
	}

	public static String MD5(String str) {  
        MessageDigest md5 = null;  
        try {  
            md5 = MessageDigest.getInstance("MD5");  
        }catch(Exception e) {  
            e.printStackTrace();  
            return "";  
        }
        char[] charArray = str.toCharArray();  
        byte[] byteArray = new byte[charArray.length];
        for(int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte)charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();  
        for( int i = 0; i < md5Bytes.length; i++) {  
            int val = ((int)md5Bytes[i])&0xff;  
            if(val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
    } 
	
	@SuppressLint("NewApi")
	public static Boolean parseByte(Deque<Byte> bufferInput,
			Queue<String> messages) {
		if (bufferInput.size() < Parameter.bytesForLength)
			return false;
		Byte[] tmp = new Byte[Parameter.bytesForLength];
		int length = 0;
		for (int i = 0; i < Parameter.bytesForLength; i++) {
			tmp[i] = bufferInput.pollFirst();
			length = (length << 8) + tmp[i].intValue();
		}
		if (bufferInput.size() < length) {
			for (int i = Parameter.bytesForLength - 1; i >= 0; i--)
				bufferInput.addFirst(tmp[i]);
			return Util.errorReport("Byte not enough");
		}
		String data = "";
		byte[] b = new byte[length];
		for (int i = 0; i < length; i++)
			b[i] = bufferInput.pollFirst();
		data += new String(b, 0, length);
		messages.add(data);
		return true;
	}

	public static String dateLongToString(long deadline) { 
		Date d = new Date(deadline);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
	}

	public static long getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		return (cal.getTime()).getTime();
	}
	public static int rng58() {
		return (int)getCurrentTime();
	}

	public static String[] parseJAZZ(String s) {
		if(s.equals(""))return new String[0];
		int cnt=0;
		for(int i=0;i<s.length();i++)
			if(s.charAt(i)=='@')
				cnt++;
		String []ret=new String[cnt];
		cnt=0;
		for(int i=0;i<s.length();i++){
			String tmp="";
			while(s.charAt(i)!='@'){
				tmp+=(s.charAt(i)+"");
				i++;
			}
			ret[cnt++]=tmp;
		}
		return ret;
	}
}
