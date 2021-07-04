package br.edu.ifsp.arq.domos5_2021.meuslivros.controller;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import br.edu.ifsp.arq.domos5_2021.meuslivros.dao.AmigoDao;
import br.edu.ifsp.arq.domos5_2021.meuslivros.model.Amigo;

public class AmigosController {

    public static ArrayAdapter<Amigo> getAmigosAdapter(Context context){
        AmigoDao dao = new AmigoDao(context);
        ArrayAdapter<Amigo> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, dao.recuperate());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    public static boolean insereAmigo(Context context, String nome){
        AmigoDao dao = new AmigoDao(context);
        return dao.insert(new Amigo(nome));
    }

}
