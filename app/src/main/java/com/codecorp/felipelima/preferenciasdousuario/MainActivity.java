package com.codecorp.felipelima.preferenciasdousuario;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonSalvar;
    private TextInputEditText editNome;
    private TextView textResultado;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSalvar = findViewById(R.id.buttonSalvar);
        editNome = findViewById(R.id.editNome);
        textResultado = findViewById(R.id.textResultado);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                //Validar o nome
                if (editNome.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Preencha o nome!!",Toast.LENGTH_SHORT);
                }
                else {
                    String nome = editNome.getText().toString();
                    editor.putString("nome",nome);
                    editor.commit();

                    textResultado.setText("Olá, " + nome + "!!");
                }
            }
        });

        //Recuperar os dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,MODE_PRIVATE);

        //Valida se o nome existe em preferencias
        if (preferences.contains("nome")){
            String nome = preferences.getString("nome","Olá usuário não definido!");
            textResultado.setText("Olá, " + nome + "!!");
        }
    }
}
