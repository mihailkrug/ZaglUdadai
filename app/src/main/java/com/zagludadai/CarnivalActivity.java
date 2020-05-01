package com.zagludadai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static java.util.Collections.shuffle;

public class CarnivalActivity extends AppCompatActivity {

    ArrayList<ImageView> slots, slots1;
    Integer [] slotsImageArray4 = {R.drawable.fruit1, R.drawable.fruit2,R.drawable.fruit3, R.drawable.fruit4, R.drawable.fruit5,  R.drawable.fruit6, R.drawable.fruit7,R.drawable.fruit8, R.drawable.fruit9, R.drawable.fruit10,
            R.drawable.fruit11, R.drawable.fruit12,R.drawable.fruit13, R.drawable.fruit14, R.drawable.fruit15,  R.drawable.fruit1, R.drawable.fruit2,R.drawable.fruit3, R.drawable.fruit4, R.drawable.fruit5,
            R.drawable.fruit6, R.drawable.fruit7,R.drawable.fruit8, R.drawable.fruit9, R.drawable.fruit10, R.drawable.fruit11, R.drawable.fruit12,R.drawable.fruit13, R.drawable.fruit14, R.drawable.fruit15,
            R.drawable.fruit1, R.drawable.fruit2,R.drawable.fruit3, R.drawable.fruit4, R.drawable.fruit5,  R.drawable.fruit1, R.drawable.fruit2,R.drawable.fruit3, R.drawable.fruit4, R.drawable.fruit5};
    Integer [] slotsImageArray3 = { R.drawable.fruit1, R.drawable.fruit2,R.drawable.fruit3, R.drawable.fruit4, R.drawable.fruit5,  R.drawable.fruit6, R.drawable.fruit7,R.drawable.fruit8, R.drawable.fruit9, R.drawable.fruit10,
            R.drawable.fruit11, R.drawable.fruit12,R.drawable.fruit13, R.drawable.fruit14, R.drawable.fruit15,  R.drawable.fruit1, R.drawable.fruit2,R.drawable.fruit3, R.drawable.fruit4, R.drawable.fruit5,
            R.drawable.fruit6, R.drawable.fruit7,R.drawable.fruit8, R.drawable.fruit9, R.drawable.fruit10, R.drawable.fruit11, R.drawable.fruit12,R.drawable.fruit13, R.drawable.fruit14, R.drawable.fruit15};
    Integer [] slotsImageArray2 = { R.drawable.fruit6, R.drawable.fruit7,R.drawable.fruit8, R.drawable.fruit9, R.drawable.fruit10, R.drawable.fruit11, R.drawable.fruit12,R.drawable.fruit13, R.drawable.fruit14, R.drawable.fruit15,
            R.drawable.fruit6, R.drawable.fruit7,R.drawable.fruit8, R.drawable.fruit9, R.drawable.fruit10, R.drawable.fruit11, R.drawable.fruit12,R.drawable.fruit13, R.drawable.fruit14, R.drawable.fruit15};
    Integer [] slotsImageArray = {  R.drawable.fruit11, R.drawable.fruit12,R.drawable.fruit13, R.drawable.fruit14, R.drawable.fruit15,   R.drawable.fruit11, R.drawable.fruit12,R.drawable.fruit13, R.drawable.fruit14, R.drawable.fruit15};
    List<Integer> listOfSlot = Arrays.asList(slotsImageArray);

    int countLoose = 1, lvl, prevClickId = 0, currentClickId = 0, iPrev = 0, iWin = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setBackgroundDrawableResource(R.drawable.backgr);
        Intent intent = getIntent();
        lvl = intent.getIntExtra("int_value", 1);

        initializeSlot();
        setSlotsListener();
        shuffle(listOfSlot);
        shSlot();
    }
    void initializeSlot(){
        slots = new ArrayList<>();
        slots1 = new ArrayList<>();
        if(lvl == 1){
            listOfSlot = Arrays.asList(slotsImageArray);
            lvl1();
        }else if(lvl == 2){
            listOfSlot = Arrays.asList(slotsImageArray2);
            lvl2();
        }else if(lvl == 3){
            listOfSlot = Arrays.asList(slotsImageArray3);
            lvl3();
        }else if(lvl == 4){
            listOfSlot = Arrays.asList(slotsImageArray4);
            lvl4();
        }

    }
    void setSlotsListener(){
        for (int i = 0; i < slots.size(); i++) {
            final int k = i;
            slots.get(k).setOnClickListener((View v) -> {

                slots.get(k).setImageResource(listOfSlot.get(k));
                currentClickId = listOfSlot.get(k);

                if (currentClickId == prevClickId) {
                    slots.get(k).setClickable(false);
                    slots.get(iPrev).setClickable(false);
                    countLoose = 0;
                    iWin++;
                    if(lvl == 1) {
                        if (iWin == 5) {
                            Toast.makeText(getApplicationContext(), "You Win", Toast.LENGTH_SHORT).show();
                            iWin = 0;
                            shuffle(listOfSlot);
                            shSlot();
                        }
                    }else if(lvl == 2) {
                        if (iWin == 10) {
                            Toast.makeText(getApplicationContext(), "You Win", Toast.LENGTH_SHORT).show();
                            iWin = 0;
                            shuffle(listOfSlot);
                            shSlot();
                        }
                    }else if(lvl == 3) {
                        if (iWin == 15) {
                            Toast.makeText(getApplicationContext(), "You Win", Toast.LENGTH_SHORT).show();
                            iWin = 0;
                            shuffle(listOfSlot);
                            shSlot();
                        }
                    }else if(lvl == 4) {
                        if (iWin == 20) {
                            Toast.makeText(getApplicationContext(), "You Win", Toast.LENGTH_SHORT).show();
                            iWin = 0;
                            shuffle(listOfSlot);
                            shSlot();
                        }
                    }
                } else {
                    if (countLoose == 2) {
                        Timer timer = new Timer();
                        int saveIndexPrev = iPrev;
                        timer.scheduleAtFixedRate(new TimerTask() {

                            @Override
                            public void run() {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        prevClickId = 0;
                                        currentClickId = 0;
                                        slots.get(k).setImageResource(R.drawable.close_fruit);
                                        slots.get(saveIndexPrev).setImageResource(R.drawable.close_fruit);

                                    }
                                });
                            }
                        }, 400, 1000000);
                        countLoose = 0;
                    }


                }
                iPrev = k;
                countLoose++;
                prevClickId = currentClickId;

            });
        }
    }
    private void shSlot() {
        Timer timer = new Timer();
        opSlot();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        clSlot();
                    }
                });
            }
        }, 3000, 10000000);
    }
    void opSlot() {
        for (int i = 0; i < slots.size(); i++) {
            slots.get(i).setImageResource(listOfSlot.get(i));
        }
    }

    void clSlot() {
        for (int i = 0; i < slots.size(); i++) {
            slots.get(i).setImageResource(R.drawable.close_fruit);
        }
    }

    void lvl1(){
        slots.add( findViewById(R.id.im11));
        slots.add( findViewById(R.id.im12));
        slots.add( findViewById(R.id.im13));
        slots.add( findViewById(R.id.im14));
        slots.add( findViewById(R.id.im15));


        slots.add( findViewById(R.id.im21));
        slots.add( findViewById(R.id.im22));
        slots.add( findViewById(R.id.im23));
        slots.add( findViewById(R.id.im24));
        slots.add( findViewById(R.id.im25));

        slots1.add( findViewById(R.id.im31));
        slots1.add( findViewById(R.id.im32));
        slots1.add( findViewById(R.id.im33));
        slots1.add( findViewById(R.id.im34));
        slots1.add( findViewById(R.id.im35));

        slots1.add( findViewById(R.id.im41));
        slots1.add( findViewById(R.id.im42));
        slots1.add( findViewById(R.id.im43));
        slots1.add( findViewById(R.id.im44));
        slots1.add( findViewById(R.id.im45));

        slots1.add( findViewById(R.id.im51));
        slots1.add( findViewById(R.id.im52));
        slots1.add( findViewById(R.id.im53));
        slots1.add( findViewById(R.id.im54));
        slots1.add( findViewById(R.id.im55));

        slots1.add( findViewById(R.id.im61));
        slots1.add( findViewById(R.id.im62));
        slots1.add( findViewById(R.id.im63));
        slots1.add( findViewById(R.id.im64));
        slots1.add( findViewById(R.id.im65));

        slots1.add( findViewById(R.id.im71));
        slots1.add( findViewById(R.id.im72));
        slots1.add( findViewById(R.id.im73));
        slots1.add( findViewById(R.id.im74));
        slots1.add( findViewById(R.id.im75));

        slots1.add( findViewById(R.id.im81));
        slots1.add( findViewById(R.id.im82));
        slots1.add( findViewById(R.id.im83));
        slots1.add( findViewById(R.id.im84));
        slots1.add( findViewById(R.id.im85));
        for(int i = 0; i< slots1.size(); i++){
            slots1.get(i).setVisibility(View.INVISIBLE);
        }


    }

    void lvl2(){
        slots.add( findViewById(R.id.im11));
        slots.add( findViewById(R.id.im12));
        slots.add( findViewById(R.id.im13));
        slots.add( findViewById(R.id.im14));
        slots.add( findViewById(R.id.im15));


        slots.add( findViewById(R.id.im21));
        slots.add( findViewById(R.id.im22));
        slots.add( findViewById(R.id.im23));
        slots.add( findViewById(R.id.im24));
        slots.add( findViewById(R.id.im25));

        slots.add( findViewById(R.id.im31));
        slots.add( findViewById(R.id.im32));
        slots.add( findViewById(R.id.im33));
        slots.add( findViewById(R.id.im34));
        slots.add( findViewById(R.id.im35));

        slots.add( findViewById(R.id.im41));
        slots.add( findViewById(R.id.im42));
        slots.add( findViewById(R.id.im43));
        slots.add( findViewById(R.id.im44));
        slots.add( findViewById(R.id.im45));

        slots1.add( findViewById(R.id.im51));
        slots1.add( findViewById(R.id.im52));
        slots1.add( findViewById(R.id.im53));
        slots1.add( findViewById(R.id.im54));
        slots1.add( findViewById(R.id.im55));

        slots1.add( findViewById(R.id.im61));
        slots1.add( findViewById(R.id.im62));
        slots1.add( findViewById(R.id.im63));
        slots1.add( findViewById(R.id.im64));
        slots1.add( findViewById(R.id.im65));

        slots1.add( findViewById(R.id.im71));
        slots1.add( findViewById(R.id.im72));
        slots1.add( findViewById(R.id.im73));
        slots1.add( findViewById(R.id.im74));
        slots1.add( findViewById(R.id.im75));

        slots1.add( findViewById(R.id.im81));
        slots1.add( findViewById(R.id.im82));
        slots1.add( findViewById(R.id.im83));
        slots1.add( findViewById(R.id.im84));
        slots1.add( findViewById(R.id.im85));

        for(int i = 0; i< slots1.size(); i++){
            slots1.get(i).setVisibility(View.INVISIBLE);
        }


    }
    void lvl3(){
        slots.add( findViewById(R.id.im11));
        slots.add( findViewById(R.id.im12));
        slots.add( findViewById(R.id.im13));
        slots.add( findViewById(R.id.im14));
        slots.add( findViewById(R.id.im15));


        slots.add( findViewById(R.id.im21));
        slots.add( findViewById(R.id.im22));
        slots.add( findViewById(R.id.im23));
        slots.add( findViewById(R.id.im24));
        slots.add( findViewById(R.id.im25));

        slots.add( findViewById(R.id.im31));
        slots.add( findViewById(R.id.im32));
        slots.add( findViewById(R.id.im33));
        slots.add( findViewById(R.id.im34));
        slots.add( findViewById(R.id.im35));

        slots.add( findViewById(R.id.im41));
        slots.add( findViewById(R.id.im42));
        slots.add( findViewById(R.id.im43));
        slots.add( findViewById(R.id.im44));
        slots.add( findViewById(R.id.im45));

        slots.add( findViewById(R.id.im51));
        slots.add( findViewById(R.id.im52));
        slots.add( findViewById(R.id.im53));
        slots.add( findViewById(R.id.im54));
        slots.add( findViewById(R.id.im55));

        slots.add( findViewById(R.id.im61));
        slots.add( findViewById(R.id.im62));
        slots.add( findViewById(R.id.im63));
        slots.add( findViewById(R.id.im64));
        slots.add( findViewById(R.id.im65));

        slots1.add( findViewById(R.id.im71));
        slots1.add( findViewById(R.id.im72));
        slots1.add( findViewById(R.id.im73));
        slots1.add( findViewById(R.id.im74));
        slots1.add( findViewById(R.id.im75));

        slots1.add( findViewById(R.id.im81));
        slots1.add( findViewById(R.id.im82));
        slots1.add( findViewById(R.id.im83));
        slots1.add( findViewById(R.id.im84));
        slots1.add( findViewById(R.id.im85));

        for(int i = 0; i< slots1.size(); i++){
            slots1.get(i).setVisibility(View.INVISIBLE);
        }


    }
    void lvl4(){
        slots.add( findViewById(R.id.im11));
        slots.add( findViewById(R.id.im12));
        slots.add( findViewById(R.id.im13));
        slots.add( findViewById(R.id.im14));
        slots.add( findViewById(R.id.im15));


        slots.add( findViewById(R.id.im21));
        slots.add( findViewById(R.id.im22));
        slots.add( findViewById(R.id.im23));
        slots.add( findViewById(R.id.im24));
        slots.add( findViewById(R.id.im25));

        slots.add( findViewById(R.id.im31));
        slots.add( findViewById(R.id.im32));
        slots.add( findViewById(R.id.im33));
        slots.add( findViewById(R.id.im34));
        slots.add( findViewById(R.id.im35));

        slots.add( findViewById(R.id.im41));
        slots.add( findViewById(R.id.im42));
        slots.add( findViewById(R.id.im43));
        slots.add( findViewById(R.id.im44));
        slots.add( findViewById(R.id.im45));

        slots.add( findViewById(R.id.im51));
        slots.add( findViewById(R.id.im52));
        slots.add( findViewById(R.id.im53));
        slots.add( findViewById(R.id.im54));
        slots.add( findViewById(R.id.im55));

        slots.add( findViewById(R.id.im61));
        slots.add( findViewById(R.id.im62));
        slots.add( findViewById(R.id.im63));
        slots.add( findViewById(R.id.im64));
        slots.add( findViewById(R.id.im65));

        slots.add( findViewById(R.id.im71));
        slots.add( findViewById(R.id.im72));
        slots.add( findViewById(R.id.im73));
        slots.add( findViewById(R.id.im74));
        slots.add( findViewById(R.id.im75));

        slots.add( findViewById(R.id.im81));
        slots.add( findViewById(R.id.im82));
        slots.add( findViewById(R.id.im83));
        slots.add( findViewById(R.id.im84));
        slots.add( findViewById(R.id.im85));

    }
}

