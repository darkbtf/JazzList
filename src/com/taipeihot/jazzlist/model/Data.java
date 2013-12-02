package com.taipeihot.jazzlist.model;

import java.util.ArrayList;

import com.taipeihot.jazzlist.CommunicateHelper;
import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.table.CategoryTable;

public class Data {
    public static String account = null;
    public static String nickname = null;
    public static String encryptedPassword = null;
    public static ArrayList<User> friends = new ArrayList<User>();
    public static ArrayList<Status> status = new ArrayList<Status>();
    public static ArrayList<Category> categories = new ArrayList<Category>();
    public static void setAccount(String a, String n, String password) {
        account = a;
        nickname = n;
        encryptedPassword = password;
    }
    public static boolean addFriend(String account){
        return CommunicateHelper.addFriend(account);
    }
    public static boolean addCategory(String title){
        Category c = new Category(title,R.drawable.ic_home);
        CategoryTable.insert(c);
        categories.add(c);
        return CommunicateHelper.addCategory(c);
    }
    public static ArrayList<Category> getCategories() {
        return categories;
    }
}
