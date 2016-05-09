package dam.teide.com.juegoprincipal.hilos;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import dam.teide.com.juegoprincipal.nucleo.ProcesarMateriales;

public class HiloEliminarMaterial extends AsyncTask<Void,Void,Void>{

    private ProcesarMateriales activity;
    private ArrayList<ImageView> arrayList;
    private View iv;

    public HiloEliminarMaterial(ProcesarMateriales activity, ArrayList<ImageView> arrayList, View iv) {
        this.activity = activity;
        this.arrayList = arrayList;
        this.iv = iv;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publishProgress();
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        arrayList.remove(iv);
        activity.getLlJuego().removeView(iv);
    }


}
