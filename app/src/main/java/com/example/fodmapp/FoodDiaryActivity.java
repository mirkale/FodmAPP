package com.example.fodmapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FoodDiaryActivity extends AppCompatActivity {

    public static final String MESSAGE_FROM_MY_DIARY = "DiaryMessage";
    ListView myDiaryEntriesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_diary);

        myDiaryEntriesListView = findViewById(R.id.myFoodDiaryListView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(FoodDiaryActivity.this,
                android.R.layout.simple_list_item_1,
                MyDiaryEntriesList.getInstance().getMyDiaryEntriesArrayList()
        );

        myDiaryEntriesListView.setAdapter(arrayAdapter);

        myDiaryEntriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent openNewMyDiaryEntry = new Intent(FoodDiaryActivity.this, NewMyDiaryEntry.class);
                openNewMyDiaryEntry.putExtra(MESSAGE_FROM_MY_DIARY, i);
                startActivity(openNewMyDiaryEntry);
            }

        });
    }

    //opens new activity where it is possible to add new entry to users diary
    public void newEntryButtonPressed(View v){
        Intent openNewMyDiaryEntry = new Intent(this, NewMyDiaryEntry.class);
        startActivity(openNewMyDiaryEntry);
    }
    public void toMain(View v){
        Intent openMain = new Intent(this, MainActivity.class);
        startActivity(openMain);
    }
    public void toMyList(View v){
        Intent openMyList = new Intent(this, MyFodmapListActivity.class);
        startActivity(openMyList);
    }
}
