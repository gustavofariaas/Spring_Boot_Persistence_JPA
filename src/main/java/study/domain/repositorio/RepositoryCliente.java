package study.domain.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.domain.entity.Cliente;

import java.util.List;

//JPARepository<Nome da entidade, tipo do ID da entidade>

public interface RepositoryCliente extends JpaRepository<Cliente, Integer> {

    //Query methods
    List<Cliente> findByNomeLike(String nome);
    List<Cliente> findByNomeOrId(String nome, Integer id);
    boolean existsByNome(String nome);
    void deleteByNome(String nome);


    //Query criada manualmente
    @Query(value = "select c from cliente c where c.nome like :nome")
    List<Cliente> encontrarNome( @Param("nome") String nome);

    //Query Nativa
    @Query(value = "select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> encontrarNomeComQueryNativa( @Param("nome") String nome);

    @Query("delete from cliente c where c.nome =:nome")
    @Modifying //Usar modifing para modificar campo
    void deletarPorNome(@Param("nome") String nome);
}
