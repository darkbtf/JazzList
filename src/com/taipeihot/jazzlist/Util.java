package com.taipeihot.jazzlist;

import android.annotation.SuppressLint;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Deque;
import java.util.Queue;


public class Util {

	public static Boolean errorReport(String msg) {
		System.out.println(msg);
		return false;
	}

	public static byte[] intToByteArray(int value) {
		return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16),
				(byte) (value >>> 8), (byte) value };
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
}
