package com.shahniz.android.shahniz_1202150279_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListNama extends AppCompatActivity {
    //deklarasi komponen variabel yang dibutuhkan
    ListView mlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama);

        mlistview=findViewById(R.id.listview);
    }

    //method saat button diklik
    public void startTask(View view) {
        new getData(mlistview).execute(); //proses asynctask dimulai
    }

    //subclass asynctask
    class getData extends AsyncTask<String, Integer, String> {
        ListView mlistview;
        ArrayAdapter madapter;
        ArrayList<String> mlistnama;
        ProgressDialog mdialog;

        //konstruktor saat asynctask diinisialisasi
        public getData(ListView listnama) {
            this.mlistview = listnama;
            mdialog = new ProgressDialog(ListNama.this);
            mlistnama = new ArrayList<>();
        }

        //method ketika asynctask belum dimulai
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //menampilkan proses progress dialog
            mdialog.setTitle("Loading Data");
            mdialog.setIndeterminate(true);
            mdialog.setProgress(0);
            mdialog.setMax(100);
            mdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mdialog.setCancelable(true);
            mdialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mdialog.dismiss();
                    getData.this.cancel(true);
                }
            });

            mdialog.show();
        }

        //method saat proses asynctask dijalankan
        @Override
        protected String doInBackground(String... strings) {
            //membuat adapter
            madapter = new ArrayAdapter<>(ListNama.this, android.R.layout.simple_list_item_1, mlistnama);
            //menyimpan array pada sebuah variabel
            String[] nama = getResources().getStringArray(R.array.listnama);
            //perulangan untuk menyimpan array
            for (int x = 0; x<nama.length; x++) {
                final long persen = 100*x/nama.length;
                try {
                    Runnable change = new Runnable() {
                        @Override
                        public void run() {
                            mdialog.setMessage((int) persen+"%");
                        }
                    };
                    runOnUiThread(change);
                    Thread.sleep(300);
                    mlistnama.add(nama[x]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        //method sesudah asynctask dijalankan
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mlistview.setAdapter(madapter);
            mdialog.dismiss();
        }
    }
}
