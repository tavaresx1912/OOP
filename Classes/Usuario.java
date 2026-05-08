package Classes;

public class Usuario {
    private String nome;

    public Usuario(String nome) {
        setNome(nome);
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio");
        }
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

}
