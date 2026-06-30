package br.com.poupyg.fintechNovo.repository;

import br.com.poupyg.fintechNovo.model.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {
    // Aqui você pode adicionar métodos de consulta personalizados, se precisar
    // Exemplo: List<Despesa> findByDescricaoContaining(String descricao);
}