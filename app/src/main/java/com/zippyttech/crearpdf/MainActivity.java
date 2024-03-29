package com.zippyttech.crearpdf;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String NOMBRE_DIRECTORIO = "MisPDFs";
    String NOMBRE_DOCUMENTO = "MiPDF.pdf";

    EditText etTexto;
    Button btnGenerar;
    private List<Anime> list;
    private String title;
    private String subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTexto = findViewById(R.id.etTexto);
        btnGenerar = findViewById(R.id.btnGenerar);

        // Permisos
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                    1000);



            title = getResources().getString(R.string.app_name);
            subtitle = etTexto.getText().toString();

        }

        // Genera el documento
        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Anime> list1 = new ArrayList<>();

                for (int i=0;i<10;i++){
                    Anime a = new Anime();
                    a.setId(i+1);
                    a.setName("name "+i);
                    a.setCapitule(i+(i*3));
                    a.setStatus("emision");
                    a.setDate_c("14/08/2019");
                    a.setDate_int_c(i+(i*1000));
                    a.setDate_u("15/08/2019");
                    a.setDate_int_u(i+(i*1000));
                    a.setColor("xcc");
                    a.setDirImage("//xxx");
                    a.setImage("xxx");
                    a.setType("anime");
                    a.setType_int(i+(i*1000));
                    list1.add(a);
                }
               CreatedPDF pdf = new CreatedPDF(getApplicationContext(),""+getResources().getString(R.string.app_name),list1);
                pdf.crearPDF(""+etTexto.getText().toString());

//                crearPDF();
                Toast.makeText(MainActivity.this, "SE CREO EL PDF", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void crearPDF() {
        Document documento = new Document();

        try {
            File file = crearFichero(NOMBRE_DOCUMENTO);
            FileOutputStream ficheroPDF = new FileOutputStream(file.getAbsolutePath());

            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPDF);

            documento.open();

            documento.add(new Paragraph("TABLA \n\n"));
            documento.add(new Paragraph( etTexto.getText().toString()+"\n\n"));

            // Insertamos una tabla
            PdfPTable tabla = new PdfPTable(5);

            for(int i = 0 ; i < 15 ; i++) {
                tabla.addCell("CELDA "+i);
            }

            documento.add(tabla);

        } catch(DocumentException e) {
        } catch(IOException e) {
        } finally {
            documento.close();
        }
    }

    public File crearFichero(String nombreFichero) {
        File ruta = getRuta();

        File fichero = null;
        if(ruta != null) {
            fichero = new File(ruta, nombreFichero);
        }

        return fichero;
    }

    public File getRuta() {
        File ruta = null;

        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            ruta = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), NOMBRE_DIRECTORIO);

            if(ruta != null) {
                if(!ruta.mkdirs()) {
                    if(!ruta.exists()) {
                        return null;
                    }
                }
            }

        }
        return ruta;
    }
}