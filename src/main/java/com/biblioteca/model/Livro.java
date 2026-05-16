package com.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro extends ItemAcervo {

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private int numeroPaginas;

    public Livro() {}

    public Livro(String titulo, String autor, String isbn, String genero, int numeroPaginas) {
        super(null, titulo, autor);
        this.isbn = isbn;
        this.genero = genero;
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String getDetalhes() {
        return "Livro: " + getTitulo() + " | Autor: " + getAutor() +
                " | ISBN: " + isbn + " | Genero: " + genero +
                " | Paginas: " + numeroPaginas;
    }

    public String getIsbn() { return isbn; }
    public String getGenero() { return genero; }
    public int getNumeroPaginas() { return numeroPaginas; }
}