package dam.teide.com.juegoprincipal.nucleo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLException;

import dam.teide.com.juegoprincipal.R;
import dam.teide.com.juegoprincipal.dao.PersonajeDao;
import dam.teide.com.juegoprincipal.entidades.Personaje;

public class Herreria extends AppCompatActivity implements View.OnClickListener{
    private TextView txtPiedra,txtTablasMadera,txtLingoteHierro,txtLingoteOro,txtGema,txtNivCasco,txtNivArco,txtNivEscudo,txtNivGuantes,txtNivBotas,txtNivFlechas;
    private Button btnCasco,btnArco,btnEscudo,btnGuantes,btnBotas,btnFlechas,btnIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.herreria);
        txtPiedra =(TextView)findViewById(R.id.txtPiedra);
        txtTablasMadera =(TextView)findViewById(R.id.txtTablasMadera);
        txtLingoteHierro =(TextView)findViewById(R.id.txtLingoteHierro);
        txtLingoteOro =(TextView)findViewById(R.id.txtLingoteOro);
        txtGema =(TextView)findViewById(R.id.txtGema);
        txtNivCasco =(TextView)findViewById(R.id.txtNivCasco);
        txtNivArco =(TextView)findViewById(R.id.txtNivArco);
        txtNivEscudo =(TextView)findViewById(R.id.txtNivEscudo);
        txtNivGuantes =(TextView)findViewById(R.id.txtNivGuantes);
        txtNivBotas =(TextView)findViewById(R.id.txtNivBotas);
        txtNivFlechas =(TextView)findViewById(R.id.txtNivFlechas);
        btnCasco = (Button)findViewById(R.id.btnCasco);
        btnArco = (Button)findViewById(R.id.btnArco);
        btnEscudo = (Button)findViewById(R.id.btnEscudo);
        btnGuantes = (Button)findViewById(R.id.btnGuantes);
        btnBotas = (Button)findViewById(R.id.btnBotas);
        btnFlechas = (Button)findViewById(R.id.btnFlechas);
        btnIndex = (Button)findViewById(R.id.btnIndex);

        btnCasco.setOnClickListener(this);
        btnArco.setOnClickListener(this);
        btnEscudo.setOnClickListener(this);
        btnGuantes.setOnClickListener(this);
        btnBotas.setOnClickListener(this);
        btnFlechas.setOnClickListener(this);
        btnIndex.setOnClickListener(this);
        Personaje p=null;
        try {
            p = PersonajeDao.buscarPersonaje(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtPiedra.setText(p.getPiedra()+"");
        txtTablasMadera.setText(p.getTabla_madera()+"");
        txtLingoteHierro.setText(p.getLingote_hierro()+"");
        txtLingoteOro.setText(p.getLingote_oro()+"");
        txtGema.setText(p.getGema()+"");
        txtNivCasco.setText(p.getNivCasco()+"");
        txtNivArco.setText(p.getNivArco()+"");
        txtNivEscudo.setText(p.getNivEscudo()+"");
        txtNivGuantes.setText(p.getNivGuantes()+"");
        txtNivBotas.setText(p.getNivBotas()+"");
        txtNivFlechas.setText(p.getNivFlecha()+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCasco:

                break;
            case R.id.btnArco:

                break;
            case R.id.btnEscudo:

                break;
            case R.id.btnGuantes:

                break;
            case R.id.btnBotas:

                break;
            case R.id.btnFlechas:

                break;
            case R.id.btnIndex:
                Intent i = new Intent(this,Index.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
