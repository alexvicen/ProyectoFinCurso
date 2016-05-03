package dam.teide.com.juegoprincipal.nucleo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dam.teide.com.juegoprincipal.R;

public class Index extends AppCompatActivity implements View.OnClickListener{

    private Button btnCandy, btnJuegoPrincipal,btnHerreria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        btnCandy = (Button)findViewById(R.id.btnCandy);
        btnHerreria = (Button)findViewById(R.id.btnHerreria);
        btnJuegoPrincipal = (Button)findViewById(R.id.btnJuegoPrincipal);
        btnCandy.setOnClickListener(this);
        btnJuegoPrincipal.setOnClickListener(this);
        btnHerreria.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.btnCandy:
                 i = new Intent(this,MainActivity.class);
                startActivity(i);
                break;
            case R.id.btnJuegoPrincipal:
                i = new Intent(this,JuegoPrincipal.class);
                startActivity(i);
                break;
            case R.id.btnHerreria:

                break;
        }
    }
}
