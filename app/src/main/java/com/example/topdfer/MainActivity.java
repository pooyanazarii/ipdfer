package com.example.topdfer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rand  = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar rightNow = Calendar.getInstance();


        createAndDisplayPDF("POOYA NAZARI IS A ANDROID DEVELOPER");
    }

    public void createAndDisplayPDF (String mytext){
        Document doc = new Document();

        try{
            String pathPdf;
            pathPdf = Environment.getExternalStorageDirectory().getAbsolutePath();

            File dir = new File(pathPdf);
            if(!dir.exists()) // Agar Nabood Besaz Directory ro
                dir.mkdir();
            String nameFile = "sssTPDF"+rand.nextInt(1000)+".pdf";

            File file = new File (dir,nameFile);
            FileOutputStream fOut= new FileOutputStream(file); // this is have Exception

            PdfWriter.getInstance(doc,fOut); // this is have Exception

            doc.open();
            Paragraph p1 = new Paragraph(mytext);
            //Font paraFont = new Font(Font.)
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            //p1.setFont(paraFont);

            doc.add(p1);
        }catch(DocumentException de){
            Log.e("---------- deErr",""+de);
        }catch (IOException e){
            Log.e("----------eErr",""+e);
        }finally{
            doc.close();
            Toast.makeText(this, "File is ready", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "File is ready", Toast.LENGTH_SHORT).show();
        viewPdf("sample.pdf");

    }

    private void viewPdf(String file) {
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/" + file);
        Uri path = Uri.fromFile(pdfFile);

        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path,"application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try{
            startActivity(pdfIntent);
        }catch(ActivityNotFoundException e){
            Toast.makeText(this, "Can't read pdf File", Toast.LENGTH_SHORT).show();
            Log.e("---------ActivityErr",""+e);
        }

    }
}