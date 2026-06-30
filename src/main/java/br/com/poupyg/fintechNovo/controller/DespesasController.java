package br.com.poupyg.fintechNovo.controller;

import br.com.poupyg.fintechNovo.model.Despesa;
import  br.com.poupyg.fintechNovo.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/despesa")
@CrossOrigin(origins = "*")
public class DespesasController {

    @Autowired
    private DespesaService service;

    @GetMapping
    public List<Despesa> listar() { return service.buscarTodos(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Despesa criar(@RequestBody Despesa despesa) { return service.salvar(despesa); }

    @PutMapping("/{id}")
    public Despesa atualizar(@PathVariable Long id, @RequestBody Despesa despesa) {
        return service.atualizar(id, despesa);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) { service.excluir(id); }
}

