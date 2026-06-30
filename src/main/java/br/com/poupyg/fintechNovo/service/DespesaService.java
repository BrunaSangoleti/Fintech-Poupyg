package br.com.poupyg.fintechNovo.service;
import br.com.poupyg.fintechNovo.exception.RecursoNaoEncontradoException;
import br.com.poupyg.fintechNovo.model.Despesa;
import br.com.poupyg.fintechNovo.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa buscarDespesas(Long id) {

        Optional<Despesa> despesa = despesaRepository.findById(id);

        if(despesa.isPresent()) {
            return despesa.get();
        } else {
            throw new RecursoNaoEncontradoException("Despesa com o ID " + id + " não foi encontrada.");
        }
    }

    public List<Despesa> buscarTodos() {
        return despesaRepository.findAll();
    }

    public Despesa salvar(Despesa despesas) {
        return despesaRepository.save(despesas);
    }

    public void excluir(Long id) {
        Optional<Despesa> despesas = despesaRepository.findById(id);

        if(despesas.isPresent()) {
            despesaRepository.deleteById(id);
        } else {
            throw new RecursoNaoEncontradoException("Não foi possível excluir. Despesa com o ID " + id + " não encontrada.");
        }
    }

    public Despesa atualizar(Long id, Despesa despesa) {
        Optional<Despesa> despesaAtual = despesaRepository.findById(id);

        if(despesaAtual.isPresent()) {

            return despesaRepository.save(despesa);
        } else {
            throw new RecursoNaoEncontradoException("Não foi possível atualizar. Despesa com o ID " + id + " não encontrada.");
        }
    }
}



