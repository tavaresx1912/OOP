package Classes;

import java.util.ArrayList;

// Playlist comum (classe base concreta da hierarquia de playlists).
// PlaylistAutomatica estende esta classe e sobrescreve gerarMusicas().
public class Playlist {

    protected String nome;
    protected ArrayList<Musica> musicas;

    public Playlist(String nome) {
        setNome(nome);
        this.musicas = new ArrayList<>();
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("O nome da playlist não pode estar vazio");
        this.nome = nome.trim();
    }

    public String getNome() { return nome; }

    public void addMusica(Musica musica) {
        if (musica == null) throw new IllegalArgumentException("Música nula");
        musicas.add(musica);
    }

    public void removeMusica(Musica musica) { musicas.remove(musica); }

    public ArrayList<Musica> getMusicas() { return new ArrayList<>(musicas); }

    public int getTamanho() { return musicas.size(); }

    public int getDuracaoTotal() {
        int total = 0;
        for (Musica m : musicas) total += m.getDuracao();
        return total;
    }

    // Sobrescrito por PlaylistAutomatica para diferenciar tipos.
    public String getTipo() { return "Manual"; }

    // Em playlists manuais não faz nada; PlaylistAutomatica regenera a partir
    // do catálogo recebido.
    public void gerarMusicas(ArrayList<Musica> catalogo) { }

    @Override
    public String toString() {
        return String.format("%s [%s] (%d músicas)", nome, getTipo(), musicas.size());
    }
}
