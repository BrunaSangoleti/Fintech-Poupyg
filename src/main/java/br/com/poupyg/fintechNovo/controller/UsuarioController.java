package br.com.poupyg.fintechNovo.controller;

import br.com.poupyg.fintechNovo.model.Usuario;
import br.com.poupyg.fintechNovo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    @Autowired
    private UsuarioService service;


    @GetMapping()
    public List<Usuario> listar() { return service.buscarTodos(); }

    @GetMapping("/me")
    public Usuario buscarMeuPerfil() {
        try {

            Usuario usuario = service.buscarUsuario(1L);
            if (usuario != null) {
                return usuario;
            }
        } catch (Exception e) {
            System.out.println("Usuário ID 1 não encontrado no banco. Retornando dados de teste.");
        }

        // Caso o banco esteja vazio, cria um objeto temporário para o React não quebrar
        Usuario temporario = new Usuario();
        temporario.setCodigo(21L);
        temporario.setNome("Bruna Cristina");
        temporario.setEmail("bruna@email.com");
        temporario.setTelefone("(11) 99999-9999");
        temporario.setCpf("123.456.789-00");
        temporario.setSenha("usuario123");

        return temporario;
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criar(@RequestBody Usuario usuario) { return service.salvar(usuario); }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return service.atualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) { service.excluir(id); }
}