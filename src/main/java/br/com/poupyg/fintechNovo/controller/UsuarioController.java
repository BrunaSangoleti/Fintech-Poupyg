package br.com.poupyg.fintechNovo.controller;



import br.com.poupyg.fintechNovo.model.Usuario;
import br.com.poupyg.fintechNovo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> realizarLogin(@RequestBody LoginRequest loginData) { // Usando o DTO aqui
        try {
            String email = loginData.getEmail();
            String senha = loginData.getSenha();

            if (email == null || senha == null || email.isBlank() || senha.isBlank()) {
                return ResponseEntity.badRequest().body("E-mail e senha são obrigatórios.");
            }

            Usuario usuarioAutenticado = usuarioService.autenticar(email, senha);

            if (usuarioAutenticado != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("id", usuarioAutenticado.getCodigo());
                response.put("nome", usuarioAutenticado.getNome());
                response.put("email", usuarioAutenticado.getEmail());
                response.put("token", "token-gerado-pela-api");

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha incorretos.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno no servidor ao tentar realizar o login.");
        }
    }

    // Classe DTO auxiliar (pode colocar no final do arquivo do Controller ou em uma classe separada)
    public static class LoginRequest {
        private String email;
        private String senha;

        // Getters e Setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }

    @GetMapping()
    public List<Usuario> listar() {
        return usuarioService.buscarTodos();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPerfil(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.buscarUsuario(id);

            if (usuario != null) {
                return ResponseEntity.ok(usuario); // Retorna 200 OK com os dados do banco
            } else {
                // Mapeamento correto de erro 404: O usuário não existe no banco
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao buscar o perfil do usuário.");
        }
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.salvar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar usuário.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioAtualizado = usuarioService.atualizar(id, usuario);
            if (usuarioAtualizado != null) {
                return ResponseEntity.ok(usuarioAtualizado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para atualização.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar usuário.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {

            Usuario usuario = usuarioService.buscarUsuario(id);
            if (usuario != null) {
                usuarioService.excluir(id);
                return ResponseEntity.noContent().build(); // Status 204 No Content
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para deleção.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar usuário.");
        }
    }
}