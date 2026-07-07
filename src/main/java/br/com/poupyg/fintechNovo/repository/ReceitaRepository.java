package br.com.poupyg.fintechNovo.repository;

import br.com.poupyg.fintechNovo.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByUsuarioCodigo(Long usuarioCodigo);
}