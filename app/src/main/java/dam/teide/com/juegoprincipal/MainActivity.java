package dam.teide.com.juegoprincipal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int arraynum [][] = new int [8][8];
    private ImageView arrayimg [][] = new ImageView [arraynum.length][arraynum[1].length];
    private Random r = new Random();
    private LinearLayout panelJuego;
    private int contador=0,puntos=20,punNar=0,punRoj=0,punVer=0,punAzu=0,punMor=0;
    private ImageView casilla1,casilla2;
    private TextView txtPuntos,txtNar,txtRoj,txtVer,txtAzu,txtMor;
    private boolean play=false;
    private HiloJuego hj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        panelJuego = (LinearLayout)findViewById(R.id.panelJuego);
        txtPuntos = (TextView)findViewById(R.id.txtPuntos);
        txtNar=(TextView)findViewById(R.id.txtNar);
        txtRoj=(TextView)findViewById(R.id.txtRoj);
        txtVer=(TextView)findViewById(R.id.txtVer);
        txtAzu=(TextView)findViewById(R.id.txtAzu);
        txtMor=(TextView)findViewById(R.id.txtMor);
        play=true;
        CrearArrayTablero();
        CrearImagenesTablero();
        CrearTablero();
        hj = new HiloJuego(this,puntos,arraynum,arrayimg);
        hj.execute();
        hj.comprobarHorizontal();
        hj.comprobarVertical();
        txtPuntos.setText(puntos+"");
    }
    public void CrearArrayTableroMano(){

        arraynum [0][0] = 0; arraynum [0][1] = 0; arraynum [0][2] = 2; arraynum [0][3] = 3; arraynum [0][4] = 4; arraynum [0][5] = 0; arraynum [0][6] = 1; arraynum [0][7] = 2;
        arraynum [1][0] = 3; arraynum [1][1] = 4; arraynum [1][2] = 0; arraynum [1][3] = 1; arraynum [1][4] = 2; arraynum [1][5] = 3; arraynum [1][6] = 4; arraynum [1][7] = 0;
        arraynum [2][0] = 1; arraynum [2][1] = 2; arraynum [2][2] = 3; arraynum [2][3] = 3; arraynum [2][4] = 0; arraynum [2][5] = 1; arraynum [2][6] = 2; arraynum [2][7] = 3;
        arraynum [3][0] = 4; arraynum [3][1] = 1; arraynum [3][2] = 1; arraynum [3][3] = 2; arraynum [3][4] = 3; arraynum [3][5] = 2; arraynum [3][6] = 0; arraynum [3][7] = 1;
        arraynum [4][0] = 2; arraynum [4][1] = 3; arraynum [4][2] = 4; arraynum [4][3] = 0; arraynum [4][4] = 1; arraynum [4][5] = 2; arraynum [4][6] = 3; arraynum [4][7] = 1;
        arraynum [5][0] = 0; arraynum [5][1] = 1; arraynum [5][2] = 2; arraynum [5][3] = 2; arraynum [5][4] = 4; arraynum [5][5] = 0; arraynum [5][6] = 1; arraynum [5][7] = 2;
        arraynum [6][0] = 1; arraynum [6][1] = 4; arraynum [6][2] = 0; arraynum [6][3] = 1; arraynum [6][4] = 2; arraynum [6][5] = 3; arraynum [6][6] = 4; arraynum [6][7] = 0;
        arraynum [7][0] = 1; arraynum [7][1] = 2; arraynum [7][2] = 3; arraynum [7][3] = 4; arraynum [7][4] = 0; arraynum [7][5] = 1; arraynum [7][6] = 2; arraynum [7][7] = 3;

    }
    public void CrearArrayTablero(){
        for (int i = 0;i<arraynum.length;i++){
            for (int j = 0;j<arraynum[i].length;j++){
                arraynum[i][j]=r.nextInt(5);
            }
        }
    }
    public void CrearImagenesTablero(){
        for (int i = 0;i<arraynum.length;i++){
            for (int j = 0;j<arraynum[i].length;j++){
                ImageView imageView = new ImageView(this);
                if(arraynum[i][j]==0){
                    imageView.setImageResource(R.drawable.diamante);
                }else if(arraynum[i][j]==1){
                    imageView.setImageResource(R.drawable.diamante1);
                }else if(arraynum[i][j]==2){
                    imageView.setImageResource(R.drawable.diamante2);
                }else if(arraynum[i][j]==3){
                    imageView.setImageResource(R.drawable.diamante3);
                }else{
                    imageView.setImageResource(R.drawable.diamante4);
                }
                arrayimg[i][j]= imageView;
            }
        }
    }
    public void CrearTablero() {
        for (int i = 0; i < arraynum.length; i++) {
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < arraynum[i].length; j++) {
                ImageView iv = new ImageView(this);
                iv = arrayimg[i][j];
                iv.setTag(i + "" + j);
                iv.setOnClickListener(this);
                ll.addView(iv);
            }
            panelJuego.addView(ll);
        }

    }

    @Override
    public void onClick(View v) {
        contador++;
        if(contador==1){
            casilla1 = (ImageView) v;
            activarProximos();
        }else if(contador==2){
            casilla2 = (ImageView) v;
            if (casilla1 == casilla2){
                contador = 0;
                activarTodos();
            }else {
                cambiarPosicion(casilla1, casilla2);
                hj.comprobarVertical();
                hj.comprobarHorizontal();
                contador = 0;
                movimientos();
                activarTodos();
            }
        }
    }
    public void cambiarPosicion(ImageView imageView1, ImageView imageView2){
        String tag1 = imageView1.getTag().toString();
        String tag2 = imageView2.getTag().toString();
        int i1 = Integer.parseInt(tag1.substring(0,1));
        int j1 = Integer.parseInt(tag1.substring(1));
        int i2 = Integer.parseInt(tag2.substring(0,1));
        int j2 = Integer.parseInt(tag2.substring(1));
        int aux;
        aux = arraynum[i1][j1];
        arraynum[i1][j1]=arraynum[i2][j2];
        arraynum[i2][j2]=aux;
        panelJuego.removeAllViews();
        CrearImagenesTablero();
        CrearTablero();
    }

    public void activarProximos(){
        String tag1 = casilla1.getTag().toString();
        int i1 = Integer.parseInt(tag1.substring(0,1));
        int j1 = Integer.parseInt(tag1.substring(1));
        for (int i = 0;i<arrayimg.length;i++){
            for (int j = 0;j<arrayimg[i].length;j++){
                if (i1==i-1&&j1==j-1||i1==i&&j1==j-1||i1==i+1&&j1==j-1||i1==i+1&&j1==j||i1==i+1&&j1==j+1||i1==i&&j1==j+1||i1==i-1&&j1==j+1||i1==i-1&&j1==j||i1==i&&j1==j){

                }else {
                    arrayimg[i][j].setClickable(false);
                    arrayimg[i][j].setAlpha(0.5f);
                }
            }
        }
    }
    public void activarTodos(){
        for (int i = 0;i<arrayimg.length;i++){
            for (int j = 0;j<arrayimg[i].length;j++){
                arrayimg[i][j].setClickable(true);
                arrayimg[i][j].setAlpha(1f);
            }
        }
    }

    public int getPuntos() {
        return puntos;
    }

    public int getPunNar() {
        return punNar;
    }

    public void setPunNar(int punNar) {
        this.punNar = punNar;
        txtNar.setText(punNar+"");
    }

    public int getPunRoj() {
        return punRoj;
    }

    public void setPunRoj(int punRoj) {
        this.punRoj = punRoj;
        txtRoj.setText(punRoj+"");
    }

    public int getPunAzu() {
        return punAzu;
    }

    public void setPunAzu(int punAzu) {
        this.punAzu = punAzu;
        txtAzu.setText(punAzu+"");
    }

    public int getPunVer() {
        return punVer;
    }

    public void setPunVer(int punVer) {
        this.punVer = punVer;
        txtVer.setText(punVer+"");
    }

    public int getPunMor() {
        return punMor;
    }

    public void setPunMor(int punMor) {
        this.punMor = punMor;
        txtMor.setText(punMor+"");
    }
    public void movimientos(){
        puntos--;
        txtPuntos.setText(puntos+"");
        if (puntos==0){
            panelJuego.setVisibility(View.INVISIBLE);
        }
    }
    public boolean isPlay() {
        return play;
    }
}
