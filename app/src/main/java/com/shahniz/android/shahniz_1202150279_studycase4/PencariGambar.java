package com.shahniz.android.shahniz_1202150279_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class PencariGambar extends AppCompatActivity {
    //deklarasi komponen variabel yang dibutuhkan
    EditText murlgambar;
    ImageView mimage;
    ProgressDialog mprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencari_gambar);

        murlgambar=findViewById(R.id.urlgambar);
        mimage=findViewById(R.id.image);
    }

    //method saat button diklik
    public void carigambar(View view) {
        loadInit();
    }

    private void loadInit() {
        String url = murlgambar.getText().toString();
        //asynctask mencari gambar di internet
        new loadingimage().execute(url);
    }

    //subclass asynctask
    private class loadingimage extends AsyncTask<String, Void, Bitmap> {

        //method ketika asynctask belum dimulai
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //membuat progress dialog
            mprogress = new ProgressDialog(PencariGambar.this);
            //judul
            mprogress.setTitle("Searching Image");
            //set pesan
            mprogress.setMessage("Processing...");
            //tampil
            mprogress.show();
        }

        //method saat asynctask dijalankan
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                //download gambar dari url
                URL url = new URL(params[0]);
                //konversi gambar ke bitmap
                bitmap = BitmapFactory.decodeStream((InputStream) url.getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        //method sesudah asynctask dijalankan
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //menampillkan gambar di imageview
            mimage.setImageBitmap(bitmap);
            //menghilangkan progress dialog
            mprogress.dismiss();
        }
    }
}
