package com.senac.biblioteca.model;

import java.util.ArrayList;

public class ListaLivro {
    public static ArrayList<Livro> lista = new ArrayList<Livro>();
    
    public static void adicionar(Livro livro) {
        lista.add(livro);
    }
    
    public static ArrayList<Livro> listar() {
        return lista;
    }
}
