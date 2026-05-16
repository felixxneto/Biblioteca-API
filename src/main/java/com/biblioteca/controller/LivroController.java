package com.biblioteca.controller;

import com.biblioteca.model.Livro;
import com.biblioteca.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller responsável pelos endpoints de livros
// Aqui eu recebo as requisições HTTP e delego pro service
@RestController
@RequestMapping("/livros")
public class LivroController {

    private final BibliotecaService service;

    @Autowired
    public LivroController(BibliotecaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarTodos() {
        return ResponseEntity.ok(service.listarLivros());
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Livro>> listarDisponiveis() {
        return ResponseEntity.ok(service.listarLivrosDisponiveis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarLivroPorId(id));
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Livro>> buscarPorGenero(@PathVariable String genero) {
        return ResponseEntity.ok(service.buscarLivrosPorGenero(genero));
    }

    @PostMapping
    public ResponseEntity<Livro> criar(@RequestBody Livro livro) {
        Livro salvo = service.salvarLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }

}