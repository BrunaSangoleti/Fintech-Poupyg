package br.com.poupyg.fintechNovo.service;

import br.com.poupyg.fintechNovo.exception.RecursoNaoEncontradoException;
import br.com.poupyg.fintechNovo.model.Investimento;

import br.com.poupyg.fintechNovo.repository.InvestimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InvestimentoService {

    @Autowired
    private InvestimentoRepository investimentoRepository;

    public Investimento buscarInvestimento(Long id) {

        Optional<Investimento> investimento = investimentoRepository.findById(id);

        if(investimento.isPresent()) {
            return investimento.get();
        } else {
            throw new RecursoNaoEncontradoException("Investimento com o ID " + id + " não foi encontrada.");
        }
    }

    public List<Investimento> buscarTodos() {
        return investimentoRepository.findAll();
    }

    public Investimento salvar(Investimento investimento) {
        return investimentoRepository.save(investimento);
    }

    public void excluir(Long id) {
        Optional<Investimento> investimento = investimentoRepository.findById(id);

        if(investimento.isPresent()) {
            investimentoRepository.deleteById(id);
        } else {
            throw new RecursoNaoEncontradoException("Não foi possível excluir. Investimento com o ID " + id + " não encontrada.");
        }
    }

    public Investimento atualizar(Long id, Investimento investimento) {
        Optional<Investimento> investimentoAtual = investimentoRepository.findById(id);

        if(investimentoAtual.isPresent()) {

            return investimentoRepository.save(investimento);
        } else {
            throw new RecursoNaoEncontradoException("Não foi possível atualizar. Investimento com o ID " + id + " não encontrada.");
        }
    }
}
