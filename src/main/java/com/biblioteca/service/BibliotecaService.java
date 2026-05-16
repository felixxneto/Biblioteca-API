package com.biblioteca.service;

import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;
import com.biblioteca.repository.LivroRepository;
import com.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BibliotecaService {

    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public BibliotecaService(LivroRepository livroRepository,
                             UsuarioRepository usuarioRepository) {
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // ── LIVROS ───────────────────────────────────────────

    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    public Livro buscarLivroPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro nao encontrado: " + id));
    }

    public List<Livro> listarLivrosDisponiveis() {
        return livroRepository.findByDisponivel(true);
    }

    public List<Livro> buscarLivrosPorGenero(String genero) {
        return livroRepository.findByGenero(genero);
    }

    public Livro salvarLivro(Livro livro) {
        if (livroRepository.findByIsbn(livro.getIsbn()).isPresent()) {
            throw new RuntimeException("Ja existe um livro com esse ISBN");
        }
        return livroRepository.save(livro);
    }

    public void deletarLivro(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro nao encontrado: " + id);
        }
        livroRepository.deleteById(id);
    }

    // ── USUARIOS ─────────────────────────────────────────

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado: " + id));
    }

    public Usuario salvarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Ja existe um usuario com esse email");
        }
        return usuarioRepository.save(usuario);
    }

    // ── EMPRESTIMO ────────────────────────────────────────

    public void realizarEmprestimo(Long usuarioId, Long livroId) {
        Usuario usuario = buscarUsuarioPorId(usuarioId);
        Livro livro = buscarLivroPorId(livroId);

        if (!livro.isDisponivel()) {
            throw new RuntimeException("Livro indisponivel para emprestimo");
        }

        livro.setDisponivel(false);
        livroRepository.save(livro);
        usuario.emprestarLivro(livro);
        usuarioRepository.save(usuario);
    }

    public void realizarDevolucao(Long usuarioId, Long livroId) {
        Usuario usuario = buscarUsuarioPorId(usuarioId);
        Livro livro = buscarLivroPorId(livroId);

        livro.setDisponivel(true);
        livroRepository.save(livro);
        usuario.devolverLivro(livro);
        usuarioRepository.save(usuario);
    }
}