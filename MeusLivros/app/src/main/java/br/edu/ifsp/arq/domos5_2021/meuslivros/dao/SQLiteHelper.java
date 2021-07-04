package br.edu.ifsp.arq.domos5_2021.meuslivros.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    //Configuracoes gerais do banco de dados
    public static final String DATABASE_NAME = "livros.db";
    public static final int DATABASE_VERSION = 2;

    //Tabelas e colunas do banco de dados
    public static final String TABLE_NAME_BOOK = "livros";
    public static final String ATTR_TITLE = "titulo";
    public static final String ATTR_AUTHOR = "autor";
    public static final String ATTR_BORROWED = "emprestado";

    //Tabelas do banco de dados
    private String createTable1 = "CREATE TABLE " + TABLE_NAME_BOOK + " ("
            + ATTR_TITLE + " TEXT NOT NULL, "
            + ATTR_AUTHOR + " TEXT NOT NULL)";

    private String createTable2 = "CREATE TABLE " + TABLE_NAME_BOOK + " ("
            + ATTR_TITLE + " TEXT NOT NULL, "
            + ATTR_AUTHOR + " TEXT NOT NULL, "
            + ATTR_BORROWED + " INT CHECK (" + ATTR_BORROWED + " IN (0,1)) DEFAULT 0)";

    private String alterTableLivros1 = "ALTER TABLE " + TABLE_NAME_BOOK
            + " ADD COLUMN " + ATTR_BORROWED + " INT CHECK (" + ATTR_BORROWED + " IN (0,1) ) DEFAULT 0";


    /*
        Como o banco de dados esta vinculado ao aplicativo, e necessario acesso ao contexto do
        aplicativo, isso porque o banco de dados e um arquivo dentro do diretorio do app.
     */
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /*
        O método onCreate() é chamado apenas quando o aplicativo é instalado no dispositivo. Depois
        de instalado esse método não é mais invocado.
        Esse método é responsável pela criação de todas as tabelas no banco de dados. Observe
        que é muito simples a execução de um comando sql no banco de dados.
    */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Definiçao do SQL que cria a tabela
        String sql = createTable2;

        //Executa o comando sql
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql;
        switch (oldVersion){
            case 1:
                sql = alterTableLivros1;
                db.execSQL(sql);
                break;
        }
    }
}
