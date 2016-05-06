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
import dam.teide.com.juegoprincipal.entidades.Personaje;
import dam.teide.com.juegoprincipal.hilos.HiloConexion;

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
                Personaje p=null;
                try {
                    p = PersonajeDao.buscarPersonaje(this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String nombre_personaje = p.getNombre_personaje();
                int fk_usuario = p.getFk_usuario();
                int nivel = p.getNivel();
                int nivel_casco=p.getNivCasco();
                int nivel_arco=p.getNivArco();
                int nivel_escudo=p.getNivEscudo();
                int nivel_guantes=p.getNivGuantes();
                int nivel_botas=p.getNivBotas();
                int nivel_flecha=p.getNivFlecha();
                int pepita=p.getPepita();
                int roca=p.getRoca();
                int tronco=p.getTronco();
                int hierro=p.getHierro();
                int gema_bruto=p.getGema_bruto();
                int lingote_oro=p.getLingote_oro();
                int lingote_hierro=p.getLingote_hierro();
                int gema=p.getGema();
                int piedra=p.getPiedra();
                int tabla_madera=p.getTabla_madera();
                new HiloConexion(this,"update",nombre_personaje,fk_usuario,nivel,nivel_casco,nivel_arco,nivel_escudo,
                        nivel_guantes,nivel_botas,nivel_flecha,pepita,roca,tronco,hierro,gema_bruto,lingote_oro,lingote_hierro,gema,piedra,tabla_madera).execute();

                break;
        }
    }

    public void recogerJsonActualizarPersonaje(String Json) throws JSONException {
        JSONObject jsonObject = new JSONObject(Json);
        int estado = jsonObject.getInt("estado");
        Toast.makeText(Index.this, estado+"", Toast.LENGTH_SHORT).show();
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
