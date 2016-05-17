package dam.teide.com.juegoprincipal.nucleo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import dam.teide.com.juegoprincipal.constats.BBDDConstantes;
import dam.teide.com.juegoprincipal.dao.PersonajeDao;
import dam.teide.com.juegoprincipal.entidades.Personaje;
import dam.teide.com.juegoprincipal.hilos.HiloConexion;
import dam.teide.com.juegoprincipal.nucleo.Index;

public class Login extends AppCompatActivity implements View.OnClickListener,TextWatcher{

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
        etLogin.addTextChangedListener(this);
        etPass.addTextChangedListener(this);
        btnEntrar.setOnClickListener(this);
        tvContOlvi.setOnClickListener(this);
        tvRegistro.setOnClickListener(this);
        try {
            if (PersonajeDao.buscarPersonaje(this)!=null){
                Intent i = new Intent(this,Index.class);
                startActivity(i);
                finish();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!etLogin.getText().toString().trim().isEmpty()&&!etPass.getText().toString().isEmpty()){
            btnEntrar.setClickable(true);
            btnEntrar.setAlpha(1f);
        }else{
            btnEntrar.setClickable(false);
            btnEntrar.setAlpha(0.5f);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void opengoogle() throws Exception{
        String link = "http://www.google.es";
        Intent intent = null;
        intent = new Intent(intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }
    public void errorDatos(){
        Toast.makeText(this,"Login fallidos",Toast.LENGTH_SHORT).show();
    }
    public void recogerJsonPersonaje(String Json) throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(Json);
        JSONArray jsonArray = jsonObject.getJSONArray("0");
        int fk_usuario = jsonArray.getJSONObject(0).getInt("fk_usuario");
        String nombre = jsonArray.getJSONObject(0).getString("nombre_personaje");
        int nivel = jsonArray.getJSONObject(0).getInt("nivel");
        int nivel_casco = jsonArray.getJSONObject(0).getInt("nivCasco");
        int nivel_arco = jsonArray.getJSONObject(0).getInt("nivArco");
        int nivel_escudo = jsonArray.getJSONObject(0).getInt("nivEscudo");
        int nivel_guantes = jsonArray.getJSONObject(0).getInt("nivGuantes");
        int nivel_botas = jsonArray.getJSONObject(0).getInt("nivBotas");
        int nivel_flecha = jsonArray.getJSONObject(0).getInt("nivFlecha");

        int pepita = jsonArray.getJSONObject(0).getInt("pepita");
        int hierro = jsonArray.getJSONObject(0).getInt("hierro");
        int gema_bruto = jsonArray.getJSONObject(0).getInt("gema_bruto");
        int roca = jsonArray.getJSONObject(0).getInt("roca");
        int tronco = jsonArray.getJSONObject(0).getInt("tronco");
        int lingote_oro = jsonArray.getJSONObject(0).getInt("lingote_oro");
        int lingote_hierro = jsonArray.getJSONObject(0).getInt("lingote_hierro");
        int gema = jsonArray.getJSONObject(0).getInt("gema");
        int piedra = jsonArray.getJSONObject(0).getInt("piedra");
        int tabla_madera = jsonArray.getJSONObject(0).getInt("tabla_madera");

        if(PersonajeDao.newPersonaje(this,fk_usuario,nombre, nivel, nivel_casco,  nivel_arco, nivel_escudo, nivel_guantes, nivel_botas, nivel_flecha,
                pepita,hierro,gema_bruto,roca,tronco,lingote_oro,lingote_hierro,gema,piedra,tabla_madera)){
            Intent i = new Intent(this,Index.class);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(this,"Fallo de recogida de datos del servidor",Toast.LENGTH_SHORT).show();
        }
    }
}
