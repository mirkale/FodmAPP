package com.example.fodmapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class MySelectedFodmaps {
    public static final String PREFS_NAME = "TASKS";
    private static final MySelectedFodmaps ourInstance = new MySelectedFodmaps();


    static MySelectedFodmaps getInstance() {
        return ourInstance;
    }
    public ArrayList<String> selectedItemsArrayList;

    public MySelectedFodmaps() {
        selectedItemsArrayList = new ArrayList<String>();

    }
    public void addToMyList(String name){
        selectedItemsArrayList.add(name);

    }
    public  void deleteFromMyList(String name){
        selectedItemsArrayList.remove(name);
    }

    public ArrayList<String> getMySelectedFodmapsArrayList(){

        return selectedItemsArrayList;
    }
    public String getMySelectedFodmapsArrayList(int i){

        return selectedItemsArrayList.get(i);
    }
    public int getIndex(String name){

        int indexOfItem = selectedItemsArrayList.indexOf(name);

        return indexOfItem;
    }

}
