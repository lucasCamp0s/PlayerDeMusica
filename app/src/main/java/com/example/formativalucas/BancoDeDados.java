package com.example.formativalucas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados extends SQLiteOpenHelper {

    //comamdo create para criar a table
    private final String criarTebelaUsuario = "CREATE TABLE usuario("+
            "nome VARCHAR(100) NOT NULL," +
            "email VARCHAR(100) PRIMARY KEY," +
            "senha VARCHAR(30) NOT NULL);";

    public BancoDeDados(Context context, int version) {
        super(context ,"BD_aplicativo",null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(criarTebelaUsuario);
    }

    public void onOpen(SQLiteDatabase db){
        db.execSQL("PRAGMA foreign_keys = ON;");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //funcao para cadastrar um usuario

    public boolean cadastraUsuario(String nome,String email,String senha){
        //criar um objeto da classe sqliteDataBase e abre a conexão
        SQLiteDatabase banco = getWritableDatabase();

        //utilização da classe cotnent values para passar o valor de cada coluna
        ContentValues valores = new ContentValues();
        valores.put("nome",nome);
        valores.put("email",email);
        valores.put("senha",senha);

        if(banco.insert("usuario",null,valores)!=-1){
            banco.close();
            return  true;
        }else {
            banco.close();
            return false;
        }
    }
    public String nomeUser(String email){
        String nome=null;
        SQLiteDatabase bd = getWritableDatabase();//Abre conexao com banco
        //classe cursor para armazenar os dados que o select irá buscar
        Cursor c = bd.rawQuery("SELECT nome FROM usuario WHERE email = ?",new String[] {email});
        if(c.moveToFirst()){
            do{
                nome = c.getString(0);
            }while (c.moveToNext());

        }
        return nome;
    }
    public boolean verificaAcesso(String email,String senha){
        SQLiteDatabase bd = getWritableDatabase();//Abre conexao com banco
        //classe cursor para armazenar os dados que o select irá buscar

        Cursor c = bd.rawQuery("SELECT senha FROM usuario WHERE email = ?",new String[] {email});

        //verifica se encontrou alguma coisa
        if(c.moveToFirst()){
            do{
                //recupara o valor da coluna 0 do select (que é a senha)
                String senhaBanco = c.getString(0);

                if(senhaBanco.equals(senha)){
                    bd.close();
                    return true;
                }
            }while (c.moveToNext());
        }
        //se caso não encontrou o email ou a senha corresponde
        bd.close();
        return false;
    }
}
