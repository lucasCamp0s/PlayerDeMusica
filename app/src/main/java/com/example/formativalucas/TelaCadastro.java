package com.example.formativalucas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TelaCadastro extends AppCompatActivity {


    //declarando objetos
    Button btnCadastrar;
    TextView edEmail,edSenha,edNome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        //criando obj
        btnCadastrar = findViewById(R.id.btnCadastrar);
        edEmail = findViewById(R.id.edEmail1);
        edSenha = findViewById(R.id.edSenha1);
        edNome = findViewById(R.id.edNome1);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //criar um objeto da classe banco de dados
                BancoDeDados bd = new BancoDeDados(getApplicationContext(),1);

                if(bd.cadastraUsuario(edNome.getText().toString(),edEmail.getText().toString(),edSenha.getText().toString()) == true){
                    Toast.makeText(getApplicationContext(),"Dados Cadastrados",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Não foi possivel Cadastrar",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
