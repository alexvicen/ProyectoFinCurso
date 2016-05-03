package dam.teide.com.juegoprincipal.nucleo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dam.teide.com.juegoprincipal.R;
import dam.teide.com.juegoprincipal.nucleo.Index;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private Button btnEntrar;
    private TextView tvContOlvi,tvRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        tvContOlvi = (TextView)findViewById(R.id.tvContOlvi);
        tvRegistro = (TextView)findViewById(R.id.tvRegistro);
        btnEntrar.setOnClickListener(this);
        tvContOlvi.setOnClickListener(this);
        tvRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:
                Intent i = new Intent(this,Index.class);
                startActivity(i);
                finish();
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
}
