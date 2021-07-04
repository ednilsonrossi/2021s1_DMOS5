package br.edu.ifsp.arq.domos5_2021.meuslivros.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.domos5_2021.meuslivros.model.Livro;

public class LivroDao {

    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;

    public LivroDao(@Nullable Context context) {
        mHelper = new SQLiteHelper(context);
    }


    /*
        Para salvar os dados na base basta indicar em um ContentValues ou seja, em um objeto que
        armazena chave e valor e solicitar que os dados sejam salvos no banco de dados.
        Atenção pois o nome das colunas deve ser sempre o mesmo.
    */
    public boolean insert(Livro livro){
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.ATTR_TITLE, livro.getTitulo());
        values.put(SQLiteHelper.ATTR_AUTHOR, livro.getAutor());

        mDatabase = mHelper.getWritableDatabase();
        /*
            O metodo insert do Database retorna o numero de linhas inseridas ou -1 caso
            ocorra algum erro.
         */
        long linhas = mDatabase.insert(
                SQLiteHelper.TABLE_NAME_BOOK,
                null,
                values
        );
        mDatabase.close();
        return ! (linhas == -1);
    }

    public List<Livro> recuperate(){
        Livro mLivro;
        List<Livro> mLivros = new ArrayList<>();
        Cursor mCursor;

        String mColunas[] = new String[]{
                SQLiteHelper.ATTR_TITLE,
                SQLiteHelper.ATTR_AUTHOR,
                SQLiteHelper.ATTR_BORROWED
        };

        mDatabase = mHelper.getReadableDatabase();
        mCursor = mDatabase.query(
                SQLiteHelper.TABLE_NAME_BOOK,
                mColunas,
                null,
                null,
                null,
                null,
                SQLiteHelper.ATTR_TITLE
        );

        while (mCursor.moveToNext()){
            mLivro = new Livro(
                    mCursor.getString(0),
                    mCursor.getString(1),
                    mCursor.getInt(2) == 1
            );
            mLivros.add(mLivro);
        }

        mCursor.close();
        mDatabase.close();
        return mLivros;
    }

    public boolean update(Livro livro){
        boolean deuCerto = true;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.ATTR_AUTHOR, livro.getAutor());
        values.put(SQLiteHelper.ATTR_BORROWED, livro.isEmprestado()?1:0);

        String where = SQLiteHelper.ATTR_TITLE + " = ?";
        String whereArgs[] = {livro.getTitulo()};

        try{
            mDatabase = mHelper.getWritableDatabase();
            mDatabase.update(SQLiteHelper.TABLE_NAME_BOOK, values, where, whereArgs);
        }catch (Exception e){
            deuCerto = false;
        }finally {
            mDatabase.close();
        }
        return deuCerto;
    }
}
