package com.biblioteca.controller;

import com.biblioteca.model.Livro;
import com.biblioteca.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final BibliotecaService service;

    @Autowired
    public LivroController(BibliotecaService service) {
        this.service = service;
    }

    // GET /livros
    @GetMapping
    public ResponseEntity<List<Livro>> listarTodos() {
        return ResponseEntity.ok(service.listarLivros());
    }

    // GET /livros/disponiveis
    @GetMapping("/disponiveis")
    public ResponseEntity<List<Livro>> listarDisponiveis() {
        return ResponseEntity.ok(service.listarLivrosDisponiveis());
    }

    // GET /livros/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarLivroPorId(id));
    }

    // GET /livros/genero/{genero}
    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Livro>> buscarPorGenero(@PathVariable String genero) {
        return ResponseEntity.ok(service.buscarLivrosPorGenero(genero));
    }

    // POST /livros
    @PostMapping
    public ResponseEntity<Livro> criar(@RequestBody Livro livro) {
        Livro salvo = service.salvarLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // DELETE /livros/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }

}