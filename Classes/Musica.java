package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Classe que representa uma música
public class Musica {
    private String titulo;
    private String artista;
    private int duracao; // em segundos
    private String genero;
    private String listaGenero = Arrays.toString(new String[]{"Pop, Rock, Jazz, Eletrônica, Hip-Hop, Clássica"});

    public Musica(String titulo, String artista, int duracao, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("O título não pode estar vazio");
        }
        this.titulo = titulo.trim();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setArtista(String artista) {
        if (artista == null || artista.isEmpty()) {
            throw new IllegalArgumentException("O artista não pode estar vazio");
        }
        this.artista = artista.trim();
    }

    public String getArtista() {
        return artista;
    }

    public void setDuracao(int duracao) {
        if (duracao <= 0 || duracao > 3600) {
            throw new IllegalArgumentException("Duração inválida");
        }
        this.duracao = duracao;
    }

    public String formatarDuracao() {
        int minutos = duracao / 60;
        int segundos = duracao % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    public int getDuracao() {
        return duracao;
    }

    public void setGenero(String genero) {
        genero = genero.trim().toLowerCase();
        if (genero.isEmpty() || listaGenero.contains(genero)) {
            throw new IllegalArgumentException("O gênero não pode estar vazio ou não estar na lista de gêneros permitidos: " + listaGenero + "");
        }
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

}
