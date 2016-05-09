package dam.teide.com.juegoprincipal.nucleo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import dam.teide.com.juegoprincipal.R;
import dam.teide.com.juegoprincipal.dao.PersonajeDao;
import dam.teide.com.juegoprincipal.entidades.Personaje;
import dam.teide.com.juegoprincipal.hilos.HiloBajaProcesa;
import dam.teide.com.juegoprincipal.hilos.HiloCrearElementoProcesa;
import dam.teide.com.juegoprincipal.hilos.TaskHelper;

public class ProcesarMateriales extends AppCompatActivity implements View.OnClickListener{

    private TextView txtRoca,txtTronco,txtHierro,txtOro,txtGemaBruto,txtPiedra,txtTablasMadera,txtLingoteHierro,txtLingoteOro,txtGema;
    private int roca,tronco,hierro,oro,gemaBruto,piedra,tablasMadera,lingoteHierro,lingoteOro,gema;
    private TextView txtTiempo;
    private Button btnJugar;
    private Personaje personaje;
    private RelativeLayout llJuego;
    private Boolean play = true;
    private ArrayList<ImageView> arrayList = new ArrayList<>();
    private HiloCrearElementoProcesa hcep;
    private HiloBajaProcesa hbp;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.procesar_materiales);
        txtRoca = (TextView)findViewById(R.id.txtRoca);
        txtTronco = (TextView)findViewById(R.id.txtTronco);
        txtHierro = (TextView)findViewById(R.id.txtHierro);
        txtOro= (TextView)findViewById(R.id.txtOro);
        txtGemaBruto = (TextView)findViewById(R.id.txtGemaBruto);
        txtPiedra = (TextView)findViewById(R.id.txtPiedra);
        txtTablasMadera = (TextView)findViewById(R.id.txtTablasMadera);
        txtLingoteHierro = (TextView)findViewById(R.id.txtLingoteHierro);
        txtLingoteOro = (TextView)findViewById(R.id.txtLingoteOro);
        txtGema = (TextView)findViewById(R.id.txtGema);
        btnJugar = (Button)findViewById(R.id.btnJugar);
        llJuego = (RelativeLayout)findViewById(R.id.llJuego);
        txtTiempo=(TextView)findViewById(R.id.txtTiempo);
        btnJugar.setOnClickListener(this);
        try {
            personaje = PersonajeDao.buscarPersonaje(this);
            roca=personaje.getRoca();
            txtRoca.setText(String.valueOf(roca));
            tronco=personaje.getTronco();
            txtTronco.setText(String.valueOf(tronco));
            hierro=personaje.getHierro();
            txtHierro.setText(String.valueOf(hierro));
            oro=personaje.getPepita();
            txtOro.setText(String.valueOf(oro));
            gemaBruto=personaje.getGema_bruto();
            txtGemaBruto.setText(String.valueOf(gemaBruto));
            piedra=personaje.getPiedra();
            txtPiedra.setText(String.valueOf(piedra));
            tablasMadera=personaje.getTabla_madera();
            txtTablasMadera.setText(String.valueOf(tablasMadera));
            lingoteHierro=personaje.getLingote_hierro();
            txtLingoteHierro.setText(String.valueOf(lingoteHierro));
            lingoteOro=personaje.getLingote_oro();
            txtLingoteOro.setText(String.valueOf(lingoteOro));
            gema=personaje.getGema();
            txtGema.setText(String.valueOf(personaje.getGema()));
        } catch (SQLException e) {
            Toast.makeText(ProcesarMateriales.this, "Fallo al coger personaje", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnJugar){
            hcep = new HiloCrearElementoProcesa(this,arrayList);
            TaskHelper.execute(hcep);
            hbp = new HiloBajaProcesa(this,arrayList);
            TaskHelper.execute(hbp);
            btnJugar.setVisibility(View.INVISIBLE);
            btnJugar.setEnabled(false);
        }else{

            switch (v.getTag().toString()){
                case "roca":
                    setRoca(-1);
                    if (random.nextInt(3)==2){

                    }else {
                        setPiedra(1);
                    }
                    break;
                case "tronco":
                    setTronco(-1);
                    if (random.nextInt(3)==2){

                    }else {
                        setTablasMadera(1);
                    }
                    break;
                case "gema_bruto":
                    setGemaBruto(-1);
                    if (random.nextInt(3)==2){

                    }else {
                        setGema(1);
                    }
                    break;
                case "hierro":
                    setHierro(-1);
                    break;
                case "pepita":
                    setOro(-1);
                    break;
            }
            arrayList.remove(v);
            llJuego.removeView(v);
            if (arrayList.size()==0&&hcep.getStatus()== AsyncTask.Status.FINISHED){
                hbp.cancel(true);
            }
        }

    }

    public void TerminarJuego() throws SQLException {
        PersonajeDao.actualizarGemaBruto(this,personaje.getGema_bruto()-getGemaBruto());
        PersonajeDao.actualizarRoca(this,personaje.getRoca()-getRoca());
        PersonajeDao.actualizarPepita(this,personaje.getPepita()-getOro());
        PersonajeDao.actualizarHierro(this,personaje.getHierro()-getHierro());
        PersonajeDao.actualizarTronco(this,personaje.getTronco()-getTronco());

        PersonajeDao.actualizarGema(this,getGema());
        PersonajeDao.actualizarPiedra(this,getPiedra());
        PersonajeDao.actualizarLingoteOro(this,getLingoteOro());
        PersonajeDao.actualizarLingoteHierro(this,getLingoteHierro());
        PersonajeDao.actualizarTablaMadera(this,getTablasMadera());

        Intent i = new Intent(this, Index.class);
        startActivity(i);
        finish();
    }
    public Boolean isPlaying() {
        return play;
    }
    public void setPlay(Boolean play) {
        this.play = play;
    }
    public TextView getTxtRoca() {
        return txtRoca;
    }
    public void setTxtRoca(TextView txtRoca) {
        this.txtRoca = txtRoca;
    }
    public TextView getTxtTronco() {
        return txtTronco;
    }
    public void setTxtTronco(TextView txtTronco) {
        this.txtTronco = txtTronco;
    }
    public TextView getTxtHierro() {
        return txtHierro;
    }
    public void setTxtHierro(TextView txtHierro) {
        this.txtHierro = txtHierro;
    }
    public TextView getTxtOro() {
        return txtOro;
    }
    public void setTxtOro(TextView txtOro) {
        this.txtOro = txtOro;
    }
    public TextView getTxtGemaBruto() {
        return txtGemaBruto;
    }
    public void setTxtGemaBruto(TextView txtGemaBruto) {
        this.txtGemaBruto = txtGemaBruto;
    }
    public TextView getTxtPiedra() {
        return txtPiedra;
    }
    public void setTxtPiedra(TextView txtPiedra) {
        this.txtPiedra = txtPiedra;
    }
    public TextView getTxtTablasMadera() {
        return txtTablasMadera;
    }
    public void setTxtTablasMadera(TextView txtTablasMadera) {
        this.txtTablasMadera = txtTablasMadera;
    }
    public TextView getTxtLingoteHierro() {
        return txtLingoteHierro;
    }
    public void setTxtLingoteHierro(TextView txtLingoteHierro) {
        this.txtLingoteHierro = txtLingoteHierro;
    }
    public TextView getTxtLingoteOro() {
        return txtLingoteOro;
    }
    public void setTxtLingoteOro(TextView txtLingoteOro) {
        this.txtLingoteOro = txtLingoteOro;
    }
    public TextView getTxtGema() {
        return txtGema;
    }
    public void setTxtGema(TextView txtGema) {
        this.txtGema = txtGema;
    }
    public Personaje getPersonaje() {
        return personaje;
    }
    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
    public RelativeLayout getLlJuego() {
        return llJuego;
    }
    public void setLlJuego(RelativeLayout llJuego) {
        this.llJuego = llJuego;
    }
    public TextView getTxtTiempo() {
        return txtTiempo;
    }
    public void setTxtTiempo(TextView txtTiempo) {
        this.txtTiempo = txtTiempo;
    }
    public Button getBtnJugar() {
        return btnJugar;
    }
    public void setBtnJugar(Button btnJugar) {
        this.btnJugar = btnJugar;
    }
    public Boolean getPlay() {
        return play;
    }
    public ArrayList<ImageView> getArrayList() {
        return arrayList;
    }
    public void setArrayList(ArrayList<ImageView> arrayList) {
        this.arrayList = arrayList;
    }
    public int getRoca() {
        return roca;
    }
    public void setRoca(int roca) {
        this.roca += roca;
        txtRoca.setText(String.valueOf(this.roca));
    }

    public int getTronco() {
        return tronco;
    }
    public void setTronco(int tronco) {
        this.tronco += tronco;
        txtTronco.setText(String.valueOf(this.tronco));
    }

    public int getHierro() {
        return hierro;
    }
    public void setHierro(int hierro) {
        this.hierro += hierro;
        txtHierro.setText(String.valueOf(this.hierro));
    }

    public int getOro() {
        return oro;
    }
    public void setOro(int oro) {
        this.oro += oro;
        txtOro.setText(String.valueOf(this.oro));
    }

    public int getGemaBruto() {
        return gemaBruto;
    }
    public void setGemaBruto(int gemaBruto) {
        this.gemaBruto += gemaBruto;
        txtGemaBruto.setText(String.valueOf(this.gemaBruto));
    }

    public int getPiedra() {
        return piedra;
    }
    public void setPiedra(int piedra) {
        this.piedra += piedra;
        txtPiedra.setText(String.valueOf(this.piedra));
    }

    public int getTablasMadera() {
        return tablasMadera;
    }
    public void setTablasMadera(int tablasMadera) {
        this.tablasMadera += tablasMadera;
        txtTablasMadera.setText(String.valueOf(this.tablasMadera));
    }

    public int getLingoteHierro() {
        return lingoteHierro;
    }
    public void setLingoteHierro(int lingoteHierro) {
        this.lingoteHierro += lingoteHierro;
        txtLingoteHierro.setText(String.valueOf(this.lingoteHierro));
    }

    public int getLingoteOro() {
        return lingoteOro;
    }
    public void setLingoteOro(int lingoteOro) {
        this.lingoteOro += lingoteOro;
        txtLingoteOro.setText(String.valueOf(this.lingoteOro));
    }

    public int getGema() {
        return gema;
    }
    public void setGema(int gema) {
        this.gema += gema;
        txtGema.setText(String.valueOf(this.gema));
    }
}
