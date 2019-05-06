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

        /**
         * receives the bundle with i.
         * i equals the index of the arrayslist either on main fodmap list, or from users own list
         */
        Bundle b = getIntent().getExtras();

        /**
         * i, item from main fodmap list. def value -1 because index strats from 0
         */

        int i = b.getInt(SearchActivity.EXTRA_FROM_SEARCH_TO_EDIT,-1);

        if (i != -1) {
            ((TextView) findViewById(R.id.editMyListTextView)).setText(FodmapList.getInstance().getAllFodmapsArrayList(i));
        }
    }
    public void addToMyListButtonPressed(View v){
        /**
         * If the list does not contain the item user wants to add, it is added.
         *
         * if users own list already contains the item user wants to add, it is not added and the
         * user is notified with a message that the list already contains the item.
         *
         */
        TextView tv = (TextView) findViewById(R.id.textViewAddItemToMyListActivity);
        String fodmapItemSelected = tv.getText().toString();


        if (MySelectedFodmaps.getInstance().selectedItemsArrayList.contains(fodmapItemSelected)) {
            ((TextView) findViewById(R.id.editMyListTextView)).setText("Listassasi on jo\n " +  fodmapItemSelected);
        }else {

            MySelectedFodmaps.getInstance().addToMyList(fodmapItemSelected);

            SharedPreferences prefs = getSharedPreferences(MySelectedFodmaps.PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            try {
                editor.putString("TASKS", ObjectSerializer.serialize(MySelectedFodmaps.getInstance().getMySelectedFodmapsArrayList()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            editor.commit();

            Intent openMySelectedItemsList = new Intent(this, MyFodmapListActivity.class);
            openMySelectedItemsList.putExtra(EditMyListActivity.MESSAGE_FROM_EDIT, fodmapItemSelected);
            startActivity(openMySelectedItemsList);
        }

    }
    public void backToSearch(View v){
        Intent openSearchActivity = new Intent(this, SearchActivity.class);
        startActivity(openSearchActivity);
    }
    public void toFrontPage(View v){
        Intent openMainActivity = new Intent(this, MainActivity.class);
        startActivity(openMainActivity);
    }

}
