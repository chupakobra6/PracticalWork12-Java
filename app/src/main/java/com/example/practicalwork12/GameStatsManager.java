package com.example.practicalwork12;

import android.content.Context;
import android.content.SharedPreferences;

public class GameStatsManager {
    private static final String PREF_NAME = "GameStatsPref";
    private static final String KEY_WINS = "wins";
    private static final String KEY_LOSSES = "losses";
    private static final String KEY_DRAWS = "draws";

    private SharedPreferences sharedPreferences;

    public GameStatsManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void incrementWins() {
        int currentWins = sharedPreferences.getInt(KEY_WINS, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_WINS, currentWins + 1);
        editor.apply();
    }

    public void incrementLosses() {
        int currentLosses = sharedPreferences.getInt(KEY_LOSSES, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_LOSSES, currentLosses + 1);
        editor.apply();
    }

    public void incrementDraws() {
        int currentDraws = sharedPreferences.getInt(KEY_DRAWS, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_DRAWS, currentDraws + 1);
        editor.apply();
    }

    public int getWins() {
        return sharedPreferences.getInt(KEY_WINS, 0);
    }

    public int getLosses() {
        return sharedPreferences.getInt(KEY_LOSSES, 0);
    }

    public int getDraws() {
        return sharedPreferences.getInt(KEY_DRAWS, 0);
    }

    public String getStats() {
        return String.format("Статистика:\nX: %d, O: %d, ничьи: %d", getWins(), getLosses(), getDraws());
    }
}

