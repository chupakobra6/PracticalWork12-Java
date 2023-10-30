package com.example.practicalwork12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences themeSettings;
    SharedPreferences.Editor settingsEditor;
    private boolean isNightModeEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        themeSettings = getSharedPreferences("SETTINGS", MODE_PRIVATE);
        isNightModeEnabled = themeSettings.getBoolean("MODE_NIGHT_ON", false);

        setCurrentTheme();

        setContentView(R.layout.activity_main);

        Button themeButton = findViewById(R.id.themeButton);
        themeButton.setOnClickListener(v -> {
            isNightModeEnabled = !isNightModeEnabled;
            settingsEditor = themeSettings.edit();
            settingsEditor.putBoolean("MODE_NIGHT_ON", isNightModeEnabled);
            settingsEditor.apply();
            recreate();
        });

        Button selfgameButton = findViewById(R.id.selfgameButton);
        selfgameButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SelfgameActivity.class);
            startActivity(intent);
        });

        Button botgameButton = findViewById(R.id.botgameButton);
        botgameButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BotgameActivity.class);
            startActivity(intent);
        });
    }

    private void setCurrentTheme() {
        if (isNightModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
