package br.com.poupyg.fintechNovo.service;

import br.com.poupyg.fintechNovo.exception.RecursoNaoEncontradoException;
import br.com.poupyg.fintechNovo.model.Usuario;

import br.com.poupyg.fintechNovo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario autenticar(String email, String senha) {


        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailAndSenha(email, senha);


        // Se encontrar, devolve o usuário. Se não encontrar (senha ou e-mail errados), devolve null.
        return usuarioOpt.orElse(null);
    }

    public Usuario buscarUsuario(Long id) {

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(usuario.isPresent()) {
            return usuario.get();
        } else {
            throw new RecursoNaoEncontradoException("Usuario com o ID " + id + " não foi encontrada.");
        }
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void excluir(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(usuario.isPresent()) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RecursoNaoEncontradoException("Não foi possível excluir. Usuário com o ID " + id + " não encontrada.");
        }
    }

    public Usuario atualizar(Long id, Usuario usuario) {
        Optional<Usuario> usuarioAtual = usuarioRepository.findById(id);

        if(usuarioAtual.isPresent()) {

            return usuarioRepository.save(usuario);
        } else {
            throw new RecursoNaoEncontradoException("Não foi possível atualizar. Usuário com o ID " + id + " não encontrada.");
        }
    }
}