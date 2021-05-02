package br.edu.ifsp.arq.domos5_2021.hardlogin.controller;

import br.edu.ifsp.arq.domos5_2021.hardlogin.constantes.Constantes;
import br.edu.ifsp.arq.domos5_2021.hardlogin.model.Usuario;

public class LoginController {

    public static boolean checklogin(String username, int passwd){
        Usuario usuario;
        usuario = new Usuario(username, passwd);
        return usuario.validate(Constantes.USER, Constantes.PASSWORD);
    }

}
