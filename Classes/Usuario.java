package Classes;

import java.util.ArrayList;

// Classe abstrata — não pode ser instanciada diretamente.
// Cada subclasse concreta define seu próprio comportamento para os
// métodos abstratos (limite de playlists, possibilidade de download e
// reprodução de música).
public abstract class Usuario {

    protected String nome;
    protected String email;
    protected String senha;
    protected ArrayList<Playlist> playlists;

    public Usuario(String nome, String email, String senha) {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        this.playlists = new ArrayList<>();
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("O nome não pode estar vazio");
        this.nome = nome.trim();
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@"))
            throw new IllegalArgumentException("E-mail inválido");
        this.email = email.trim().toLowerCase();
    }

    public void setSenha(String senha) {
        if (senha == null || senha.length() < 3)
            throw new IllegalArgumentException("Senha inválida (mínimo 3 caracteres)");
        this.senha = senha;
    }

    public String getNome()  { return nome; }
    public String getEmail() { return email; }

    public boolean autenticar(String email, String senha) {
        return this.email.equalsIgnoreCase(email) && this.senha.equals(senha);
    }

    public ArrayList<Playlist> getPlaylists() { return new ArrayList<>(playlists); }

    public boolean adicionarPlaylist(Playlist playlist) {
        if (playlist == null)
            throw new IllegalArgumentException("Playlist nula");
        if (playlists.size() >= getLimitePlaylists()) {
            System.out.println(">> Limite de " + getLimitePlaylists()
                    + " playlists atingido para usuário " + getTipo() + ".");
            return false;
        }
        playlists.add(playlist);
        return true;
    }

    // ----- Métodos abstratos: cada subclasse define seu comportamento -----
    public abstract String getTipo();
    public abstract int getLimitePlaylists();
    public abstract boolean podeBaixar();
    public abstract void ouvirMusica(Musica musica);

    // ----- Método concreto que pode ser sobrescrito (não é abstrato) -----
    public void exibirInfo() {
        System.out.println("Usuário: " + nome + " (" + getTipo() + ")");
        System.out.println("E-mail: " + email);
        System.out.println("Playlists: " + playlists.size() + "/" + getLimitePlaylists());
    }
}
