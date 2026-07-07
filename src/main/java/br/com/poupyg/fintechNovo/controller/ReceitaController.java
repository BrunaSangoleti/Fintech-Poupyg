package br.com.poupyg.fintechNovo.controller;

import br.com.poupyg.fintechNovo.model.Receita;
import br.com.poupyg.fintechNovo.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/receita")
@CrossOrigin(origins = "*")
public class ReceitaController {

    @Autowired
    private ReceitaService service;

    @GetMapping
    public ResponseEntity<List<Receita>> listar(@RequestParam(required = false) Long usuarioId) {

        if (usuarioId != null) {
            List<Receita> receitas = service.buscarPorUsuario(usuarioId);
            return ResponseEntity.ok(receitas);
        }


        return ResponseEntity.ok(service.buscarTodos());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Receita criar(@RequestBody Receita receita) { return service.salvar(receita); }

    @PutMapping("/{id}")
    public Receita atualizar(@PathVariable Long id, @RequestBody Receita receita) {
        return service.atualizar(id, receita);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) { service.excluir(id); }
}
