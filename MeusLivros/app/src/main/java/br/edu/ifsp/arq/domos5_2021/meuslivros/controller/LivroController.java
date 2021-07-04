package br.edu.ifsp.arq.domos5_2021.meuslivros.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import br.edu.ifsp.arq.domos5_2021.meuslivros.adapter.ItemLivrosAdapter;
import br.edu.ifsp.arq.domos5_2021.meuslivros.constants.Constants;
import br.edu.ifsp.arq.domos5_2021.meuslivros.dao.LivroDao;
import br.edu.ifsp.arq.domos5_2021.meuslivros.model.Livro;
import br.edu.ifsp.arq.domos5_2021.meuslivros.view.DetalhesActivity;
import br.edu.ifsp.arq.domos5_2021.meuslivros.view.RecyclerItemClickListener;

public class LivroController {

    public static ItemLivrosAdapter getLivrosAdapter(Context context, RecyclerItemClickListener listener){
        List<Livro> livros;
        LivroDao dao = new LivroDao(context);
        livros = dao.recuperate();
        return new ItemLivrosAdapter(livros, listener);
    }

    public static void updateAdapterDataSource(Context context, ItemLivrosAdapter adapter){
        List<Livro> livros;
        LivroDao dao = new LivroDao(context);
        livros = dao.recuperate();
        adapter.setDataSource(livros);
    }

    public static boolean salvarLivro(Context context, String title, String author){
        LivroDao dao = new LivroDao(context);
        return dao.insert(new Livro(title, author));
    }

    public static boolean atualizarLivro(Context context, String title, String author, boolean borrowed){
        LivroDao dao = new LivroDao(context);
        return dao.update(new Livro(title, author, borrowed));
    }

    public static void mostrarDetalhes(Context context, ItemLivrosAdapter adapter, int position){
        Livro livro = adapter.getDataSource().get(position);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_TITLE, livro.getTitulo());
        bundle.putString(Constants.KEY_AUTHOR, livro.getAutor());
        bundle.putBoolean(Constants.KEY_BORROWED, livro.isEmprestado());
        Intent intent = new Intent(context, DetalhesActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
