package com.example.financialmaster;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private ImageView mainGame;
    private ImageView mainBot;
    private ImageView mainSetting;
    private TextView textSetting;
    private TextView textChat;
    private TextView textGame;
    private TextView textView4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        mainGame = findViewById(R.id.main_game);
        mainBot = findViewById(R.id.main_bot);
        mainSetting = findViewById(R.id.main_setting);
        textSetting = findViewById(R.id.text_setting);
        textChat = findViewById(R.id.text_chat);
        textGame = findViewById(R.id.text_game);
        textView4 = findViewById(R.id.textView4);

        mainGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        mainBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasConsumerTypes()) {
                    startActivity(new Intent(MainActivity.this, ChatActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this, SelectCurrentTypeActivity.class));
                }
            }
        });


        mainSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });









    }

    private boolean hasConsumerTypes() {
        SharedPreferences preferences = getSharedPreferences("ConsumerPreferences", MODE_PRIVATE);
        return preferences.contains("currentType") && preferences.contains("targetType");
    }



}