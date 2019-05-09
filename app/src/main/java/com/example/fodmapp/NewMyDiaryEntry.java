package com.example.fodmapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewMyDiaryEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_my_diary_entry);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
        Date date = new Date();

        ((EditText) findViewById(R.id.setDiaryEntryEditText)).setText(dateFormat.format(date));


        Bundle b = getIntent().getExtras();
        if (b != null) {
            int i = b.getInt(FoodDiaryActivity.MESSAGE_FROM_MY_DIARY, 0);

            ((TextView) findViewById(R.id.setDiaryEntryTextView)).setText(MyDiaryEntriesList.getInstance().getMyDiaryEntriesArrayList(i));
            ((EditText) findViewById(R.id.setDiaryEntryEditText)).setText(MyDiaryEntriesList.getInstance().getMyDiaryEntriesArrayList(i));
        }
    }
    public void saveAddedDiaryEntry(View v){
        EditText newDiaryEntry = (EditText) findViewById(R.id.setDiaryEntryEditText);

        Bundle b = getIntent().getExtras();
        if(b != null){
            int i = b.getInt(FoodDiaryActivity.MESSAGE_FROM_MY_DIARY, 0);
            String oldEntryInDiary = MyDiaryEntriesList.getInstance().getMyDiaryEntriesArrayList(i);
            MyDiaryEntriesList.getInstance().deleteEntryFromMyDiary(oldEntryInDiary);
        }
        String newEntryInMyDiary = newDiaryEntry.getText().toString();
        MyDiaryEntriesList.getInstance().addNewEntryToMyDiary(newEntryInMyDiary);

        SharedPreferences prefEntry = getSharedPreferences(MyDiaryEntriesList.PREF_DIARY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefEntry.edit();

        try {
            editor.putString("DIARY", ObjectSerializer.serialize(MyDiaryEntriesList.getInstance().getMyDiaryEntriesArrayList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();

        Intent openFoodDiaryActivity = new Intent(this, FoodDiaryActivity.class);
        startActivity(openFoodDiaryActivity);
    }

    public void deleteEntryFromMyDiary (View v){
        Bundle b = getIntent().getExtras();
        int i = b.getInt(FoodDiaryActivity.MESSAGE_FROM_MY_DIARY, 0);

        EditText newEntry = (EditText) findViewById(R.id.setDiaryEntryEditText);

        String clicked = MyDiaryEntriesList.getInstance().getMyDiaryEntriesArrayList(i);
        MyDiaryEntriesList.getInstance().deleteEntryFromMyDiary(clicked);

        SharedPreferences prefEntry = getSharedPreferences(MyDiaryEntriesList.PREF_DIARY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefEntry.edit();
        try {
            editor.putString("DIARY", ObjectSerializer.serialize(MyDiaryEntriesList.getInstance().getMyDiaryEntriesArrayList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();

        Intent openDiary = new Intent(this, FoodDiaryActivity.class);
        startActivity(openDiary);


    }
    public void toMain(View v){
        Intent openMain = new Intent(this, MainActivity.class);
        startActivity(openMain);
    }
    public void toDiary(View v){
        Intent openDiary = new Intent(this, FoodDiaryActivity.class);
        startActivity(openDiary);
    }
    public void toMyList(View v){
        Intent openMyList = new Intent(this, MyFodmapListActivity.class);
        startActivity(openMyList);
    }
}
