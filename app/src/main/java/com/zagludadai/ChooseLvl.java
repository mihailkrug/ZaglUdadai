package com.zagludadai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseLvl extends AppCompatActivity {

    ImageView lvl_1, lvl_2, lvl_3, lvl_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        lvl_1 = findViewById(R.id.lvl_1);
        lvl_2 = findViewById(R.id.lvl_2);
        lvl_3 = findViewById(R.id.lvl_3);
        lvl_4 = findViewById(R.id.lvl_4);

        lvl_1.setOnClickListener((View v) -> {
            Intent intent = new Intent(ChooseLvl.this, CarnivalActivity.class);
            intent.putExtra("int_value", 1);
            startActivity(intent);
        });
        lvl_2.setOnClickListener((View v) -> {
            Intent intent = new Intent(ChooseLvl.this, CarnivalActivity.class);
            intent.putExtra("int_value", 2);
            startActivity(intent);
        });
        lvl_3.setOnClickListener((View v) -> {
            Intent intent = new Intent(ChooseLvl.this, CarnivalActivity.class);
            intent.putExtra("int_value", 3);
            startActivity(intent);
        });
        lvl_4.setOnClickListener((View v) -> {
            Intent intent = new Intent(ChooseLvl.this, CarnivalActivity.class);
            intent.putExtra("int_value", 4);
            startActivity(intent);
        });

    }

}
