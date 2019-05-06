package com.example.fodmapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;

public class EditMyListActivity extends AppCompatActivity {


    //public static final String PREFS_COLORS ="colorInTextView";
    String redColor = "red";
    String yellowColor = "yellow";
    String greenColor = "green";

    public static final String MESSAGE_FROM_EDIT = "editmsg";
    private static boolean added = true;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_list);

       Intent intent = getIntent();
       String myNewItemAdd = intent.getStringExtra(SearchActivity.EXTRA_FROM_SEARCH_TO_EDIT);  //oma lisäämän asian syöttäminen tekstikenttään
       TextView tv = (TextView) findViewById(R.id.editMyListTextView);
       tv.setText(myNewItemAdd);

       /**
        * receives the bundle with i.
        * i equals the index of the arrayslist either on main fodmap list, or from users own list
        */
       Bundle b = getIntent().getExtras();

       int i = b.getInt(SearchActivity.EXTRA_FROM_SEARCH_TO_EDIT,-1); ////i, item from search list. def value -1,index starts from 0


       if (i != -1) {
           ((TextView) findViewById(R.id.editMyListTextView)).setText(FodmapList.getInstance().getAllFodmapsArrayList(i));
       }

       int j = b.getInt(MyFodmapListActivity.EXTRA_FROM_MYLIST_TO_EDIT,-1); //j, item from users own fodmap list. def value -1,index starts from 0

       if(j != -1){
           //when user clicks item on own list, it shows on upper textview
           TextView tvFodmapOnView = findViewById(R.id.editMyListTextView);
           tvFodmapOnView.setText(MySelectedFodmaps.getInstance().getMySelectedFodmapsArrayList(j));

           //retrieves added note from singleton arraylist of notes, if not null it shows on textview
           TextView tvForNotes = findViewById(R.id.editActivityEditTextSetNote);
           String retrieveNote = MySelectedFodmapsNotes.getInstance().getMySelectedFodmapsNotesArrayList(j);
           if(retrieveNote != null){
               tvForNotes.setText(retrieveNote);
           }else{
               tvForNotes.setText("Lisää muistiinpano");
           }

           String retrieveColor = MySelectedFodmapsColors.getInstance().getMySelectedFodmapsColorsArrayList(j);
           if(retrieveColor != null){
               if(retrieveColor.equals("red")){
                   tvFodmapOnView.setTextColor(Color.RED);
               }
               if(retrieveColor.equals("yellow")){
                   tvFodmapOnView.setTextColor(Color.YELLOW);
               }
               if(retrieveColor.equals("green")){
                   tvFodmapOnView.setTextColor(Color.GREEN);
               }
               else{
                   tvFodmapOnView.setTextColor(Color.GRAY);
               }
           }



           //((TextView) findViewById(R.id.editMyListTextView)).setText(MySelectedFodmaps.getInstance().getMySelectedFodmapsArrayList(j));
           //((TextView) findViewById(R.id.editActivityEditTextSetNote)).setText(MySelectedFodmapsNotes.getInstance().getMySelectedFodmapsNotesArrayList(j));

       }

    }
    /**
     *
     * add to my list of bad fodmaps -button on editActivity, adds the food to MY list
     */
    public void addToMyListButtonPressed(View v){
        /**
         * If the list does not contain the item user wants to add, it is added.
         *
         * if users own list already contains the item user wants to add, it is not added and the
         * user is notified with a message that the list already contains the item.
         *
         */
        Bundle b = getIntent().getExtras();
        int i = b.getInt(SearchActivity.EXTRA_FROM_SEARCH_TO_EDIT, 0);

        TextView tv = (TextView) findViewById(R.id.editMyListTextView);
        String fodmapItemSelected = tv.getText().toString();


        if (MySelectedFodmaps.getInstance().selectedItemsArrayList.contains(fodmapItemSelected)) {
            ((TextView) findViewById(R.id.editMyListTextView)).setText("Listassasi on jo\n " +  fodmapItemSelected);
        }else  {
            if (added = true) {
                added = false;
                MySelectedFodmaps.getInstance().addToMyList(fodmapItemSelected);

                SharedPreferences prefs = getSharedPreferences(MySelectedFodmaps.PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                try {
                    editor.putString("TASKS", ObjectSerializer.serialize(MySelectedFodmaps.getInstance().getMySelectedFodmapsArrayList()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                editor.commit();

                Intent openMyList = new Intent(this, MyFodmapListActivity.class);
                //openMyList.putExtra(MainActivity.EXTRA, i);
                startActivity(openMyList);
            } else
                MySelectedFodmaps.getInstance().addToMyList(fodmapItemSelected);


                SharedPreferences prefs = getSharedPreferences(MySelectedFodmaps.PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                try {
                    editor.putString("TASKS", ObjectSerializer.serialize(MySelectedFodmaps.getInstance().getMySelectedFodmapsArrayList()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                editor.commit();

                Intent openMyList = new Intent(this, MyFodmapListActivity.class);
                //openMyList.putExtra(MainActivity.EXTRA, i);
                startActivity(openMyList);

        }

    }

    public void deleteFromMyListButtonPressed(View v){
        /**
         * deletes the item from users own list
         */

        Bundle b = getIntent().getExtras();
        int j = b.getInt(MyFodmapListActivity.EXTRA_FROM_MYLIST_TO_EDIT,0);

        String itemFromMyList = MySelectedFodmaps.getInstance().getMySelectedFodmapsArrayList(j);
        MySelectedFodmaps.getInstance().deleteFromMyList(itemFromMyList);

        SharedPreferences prefs = getSharedPreferences(MySelectedFodmaps.PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        try {
            editor.putString("TASKS", ObjectSerializer.serialize(MySelectedFodmaps.getInstance().getMySelectedFodmapsArrayList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();

        Intent openMyList = new Intent(this, MyFodmapListActivity.class);
        //openMySelectedItemsList.putExtra(MainActivity.EXTRA, j);
        startActivity(openMyList);
    }

    public void addNote(View v){
        /**
         * checks the index of the item on the textview.
         *
         *
         */
        TextView tv = findViewById(R.id.editMyListTextView);
        String itemOnTextView = tv.getText().toString();

        int indexOfItem = MySelectedFodmaps.getInstance().getIndex(itemOnTextView);

        EditText noteOnEditActivity = findViewById(R.id.editActivityEditTextSetNote);
        String userInputNote = noteOnEditActivity.getText().toString();
        tv.setText(userInputNote);

        MySelectedFodmapsNotes.getInstance().addNote(indexOfItem, userInputNote);

        SharedPreferences prefNotes = getSharedPreferences(MySelectedFodmapsNotes.PREFS_NOTES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefNotes.edit();
        try {
            editor.putString("TASKS", ObjectSerializer.serialize(MySelectedFodmapsNotes.getInstance().getMySelectedFodmapsNotesArrayList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();

        Intent openThis = new Intent(this, EditMyListActivity.class);
        //openThis.putExtra(MainActivity.EXTRA, indexOfItem);
        startActivity(openThis);

    }
    public void addRedColorButtonPressed(View v){
        TextView tv = findViewById(R.id.editMyListTextView);
        String itemOnTextView = tv.getText().toString();

        int indexOfItem = MySelectedFodmaps.getInstance().getIndex(itemOnTextView);

        MySelectedFodmapsColors.getInstance().addColor(indexOfItem, redColor);

        SharedPreferences prefColors = getSharedPreferences(MySelectedFodmapsColors.PREFS_COLORS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefColors.edit();
        try {
            editor.putString("TASKS", ObjectSerializer.serialize(MySelectedFodmapsColors.getInstance().getMySelectedFodmapsColorsArrayList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();

        //ALLA OLEVA VÄHÄ HÄMÄRÄÄÄÄÄÄÄ ???? lähetetäänkö msg MINERVAINEN????
        Intent openThis = new Intent(this, EditMyListActivity.class);
        //openThis.putExtra(MainActivity.EXTRA, indexOfItem);
        startActivity(openThis);

    }
    public void addYellowColorButtonPressed(View v){
        TextView tv = findViewById(R.id.editMyListTextView);
        String itemOnTextView = tv.getText().toString();

        int indexOfItem = MySelectedFodmaps.getInstance().getIndex(itemOnTextView);

        EditText noteOnEditActivity = findViewById(R.id.editActivityEditTextSetNote);
        String userInputNote = noteOnEditActivity.getText().toString();
        tv.setText(userInputNote);

        MySelectedFodmapsColors.getInstance().addColor(indexOfItem, yellowColor);

        SharedPreferences prefColors = getSharedPreferences(MySelectedFodmapsColors.PREFS_COLORS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefColors.edit();
        try {
            editor.putString("TASKS", ObjectSerializer.serialize(MySelectedFodmapsColors.getInstance().getMySelectedFodmapsColorsArrayList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();

        Intent openThis = new Intent(this, EditMyListActivity.class);
        //openThis.putExtra(MainActivity.EXTRA, indexOfItem);
        startActivity(openThis);

    }
    public void addGreenColorButtonPressed(View v){
        TextView tv = findViewById(R.id.editMyListTextView);
        String itemOnTextView = tv.getText().toString();

        int indexOfItem = MySelectedFodmaps.getInstance().getIndex(itemOnTextView);

        EditText noteOnEditActivity = findViewById(R.id.editActivityEditTextSetNote);
        String userInputNote = noteOnEditActivity.getText().toString();
        tv.setText(userInputNote);

        MySelectedFodmapsColors.getInstance().addColor(indexOfItem, greenColor);

        SharedPreferences prefColors = getSharedPreferences(MySelectedFodmapsColors.PREFS_COLORS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefColors.edit();
        try {
            editor.putString("TASKS", ObjectSerializer.serialize(MySelectedFodmapsColors.getInstance().getMySelectedFodmapsColorsArrayList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();

        Intent openThis = new Intent(this, EditMyListActivity.class);
        //openThis.putExtra(MainActivity.EXTRA, indexOfItem);
        startActivity(openThis);

    }
    //tarvitaanko toinen edit ikkuna, kun tullaan omasta listasta, missä olis note ja liikennevalot???
    //BUTTON: Back to search activity
    public void backToSearch(View v){
        Intent openSearchActivity = new Intent(this, SearchActivity.class);
        startActivity(openSearchActivity);
    }


}

