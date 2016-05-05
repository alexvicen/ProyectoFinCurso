package dam.teide.com.juegoprincipal.nucleo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import dam.teide.com.juegoprincipal.R;
import dam.teide.com.juegoprincipal.constats.BBDDConstantes;
import dam.teide.com.juegoprincipal.dao.PersonajeDao;

public class Index extends AppCompatActivity implements View.OnClickListener{

    private Button btnCandy, btnJuegoPrincipal,btnHerreria,btnCerrarSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        btnCandy = (Button)findViewById(R.id.btnCandy);
        btnHerreria = (Button)findViewById(R.id.btnHerreria);
        btnJuegoPrincipal = (Button)findViewById(R.id.btnJuegoPrincipal);
        btnCerrarSesion = (Button)findViewById(R.id.btnCerrarSesion);
        btnCandy.setOnClickListener(this);
        btnJuegoPrincipal.setOnClickListener(this);
        btnHerreria.setOnClickListener(this);
        btnCerrarSesion.setOnClickListener(this);
        try {
            Toast.makeText(Index.this, PersonajeDao.buscarPersonaje(this).getGema_bruto()+"", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.btnCandy:
                i = new Intent(this,TresLinea.class);
                startActivity(i);
                finish();
                break;
            case R.id.btnJuegoPrincipal:
                i = new Intent(this,JuegoPrincipal.class);
                startActivity(i);
                finish();
                break;
            case R.id.btnHerreria:
                i = new Intent(this,Herreria.class);
                startActivity(i);
                finish();
                break;
            case R.id.btnCerrarSesion:


                break;
        }
    }

    public void recogerJsonActualizarPersonaje(String Json) throws JSONException {
        JSONObject jsonObject = new JSONObject(Json);
        int estado = jsonObject.getInt("estado");
        if (estado == 201) {
            try {
                BBDDConstantes.borrarDatosTablas(this);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finish();
        }else{
            Toast.makeText(Index.this, "No se ha podido actualizar", Toast.LENGTH_SHORT).show();
        }

    }

    public void errorDatos() {
        Toast.makeText(Index.this, "Error al cerrar sesion", Toast.LENGTH_SHORT).show();
    }
}
