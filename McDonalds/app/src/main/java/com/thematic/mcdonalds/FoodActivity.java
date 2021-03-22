package com.thematic.mcdonalds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener {

    CountDownTimer countDownTimer;
    ImageView img_background;

    LinearLayout countdown_timer;
    RelativeLayout food_activity_layout;

    Toolbar toolbar;

    Button coupon_btn;
    TextView countdown_txv_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        img_background = findViewById(R.id.img_background);

        Intent get_background_intent = getIntent();
        int background_image = get_background_intent.getIntExtra("Coupon_Background_Position", 0);
        //Toast.makeText(this, String.valueOf(background_image), Toast.LENGTH_SHORT).show();
        set_background_img(background_image);

        countdown_timer = findViewById(R.id.countdown_timer_layout);

        coupon_btn = findViewById(R.id.get_coupon_btn);
        coupon_btn.setOnClickListener(this);

        // countdown_timer_layout 的 TextView
        countdown_txv_view = findViewById(R.id.countdown_txv_view);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void set_background_img(int background_image) {
        switch (background_image) {
            case 0:
                img_background.setBackgroundResource(R.drawable.coupon_0);
                break;
            case 1:
                img_background.setBackgroundResource(R.drawable.coupon_1);
                break;
            case 2:
                img_background.setBackgroundResource(R.drawable.coupon_2);
                break;
            case 3:
                img_background.setBackgroundResource(R.drawable.coupon_3);
                break;
            case 4:
                img_background.setBackgroundResource(R.drawable.coupon_4);
                break;
            case 5:
                img_background.setBackgroundResource(R.drawable.coupon_5);
                break;
            case 6:
                img_background.setBackgroundResource(R.drawable.coupon_6);
                break;
            case 7:
                img_background.setBackgroundResource(R.drawable.coupon_7);
                break;
            case 8:
                img_background.setBackgroundResource(R.drawable.coupon_8);
                break;
            case 9:
                img_background.setBackgroundResource(R.drawable.coupon_9);
                break;
            case 10:
                img_background.setBackgroundResource(R.drawable.coupon_10);
                break;
            case 11:
                img_background.setBackgroundResource(R.drawable.coupon_11);
                break;
            case 12:
                img_background.setBackgroundResource(R.drawable.coupon_12);
                break;
            case 13:
                img_background.setBackgroundResource(R.drawable.coupon_13);
                break;
            case 14:
                img_background.setBackgroundResource(R.drawable.coupon_14);
                break;
            case 15:
                img_background.setBackgroundResource(R.drawable.coupon_15);
                break;
            case 16:
                img_background.setBackgroundResource(R.drawable.coupon_16);
                break;
            case 17:
                img_background.setBackgroundResource(R.drawable.coupon_17);
                break;
            case 18:
                img_background.setBackgroundResource(R.drawable.coupon_18);
                break;
            case 19:
                img_background.setBackgroundResource(R.drawable.coupon_19);
                break;
            case 20:
                img_background.setBackgroundResource(R.drawable.coupon_20);
                break;
            case 21:
                img_background.setBackgroundResource(R.drawable.coupon_21);
                break;
            case 22:
                img_background.setBackgroundResource(R.drawable.coupon_22);
                break;
            case 23:
                img_background.setBackgroundResource(R.drawable.coupon_23);
                break;
            case 24:
                img_background.setBackgroundResource(R.drawable.coupon_24);
                break;
            case 25:
                img_background.setBackgroundResource(R.drawable.coupon_25);
                break;
            case 26:
                img_background.setBackgroundResource(R.drawable.coupon_26);
                break;
            case 27:
                img_background.setBackgroundResource(R.drawable.coupon_27);
                break;
            case 28:
                img_background.setBackgroundResource(R.drawable.coupon_28);
                break;
            case 29:
                img_background.setBackgroundResource(R.drawable.coupon_29);
                break;
            case 30:
                img_background.setBackgroundResource(R.drawable.coupon_30);
                break;
        }
    }

    //替時間 加0，避免跑版
    private String time_check(Long get_time) {
        String r_time = "00";
        if (String.valueOf(get_time).length() == 1) {
            r_time = "0" + get_time;
        } else {
            r_time = String.valueOf(get_time);
        }
        return r_time;
    }

    //兌換按鈕 點擊
    @Override
    public void onClick(View view) {

        AlertDialog.Builder alertbuild = new AlertDialog.Builder(this);
        alertbuild.setTitle(getResources().getString(R.string.dialog_title))
                .setMessage(getResources().getString(R.string.dialog_message))
                .setNegativeButton(getResources().getString(R.string.dialog_cancel), null)
                .setPositiveButton(getResources().getString(R.string.dialog_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        button_set_visibily(true);
                        dialog_ok_countdown_timer();
                    }
                })
                .show();
    }

    //兌換確認按鈕 點擊
    private void dialog_ok_countdown_timer() {

        countDownTimer = new CountDownTimer(120000 + 1000, 1000) {
            @Override
            public void onTick(long l) {
                countdown_txv_view.setText("" + String.format("優惠倒數 %s：%s",
                        time_check(TimeUnit.MILLISECONDS.toMinutes(l)),
                        time_check(TimeUnit.MILLISECONDS.toSeconds(l) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)))));
            }

            @Override
            public void onFinish() {
                button_set_visibily(false);
            }
        }.start();
    }

    //可見不可見 設定
    private void button_set_visibily(boolean status) {
        if (status) {
            coupon_btn.setVisibility(View.INVISIBLE);
            countdown_timer.setVisibility(View.VISIBLE);
        } else {
            countdown_timer.setVisibility(View.INVISIBLE);
            coupon_btn.setVisibility(View.VISIBLE);
        }
    }
}
