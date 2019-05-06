package com.example.fodmapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

/**
 * Contains a list with most fodmap -foods
 * Also contains a search, that goes through the list and prints out the item user is searching
 * At the end of the list it is possible to add new item to the list.
 * It is possible to select an item from the list and add it to users own list.
 */

public class SearchActivity extends AppCompatActivity {

    public static final String EXTRA_FROM_SEARCH_TO_EDIT = "keySearchList";
    //SearchView allFodmapsSearchView;
    ListView allFodmapsListView;
    EditText addToOwnList;
    SearchView fodmapSearchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        fodmapSearchView = (SearchView) findViewById(R.id.searchActivitySearchView);
        allFodmapsListView = (ListView) findViewById(R.id.searchActivityListView);

        //arrayAdapter retrieves the list from the singleton class, containing full list of known bad fodmapfoods
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                FodmapList.getInstance().getAllFodmapsArrayList()
        );

        allFodmapsListView.setAdapter(arrayAdapter);

        fodmapSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(FodmapList.getInstance().getAllFodmapsArrayList().contains(query)){
                    arrayAdapter.getFilter().filter(query);
                    Intent openEditActivity = new Intent(SearchActivity.this, EditMyListActivity.class);

                    openEditActivity.putExtra(EXTRA_FROM_SEARCH_TO_EDIT, query);
                    startActivity(openEditActivity);
                }else {
                    Toast.makeText(SearchActivity.this, "Ei löydy", Toast.LENGTH_SHORT).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //when an item is clicked on the list EditActivity opens. there it is possible to add the item to your own list,
        //change the color etc...
        allFodmapsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent openEditActivity = new Intent(SearchActivity.this, EditMyListActivity.class);

                openEditActivity.putExtra(SearchActivity.EXTRA_FROM_SEARCH_TO_EDIT, i);
                startActivity(openEditActivity);
            }
        });

    }

    /**
     *If the main list does not contain food user is looking for, user can add new item to the list
     * clicking the item edit window opens where it is possible to add the item to users own list
     *
     */
    public void addToFodMapList(View v){
        Intent openEditMyListActivity = new Intent(this, EditMyListActivity.class);
        addToOwnList = (EditText) findViewById(R.id.addToOwnListEditText);
        String addNewFodmap = addToOwnList.getText().toString();
        openEditMyListActivity.putExtra(EXTRA_FROM_SEARCH_TO_EDIT, addNewFodmap);
        startActivity(openEditMyListActivity);

    }
    //takes user back to main activity
    public void toMain(View v){
        Intent openMainActivity = new Intent(this, MainActivity.class);
        startActivity(openMainActivity);
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