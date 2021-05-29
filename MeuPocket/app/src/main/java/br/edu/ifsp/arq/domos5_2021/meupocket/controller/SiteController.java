package br.edu.ifsp.arq.domos5_2021.meupocket.controller;

import java.util.List;

import br.edu.ifsp.arq.domos5_2021.meupocket.dao.SiteDAO;
import br.edu.ifsp.arq.domos5_2021.meupocket.model.Site;

public class SiteController {

    public static List<Site> allSites(){
        return SiteDAO.getInstance().getSites();
    }

    public static void addSite(String title, String url){
        Site novo = new Site(title, url);
        SiteDAO.getInstance().addSite(novo);
    }

    public static void updateSite(String oldTitle, String title, String url){
        Site alterar = SiteDAO.getInstance().find(oldTitle);
        if (alterar != null) {
            alterar.setTitle(title);
            alterar.setUrl(url);
        }
    }
}
