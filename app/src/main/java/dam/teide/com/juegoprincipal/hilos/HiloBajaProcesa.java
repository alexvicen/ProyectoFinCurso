package dam.teide.com.juegoprincipal.hilos;

import android.os.AsyncTask;
import android.widget.ImageView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import dam.teide.com.juegoprincipal.nucleo.ProcesarMateriales;

public class HiloBajaProcesa extends AsyncTask<Void,Void,Void> {

    private ProcesarMateriales activity;
    private ArrayList<ImageView> arrayList;
    private Random random = new Random();

    public HiloBajaProcesa(ProcesarMateriales activity,ArrayList<ImageView> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @Override
    protected Void doInBackground(Void... params) {
        while (activity.isPlaying()){
            try {
                Thread.sleep(10);
                publishProgress();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        for (int i = 0; i <arrayList.size() ; i++) {
            arrayList.get(i).setY(arrayList.get(i).getY()+2);
            if (arrayList.get(i).getY()>=activity.getLlJuego().getHeight()-20){

                switch (arrayList.get(i).getTag().toString()){
                    case "roca":
                        activity.setRoca(-1);

                        break;
                    case "tronco":
                        activity.setTronco(-1);
                        break;
                    case "gema_bruto":
                        activity.setGemaBruto(-1);
                        break;
                    case "hierro":
                        activity.setHierro(-1);
                        if (random.nextInt(3)==2){

                        }else{
                            activity.setLingoteHierro(1);
                        }


                        break;
                    case "pepita":
                        activity.setOro(-1);
                        if (random.nextInt(3)==2){

                        }else {
                            activity.setLingoteOro(1);
                        }
                        break;
                }
                activity.getLlJuego().removeView(arrayList.get(i));
                arrayList.remove(i);
                if (arrayList.size()==0){
                    activity.setPlay(false);
                }
            }
        }

        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try {
            activity.TerminarJuego();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
