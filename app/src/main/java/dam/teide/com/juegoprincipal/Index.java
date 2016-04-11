package dam.teide.com.juegoprincipal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Index extends AppCompatActivity implements View.OnClickListener{

    private Button btnCandy, btnJuegoPrincipal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        btnCandy = (Button)findViewById(R.id.btnCandy);
        btnJuegoPrincipal = (Button)findViewById(R.id.btnJuegoPrincipal);
        btnCandy.setOnClickListener(this);
        btnJuegoPrincipal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnCandy){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }else if (v.getId()==R.id.btnJuegoPrincipal){
            Intent i = new Intent(this,JuegoPrincipal.class);
            startActivity(i);
        }
    }
}
