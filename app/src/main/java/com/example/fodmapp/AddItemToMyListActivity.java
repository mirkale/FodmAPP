package com.example.fodmapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class AddItemToMyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_to_my_list);

        Intent fromSearch = getIntent();
        String userAdd = fromSearch.getStringExtra(SearchActivity.EXTRA_FROM_SEARCH_TO_EDIT);
        TextView tv = (TextView) findViewById(R.id.textViewAddItemToMyListActivity);
        tv.setText(userAdd);

         //receives the bundle with i. i equals the index of the arrayslist on main fodmap list. received through extras from search (item user clicked on)
        Bundle b = getIntent().getExtras();
        int i = b.getInt(SearchActivity.EXTRA_FROM_SEARCH_TO_EDIT,-1);
        if (i != -1) {
            //sets the item user clicked on to textview
            ((TextView) findViewById(R.id.textViewAddItemToMyListActivity)).setText(FodmapList.getInstance().getAllFodmapsArrayList(i));
        }
    }
    public void addToMyListButtonPressed(View v){
        //if users own list already contains the product it's not added and user is notified with a message
        TextView tv = (TextView) findViewById(R.id.textViewAddItemToMyListActivity);
        String fodmapItemSelected = tv.getText().toString();


        if (MySelectedFodmaps.getInstance().selectedItemsArrayList.contains(fodmapItemSelected)) {
            ((TextView) findViewById(R.id.editMyListTextView)).setText("Listassasi on jo\n " +  fodmapItemSelected);
        }else {

            MySelectedFodmaps.getInstance().addToMyList(fodmapItemSelected);
            int indexOfSelectedItems = MySelectedFodmaps.getInstance().selectedItemsArrayList.lastIndexOf(fodmapItemSelected);

            MySelectedFodmapsNotes.getInstance().addNote(indexOfSelectedItems, "");
            MySelectedFodmapsColors.getInstance().addColor(indexOfSelectedItems, "");

            SharedPreferences prefs = getSharedPreferences(MySelectedFodmaps.PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            try {
                editor.putString("TASKS", ObjectSerializer.serialize(MySelectedFodmaps.getInstance().getMySelectedFodmapsArrayList()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            editor.commit();

            SharedPreferences prefNotes = getSharedPreferences(MySelectedFodmapsNotes.PREFS_NOTES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = prefNotes.edit();
            try {
                editor.putString("NOTES", ObjectSerializer.serialize(MySelectedFodmapsNotes.getInstance().getMySelectedFodmapsNotesArrayList()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            editor1.commit();

            SharedPreferences prefColors = getSharedPreferences(MySelectedFodmapsColors.PREFS_COLORS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor2 = prefColors.edit();
            try {
                editor.putString("COLORS", ObjectSerializer.serialize(MySelectedFodmapsColors.getInstance().getMySelectedFodmapsColorsArrayList()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            editor2.commit();

            Intent openMySelectedItemsList = new Intent(this, MyFodmapListActivity.class);
            startActivity(openMySelectedItemsList);
        }
    }
    public void backToSearch(View v){
        Intent openSearchActivity = new Intent(AddItemToMyListActivity.this, SearchActivity.class);
        startActivity(openSearchActivity);
    }
    public void toFrontPage(View v){
        Intent openMainActivity = new Intent(AddItemToMyListActivity.this, MainActivity.class);
        startActivity(openMainActivity);
    }

}
