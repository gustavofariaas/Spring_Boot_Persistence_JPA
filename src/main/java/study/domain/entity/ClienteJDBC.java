package study.domain.entity;

public class ClienteJDBC {
    private Integer id;
    private String nome;
    private Integer altura;

    public ClienteJDBC() {
    }

    public ClienteJDBC(String nome) {
        this.nome = nome;
    }

    public ClienteJDBC(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    //Fins de teste
    public ClienteJDBC(String nome, Integer altura) {
        this.nome = nome;
        this.altura = altura;
    }

    public ClienteJDBC(Integer i){
        this.altura = i;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public Integer getAltura() {
        return altura;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", altura=" + altura +
                '}';
    }


}
