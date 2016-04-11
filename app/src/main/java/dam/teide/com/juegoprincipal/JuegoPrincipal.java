package dam.teide.com.juegoprincipal;

import android.support.v7.app.AppCompatActivity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class JuegoPrincipal extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rl;
    private AnimationDrawable animacion1 = new AnimationDrawable();
    private AnimationDrawable animacion2 = new AnimationDrawable();
    private AnimationDrawable animacion3 = new AnimationDrawable();
    private AnimationDrawable animacion4 = new AnimationDrawable();
    private int movimiento1, movimiento2, movimiento3,movimiento4;
    private ImageView ivPer,iv1, iv2, iv3;
    private int ancho, alto;
    private TextView txt1, txt2,txt3,txtVida;
    private Button btnJugar,btnSimple,btnEspecial;
    private ProgressBar pbVida,pb1,pb2,pb3;
    private boolean encendido = true,ataque=true,ataquePer=true,ataqueEsp=true,enem1=false,enem2=false,enem3=false;
    private HiloMoverEntrada hm;
    private HiloAtaqueEnemigos ha;
    private TaskHelper t = new TaskHelper();
    private Personaje personaje = new Personaje(2);
    private Esqueleto esqueleto1 = new Esqueleto(1);
    private Esqueleto esqueleto2 = new Esqueleto(2);
    private Esqueleto esqueleto3 = new Esqueleto(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_principal);
        rl = (RelativeLayout) findViewById(R.id.rl);
        txtVida = (TextView)findViewById(R.id.txtvida);
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView)findViewById(R.id.txt3);
        btnJugar = (Button) findViewById(R.id.btn);
        btnSimple = (Button) findViewById(R.id.btnSimple);
        btnEspecial = (Button) findViewById(R.id.btnEspecial);
        pbVida = (ProgressBar)findViewById(R.id.pbVida);
        pb1 = (ProgressBar)findViewById(R.id.pb1);
        pb2 = (ProgressBar)findViewById(R.id.pb2);
        pb3 = (ProgressBar)findViewById(R.id.pb3);

        btnJugar.setOnClickListener(this);
        btnSimple.setOnClickListener(this);
        btnEspecial.setOnClickListener(this);
        btnSimple.setVisibility(View.INVISIBLE);
        btnEspecial.setVisibility(View.INVISIBLE);
        txtVida.setVisibility(View.INVISIBLE);
        txt1.setVisibility(View.INVISIBLE);
        txt2.setVisibility(View.INVISIBLE);
        txt3.setVisibility(View.INVISIBLE);
        pbVida.setVisibility(View.INVISIBLE);
        pb1.setVisibility(View.INVISIBLE);
        pb2.setVisibility(View.INVISIBLE);
        pb3.setVisibility(View.INVISIBLE);
        btnJugar.setBackground(getDrawable(R.drawable.boton));
        btnSimple.setBackground(getDrawable(R.drawable.boton));
        btnEspecial.setBackground(getDrawable(R.drawable.boton));

        txtVida.setText(personaje.getVida() + "");
        pbVida.setMax(personaje.getVida());
        pbVida.setProgress(personaje.getVida());

        txt1.setText(esqueleto1.getVida() + "");
        pb1.setMax(esqueleto1.getVida());
        pb1.setProgress(esqueleto1.getVida());

        txt2.setText(esqueleto2.getVida()+"");
        pb2.setMax(esqueleto2.getVida());
        pb2.setProgress(esqueleto2.getVida());

        txt3.setText(esqueleto3.getVida()+"");
        pb3.setMax(esqueleto3.getVida());
        pb3.setProgress(esqueleto3.getVida());

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn) {
            btnJugar.setVisibility(View.INVISIBLE);
            btnJugar.setEnabled(false);
            iv1 = new ImageView(this);
            iv2 = new ImageView(this);
            iv3 = new ImageView(this);
            ivPer = new ImageView(this);
            ancho = rl.getWidth();
            alto = rl.getHeight() - 120;
            iv1.setX(ancho);
            iv1.setY(alto);
            iv2.setX(ancho);
            iv2.setY(alto / 2);
            iv3.setX(ancho);
            iv3.setY(10);
            ivPer.setX(0);
            ivPer.setY(alto / 2);
            rl.addView(iv1);
            rl.addView(iv2);
            rl.addView(iv3);
            rl.addView(ivPer);
            movimiento1 = R.drawable.entrada_esqueleto;
            movimiento2 = R.drawable.entrada_esqueleto;
            movimiento3 = R.drawable.entrada_esqueleto;
            movimiento4 = R.drawable.entrada_chico;
            iv1.setBackgroundResource(movimiento1);
            iv2.setBackgroundResource(movimiento2);
            iv3.setBackgroundResource(movimiento3);
            ivPer.setBackgroundResource(movimiento4);
            animacion1 = (AnimationDrawable) iv1.getBackground();
            animacion2 = (AnimationDrawable) iv2.getBackground();
            animacion3 = (AnimationDrawable) iv3.getBackground();
            animacion4 = (AnimationDrawable) ivPer.getBackground();
            animacion1.start();
            animacion2.start();
            animacion3.start();
            animacion4.start();
            hm = new HiloMoverEntrada(this, iv1, iv2, iv3, ivPer);
            hm.execute();
        }else if (v.getId()==R.id.btnSimple){
            HiloAtaquePersonaje hap= new HiloAtaquePersonaje(this, iv1, iv2, iv3, ivPer);
            t.execute(hap);
            ataqueEsp=false;
        }else if (v.getId()==R.id.btnEspecial){
            HiloAtaquePersonaje hap= new HiloAtaquePersonaje(this, iv1, iv2, iv3, ivPer);
            t.execute(hap);
            ataqueEsp=true;
        }
    }

    public boolean isEncendido() {
        return encendido;
    }
    public void setEncendido(boolean encendido) {
        this.encendido = encendido;
    }
    public RelativeLayout getRl() {
        return rl;
    }
    public AnimationDrawable getAnimacion1() {
        return animacion1;
    }
    public AnimationDrawable getAnimacion2() {
        return animacion2;
    }
    public AnimationDrawable getAnimacion3() {
        return animacion3;
    }
    public AnimationDrawable getAnimacion4() {
        return animacion4;
    }
    public Button getBtnEspecial() {
        return btnEspecial;
    }
    public Button getBtnSimple() {
        return btnSimple;
    }
    public boolean isAtaque() {
        return ataque;
    }
    public void setAtaque(boolean ataque) {
        this.ataque = ataque;
    }
    public TaskHelper getT() {
        return t;
    }
    public boolean isAtaquePer() {
        return ataquePer;
    }
    public void setAtaquePer(boolean ataquePer) {
        this.ataquePer = ataquePer;
    }
    public boolean isAtaqueEsp() {
        return ataqueEsp;
    }
    public Personaje getPersonaje() {
        return personaje;
    }
    public TextView getTxtVida() {
        return txtVida;
    }
    public Esqueleto getEsqueleto1() {
        return esqueleto1;
    }
    public Esqueleto getEsqueleto2() {
        return esqueleto2;
    }
    public Esqueleto getEsqueleto3() {
        return esqueleto3;
    }
    public TextView getTxt1() {
        return txt1;
    }
    public TextView getTxt2() {
        return txt2;
    }
    public TextView getTxt3() {
        return txt3;
    }
    public ProgressBar getPb1() {
        return pb1;
    }
    public ProgressBar getPb2() {
        return pb2;
    }
    public ProgressBar getPb3() {
        return pb3;
    }
    public ProgressBar getPbVida() {
        return pbVida;
    }
    public boolean isEnem1() {
        return enem1;
    }
    public void setEnem1(boolean enem1) {
        this.enem1 = enem1;
    }
    public boolean isEnem2() {
        return enem2;
    }
    public void setEnem2(boolean enem2) {
        this.enem2 = enem2;
    }
    public boolean isEnem3() {
        return enem3;
    }
    public void setEnem3(boolean enem3) {
        this.enem3 = enem3;
    }

    public void bajarVida(Esqueleto esqueleto){
        int daño =personaje.getVida()-(esqueleto.getAtaque()-(personaje.getDefensa()));
        if (daño<0){
            daño=0;
        }
        personaje.setVida(daño);
        if (personaje.getVida()<1){
            personaje.setVida(1);
        }
        pbVida.setProgress(personaje.getVida());
        txtVida.setText(personaje.getVida() + "");
        if (personaje.getVida()<=5){
            encendido=false;
        }
        if ((esqueleto1.getVida()<1)&&(esqueleto2.getVida()<1)&&(esqueleto3.getVida()<1)){
            encendido=false;
        }
    }
    public void bajarVidaCritico(Esqueleto esqueleto){
        int daño = personaje.getVida() - (esqueleto.getAtaque()+esqueleto.getCritico()-(personaje.getDefensa()));
        if (daño<0){
            daño=0;
        }
        personaje.setVida(daño);
        if (personaje.getVida()<1){
            personaje.setVida(1);
        }
        pbVida.setProgress(personaje.getVida());
        txtVida.setText(personaje.getVida()+"");
        if (personaje.getVida()<=5){
            encendido=false;
        }
        if ((esqueleto1.getVida()<1)&&(esqueleto2.getVida()<1)&&(esqueleto3.getVida()<1)){
            encendido=false;
        }
    }
    public void bajarVidaEnemigo(Esqueleto esqueleto){
        if (esqueleto==esqueleto1){
            int daño = esqueleto1.getVida() - (personaje.getAtaque() - (esqueleto1.getDefensa()));
            if (daño<0){
                daño=0;
            }
            esqueleto1.setVida(daño);
            pb1.setProgress(esqueleto.getVida());
            txt1.setText(esqueleto.getVida()+"");
            if (esqueleto1.getVida()<=0){
                enem1=true;
            }
        }else if (esqueleto==esqueleto2){
            int daño=esqueleto2.getVida() - (personaje.getAtaque()-(esqueleto2.getDefensa()));
            if (daño<0){
                daño=0;
            }
            esqueleto2.setVida(daño);
            pb2.setProgress(esqueleto.getVida());
            txt2.setText(esqueleto.getVida()+"");
            if (esqueleto2.getVida()<=0){
                enem2=true;
            }
        }else if (esqueleto==esqueleto3){
            int daño = esqueleto3.getVida() - (personaje.getAtaque()-(esqueleto3.getDefensa()));
            if (daño<0){
                daño=0;
            }
            esqueleto3.setVida(daño);
            pb3.setProgress(esqueleto.getVida());
            txt3.setText(esqueleto.getVida()+"");
            if (esqueleto3.getVida()<=0){
                enem3=true;
            }
        }
        if ((esqueleto1.getVida()<1)&&(esqueleto2.getVida()<1)&&(esqueleto3.getVida()<1)){
            encendido=false;
        }

    }
    public void bajarVidaEnemigoCritico(Esqueleto esqueleto){
        if (esqueleto==esqueleto1){
            int daño = esqueleto1.getVida() - (personaje.getAtaque()+personaje.getCritico()-esqueleto1.getDefensa());
            if (daño<0){
                daño=0;
            }
            esqueleto1.setVida(daño);
            pb1.setProgress(esqueleto.getVida());
            txt1.setText(esqueleto.getVida()+"");
            if (esqueleto1.getVida()<=0){
                enem1=true;
            }
        }else if (esqueleto==esqueleto2){
            int daño = esqueleto2.getVida() - (personaje.getAtaque()+personaje.getCritico()-esqueleto2.getDefensa());
            if (daño<0){
                daño=0;
            }
            esqueleto2.setVida(daño);
            pb2.setProgress(esqueleto.getVida());
            txt2.setText(esqueleto.getVida()+"");
            if (esqueleto2.getVida()<=0){
                enem2=true;
            }
        }else if (esqueleto==esqueleto3){
            int daño = esqueleto3.getVida() - (personaje.getAtaque()+personaje.getCritico()-esqueleto3.getDefensa());
            if (daño<0){
                daño=0;
            }
            esqueleto3.setVida(daño);
            pb3.setProgress(esqueleto.getVida());
            txt3.setText(esqueleto.getVida()+"");
            if (esqueleto3.getVida()<=0){
                enem3=true;
            }
        }
        if (esqueleto1.getVida()<1&&esqueleto2.getVida()<1&&esqueleto3.getVida()<1){
            encendido=false;
        }
    }
}
