package com.example.widya.nimadepradnya_1202150236_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ganti (View view){
            //untuk mendapatkan id dari button
            int id = view.getId();
            //mengatur perpindahan activity
            switch (id){
                //button untuk list nama mahasiswa
                case R.id.btmhs:
                    Intent intent = new Intent(MainActivity.this, NamaActivity.class);
                    startActivity(intent);
                    break;
                //button untuk mencari gambar
                case R.id.btgbr:
                    Intent intent1 = new Intent(MainActivity.this,GambarActivity.class);
                    startActivity(intent1);
                    break;
            }
    }
}
