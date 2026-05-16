package com.biblioteca.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class ItemAcervo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private boolean disponivel;

    public ItemAcervo(Long id, String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    public ItemAcervo() {}

    public abstract String getDetalhes();

    public void exibir() {
        System.out.println("=== " + getDetalhes() + " ===");
        System.out.println("Disponivel: " + (disponivel ? "Sim" : "Nao"));
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}