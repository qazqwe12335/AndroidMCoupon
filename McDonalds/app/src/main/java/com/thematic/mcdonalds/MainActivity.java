package com.thematic.mcdonalds;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    spconfig spconfig;

    ListView listView;

    private ArrayAdapter list_adapter;

    Random random = new Random();

    String[] coupon_array;

    int random_num = 0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spconfig = new spconfig(this);

        coupon_array = getResources().getStringArray(R.array.mc_food_array);

        listView = findViewById(R.id.list);

        for (int i = 0; i < coupon_array.length; i++) {
            random_num++;
        }


        list_adapter = new ArrayAdapter<String>(this,
                R.layout.item_list, R.id.food_list_text, coupon_array);

        listView.setAdapter(list_adapter);

        listView.setOnItemClickListener(this);
        if (!spconfig.apply_check()){
            notice_dialog();
        }
    }

    public void random_btn(View view) {
        Intent random_intent = new Intent(this, FoodActivity.class);
        random_intent.putExtra("Coupon_Background_Position", random.nextInt(random_num));
        startActivity(random_intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent food_intent = new Intent(this, FoodActivity.class);
        food_intent.putExtra("Coupon_Background_Position", position);
        startActivity(food_intent);
    }

    private void notice_dialog() {
        final AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setTitle("責任切結書")
                .setMessage("請保證任何情況絕不會供出開發者，供出來沒蛋蛋")
                .setCancelable(false)
                .setNegativeButton("不同意", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertdialog.setTitle("你沒蛋蛋")
                                .setMessage(null)
                                .setCancelable(false)
                                .setNegativeButton(null, null)
                                .setPositiveButton("離開", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish();
                                    }
                                })
                                .show();
                    }
                })
                .setPositiveButton("同意", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        spconfig.notice_apply(true);
                    }
                }).show();
    }
}
