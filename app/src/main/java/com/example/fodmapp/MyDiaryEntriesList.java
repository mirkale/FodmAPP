package com.example.fodmapp;

import java.util.ArrayList;

class MyDiaryEntriesList {
    public static final String PREF_DIARY = "PrefDiaryEntries";
    private static final MyDiaryEntriesList ourInstance = new MyDiaryEntriesList();
    private ArrayList<String> diaryEntries;

    static MyDiaryEntriesList getInstance() {
        return ourInstance;
    }

    public MyDiaryEntriesList() {
        diaryEntries = new ArrayList<String>();
    }

    public void addNewEntryToMyDiary(String name){
        diaryEntries.add(name);
    }

    public void deleteEntryFromMyDiary(String name){
        diaryEntries.remove(name);
    }

    public ArrayList<String>getMyDiaryEntriesArrayList(){
        return diaryEntries;
    }

    public String getMyDiaryEntriesArrayList(int i){ return diaryEntries.get(i);}
}
