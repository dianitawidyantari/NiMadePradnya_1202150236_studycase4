package com.example.widya.nimadepradnya_1202150236_studycase4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.InputStream;


public class GambarActivity extends AppCompatActivity {
    EditText url;
    ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gambar);

        //mendapatkan id untuk edittext dan imageview
        url  = findViewById(R.id.editText);
        gambar = findViewById(R.id.imageView);
    }

    //method ketika tombol ditekan
    public void carigambar(View view) {
        //menload gambar dari internet
        String urlgbr = url.getText().toString();
        new DownloadImage().execute(urlgbr);
    }
    //subclass untuk asynctask
    class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        //method sebelum proses asynctask dieksekusi
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        //method saat proses asynctask dieksekusi
        @Override
        protected Bitmap doInBackground(String... gambarr) {
            String gbrurl = gambarr[0];
            Bitmap btp = null;
            try {
                InputStream input = new java.net.URL(gbrurl).openStream();
                btp = BitmapFactory.decodeStream(input);
            } catch (Exception e){
                e.printStackTrace();
            }
            return btp;
        }

        //method saat proses asynctask selesai dilakukan
        @Override
        protected void onPostExecute(Bitmap hasil){
            gambar.setImageBitmap(hasil);
        }
    }
}
