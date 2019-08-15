package com.zippyttech.crearpdf;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreatedPDF {

    private static final String NOMBRE_DIRECTORIO = "MisPDFs";
    private static final String NOMBRE_DOCUMENTO = "AnimeList.pdf";
    private Context context;
    private String Title,SubTitle;
    private List<Anime> list1;

    public CreatedPDF(Context context, String title, List<Anime> list) {
        this.Title = title;
        this.context = context;
        this.list1 = list;
    }

    public  void crearPDF(String subTitle) {
        Document documento = new Document();
        this.SubTitle = subTitle;

        try {
            File file = crearFichero(NOMBRE_DOCUMENTO);
            FileOutputStream ficheroPDF = new FileOutputStream(file.getAbsolutePath());

            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPDF);

            documento.open();

            Paragraph ptitle = new Paragraph(""+Title+" \n\n");
            ptitle.setAlignment(Element.ALIGN_CENTER);

            Paragraph pSub = new Paragraph(""+SubTitle+"\n\n");

            documento.add(ptitle);
            documento.add(pSub);

            // Insertamos una tabla
            PdfPTable tabla = new PdfPTable(7);
            tabla.addCell("Id");
            tabla.addCell("Nombre");
            tabla.addCell("Capitulo");
            tabla.addCell("Estatus");
            tabla.addCell("Color");
//            tabla.addCell("Imagen");
            tabla.addCell("Tipo");
//            tabla.addCell("F. Creado");
            tabla.addCell("Fecha");

            for (Anime a: list1){
                tabla.addCell(""+a.getId());
                tabla.addCell(""+a.getName());
                tabla.addCell(""+a.getCapitule());
                tabla.addCell(""+a.getStatus());
                tabla.addCell(""+a.getColor());
//                tabla.addCell(""+a.getDirImage());
                tabla.addCell(""+a.getType());
//                  tabla.addCell(""+a.getDate_c());
                  tabla.addCell(""+a.getDate_u());
            }



//            for(int i = 0 ; i < 15 ; i++) {
//                tabla.addCell("CELDA "+i);
//            }

            documento.add(tabla);

        } catch(DocumentException e) {
        } catch(IOException e) {
        } finally {
            documento.close();
        }
    }

    public  File crearFichero(String nombreFichero) {
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
