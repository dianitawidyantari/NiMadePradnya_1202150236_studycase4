package com.example.widya.nimadepradnya_1202150236_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class NamaActivity extends AppCompatActivity {
    ListView ListNamaMhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nama);
        //mendapatkan id dari listview
        ListNamaMhs = findViewById(R.id.nmmhs);
    }
        //jika tombol ditekan akan memulai asynctask
        public void tampilnama (View view) {
            new getData(ListNamaMhs).execute();
        }

        //membuat subclass untuk asynctask
    class getData extends AsyncTask<String, Integer, String> {
        ListView Listnama;
        ArrayAdapter adapternama;
        ArrayList<String> item_list;
        ProgressDialog progress;

        //Constructor untuk asynctask
        public getData(ListView ListNamaMahasiswa) {
            this.Listnama = ListNamaMahasiswa;
            progress = new ProgressDialog(NamaActivity.this);
            item_list = new ArrayList<>();
        }

        //method sebelum proses asynctask dieksekusi
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Menampilkan ProgressDialog
            progress.setTitle("Memuat Nama...");
            progress.setCancelable(true);
            //tombol cancel pada progress dialog
            progress.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    progress.dismiss();
                    getData.this.cancel(true);
                }
            });
            //menambahkan style dan menampilkan progress dialog
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.show();
        }

        //method saat asynctask dieksekusi
        @Override
        protected String doInBackground(String... strings) {
            //membuat adaper
            adapternama = new ArrayAdapter<>(NamaActivity.this, android.R.layout.simple_list_item_1, item_list);
            //menyimpan array dalam variabel
            String [] nmmahasiswa = getResources().getStringArray(R.array.nama_mahasiswa);
            for(int i=0; i<nmmahasiswa.length;i++){
                //menentukan panjang persentase
                final long percentage = 100*i/nmmahasiswa.length;
                try{
                    //menambahkan message pada progress dialog
                    Runnable changingmessage = new Runnable() {
                        @Override
                        public void run() {
                            progress.setMessage((int)percentage+"%");
                        }
                    };
                    //menentukan waktu progress
                    runOnUiThread(changingmessage);
                    Thread.sleep(200);
                    item_list.add(nmmahasiswa[i]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        //method setelah proses asynctask dieksekusi
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Listnama.setAdapter(adapternama);
            progress.dismiss();
        }
    }
}
