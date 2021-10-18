package study.domain.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import study.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto,Integer> {
}