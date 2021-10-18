package study.domain.repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import study.domain.entity.ClienteJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositoryClienteJDBC {

//    private static String INSERT_with_altura =  "insert into cliente (nome,altura) values (?,?) ";

    private static String INSERT =  "insert into cliente (nome) values ? ";
    private static String SELECTALL= "SELECT * FROM CLIENTE ";
    private static String UPDATE = "UPDATE cliente set nome = ?";
    private static String DELETE = "DELETE FROM cliente WHERE ID = ?";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ClienteJDBC salvar(ClienteJDBC clienteJDBC){
        jdbcTemplate.update( INSERT, new Object[]{clienteJDBC.getNome()});
        return clienteJDBC;
    }

    public void atualizar(ClienteJDBC clienteJDBC) {
        jdbcTemplate.update(UPDATE, new Object[]{clienteJDBC.getNome()});
    }


    public void deletar(ClienteJDBC clienteJDBC) {
        deletar(clienteJDBC.getId());
    }

    public void deletar(Integer id) {
        jdbcTemplate.update(DELETE, id);
    }



    public List<ClienteJDBC> buscarPorNome(String nome){
        return jdbcTemplate.query(
                SELECTALL.concat(" where nome like ?"),
                new Object[]{"%" + nome + "%"},
                getRowMapperCliente());
    }

    public List<ClienteJDBC> obterTodos(){
        return jdbcTemplate.query(SELECTALL, getRowMapperCliente());
    }

    private RowMapper<ClienteJDBC> getRowMapperCliente(){
        return new RowMapper<ClienteJDBC>(){
            @Override
            public ClienteJDBC mapRow(ResultSet resultSet, int i) throws SQLException{
                //Integer altura = resultSet.getInt("altura");
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new ClienteJDBC(id, nome);

                //fins de teste *altura*
                //return new Cliente(nome, altura);
            }
        };
    }

    //Fins de teste adicionando 2 colunas
//    public Cliente salvarTeste(Cliente cliente){
//        jdbcTemplate.update(INSERT_with_altura, new Object[]{cliente.getNome(), cliente.getAltura()});
//        return cliente;
//    }


    // Este metodo utiliza o RowMapper, que vai mapear o resultado do banco de dados para um objeto desejado(Cliente)

    //SE EU NAO MAPEAR A ALTURA DO CLIENTE NO ROWMAPPER,
    // O OBJETO CLIENTE NUNCA VAI RECEBER ESTE VALOR NO SPRING, O APPLICATION TA SÓ ADICIONANDO NO DATABASE
//    public List<Cliente> obterTodosComRowMapper(){
//        return jdbcTemplate.query(SELECTALL, new RowMapper<Cliente>() {
//            @Override
//            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
//                Integer id = resultSet.getInt("id");
//                String nome = resultSet.getString("nome");
//                return new Cliente(id, nome);
//                //return new Cliente(resultSet.getString("nome"));
//            }
//        });
//    }


}
