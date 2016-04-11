package dam.teide.com.juegoprincipal;

import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class HiloAtaquePersonaje extends AsyncTask<Void,Integer,Void>{
    private JuegoPrincipal activity;
    private ImageView iv1,iv2,iv3,ivPer;
    private float metaX1,metaX3,x,x1,x2,x3,y,y1,y3,aumX1,aumX3;
    private ImageView flechaPer1;
    private ImageView flechaPer2;
    private ImageView flechaPer3;
    private int movimientoPer;
    private AnimationDrawable animacionPer;
    private Random r = new Random();

    public HiloAtaquePersonaje(JuegoPrincipal activity, ImageView iv1, ImageView iv2, ImageView iv3, ImageView ivPer) {
        this.activity = activity;
        this.iv1 = iv1;
        this.iv2 = iv2;
        this.iv3 = iv3;
        this.ivPer = ivPer;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            if (activity.isAtaqueEsp()) {
                publishProgress(3);
            }else{
                publishProgress(0);
            }
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!activity.isAtaqueEsp()) {
            while (activity.isAtaquePer()) {
                try {
                    publishProgress(1);
                    Thread.sleep(7);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            publishProgress(2);
            activity.setAtaquePer(true);
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.getBtnEspecial().setVisibility(View.INVISIBLE);
        activity.getBtnSimple().setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        switch (values[0]){
            case 0:
                if(activity.getEsqueleto1().getVida()>0){
                    flechaPer1 = new ImageView(activity);
                    flechaPer1.setX(ivPer.getX());
                    flechaPer1.setY(ivPer.getY());
                    if (r.nextInt(20)<5){
                        flechaPer1.setImageResource(R.drawable.personajeflechaespecial);
                        flechaPer1.setTag("fp1especial");
                    }else {
                        flechaPer1.setImageResource(R.drawable.personajeflecha);
                        flechaPer1.setTag("fp1");
                    }
                    activity.getRl().addView(flechaPer1);
                    flechaPer1.setVisibility(View.INVISIBLE);
                }

                if(activity.getEsqueleto2().getVida()>0){
                    flechaPer2 = new ImageView(activity);
                    flechaPer2.setX(ivPer.getX());
                    flechaPer2.setY(ivPer.getY());
                    if (r.nextInt(20)<5){
                        flechaPer2.setImageResource(R.drawable.personajeflechaespecial);
                        flechaPer2.setTag("fp2especial");
                    }else {
                        flechaPer2.setImageResource(R.drawable.personajeflecha);
                        flechaPer2.setTag("fp2");
                    }
                    activity.getRl().addView(flechaPer2);
                    flechaPer2.setVisibility(View.INVISIBLE);
                }

                if(activity.getEsqueleto3().getVida()>0){
                    flechaPer3 = new ImageView(activity);
                    flechaPer3.setX(ivPer.getX());
                    flechaPer3.setY(ivPer.getY());
                    if (r.nextInt(20)==10){
                        flechaPer3.setImageResource(R.drawable.personajeflechaespecial);
                        flechaPer3.setTag("fp3especial");
                    }else {
                        flechaPer3.setImageResource(R.drawable.personajeflecha);
                        flechaPer3.setTag("fp3");
                    }
                    activity.getRl().addView(flechaPer3);
                    flechaPer3.setVisibility(View.INVISIBLE);
                }


                x=ivPer.getX();
                y=ivPer.getY();
                x1=x;
                x2=x;
                x3=x;
                y1=y;
                y3=y;
                metaX1=iv1.getX();
                aumX1=metaX1/ivPer.getX();

                metaX3=iv3.getX();
                aumX3=metaX3/ivPer.getX();

                movimientoPer = R.drawable.ataque_chico;
                ivPer.setBackgroundResource(0);
                ivPer.setBackgroundResource(movimientoPer);
                animacionPer = (AnimationDrawable) ivPer.getBackground();
                animacionPer.start();

                break;
            case 1:
                if (activity.getEsqueleto1().getVida()>0){
                    if (flechaPer1.getX()<iv1.getX()){
                        flechaPer1.setVisibility(View.VISIBLE);
                        x1+=aumX1;
                        y1+=1;
                        flechaPer1.setX(x1);
                        flechaPer1.setY(y1);
                    }else{
                        activity.setAtaquePer(false);
                    }
                }
                if (activity.getEsqueleto2().getVida()>0){
                    if (flechaPer2.getX()<iv2.getX()){
                        flechaPer2.setVisibility(View.VISIBLE);
                        x2+=aumX1;
                        flechaPer2.setX(x2);
                    }else{
                        if (activity.getEsqueleto1().getVida()<=0&&activity.getEsqueleto3().getVida()<=0){
                            activity.setAtaquePer(false);
                        }
                    }
                }
                if (activity.getEsqueleto3().getVida()>0){
                    if (flechaPer3.getX()<iv3.getX()){
                        flechaPer3.setVisibility(View.VISIBLE);
                        x3+=aumX3;
                        y3-=1;
                        flechaPer3.setX(x3);
                        flechaPer3.setY(y3);
                    }else{
                        activity.setAtaquePer(false);
                    }
                }

                break;
            case 2:
                if(activity.getEsqueleto1().getVida()>0) {
                    if (flechaPer1.getTag() == "fp1") {
                        activity.bajarVidaEnemigo(activity.getEsqueleto1());
                    } else if (flechaPer1.getTag() == "fp1especial") {
                        activity.bajarVidaEnemigoCritico(activity.getEsqueleto1());
                    }
                    activity.getRl().removeView(flechaPer1);
                }
                if(activity.getEsqueleto2().getVida()>0) {
                    if (flechaPer2.getTag() == "fp2") {
                        activity.bajarVidaEnemigo(activity.getEsqueleto2());
                    } else if (flechaPer2.getTag() == "fp2especial") {
                        activity.bajarVidaEnemigoCritico(activity.getEsqueleto2());
                    }
                    activity.getRl().removeView(flechaPer2);
                }
                if(activity.getEsqueleto3().getVida()>0) {
                    if (flechaPer3.getTag() == "fp3") {
                        activity.bajarVidaEnemigo(activity.getEsqueleto3());
                    } else if (flechaPer3.getTag() == "fp3especial") {
                        activity.bajarVidaEnemigoCritico(activity.getEsqueleto3());
                    }
                    activity.getRl().removeView(flechaPer3);
                }
                break;
            case 3:
                movimientoPer = R.drawable.ataque_especial_chico;
                ivPer.setBackgroundResource(0);
                ivPer.setBackgroundResource(movimientoPer);
                animacionPer = (AnimationDrawable) ivPer.getBackground();
                animacionPer.start();
                break;
            case 4:

                break;
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        activity.getBtnEspecial().setVisibility(View.VISIBLE);
        activity.getBtnSimple().setVisibility(View.VISIBLE);
        if(activity.getEsqueleto1().getVida()>0) {
            activity.getRl().removeView(flechaPer1);
        }
        if(activity.getEsqueleto2().getVida()>0) {
            activity.getRl().removeView(flechaPer2);
        }
        if(activity.getEsqueleto3().getVida()>0) {
            activity.getRl().removeView(flechaPer3);
        }
    }
}
