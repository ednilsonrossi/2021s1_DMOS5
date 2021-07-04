package br.edu.ifsp.arq.domos5_2021.meuslivros.dao;

import android.provider.BaseColumns;

public class LivroContract {

    private LivroContract() {
    }

    public static class LivroEntry implements BaseColumns {
        //Tabelas e colunas do banco de dados
        public static final String TABLE_NAME = "livros";
        public static final String TABLE_NAME_OLD = "livros_old";

        public static final String COLUMN_TITLE = "titulo";
        public static final String COLUMN_AUTHOR = "autor";
        public static final String COLUMN_BORROWED = "emprestado";
        public static final String COLUMN_FRIEND = "amigo_id";
    }

    //Tabelas do banco de dados
//    Versão 1
//    private static String createTable(){
//        return "CREATE TABLE " + LivroContract.LivroEntry.TABLE_NAME + " ("
//                + LivroContract.LivroEntry.COLUMN_TITLE + " TEXT NOT NULL, "
//                + LivroContract.LivroEntry.COLUMN_AUTHOR + " TEXT NOT NULL)";
//    }

//    Versão 2
//    public static String createTable(){
//        return "CREATE TABLE " + LivroContract.LivroEntry.TABLE_NAME + " ("
//                + LivroContract.LivroEntry.COLUMN_TITLE + " TEXT NOT NULL, "
//                + LivroContract.LivroEntry.COLUMN_AUTHOR + " TEXT NOT NULL, "
//                + LivroContract.LivroEntry.COLUMN_BORROWED + " INT CHECK (" + LivroContract.LivroEntry.COLUMN_BORROWED + " IN (0,1)) DEFAULT 0)";
//    }

    //    Versão 3
    public static String createTable() {
        return "CREATE TABLE " + LivroContract.LivroEntry.TABLE_NAME + " ("
                + LivroEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LivroEntry.COLUMN_TITLE + " TEXT NOT NULL, "
                + LivroEntry.COLUMN_AUTHOR + " TEXT NOT NULL, "
                + LivroEntry.COLUMN_BORROWED + " INTEGER CHECK (" + LivroEntry.COLUMN_BORROWED + " IN (0,1)) DEFAULT 0, "
                + LivroEntry.COLUMN_FRIEND + " INTEGER DEFAULT NULL, "
                + " FOREIGN KEY (" + LivroEntry.COLUMN_FRIEND + ") REFERENCES " + AmigoContract.AmigoEntry.TABLE_NAME + "(" + AmigoContract.AmigoEntry._ID + ") )";
    }


    public static String alterTableToVersao2() {
        return "ALTER TABLE " + LivroContract.LivroEntry.TABLE_NAME
                + " ADD COLUMN " + LivroContract.LivroEntry.COLUMN_BORROWED
                + " INT CHECK (" + LivroContract.LivroEntry.COLUMN_BORROWED + " IN (0,1) ) DEFAULT 0";
    }


}
