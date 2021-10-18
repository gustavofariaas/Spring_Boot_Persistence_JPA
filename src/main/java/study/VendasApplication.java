package study;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import study.domain.entity.Cliente;
import study.domain.entity.Pedido;
import study.domain.repositorio.Clientes;
import study.domain.repositorio.Pedidos;
import study.domain.repositorio.RepositoryCliente;
import study.domain.repositorio.RepositoryClienteJPA;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos
    ) {
        return args -> {
            System.out.println("Salvando clientes");
            Cliente fulano = new Cliente("Fulano");
            clientes.save(fulano);

            Pedido p = new Pedido();
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

//            Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
//            System.out.println(cliente);
//            System.out.println(cliente.getPedidos());

            pedidos.findByCliente(fulano).forEach(System.out::println);



        };
    }


    public static void main(String[] args){
        SpringApplication.run(VendasApplication.class, args);
    }



    /* JDBC purpose***
    @Bean
    public CommandLineRunner init(@Autowired RepositoryClienteJDBC repositoryClienteJDBC){
        return args -> {



            //Fins de teste, adicionando a altura por aqui, só vai adicionar no banco, se nao colocar retorno da altura no RowMapper,
            //o Objeto Cliente nao vai receber esse valor, ou seja, sera NULL obs:toString()
            //repositoryCliente.salvar(new Cliente("Gustavo",10));

            System.out.println("Inserindo um dado: ");
            repositoryClienteJDBC.salvar(new ClienteJDBC("Gustavo"));
            repositoryClienteJDBC.salvar(new ClienteJDBC("Roberto"));
            repositoryClienteJDBC.salvar(new ClienteJDBC("Joao"));


            List<ClienteJDBC> listClienteJDBCS = repositoryClienteJDBC.obterTodos();
            listClienteJDBCS.forEach(System.out::println);


            System.out.println("Fazendo atualizacao dos dados");
            listClienteJDBCS.forEach(clienteJDBC -> {
                clienteJDBC.setNome(clienteJDBC.getNome() + " Atualizado");
                repositoryClienteJDBC.atualizar(clienteJDBC);

            });

            System.out.println("Nomes atualizados: " + listClienteJDBCS.toString());

            System.out.println("Deletando valores");

            repositoryClienteJDBC.obterTodos().forEach(clienteJDBC -> {
                repositoryClienteJDBC.deletar(clienteJDBC);
            });

            listClienteJDBCS = repositoryClienteJDBC.obterTodos();

            if(listClienteJDBCS.isEmpty()){
                System.out.println("Lista vazia.");
            }else{
                System.out.println("Valores: " + listClienteJDBCS.toString());
            }
        };

        /* JPA example
        return args -> {
            System.out.println("Salvando clientes");

            repositoryClienteJPA.salvar(new Cliente("Gustavo"));
            repositoryClienteJPA.salvar(new Cliente("Roberto"));
            repositoryClienteJPA.salvar(new Cliente("Joao"));

            List<Cliente> listCliente = repositoryClienteJPA.obterTodos();
            listCliente.forEach(System.out::println);

            System.out.println("Fazendo atualizacao dos dados");
            listCliente.forEach(cliente -> {
                cliente.setNome(cliente.getNome() + " Atualizado");
                repositoryClienteJPA.atualizar(cliente);

            });

            System.out.println("Nomes atualizados: " + listCliente.toString());

            System.out.println("Deletando valores");

            repositoryClienteJPA.obterTodos().forEach(cliente -> {
                repositoryClienteJPA.deletar(cliente);
            });

            listCliente = repositoryClienteJPA.obterTodos();

            if(listCliente.isEmpty()){
                System.out.println("Lista vazia.");
            }else{
                System.out.println("Valores: " + listCliente.toString());
            }

        };

        // JPA example using JPArepository
        return args -> {
            System.out.println("Salvando clientes");

            repositoryCliente.save(new Cliente("Gustavo"));
            repositoryCliente.save(new Cliente("Roberto"));
            repositoryCliente.save(new Cliente("Joao"));

            List<Cliente> listCliente = repositoryCliente.findAll();
            listCliente.forEach(System.out::println);

            System.out.println("Fazendo atualizacao dos dados");
            listCliente.forEach(cliente -> {
                cliente.setNome(cliente.getNome() + " Atualizado");
                repositoryCliente.save(cliente);

            });

            System.out.println("Nomes atualizados: " + listCliente.toString());

            System.out.println("Buscando por nomes");
            repositoryCliente.findByNomeLike("Gustavo").forEach(System.out::println);

            System.out.println("Deletando valores");

            repositoryCliente.findAll().forEach(cliente -> {
                repositoryCliente.delete(cliente);
            });

            listCliente = repositoryCliente.findAll();

            if(listCliente.isEmpty()){
                System.out.println("Lista vazia.");
            }else{
                System.out.println("Valores: " + listCliente.toString());
            }

        };
    }
     */

}
