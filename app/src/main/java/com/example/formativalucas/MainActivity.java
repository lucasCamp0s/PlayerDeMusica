package com.example.formativalucas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //declarando os objetos
    Button btnLogin,btnCadastrar;
    TextView edEmail,edSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //intanciando o botao que entrar no sistema
        edEmail = findViewById(R.id.edEmail1);
        edSenha = findViewById(R.id.edSenha1);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //verificando no banco de dados se existe esse user

                BancoDeDados bd = new BancoDeDados(getApplicationContext(),1);
                if(bd.verificaAcesso(edEmail.getText().toString(),edSenha.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Acesso liberado",Toast.LENGTH_SHORT).show();

                    //abrira a tela do player com as musicas
                    Intent i = new Intent(getApplicationContext(),Player.class);
                    String nome = bd.nomeUser(edEmail.getText().toString());
                    i.putExtra("nomeUser",nome+"");
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Sem acesso",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //criando o botao para cadastro
        btnCadastrar = findViewById(R.id.btnSignup);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TelaCadastro.class);
                startActivity(i);
            }
        });
    }
}
