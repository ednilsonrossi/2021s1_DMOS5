package br.edu.ifsp.arq.domos5_2021.meuslivros.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.domos5_2021.meuslivros.model.Amigo;

public class AmigoDao {

    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;

    public AmigoDao(Context context) {
        mHelper = new SQLiteHelper(context);
    }

    public boolean insert(Amigo amigo){
        boolean deuCerto = true;
        ContentValues values = new ContentValues();
        values.put(AmigoContract.AmigoEntry.COLUMN_NOME, amigo.getNome());

        try{
            mDatabase = mHelper.getWritableDatabase();
            long lines = mDatabase.insert(AmigoContract.AmigoEntry.TABLE_NAME, null, values);
            if(lines == -1)
                deuCerto = false;
        }catch (Exception e){
            deuCerto = false;
        }finally {
            mDatabase.close();
        }
        return deuCerto;
    }

    public List<Amigo> recuperate(){
        List<Amigo> amigoList = new ArrayList<>();
        String columns[] = {
                AmigoContract.AmigoEntry._ID,
                AmigoContract.AmigoEntry.COLUMN_NOME
        };

        try{
            mDatabase = mHelper.getReadableDatabase();
            Cursor cursor = mDatabase.query(
                    AmigoContract.AmigoEntry.TABLE_NAME,
                    columns,
                    null,null,null,null,
                    AmigoContract.AmigoEntry.COLUMN_NOME
            );

            while (cursor.moveToNext()){
                amigoList.add(new Amigo(cursor.getInt(0), cursor.getString(1)));
            }
        }catch (Exception e){
            amigoList = null;
        }finally {
            mDatabase.close();
        }

        return amigoList;
    }

    public Amigo recuperate(int id){
        Amigo amigo = null;
        String columns[] = {AmigoContract.AmigoEntry.COLUMN_NOME};
        String selection = AmigoContract.AmigoEntry._ID + " = ?";
        String args[] = {String.valueOf(id)};

        try {
            mDatabase = mHelper.getReadableDatabase();
            Cursor curso = mDatabase.query(AmigoContract.AmigoEntry.TABLE_NAME, columns, selection, args, null, null, null);
            if(curso.moveToNext()){
                amigo = new Amigo(id, curso.getString(0));
            }
        }catch (Exception e){
            amigo = null;
        }finally {
            mDatabase.close();
        }
        return amigo;
    }
}
