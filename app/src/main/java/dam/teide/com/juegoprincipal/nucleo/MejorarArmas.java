package dam.teide.com.juegoprincipal.nucleo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.Random;

import dam.teide.com.juegoprincipal.R;
import dam.teide.com.juegoprincipal.dao.PersonajeDao;
import dam.teide.com.juegoprincipal.entidades.Personaje;

public class MejorarArmas extends Activity implements View.OnClickListener {
    private String equipo;
    private TextView txtNivel,txtPiedraMejora,txtTablaMaderaMejora,txtLingoteHierroMejora,txtLingoteOroMejora,txtGemaMejora;
    private TextView txtPiedra,txtTablaMadera,txtLingoteHierro,txtLingoteOro,txtGema;
    private Button btnMejorar,btnSalir;
    private ImageButton ibArma;
    private int contMejora=0;
    private Personaje p = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mejorar_armas);
        txtNivel = (TextView)findViewById(R.id.txtNivel);
        txtPiedraMejora = (TextView)findViewById(R.id.txtPiedraMejora);
        txtTablaMaderaMejora = (TextView)findViewById(R.id.txtTablaMaderaMejora);
        txtLingoteHierroMejora = (TextView)findViewById(R.id.txtLingoteHierroMejora);
        txtLingoteOroMejora = (TextView)findViewById(R.id.txtLingoteOroMejora);
        txtGemaMejora = (TextView)findViewById(R.id.txtGemaMejora);
        txtPiedra = (TextView)findViewById(R.id.txtPiedra);
        txtTablaMadera = (TextView)findViewById(R.id.txtTablasMadera);
        txtLingoteHierro = (TextView)findViewById(R.id.txtLingoteHierro);
        txtLingoteOro = (TextView)findViewById(R.id.txtLingoteOro);
        txtGema = (TextView)findViewById(R.id.txtGema);
        btnMejorar = (Button)findViewById(R.id.btnMejorar);
        btnSalir = (Button)findViewById(R.id.btnSalir);
        ibArma = (ImageButton)findViewById(R.id.ibArma);
        btnMejorar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
        ibArma.setOnClickListener(this);
        btnMejorar.setClickable(false);
        btnMejorar.setAlpha(0.5f);
        try {
            p = PersonajeDao.buscarPersonaje(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        equipo = getIntent().getStringExtra("equipo");
        switch (equipo){
            case "casco":
                ibArma.setBackgroundResource(R.drawable.casco_pequeno);
                txtNivel.setText("Nivel: "+p.getNivCasco());
                break;
            case "arco":
                ibArma.setBackgroundResource(R.drawable.arco_pequeno);
                txtNivel.setText("Nivel: "+p.getNivArco());
                break;
            case "escudo":
                ibArma.setBackgroundResource(R.drawable.escudo_pequeno);
                txtNivel.setText("Nivel: "+p.getNivEscudo());
                break;
            case "guantes":
                ibArma.setBackgroundResource(R.drawable.guantes_pequenos);
                txtNivel.setText("Nivel: "+p.getNivGuantes());
                break;
            case "botas":
                ibArma.setBackgroundResource(R.drawable.botas_pequenas);
                txtNivel.setText("Nivel: "+p.getNivBotas());
                break;
            case "flechas":
                ibArma.setBackgroundResource(R.drawable.flecha_pequena);
                txtNivel.setText("Nivel: "+p.getNivFlecha());
                break;
        }
        txtPiedra.setText(p.getPiedra()+"");
        txtTablaMadera.setText(p.getTabla_madera()+"");
        txtLingoteHierro.setText(p.getLingote_hierro()+"");
        txtLingoteOro.setText(p.getLingote_oro()+"");
        txtGema.setText(p.getGema()+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibArma:
                contMejora++;
                if (contMejora==5){
                    contMejora=0;
                    btnMejorar.setClickable(true);
                    btnMejorar.setAlpha(1f);
                    ibArma.setClickable(false);
                }
                break;
            case R.id.btnMejorar:
                switch (equipo){
                    case "casco":
                        try {
                            PersonajeDao.actualizarNivelCasco(this,p.getNivCasco()+1);
                            finish();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "arco":
                        try {
                            PersonajeDao.actualizarNivelArco(this,p.getNivArco()+1);
                            finish();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "escudo":
                        try {
                            PersonajeDao.actualizarNivelEscudo(this,p.getNivEscudo()+1);
                            finish();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "guantes":
                        try {
                            PersonajeDao.actualizarNivelGuantes(this,p.getNivGuantes()+1);
                            finish();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "botas":
                        try {
                            PersonajeDao.actualizarNivelBotas(this,p.getNivBotas()+1);
                            finish();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "flechas":
                        try {
                            PersonajeDao.actualizarNivelFlecha(this,p.getNivFlecha()+1);
                            finish();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                break;
            case R.id.btnSalir:
                finish();
                break;
        }
    }
}
