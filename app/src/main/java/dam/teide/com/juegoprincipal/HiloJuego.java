package dam.teide.com.juegoprincipal;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by ACER Aspire on 12/03/2016.
 */
public class HiloJuego extends AsyncTask<Void,Void,Void>{
    private MainActivity activity;
    private int puntos;
    private int arraynum [][];
    private ImageView arrayimg [][];
    private Random r = new Random();

    public HiloJuego(MainActivity activity, int puntos, int[][] arraynum, ImageView[][] arrayimg) {
        this.activity = activity;
        this.puntos = puntos;
        this.arraynum = arraynum;
        this.arrayimg = arrayimg;
    }

    @Override
    protected Void doInBackground(Void... params) {
        while (activity.getPuntos()>0){
            publishProgress();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(activity, "Juego Empezado", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Intent i = new Intent(activity,Index.class);
        activity.startActivity(i);
    }

    public void comprobarHorizontal(){
        int contadorHoriz = 0;
        for (int i = 0;i<arraynum.length;i++) {
            for (int j = 1; j < arraynum[i].length; j++) {
                if (arraynum[i][j - 1] == arraynum[i][j]) {
                    contadorHoriz++;
                    if (contadorHoriz == 2) {
                        contadorHoriz = 0;
                        sumarPuntos(arraynum[i][j]);
                        activity.cambiarPosicion(arrayimg[i][j - 1],arrayimg[r.nextInt(arraynum.length)][r.nextInt(arraynum.length)]);
                        activity.cambiarPosicion(arrayimg[i][j], arrayimg[r.nextInt(arraynum.length)][r.nextInt(arraynum.length)]);
                        j=1;
                    }
                } else {
                    contadorHoriz = 0;
                }
            }
        }
    }
    public void comprobarVertical(){
        int contadorVertical=0;
        for (int i = 0; i < arraynum.length; i++) {
            for (int j = 1; j < arraynum.length; j++) {
                if (arraynum[j - 1][i] == arraynum[j][i]) {
                    contadorVertical++;
                    if (contadorVertical == 2) {
                        contadorVertical = 0;
                        sumarPuntos(arraynum[j][i]);
                        activity.cambiarPosicion(arrayimg[j - 1][i],arrayimg[r.nextInt(arraynum.length)][r.nextInt(arraynum.length)]);
                        activity.cambiarPosicion(arrayimg[j][i], arrayimg[r.nextInt(arraynum.length)][r.nextInt(arraynum.length)]);
                        j=1;
                    }
                } else {
                    contadorVertical = 0;
                }
            }
        }
    }
    public void sumarPuntos(int t){
        if (t==0){
            activity.setPunNar(activity.getPunNar()+3);
        }else if (t==1){
            activity.setPunRoj(activity.getPunRoj()+3);
        }else if (t==2){
            activity.setPunVer(activity.getPunVer()+3);
        }else if (t==3){
            activity.setPunAzu(activity.getPunAzu()+3);
        }else if (t==4){
            activity.setPunMor(activity.getPunMor()+3);
        }
    }


}
