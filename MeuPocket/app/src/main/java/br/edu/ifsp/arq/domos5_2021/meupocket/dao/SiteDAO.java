package br.edu.ifsp.arq.domos5_2021.meupocket.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import br.edu.ifsp.arq.domos5_2021.meupocket.constantes.Constantes;
import br.edu.ifsp.arq.domos5_2021.meupocket.model.Site;

public class SiteDAO {
    private final String TAG = this.getClass().getSimpleName();
    private static SiteDAO instance = null;
    private List<Site> siteList;
    private Context context;

    private SiteDAO(){
        siteList = new ArrayList<>(9);
        siteList.add(new Site("google", "www.google.com.br"));
        siteList.add(new Site("uol", "www.uol.com.br"));
        siteList.add(new Site("ifsp", "www.ifsp.edu.br"));
        siteList.add(new Site("ifsp araraquara", "arq.ifsp.edu.br"));
        siteList.add(new Site("moodle araraquara", "mooodle.arq.ifsp.edu.br"));
        siteList.add(new Site("developers", "developers.android.com"));
        siteList.add(new Site("Stackoverflow", "stackoverflow.com"));
        siteList.add(new Site("Amazon Music", "music.amazon.com"));
        siteList.add(new Site("Brasil", "www.gov.br"));
    }

    private SiteDAO(Context context){
        this.context = context;
        siteList = new ArrayList<>();
        selectAll();
    }

    private void selectAll(){
        SharedPreferences sharedPreferences;
        JSONObject jsonObject;
        JSONArray jsonArray;
        String jsonString;
        Site site;

        sharedPreferences = this.context.getSharedPreferences(Constantes.DATA_FILE_NAME, Context.MODE_PRIVATE);
        jsonString = sharedPreferences.getString(Constantes.TABLE_NAME, "");

        if(!jsonString.isEmpty()){
            try{
                jsonArray = new JSONArray(jsonString);
                for(int i=0; i < jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    site = new Site(
                            jsonObject.getString(Constantes.ATTR_TITLE),
                            jsonObject.getString(Constantes.ATTR_URL));
                    site.setFavorite(jsonObject.getBoolean(Constantes.ATTR_FAVORITE));
                    siteList.add(site);
                }
            }catch (JSONException e){
                Log.e(TAG, "Erro ao recuperar dados do JSON");
            }
        }else {
            Log.i(TAG, "Sem dados armazenados");
        }
    }

    private void commitAll(){
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        JSONObject jsonObject;
        JSONArray jsonArray;

        sharedPreferences = this.context.getSharedPreferences(Constantes.DATA_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        jsonArray = new JSONArray();

        for(Site site : siteList){
            jsonObject = new JSONObject();
            try {
                jsonObject.put(Constantes.ATTR_TITLE, site.getTitle());
                jsonObject.put(Constantes.ATTR_URL, site.getUrl());
                jsonObject.put(Constantes.ATTR_FAVORITE, site.isFavorite());
                jsonArray.put(jsonObject);
            }catch (JSONException e){
                Log.e(TAG, e.getMessage());
            }
        }
        editor.putString(Constantes.TABLE_NAME, jsonArray.toString());
        editor.commit();
    }

    public static synchronized SiteDAO getInstance(Context context){
        if(instance == null){
            instance = new SiteDAO(context);
        }
        return instance;
    }

    public List<Site> getSites() {
        return siteList;
    }

    public void addSite(Site site){
        if(site != null){
            siteList.add(site);
            commitAll();
        }
    }

    public void updateSite(String oldTitle, String title, String url){
        Site alterar = find(oldTitle);
        if (alterar != null) {
            alterar.setTitle(title);
            alterar.setUrl(url);
            commitAll();
        }
    }

    public Site find(int i){
        return siteList.get(i);
    }

    public Site find(String title){

        for(Site s : siteList){
            if(s.getTitle().equals(title)){
                return s;
            }
        }
        return null;
    }
}
