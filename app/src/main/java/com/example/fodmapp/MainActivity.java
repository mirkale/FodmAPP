package com.example.fodmapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA = "KeyMain";
    public static boolean openOnFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 1. serialized object = mySelected list can be saved also in a file and can be retrieved from there (deserialize)
         * single variable stored in shared prefs has a size limit, file has not
         * database hard this faster!
         * check if contains , add if not
         * mainActivity -> mylist -> print out from shared prefs, but only once!, not every time user returns to main.
         *
         */

        if(openOnFirstTime) {
            openOnFirstTime = false;

            SharedPreferences prefs = getSharedPreferences(MySelectedFodmaps.PREFS_NAME, Context.MODE_PRIVATE);

            try {
                ArrayList<String> mySelectedBadFodmaps = (ArrayList<String>) ObjectSerializer.deserialize(prefs.getString("TASKS", ObjectSerializer.serialize(new ArrayList<String>())));
                //Log.i("Fodmap", mySelectedBadFodmaps.toString());
                for (String badSelectedFood : mySelectedBadFodmaps) {
                    MySelectedFodmaps.getInstance().addToMyList(badSelectedFood);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            SharedPreferences prefEntry = getSharedPreferences(MyDiaryEntriesList.PREF_DIARY, Context.MODE_PRIVATE);

            try {
                ArrayList<String> myDiaryEntries = (ArrayList<String>) ObjectSerializer.deserialize(prefEntry.getString("DIARY", ObjectSerializer.serialize(new ArrayList<String>())));
                //Log.i("Fodmap", myDiaryEntries.toString());
                for(String myEntries: myDiaryEntries){
                    MyDiaryEntriesList.getInstance().addNewEntryToMyDiary(myEntries);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            SharedPreferences prefNotes = getSharedPreferences(MySelectedFodmapsNotes.PREFS_NOTES, Context.MODE_PRIVATE);

            try {
                ArrayList<String> myBadFodmapNotes = (ArrayList<String>) ObjectSerializer.deserialize(prefNotes.getString("NOTES", ObjectSerializer.serialize(new ArrayList<String>())));
                for(String myNotes: myBadFodmapNotes){
                    MySelectedFodmapsNotes.getInstance().addToMyListOfNotes(myNotes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            SharedPreferences prefColors = getSharedPreferences(MySelectedFodmapsColors.PREFS_COLORS, Context.MODE_PRIVATE);

            try {
                ArrayList<String> myBadFodmapColors = (ArrayList<String>) ObjectSerializer.deserialize(prefColors.getString("COLORS", ObjectSerializer.serialize(new ArrayList<String>())));
                for(String myColors: myBadFodmapColors){
                    MySelectedFodmapsColors.getInstance().addToMyListOfColors(myColors);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }


    //opens list of bad fodmaps
    public void searchButtonPressed(View v){
        Intent openSearch = new Intent(this, SearchActivity.class);
        startActivity(openSearch);
    }
    //opens list of selected fodmaps, not suitable for user
    public void myListButtonPressed(View v){
        Intent openMyList = new Intent(this, MyFodmapListActivity.class);
        startActivity(openMyList);
    }
    //opens food diary, with a list of diary entries
    public void myDiaryButtonPressed(View v){
        Intent openMyDiary = new Intent(this, FoodDiaryActivity.class);
        startActivity(openMyDiary);
    }
    public void recipesButtonPressed(View v){
        Intent openRecipes = new Intent(this, RecipesActivity.class);
        startActivity(openRecipes);
    }
    //opens an ibs info
    public void ibsButtonPressed(View v){
        Intent openIbs = new Intent(this, IbsActivity.class);
        startActivity(openIbs);
    }
    //opens a guide how to use this app
    public void guideButtonPressed(View v){
        Intent openGuide = new Intent(this, GuideActivity.class);
        startActivity(openGuide);
    }

}