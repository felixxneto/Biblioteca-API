package com.biblioteca.model;

public class Revista extends ItemAcervo {

    private int edicao;
    private String mesAno;

    public Revista(Long id, String titulo, String autor, int edicao, String mesAno) {
        super(id, titulo, autor);
        this.edicao = edicao;
        this.mesAno = mesAno;
    }

    @Override
    public String getDetalhes() {
        return "Revista: " + getTitulo() + " | Editora: " + getAutor() +
                " | Edição: " + edicao + " | Período: " + mesAno;
    }

    public int getEdicao() {
        return edicao;
    }

    public String getMesAno() {
        return mesAno;
    }
}
