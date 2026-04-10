package Classes;
import java.util.ArrayList;

public class Playlist {

    private String nome;
    private ArrayList<Musica> musicas = new ArrayList<>();

    public Playlist(String nome) {
        setNome(nome);
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome da playlist não pode estar vazio");
        }
        this.nome = nome.trim();
    }
    public String getNome() {
        return nome;
    }

    public void addMusica(Musica musica) {
        if (musica == null) {
            throw new IllegalArgumentException("A música não pode ser nula");
        }
        musicas.add(musica);
    }

    public void removeMusica(Musica musica) {
        if (musica == null) {
            throw new IllegalArgumentException("A música não pode ser nula");
        }
        if (musicas.contains(musica)) {
            musicas.remove(musica);
        }
    }

    public ArrayList<Musica> getMusicas() {
        return new ArrayList<>(musicas);
    }
}
