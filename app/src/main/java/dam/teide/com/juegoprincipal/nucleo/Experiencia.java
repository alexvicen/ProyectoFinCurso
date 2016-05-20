package dam.teide.com.juegoprincipal.nucleo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.SQLException;

import dam.teide.com.juegoprincipal.R;
import dam.teide.com.juegoprincipal.dao.PersonajeDao;
import dam.teide.com.juegoprincipal.entidades.Personaje;
import dam.teide.com.juegoprincipal.hilos.HiloExperiencia;

public class Experiencia extends AppCompatActivity implements View.OnClickListener {

    private TextView txtNombre,txtExperiencia,txtNivel;
    private ProgressBar pbExperiencia;
    private Button btnSalir;
    private int exp,exSum,exSumTot,exTot,nivel;
    private Personaje p = null;
    private boolean ejecutar = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experiencia);
        txtNombre = (TextView)findViewById(R.id.txtNombre);
        txtExperiencia = (TextView)findViewById(R.id.txtExperiencia);
        txtNivel = (TextView)findViewById(R.id.txtNivel);
        btnSalir = (Button)findViewById(R.id.btnSalir);
        pbExperiencia = (ProgressBar)findViewById(R.id.pbExperiencia);
        btnSalir.setOnClickListener(this);
        exp = getIntent().getIntExtra("exp",0);
        try {
            p = PersonajeDao.buscarPersonaje(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        exSum = p.getExperiencia();
        exSumTot = exSum;
        setNivel(p.getNivel());
        txtNombre.setText(p.getNombre_personaje()+"");
        txtExperiencia.setText(exSum+"/"+exTot);
        txtNivel.setText(nivel+"");
        pbExperiencia.setMax(exTot);
        pbExperiencia.setProgress(exSum);
        if (exp==0){
            btnSalir.setVisibility(View.VISIBLE);
        }else{
            btnSalir.setVisibility(View.GONE);
            new HiloExperiencia(this).execute();
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    public boolean isEjecutar() {
        return ejecutar;
    }
    public void setEjecutar(boolean ejecutar) {
        this.ejecutar = ejecutar;
    }
    public int getExp() {
        return exp;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }
    public int getExSum() {
        return exSum;
    }
    public void setExSum(int exSum) {
        this.exSum = exSum;
        txtExperiencia.setText(this.exSum+"/"+exTot);
    }
    public int getExTot() {
        return exTot;
    }
    public void setExTot(int exTot) {
        this.exTot = exTot;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
        setExTot(this.nivel*1000);
        txtNivel.setText(nivel+"");
    }
    public int getExSumTot() {
        return exSumTot;
    }
    public void setExSumTot(int exSumTot) {
        this.exSumTot = exSumTot;
    }
    public Button getBtnSalir() {
        return btnSalir;
    }
    public void setBtnSalir(Button btnSalir) {
        this.btnSalir = btnSalir;
    }
}
