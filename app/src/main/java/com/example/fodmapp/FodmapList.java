package com.example.fodmapp;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;

class FodmapList {
    private static final FodmapList ourInstance = new FodmapList();

    public ArrayList<String> allFodmapsArrayList;

    public static FodmapList getInstance() {
        return ourInstance;
    }

    private FodmapList() {

        allFodmapsArrayList = new ArrayList<String>();

        allFodmapsArrayList.add("omena");
        allFodmapsArrayList.add("aprikoosi");
        allFodmapsArrayList.add("greippi");
        allFodmapsArrayList.add("avokado");
        allFodmapsArrayList.add("banaani, kypsä");
        allFodmapsArrayList.add("boysenmarja");
        allFodmapsArrayList.add("granaattiomena");
        allFodmapsArrayList.add("kookospähkinä");
        allFodmapsArrayList.add("mustaherukka");
        allFodmapsArrayList.add("rambutaani");
        allFodmapsArrayList.add("tyrni");
        allFodmapsArrayList.add("karhunvatukka");
        allFodmapsArrayList.add("litsi");
        allFodmapsArrayList.add("kirsikka");
        allFodmapsArrayList.add("taateli");
        allFodmapsArrayList.add("viikuna");
        allFodmapsArrayList.add("longaani");
        allFodmapsArrayList.add("mango");
        allFodmapsArrayList.add("nektariini");
        allFodmapsArrayList.add("persikka");
        allFodmapsArrayList.add("päärynä");
        allFodmapsArrayList.add("persimon");
        allFodmapsArrayList.add("luumu");
        allFodmapsArrayList.add("tamarillo");
        allFodmapsArrayList.add("vesimeloni");
        allFodmapsArrayList.add("rusina");
        allFodmapsArrayList.add("mustikka");
        allFodmapsArrayList.add("vadelma");
        allFodmapsArrayList.add("mansikka");
        allFodmapsArrayList.add("maa-artisokka");
        allFodmapsArrayList.add("latva-artisokka");
        allFodmapsArrayList.add("okra");
        allFodmapsArrayList.add("kurpitsa");
        allFodmapsArrayList.add("tankoparsa");
        allFodmapsArrayList.add("parsa");
        allFodmapsArrayList.add("parsakaali");
        allFodmapsArrayList.add("kukkakaali");
        allFodmapsArrayList.add("ruusukaali");
        allFodmapsArrayList.add("taaro");
        allFodmapsArrayList.add("savojinkaali");
        allFodmapsArrayList.add("kaali, kaikki");
        allFodmapsArrayList.add("varsiselleri");
        allFodmapsArrayList.add("fenkoli");
        allFodmapsArrayList.add("valkosipuli");
        allFodmapsArrayList.add("purjo");
        allFodmapsArrayList.add("sipuli, kaikki");
        allFodmapsArrayList.add("aurinkokuivattu tomaatti");
        allFodmapsArrayList.add("sieni, kaikki");
        allFodmapsArrayList.add("tomaatti, paseerattu");
        allFodmapsArrayList.add("bataatti");
        allFodmapsArrayList.add("punajuuri");
        allFodmapsArrayList.add("maissi, tuore");
        allFodmapsArrayList.add("hapankerma");
        allFodmapsArrayList.add("piimä");
        allFodmapsArrayList.add("raejuusto");
        allFodmapsArrayList.add("maito, lehmän");
        allFodmapsArrayList.add("maito, vuohen");
        allFodmapsArrayList.add("maito, lampaan");
        allFodmapsArrayList.add("kerma");
        allFodmapsArrayList.add("tuorejuusto");
        allFodmapsArrayList.add("vanukas");
        allFodmapsArrayList.add("kermajuusto");
        allFodmapsArrayList.add("maitojauhe");
        allFodmapsArrayList.add("halloumi");
        allFodmapsArrayList.add("soijajogurtti");
        allFodmapsArrayList.add("soijamaito");
        allFodmapsArrayList.add("soiakerma");
        allFodmapsArrayList.add("maitotiiviste");
        allFodmapsArrayList.add("jogurttijäätelö");
        allFodmapsArrayList.add("jäätelö");
        allFodmapsArrayList.add("laktoosi");
        allFodmapsArrayList.add("ricotta");
        allFodmapsArrayList.add("jogurtti");
        allFodmapsArrayList.add("maito, kondensoitu");
        allFodmapsArrayList.add("soijapapu");
        allFodmapsArrayList.add("soijarouhe");
        allFodmapsArrayList.add("kikherne");
        allFodmapsArrayList.add("linssit");
        allFodmapsArrayList.add("papu");
        allFodmapsArrayList.add("pavut");
        allFodmapsArrayList.add("sokeriherne");
        allFodmapsArrayList.add("herne");
        allFodmapsArrayList.add("härkäpapu");
        allFodmapsArrayList.add("bulgur");
        allFodmapsArrayList.add("durum");
        allFodmapsArrayList.add("vehnä");
        allFodmapsArrayList.add("kuskus");
        allFodmapsArrayList.add("couscous");
        allFodmapsArrayList.add("mannasuurimo");
        allFodmapsArrayList.add("ruis");
        allFodmapsArrayList.add("speltti");
        allFodmapsArrayList.add("ohra");
        allFodmapsArrayList.add("kookosjauho");
        allFodmapsArrayList.add("mantelijauho");
        allFodmapsArrayList.add("manteli");
        allFodmapsArrayList.add("cashewpähkinä");
        allFodmapsArrayList.add("maapähkinä");
        allFodmapsArrayList.add("hasselpähkinä");
        allFodmapsArrayList.add("aspartaami");
        allFodmapsArrayList.add("asesulfaami K");
        allFodmapsArrayList.add("sakariini");
        allFodmapsArrayList.add("sukraloosi");
        allFodmapsArrayList.add("laktitoli");
        allFodmapsArrayList.add("fruktoosi");
        allFodmapsArrayList.add("hunaja");
        allFodmapsArrayList.add("isomalti");
        allFodmapsArrayList.add("maltitoli");
        allFodmapsArrayList.add("mannitoli");
        allFodmapsArrayList.add("melassi");
        allFodmapsArrayList.add("sorbitoli");
        allFodmapsArrayList.add("xylitoli");
        allFodmapsArrayList.add("ksylitoli");
        allFodmapsArrayList.add("siirappi, kaikki");
        allFodmapsArrayList.add("agave");
        allFodmapsArrayList.add("kookossokeri");
        allFodmapsArrayList.add("kamomillatee");
        allFodmapsArrayList.add("sikuri");
        allFodmapsArrayList.add("kaakaojauhe");
        allFodmapsArrayList.add("voikukka, lehti");
        allFodmapsArrayList.add("pikakahvi");
        allFodmapsArrayList.add("inuliini");
        allFodmapsArrayList.add("purukumi, kaikki");
        allFodmapsArrayList.add("pastillit");
        allFodmapsArrayList.add("cayennepippuri");
        allFodmapsArrayList.add("herbamare");
        allFodmapsArrayList.add("liemikuutio");

        Collections.sort(allFodmapsArrayList);
    }

    public ArrayList<String> getAllFodmapsArrayList(){
        return  allFodmapsArrayList;
    }

    /**
     *
     * @param i
     * @return  returns the name of the food from array (index)
     */
    public String getAllFodmapsArrayList(int i){
        return allFodmapsArrayList.get(i);
    }

    public void addToFodmapList(String name){
        allFodmapsArrayList.add(name);
        Collections.sort(allFodmapsArrayList);
    }

}
