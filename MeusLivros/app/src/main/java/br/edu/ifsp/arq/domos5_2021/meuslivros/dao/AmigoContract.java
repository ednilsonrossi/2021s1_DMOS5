package br.edu.ifsp.arq.domos5_2021.meuslivros.dao;

import android.provider.BaseColumns;

public class AmigoContract {

    private AmigoContract() {
    }

    public static class AmigoEntry implements BaseColumns {
        public static final String TABLE_NAME = "amigo";
        public static final String COLUMN_NOME = "nome";
    }

    public static String createTable() {
        return "CREATE TABLE " + AmigoEntry.TABLE_NAME + " ("
                + AmigoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AmigoEntry.COLUMN_NOME + " TEXT )";
    }
}
