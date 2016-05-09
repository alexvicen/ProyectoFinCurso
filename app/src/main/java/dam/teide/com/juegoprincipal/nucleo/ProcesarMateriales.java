package dam.teide.com.juegoprincipal.nucleo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;

import dam.teide.com.juegoprincipal.R;
import dam.teide.com.juegoprincipal.dao.PersonajeDao;
import dam.teide.com.juegoprincipal.entidades.Personaje;

public class ProcesarMateriales extends AppCompatActivity {

    private TextView txtRoca,txtTronco,txtHierro,txtOro,txtGemaBruto,txtPiedra,txtTablasMadera,txtLingoteHierro,txtLingoteOro,txtGema;
    private Personaje personaje;

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
        try {
            personaje = PersonajeDao.buscarPersonaje(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtRoca.setText(String.valueOf(personaje.getRoca()));
        txtTronco.setText(String.valueOf(personaje.getTronco()));
        txtHierro.setText(String.valueOf(personaje.getHierro()));
        txtOro.setText(String.valueOf(personaje.getPepita()));
        txtGemaBruto.setText(String.valueOf(personaje.getGema_bruto()));
        txtPiedra.setText(String.valueOf(personaje.getPiedra()));
        txtTablasMadera.setText(String.valueOf(personaje.getTabla_madera()));
        txtLingoteHierro.setText(String.valueOf(personaje.getLingote_hierro()));
        txtLingoteOro.setText(String.valueOf(personaje.getLingote_oro()));
        txtGema.setText(String.valueOf(personaje.getGema()));
    }
}
