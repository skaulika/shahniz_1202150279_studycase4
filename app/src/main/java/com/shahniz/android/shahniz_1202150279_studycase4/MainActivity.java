package com.shahniz.android.shahniz_1202150279_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //method saat button diklik
    public void ListNama (View view) {
        //untuk berpindah ke aktivitas ListNama
        Intent a = new Intent (this, ListNama.class);
        startActivity(a);
    }
    //method saat button diklik
    public void PencariGambar(View view){
        //untuk berpindah ke aktivitas PencariGambar
        Intent b = new Intent (this, PencariGambar.class);
        startActivity(b);
    }

}
