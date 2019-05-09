package com.example.fodmapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyFodmapListActivity extends AppCompatActivity {

    public static final String EXTRA_FROM_MYLIST_TO_EDIT = "keyMyList";
    ListView myFodmapListListView;

    /**
     * 1. serialized object = mySelected list can be saved also in a file and can be retrieved from there (deserialize)
     * single variable stored in shared prefs has a size limit, file has not
     * database hard this faster!
     * chech if contains , add if not
     * mainActivity -> mylist -> print out from shared prefs
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fodmap_list);

        myFodmapListListView = findViewById(R.id.myListActivityListView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MyFodmapListActivity.this,
                android.R.layout.simple_list_item_1,
                MySelectedFodmaps.getInstance().getMySelectedFodmapsArrayList());

        myFodmapListListView.setAdapter(arrayAdapter);

        //send clicked item with extramessage, and print to textview
        myFodmapListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent openEditActivity = new Intent(MyFodmapListActivity.this, EditMyListActivity.class);
                openEditActivity.putExtra(MyFodmapListActivity.EXTRA_FROM_MYLIST_TO_EDIT, i);
                startActivity(openEditActivity);
            }
        });

    }
    //Below buttons taking user to main, to search, and to diary
    public void toFirstPage (View v){
        Intent openMain = new Intent(this, MainActivity.class);
        startActivity(openMain);

    }
    public void toSearchButton (View view){
        Intent openSearch = new Intent(this, SearchActivity.class);
        startActivity(openSearch);

    }
    public void toMyDiary (View v){
        Intent openDiary = new Intent(this, FoodDiaryActivity.class);
        startActivity(openDiary);

    }



}
