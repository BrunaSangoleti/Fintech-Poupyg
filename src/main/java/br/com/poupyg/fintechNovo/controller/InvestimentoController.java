package br.com.poupyg.fintechNovo.controller;

import br.com.poupyg.fintechNovo.model.Investimento;
import br.com.poupyg.fintechNovo.service.InvestimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/investimentos")
@CrossOrigin(origins = "*")
public class InvestimentoController {

    @Autowired
    private InvestimentoService service;

    @GetMapping
    public List<Investimento> listar() { return service.buscarTodos(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Investimento criar(@RequestBody Investimento inv) { return service.salvar(inv); }

    @PutMapping("/{id}")
    public Investimento atualizar(@PathVariable Long id, @RequestBody Investimento inv) {
        return service.atualizar(id, inv);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) { service.excluir(id); }
}