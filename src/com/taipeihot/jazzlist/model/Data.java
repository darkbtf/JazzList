package com.taipeihot.jazzlist.model;

import java.util.ArrayList;

public class Data {
	public static String account = null;
	public static String nickname = null;
	public static String encryptedPassword = null;
	public static ArrayList<User> friends = new ArrayList<User>();
	public static ArrayList<Status> statis = new ArrayList<Status>();
	public static void setAccount(String a, String n, String password) {
		account = a;
		nickname = n;
		encryptedPassword = password;
	}
}
