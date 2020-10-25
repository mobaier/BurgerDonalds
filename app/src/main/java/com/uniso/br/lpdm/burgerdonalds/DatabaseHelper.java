package com.uniso.br.lpdm.burgerdonalds;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "BurgerDonalds";
    private static final int DB_VERSION = 1;

    // Construtor do banco
    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Ao iniciar o app pela primeira vez, criar o banco e mudar sua
    // versão de 0 para 1
    @Override
    public void onCreate(SQLiteDatabase db) {
        atualizarBanco(db, 0, DB_VERSION);
    }

    // Após a checagem da versão, mudar a versão do banco para um valor que
    // seja compatível para o usuário
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        atualizarBanco(db, oldVersion, newVersion);
    }

    // Função para inserir novos hamburgueres no banco
    // e evitar repetição de código
    public static void insertHamburguer(SQLiteDatabase db, String nome, String descricao, double preco, int imagem_resource_id) {
        //Esse restaurante hipotético vende apenas hamburgueres
        ContentValues hamburguer = new ContentValues();
        hamburguer.put("nome", nome);
        hamburguer.put("descricao", descricao);
        hamburguer.put("preco", preco);
        hamburguer.put("imagem_resource_id", imagem_resource_id);

        // Inserir o item acima na tabela "HAMBURGUER"
        db.insert("HAMBURGUER", null, hamburguer);
    }

    private void atualizarBanco(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql;

        // Caso esteja sendo chamado no onCreate (parametro oldVersion = 0), esse método que cria
        // a estrutura básica é chamado
        if(oldVersion < 1){

            // Se a versão do banco estiver correta a consulta
            // abaixo é executada
            sql = "CREATE TABLE HAMBURGUER (" +
                  "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  "nome TEXT, " +
                  "descricao TEXT, " +
                  "preco REAL," +
                  "imagem_resource_id INTEGER)";

            db.execSQL(sql);

            // Adicionando hamburgueres que são vendidos
            insertHamburguer(db, "X-Burguer", "Hamburguer de 150g com mussarela",  10.00, R.drawable.xburguer);
            insertHamburguer(db, "X-Bacon", "Hamburguer de 150g com mussarela e bacon", 12.00, R.drawable.xbacon);
            insertHamburguer(db, "X-Salada", "Hamburguer de soja de 100g com queijo, alface cebola, cenoura e tomate", 8.00, R.drawable.xsalada);

        }

        /*if (oldVersion <= 2){
            sql = "ALTER TABLE HAMBURGUER ADD COLUMN favorita NUMERIC;";
            db.execSQL(sql);
        }*/
    }
}