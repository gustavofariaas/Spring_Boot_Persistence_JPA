package study.domain.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import study.domain.entity.ItemPedido;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}