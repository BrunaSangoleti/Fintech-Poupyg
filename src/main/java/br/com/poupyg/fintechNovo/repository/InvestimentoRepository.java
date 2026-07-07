package br.com.poupyg.fintechNovo.repository;

import br.com.poupyg.fintechNovo.model.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {
    List<Investimento> findByUsuarioCodigo(Long usuarioCodigo);
}