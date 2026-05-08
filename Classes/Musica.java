package Classes;

// Classe que representa uma música no sistema
public class Musica {
    private String titulo;
    private String artista;
    private int duracao; // em segundos
    private String genero;

    private static final String[] GENEROS_VALIDOS = {
            "pop", "rock", "jazz", "eletrônica", "hip-hop", "clássica"
    };

    public Musica(String titulo, String artista, int duracao, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty())
            throw new IllegalArgumentException("O título não pode estar vazio");
        this.titulo = titulo.trim();
    }

    public String getTitulo() { return titulo; }

    public void setArtista(String artista) {
        if (artista == null || artista.trim().isEmpty())
            throw new IllegalArgumentException("O artista não pode estar vazio");
        this.artista = artista.trim();
    }

    public String getArtista() { return artista; }

    public void setDuracao(int duracao) {
        if (duracao <= 0 || duracao > 3600)
            throw new IllegalArgumentException("Duração inválida (1 a 3600 segundos)");
        this.duracao = duracao;
    }

    public int getDuracao() { return duracao; }

    public String formatarDuracao() {
        return String.format("%02d:%02d", duracao / 60, duracao % 60);
    }

    public void setGenero(String genero) {
        if (genero == null || genero.trim().isEmpty())
            throw new IllegalArgumentException("O gênero não pode estar vazio");
        String g = genero.trim().toLowerCase();
        boolean valido = false;
        for (String permitido : GENEROS_VALIDOS) {
            if (permitido.equals(g)) { valido = true; break; }
        }
        if (!valido)
            throw new IllegalArgumentException(
                    "Gênero inválido. Permitidos: Pop, Rock, Jazz, Eletrônica, Hip-Hop, Clássica");
        this.genero = g;
    }

    public String getGenero() { return genero; }

    @Override
    public String toString() {
        return String.format("%s - %s (%s) [%s]", titulo, artista, formatarDuracao(), genero);
    }
}
