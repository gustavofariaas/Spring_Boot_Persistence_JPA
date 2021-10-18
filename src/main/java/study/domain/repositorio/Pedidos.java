package study.domain.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import study.domain.entity.Cliente;
import study.domain.entity.Pedido;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}