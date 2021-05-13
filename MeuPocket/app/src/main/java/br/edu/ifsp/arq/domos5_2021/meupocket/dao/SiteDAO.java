package br.edu.ifsp.arq.domos5_2021.meupocket.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.domos5_2021.meupocket.model.Site;

public class SiteDAO {
    private static SiteDAO instance = null;
    private List<Site> siteList;

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

    public static synchronized SiteDAO getInstance(){
        if(instance == null){
            instance = new SiteDAO();
        }
        return instance;
    }

    public List<Site> getSites() {
        return siteList;
    }
}
