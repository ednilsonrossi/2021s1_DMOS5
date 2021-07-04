package br.edu.ifsp.arq.domos5_2021.meuslivros.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    //Configuracoes gerais do banco de dados
    public static final String DATABASE_NAME = "livros.db";
    public static final int DATABASE_VERSION = 3;

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
        //Executa o comando sql
        db.execSQL(AmigoContract.createTable());
        db.execSQL(LivroContract.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql;
        switch (oldVersion){
            case 1:
                db.execSQL(LivroContract.alterTableToVersao2());
            case 2:
                //Criar table amigo
                db.execSQL(AmigoContract.createTable());

                //Renomeia tabela livros
                sql = "ALTER TABLE " + LivroContract.LivroEntry.TABLE_NAME
                        + " RENAME TO " + LivroContract.LivroEntry.TABLE_NAME_OLD;
                db.execSQL(sql);

                //Cria nova tabela livros com a chave primaria
                db.execSQL(LivroContract.createTable());

                //Insere todos os livros já cadastrados na nova tabela livros
                sql = "INSERT INTO " + LivroContract.LivroEntry.TABLE_NAME + " ("
                        + LivroContract.LivroEntry.COLUMN_TITLE + ", "
                        + LivroContract.LivroEntry.COLUMN_AUTHOR + ", "
                        + LivroContract.LivroEntry.COLUMN_BORROWED + ") "
                        + " SELECT "
                        + LivroContract.LivroEntry.COLUMN_TITLE + ", "
                        + LivroContract.LivroEntry.COLUMN_AUTHOR + ", "
                        + LivroContract.LivroEntry.COLUMN_BORROWED
                        + " FROM " + LivroContract.LivroEntry.TABLE_NAME_OLD;
                db.execSQL(sql);

                //Apaga a tabela livros antiga
                sql = "DROP TABLE " + LivroContract.LivroEntry.TABLE_NAME_OLD;
                db.execSQL(sql);
        }
    }




}
