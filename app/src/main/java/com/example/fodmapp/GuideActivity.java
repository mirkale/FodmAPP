package com.example.fodmapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        TextView guideText = (TextView) findViewById(R.id.guideAcitivityTextView);
        guideText.setMovementMethod(new ScrollingMovementMethod());
        guideText.setText("Tätä FODMAP -sovellusta voit käyttää hyödyksi eliminoidessasi ruoka-aineita, jotka tunnetusti sisältävät fermentoituvia hiilihydraatteja. Aluksi voit ruokavaliossasi hyödyntää resepteistä löytyviä ruokia, jossa fermentoituvat hiilihydraatit on jätetty pois. Jos toiminnalliset vatsavaivat helpottavat voi hakulistasta löytyviä ruoka-aineita kokeilla yksitellen ja oireita seuraamalla pohtia, sopiiko kyseinen ruoka-aine juuri sinulle. Voit lisätä ruoka-aineita omaan listaasi muistiin, ja kommentoida niiden aiheuttamia oireita tai vastaavasti, jos oireita ei ilmene. Vielä yksinkertaisemmin voit merkitä ruoka-aineen värikoodeilla; sopii, ei sovi tai epävarma. Helpotus vatsan hyvinvointiin selviää ainoastaan karsimalla ja kokeilemalla. \n" +
                "\n\nTsemppiä! \n" +
                "Minerva & Viivi\n\n\n\n");
    }
    public void toMain(View v){
        Intent openMain = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(openMain);
    }
}

