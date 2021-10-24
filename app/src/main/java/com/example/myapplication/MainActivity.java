package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFERENCES_MAIN = "shared_preferences_main";
    public static final String BG_COLOR_SIMPLE = "bg_color_simple";
    public static final String BG_COLOR_FANCY = "bg_color_fancy";
    public static final String BG_COLOR_DEFAULT = "bg_color_default";

    public static final String TV_COLOR_SIMPLE = "tv_color_simple";
    public static final String TV_COLOR_FANCY = "tv_color_fancy";
    public static final String TV_COLOR_DEFAULT = "tv_color";

    public static final String STYLE_SIMPLE = "style_simple";
    public static final String STYLE_FANCY = "style_fancy";

    public static final String SIZE_SIMPLE = "size_simple";
    public static final String SIZE_FANCY = "size_fancy";

    private RelativeLayout layout;
    private Button simple_btn;
    private Button fancy_btn;
    private TextView ex_tv;
    private TextView status_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSP();
        mapping();

        layout.setBackgroundColor(getResources().getColor(R.color.black));
        ex_tv.setBackgroundColor(getResources().getColor(R.color.teal_200));
        simple_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bgColor = getDataFromSP(BG_COLOR_SIMPLE);
                int tvColor = getDataFromSP(TV_COLOR_SIMPLE);
                int size = getDataFromSP(SIZE_SIMPLE);
                int style = getDataFromSP(STYLE_SIMPLE);
                String styleStr = style==Typeface.NORMAL?"normal":"bold";
                layout.setBackgroundColor(bgColor);
                ex_tv.setBackgroundColor(tvColor);
                ex_tv.setTextSize(size);
                ex_tv.setTypeface(ex_tv.getTypeface(), style);

                status_tv.setText("color: " + tvColor + "\nsize: " + size + "\nstyle " + styleStr);
            }
        });
        fancy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bgColor = getDataFromSP(BG_COLOR_FANCY);
                int tvColor = getDataFromSP(TV_COLOR_FANCY);
                int size = getDataFromSP(SIZE_FANCY);
                int style = getDataFromSP(STYLE_FANCY);
                String styleStr = style==Typeface.NORMAL?"normal":"bold";
                layout.setBackgroundColor(bgColor);
                ex_tv.setBackgroundColor(tvColor);
                ex_tv.setTextSize(size);
                ex_tv.setTypeface(ex_tv.getTypeface(), style);
                status_tv.setText("color: " + tvColor + "\nsize: " + size + "\nstyle " + styleStr);
            }
        });
    }


    void mapping(){
        layout = findViewById(R.id.main_layout);
        fancy_btn = findViewById(R.id.fancy_btn);
        simple_btn = findViewById(R.id.simple_btn);
        ex_tv = findViewById(R.id.ex_tv);
        status_tv = findViewById(R.id.status_tv);
    }
    void initSP()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_MAIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(BG_COLOR_SIMPLE,getResources().getColor(R.color.green));
        editor.putInt(TV_COLOR_SIMPLE, getResources().getColor(R.color.blue));
        editor.putInt(BG_COLOR_FANCY, getResources().getColor(R.color.gray));
        editor.putInt(TV_COLOR_FANCY, getResources().getColor(R.color.black));
        editor.putInt(SIZE_SIMPLE, 20);
        editor.putInt(SIZE_FANCY, 12);
        editor.putInt(STYLE_SIMPLE, Typeface.BOLD);
        editor.putInt(STYLE_FANCY, Typeface.NORMAL);

        editor.apply();
    }

    int getDataFromSP(String key){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_MAIN, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 16776961);
    }
}