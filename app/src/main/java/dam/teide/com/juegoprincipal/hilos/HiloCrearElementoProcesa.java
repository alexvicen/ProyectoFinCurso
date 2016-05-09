package dam.teide.com.juegoprincipal.hilos;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Random;

import dam.teide.com.juegoprincipal.R;
import dam.teide.com.juegoprincipal.nucleo.Index;
import dam.teide.com.juegoprincipal.nucleo.ProcesarMateriales;

public class HiloCrearElementoProcesa extends AsyncTask<Void,Void,Void>{

    private ProcesarMateriales activity;
    private RelativeLayout llJuego;
    private int ancho,alto;
    private Random random = new Random();
    private ArrayList<ImageView> arrayList;
    private int tiempo=0,minutos,segundos;

    public HiloCrearElementoProcesa(ProcesarMateriales activity,ArrayList<ImageView>arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @Override
    protected Void doInBackground(Void... params) {
        while (activity.isPlaying()&&minutos<1&&(activity.getRoca()>0||activity.getTronco()>0||activity.getGemaBruto()>0||activity.getHierro()>0||activity.getOro()>0)) {
            try {
                publishProgress();
                Thread.sleep(500);
                publishProgress();
                Thread.sleep(500);
                tiempo+=1000;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        llJuego = activity.getLlJuego();
        ancho=llJuego.getWidth();
        alto=llJuego.getHeight();
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        segundos=tiempo/1000;
        if (segundos>=60){
            segundos=0;
            tiempo=0;
            minutos++;
        }
        DecimalFormat formato = new DecimalFormat("00");
        activity.getTxtTiempo().setText("02:00 - "+formato.format(minutos)+":"+formato.format(segundos));
        ImageView iv = new ImageView(activity);
        iv.setX(random.nextInt(ancho-90)+10);
        iv.setY(-40);
        int material = random.nextInt(100);
        if (material<=20){
            iv.setBackgroundResource(R.drawable.roca);
            iv.setTag("roca");
        }else if (material<=40){
            iv.setBackgroundResource(R.drawable.troncos);
            iv.setTag("tronco");
        }else if (material<=60){
            iv.setBackgroundResource(R.drawable.gema_bruto);
            iv.setTag("gema_bruto");
        }else if (material<=80){
            iv.setBackgroundResource(R.drawable.hierro);
            iv.setTag("hierro");
        }else{
            iv.setBackgroundResource(R.drawable.pepita);
            iv.setTag("pepita");
        }
        arrayList.add(iv);
        iv.setOnClickListener(activity);
        llJuego.addView(iv);

        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
