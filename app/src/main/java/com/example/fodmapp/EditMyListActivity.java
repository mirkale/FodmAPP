package com.example.fodmapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;

public class EditMyListActivity extends AppCompatActivity {

    String redColor = "red";
    String yellowColor = "yellow";
    String greenColor = "green";
    public static final String MESSAGE_FROM_EDIT = "editmsg";

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_list);

       //receives the bundle with i. i equals the index of the arrayslist on main fodmap list. received through extras from search (item user clicked on)
       Bundle b = getIntent().getExtras();
       int i = b.getInt(MyFodmapListActivity.EXTRA_FROM_MYLIST_TO_EDIT,-1); //j, item from users own fodmap list. def value -1,index starts from 0

       if(i != -1){
           //when user clicks item on own list, it shows on upper textview
           TextView tvUp = findViewById(R.id.editMyListTextView);
           tvUp.setText(MySelectedFodmaps.getInstance().getMySelectedFodmapsArrayList(i));

           String retrieveColor = MySelectedFodmapsColors.getInstance().getMySelectedFodmapsColorsArrayList(i);
           if(retrieveColor.equals(redColor)){
               tvUp.setTextColor(Color.RED);
           }
           if(retrieveColor.equals(yellowColor)){
               tvUp.setTextColor(Color.parseColor("#FFF4C107"));
           }
           if(retrieveColor.equals(greenColor)){
               tvUp.setTextColor(Color.parseColor("#FF10BB32"));
           }

           boolean emptyList=true;
           emptyList = MySelectedFodmapsNotes.getInstance().getMySelectedFodmapsNotesArrayList().isEmpty();
           if(emptyList==false){
               TextView tvForNotes = findViewById(R.id.editActivityEditTextSetNote);
               String retrieveNote = MySelectedFodmapsNotes.getInstance().getMySelectedFodmapsNotesArrayList(i);

               tvForNotes.setText(retrieveNote);
           }
       }
    }
    //deletes the item from users own list
    public void deleteFromMyListButtonPressed(View v){
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
        startActivity(openMyList);
    }

    public void addNote(View v){
        TextView tvUp = findViewById(R.id.editMyListTextView); //tvUp = upper textview for fodmap item

        String itemOnTextView = tvUp.getText().toString();
        int indexOfItem = MySelectedFodmaps.getInstance().getIndex(itemOnTextView); //checks the index of the item on screen

        EditText noteOnEditActivity = findViewById(R.id.editActivityEditTextSetNote);
        String userInputNote = noteOnEditActivity.getText().toString(); //retrieves the note user has written
        TextView tvDown = findViewById(R.id.notesTextViewEditActivity); //tvDown= bottom textview for fodmap item NOTES
        tvDown.setText(userInputNote); //and sets it on the botton textview

        noteOnEditActivity.setText(""); //empties the plaintext field

        MySelectedFodmapsNotes.getInstance().addNote(indexOfItem, userInputNote); //adds the note to notes arraylist, with same index as the fodmap has on fodmap list

        SharedPreferences prefNotes = getSharedPreferences(MySelectedFodmapsNotes.PREFS_NOTES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefNotes.edit();
        try {
            editor.putString("NOTES", ObjectSerializer.serialize(MySelectedFodmapsNotes.getInstance().getMySelectedFodmapsNotesArrayList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();
    }
    public void deleteNote(View view){
        TextView tvUp = findViewById(R.id.editMyListTextView);
        String itemOnTextView = tvUp.getText().toString();

        int indexOfItem = MySelectedFodmaps.getInstance().getIndex(itemOnTextView);
        String userInputNote = "";
        TextView tvDown = findViewById(R.id.editActivityEditTextSetNote);
        tvDown.setText(userInputNote);

        MySelectedFodmapsNotes.getInstance().addNote(indexOfItem, userInputNote);

        SharedPreferences prefNotes = getSharedPreferences(MySelectedFodmapsNotes.PREFS_NOTES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefNotes.edit();
        try {
            editor.putString("NOTES", ObjectSerializer.serialize(MySelectedFodmapsNotes.getInstance().getMySelectedFodmapsNotesArrayList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();
    }

    public void addRedColorButtonPressed(View v){
        TextView tv = findViewById(R.id.editMyListTextView);
        String itemOnTextView = tv.getText().toString();

        int indexOfItem = MySelectedFodmaps.getInstance().getIndex(itemOnTextView);

        MySelectedFodmapsColors.getInstance().addColor(indexOfItem, redColor);
        tv.setTextColor(Color.RED);

        SharedPreferences prefColors = getSharedPreferences(MySelectedFodmapsColors.PREFS_COLORS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefColors.edit();
        try {
            editor.putString("COLORS", ObjectSerializer.serialize(MySelectedFodmapsColors.getInstance().getMySelectedFodmapsColorsArrayList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();
    }
    public void addYellowColorButtonPressed(View v) {
        TextView tv = findViewById(R.id.editMyListTextView);
        String itemOnTextView = tv.getText().toString();

        int indexOfItem = MySelectedFodmaps.getInstance().getIndex(itemOnTextView);

        MySelectedFodmapsColors.getInstance().addColor(indexOfItem, yellowColor);
        tv.setTextColor(Color.parseColor("#FFF4C107"));

        SharedPreferences prefColors = getSharedPreferences(MySelectedFodmapsColors.PREFS_COLORS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefColors.edit();
        try {
            editor.putString("COLORS", ObjectSerializer.serialize(MySelectedFodmapsColors.getInstance().getMySelectedFodmapsColorsArrayList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();
    }
    public void addGreenColorButtonPressed(View v){
        TextView tv = findViewById(R.id.editMyListTextView);
        String itemOnTextView = tv.getText().toString();

        int indexOfItem = MySelectedFodmaps.getInstance().getIndex(itemOnTextView);

        MySelectedFodmapsColors.getInstance().addColor(indexOfItem, greenColor);
        tv.setTextColor(Color.parseColor("#FF10BB32"));

        SharedPreferences prefColors = getSharedPreferences(MySelectedFodmapsColors.PREFS_COLORS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefColors.edit();
        try {
            editor.putString("COLORS", ObjectSerializer.serialize(MySelectedFodmapsColors.getInstance().getMySelectedFodmapsColorsArrayList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();
    }
    //BUTTON: Back to search activity
    public void backToSearch(View v){
        Intent openSearchActivity = new Intent(this, SearchActivity.class);
        startActivity(openSearchActivity);
    }
}

