package com.example.financialmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SelectTargetTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_target_type);


        // 绑定按钮点击事件
        findViewById(R.id.type_intelligent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTargetType("理智型");
                startActivity(new Intent(SelectTargetTypeActivity.this, ChatActivity.class));
            }
        });

        findViewById(R.id.type_emotional).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTargetType("情绪型");
                startActivity(new Intent(SelectTargetTypeActivity.this, ChatActivity.class));
            }
        });

        findViewById(R.id.type_intelligent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTargetType("理智型");
                startActivity(new Intent(SelectTargetTypeActivity.this, ChatActivity.class));
            }
        });

        findViewById(R.id.type_emotional).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTargetType("情绪型");
                startActivity(new Intent(SelectTargetTypeActivity.this, ChatActivity.class));
            }
        });

        findViewById(R.id.type_determined).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTargetType("意志型");
                startActivity(new Intent(SelectTargetTypeActivity.this, ChatActivity.class));
            }
        });

        findViewById(R.id.type_independent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTargetType("独立型");
                startActivity(new Intent(SelectTargetTypeActivity.this, ChatActivity.class));
            }
        });

        findViewById(R.id.type_obedient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTargetType("顺从型");
                startActivity(new Intent(SelectTargetTypeActivity.this, ChatActivity.class));
            }
        });
    }

    private void saveTargetType(String type) {
        SharedPreferences preferences = getSharedPreferences("ConsumerPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("targetType", type);
        editor.apply();
    }

}
