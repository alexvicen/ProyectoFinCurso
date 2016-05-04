package dam.teide.com.juegoprincipal.nucleo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

import dam.teide.com.juegoprincipal.R;
import dam.teide.com.juegoprincipal.dao.PersonajeDao;
import dam.teide.com.juegoprincipal.hilos.HiloConexion;
import dam.teide.com.juegoprincipal.nucleo.Index;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private Button btnEntrar;
    private TextView tvContOlvi,tvRegistro;
    private EditText etLogin,etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        tvContOlvi = (TextView)findViewById(R.id.tvContOlvi);
        tvRegistro = (TextView)findViewById(R.id.tvRegistro);
        etLogin = (EditText)findViewById(R.id.etLogin);
        etPass = (EditText)findViewById(R.id.etPass);
        btnEntrar.setOnClickListener(this);
        tvContOlvi.setOnClickListener(this);
        tvRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:
                new HiloConexion(this,"login",etLogin.getText().toString().trim(),etPass.getText().toString().trim()).execute();
                break;
            case R.id.tvContOlvi:
                try {
                    opengoogle();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tvRegistro:
                try {
                    opengoogle();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }


    }

    public void opengoogle() throws Exception{
        String link = "http://www.google.es";
        Intent intent = null;
        intent = new Intent(intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }
    public void errorDatos(){
        Toast.makeText(this,"Login fallido",Toast.LENGTH_SHORT).show();
    }
    public void recogerJsonPersonaje(String Json) throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(Json);
        JSONArray jsonArray = jsonObject.getJSONArray("0");
        String nombre = jsonArray.getJSONObject(0).getString("nombre_personaje");
        int nivel = jsonArray.getJSONObject(0).getInt("nivel");
        int nivel_casco = jsonArray.getJSONObject(0).getInt("nivCasco");
        int nivel_arco = jsonArray.getJSONObject(0).getInt("nivArco");
        int nivel_escudo = jsonArray.getJSONObject(0).getInt("nivEscudo");
        int nivel_guantes = jsonArray.getJSONObject(0).getInt("nivGuantes");
        int nivel_botas = jsonArray.getJSONObject(0).getInt("nivBotas");
        int nivel_flecha = jsonArray.getJSONObject(0).getInt("nivFlecha");
        if(PersonajeDao.newPersonaje(this,nombre, nivel, nivel_casco,  nivel_arco, nivel_escudo, nivel_guantes, nivel_botas, nivel_flecha)){
            Intent i = new Intent(this,Index.class);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(this,"Login fallido",Toast.LENGTH_SHORT).show();
        }
    }
}
