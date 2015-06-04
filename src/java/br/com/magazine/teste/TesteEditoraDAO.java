
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.magazine.teste;

import br.com.magazine.dao.ClienteDAO;
import br.com.magazine.dao.EditoraDAO;
import br.com.magazine.entidade.Editora;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Evandro-PC
 */
public class TesteEditoraDAO {

    public static void main(String[] args) throws ParseException, ClassNotFoundException {
        Editora editora = new Editora();
        editora.setNome("Alamo");
        
        EditoraDAO editoraDAO = new EditoraDAO();
        editoraDAO.cadastrarEditora(editora);
        
    }
}