package com.example.fodmapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GuideActivity extends AppCompatActivity {

    TextView guideText;
    //private Button takaisinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        guideText = (TextView) findViewById(R.id.guideAcitivityTextView);
        guideText.setText("FodmAPP- sovelluksen käytöstä:\n\nSovellus on avuksi sinulle, joka haluat noudattaa FODMAP-ruokavaliota.\n\nHaku-valikostan voit etsiä FODMAP-hiilihydraatteihin kuuluvia ruoka-aineita suomen kielellä ja lisätä omaan listaan.\n\nOmassa listassa olevat ruoka-aineet voit merkitä liikennevaloväreillä itsellesi sopivuuden mukaan.\nPäiväkirja osioon voit tehdä omia merkintöjä. ");

    }
}

