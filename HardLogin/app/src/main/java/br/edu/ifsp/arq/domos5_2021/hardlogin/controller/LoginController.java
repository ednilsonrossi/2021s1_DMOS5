package br.edu.ifsp.arq.domos5_2021.hardlogin.controller;

import br.edu.ifsp.arq.domos5_2021.hardlogin.constantes.Constantes;
import br.edu.ifsp.arq.domos5_2021.hardlogin.data.UsuarioDAO;
import br.edu.ifsp.arq.domos5_2021.hardlogin.model.Usuario;

public class LoginController {

    public static boolean checklogin(String username, int passwd){
        UsuarioDAO usuarioDAO;
        Usuario usuario;
        usuarioDAO = new UsuarioDAO();
        usuario = usuarioDAO.recuperate(username);
        if(usuario != null){
            return usuario.validate(username, passwd);
        }
        return false;
    }

}
