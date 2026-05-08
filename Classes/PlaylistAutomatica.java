package Classes;

import java.util.ArrayList;

// Playlist gerada automaticamente a partir de um critério (gênero ou artista).
// Demonstra herança e sobrescrita (@Override) na hierarquia de playlists.
public class PlaylistAutomatica extends Playlist {

    public enum Criterio { GENERO, ARTISTA }

    private Criterio criterio;
    private String valorCriterio;

    public PlaylistAutomatica(String nome, Criterio criterio, String valorCriterio) {
        super(nome);
        this.criterio = criterio;
        this.valorCriterio = valorCriterio.trim().toLowerCase();
    }

    public Criterio getCriterio() { return criterio; }
    public String getValorCriterio() { return valorCriterio; }

    @Override
    public String getTipo() {
        return "Automática (" + criterio + "=" + valorCriterio + ")";
    }

    // Regenera o conteúdo a partir do catálogo, segundo o critério escolhido.
    @Override
    public void gerarMusicas(ArrayList<Musica> catalogo) {
        musicas.clear();
        if (catalogo == null) return;
        for (Musica m : catalogo) {
            boolean bate = false;
            switch (criterio) {
                case GENERO:
                    bate = m.getGenero().equalsIgnoreCase(valorCriterio);
                    break;
                case ARTISTA:
                    bate = m.getArtista().toLowerCase().contains(valorCriterio);
                    break;
            }
            if (bate) musicas.add(m);
        }
    }

    // Em playlists automáticas, manter o controle manual seria inconsistente.
    @Override
    public void addMusica(Musica musica) {
        System.out.println("Não é possível adicionar manualmente em playlist automática.");
    }
}
