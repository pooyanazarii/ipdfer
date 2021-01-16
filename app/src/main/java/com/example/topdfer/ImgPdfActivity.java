package com.example.topdfer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class ImgPdfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_pdf);
        testIMGtoPDF();
    }
    public void testIMGtoPDF(){

        Document document = new Document();
        String directoryPath = android.os.Environment.getExternalStorageDirectory().toString();
        Log.e("**----path",""+directoryPath);
        Log.e("**----path",""+directoryPath+ "/" + "example.png");
        try {

            PdfWriter.getInstance(document, new FileOutputStream((directoryPath + "/example22.pdf"))); //  Change pdf's name.
            Image image = Image.getInstance(directoryPath + "/" + "example.jpg");  // Change image's name and extension.
            //Image image = Image.getInstance(String.valueOf((R.drawable.ax_simple)));  // Change image's name and extension.


            float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin() - 0) / image.getWidth()) * 100; // 0 means you have no indentation. If you have any, change it.

            image.scalePercent(scaler);

            image.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);

            document.add(image);
            document.close();

        } catch (DocumentException e) {
            Log.e("----DocumentException",""+e);
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            Log.e("----FileNotFoundExc",""+e);
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (MalformedURLException e) {
            Log.e("----MalformedURLExc",""+e);
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("----IOException",""+e);
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }finally {
            Toast.makeText(this, "all right!", Toast.LENGTH_SHORT).show();
        }
    }

}