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
    private AmigoDao amigoDao;

    public LivroDao(@Nullable Context context) {
        mHelper = new SQLiteHelper(context);
        amigoDao = new AmigoDao(context);
    }


    /*
        Para salvar os dados na base basta indicar em um ContentValues ou seja, em um objeto que
        armazena chave e valor e solicitar que os dados sejam salvos no banco de dados.
        Atenção pois o nome das colunas deve ser sempre o mesmo.
    */
    public boolean insert(Livro livro){
        long linhas;

        ContentValues values = new ContentValues();
        values.put(LivroContract.LivroEntry.COLUMN_TITLE, livro.getTitulo());
        values.put(LivroContract.LivroEntry.COLUMN_AUTHOR, livro.getAutor());

        try {
            mDatabase = mHelper.getWritableDatabase();
        /*
            O metodo insert do Database retorna o numero de linhas inseridas ou -1 caso
            ocorra algum erro.
         */
            linhas = mDatabase.insert(
                    LivroContract.LivroEntry.TABLE_NAME,
                    null,
                    values
            );
            mDatabase.close();
        }catch (Exception e){
            linhas = -1;
        }finally {
            mDatabase.close();
        }
        return ! (linhas == -1);
    }

    public List<Livro> recuperate(){
        Livro mLivro;
        List<Livro> mLivros = new ArrayList<>();

        Cursor mCursor = null;

        String mColunas[] = new String[]{
                LivroContract.LivroEntry.COLUMN_TITLE,
                LivroContract.LivroEntry.COLUMN_AUTHOR,
                LivroContract.LivroEntry.COLUMN_BORROWED,
                LivroContract.LivroEntry._ID,
                LivroContract.LivroEntry.COLUMN_FRIEND
        };
        try {
            mDatabase = mHelper.getReadableDatabase();
            mCursor = mDatabase.query(
                    LivroContract.LivroEntry.TABLE_NAME,
                    mColunas,
                    null,
                    null,
                    null,
                    null,
                    LivroContract.LivroEntry.COLUMN_TITLE
            );

            while (mCursor.moveToNext()) {
                mLivro = new Livro(
                        mCursor.getInt(3),
                        mCursor.getString(0),
                        mCursor.getString(1),
                        mCursor.getInt(2) == 1,
                        amigoDao.recuperate(mCursor.getInt(4))
                );
                mLivros.add(mLivro);
            }
        }catch (Exception e){
            mLivros = null;
        }finally {
            mCursor.close();
            mDatabase.close();
        }

        return mLivros;
    }

    public boolean update(Livro livro){
        boolean deuCerto = true;
        ContentValues values = new ContentValues();
        values.put(LivroContract.LivroEntry.COLUMN_AUTHOR, livro.getAutor());
        values.put(LivroContract.LivroEntry.COLUMN_BORROWED, livro.isEmprestado()?1:0);
        if(livro.getAmigo() != null) {
            values.put(LivroContract.LivroEntry.COLUMN_FRIEND, livro.getAmigo().getId());
        }

        String where = LivroContract.LivroEntry._ID + " = ?";
        String whereArgs[] = {String.valueOf(livro.getId())};

        try{
            mDatabase = mHelper.getWritableDatabase();
            mDatabase.update(LivroContract.LivroEntry.TABLE_NAME, values, where, whereArgs);
        }catch (Exception e){
            deuCerto = false;
        }finally {
            mDatabase.close();
        }
        return deuCerto;
    }
}
