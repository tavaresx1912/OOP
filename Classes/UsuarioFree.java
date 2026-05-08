package Classes;

// Implementação concreta da classe abstrata Usuario para o plano gratuito.
public class UsuarioFree extends Usuario {

    private static final int LIMITE_PLAYLISTS = 3;
    private int contadorMusicasOuvidas;

    public UsuarioFree(String nome, String email, String senha) {
        super(nome, email, senha);
        this.contadorMusicasOuvidas = 0;
    }

    @Override
    public String getTipo() { return "Free"; }

    @Override
    public int getLimitePlaylists() { return LIMITE_PLAYLISTS; }

    @Override
    public boolean podeBaixar() { return false; }

    @Override
    public void ouvirMusica(Musica musica) {
        if (musica == null) { System.out.println("Música inválida."); return; }
        contadorMusicasOuvidas++;
        System.out.println("[Free] Tocando: " + musica.getTitulo()
                + " - " + musica.getArtista());
        if (contadorMusicasOuvidas % 3 == 0) mostrarAnuncio();
    }

    public void mostrarAnuncio() {
        System.out.println("[ANÚNCIO] Assine o Premium e ouça sem interrupções!");
    }

    public int getMusicasOuvidas() { return contadorMusicasOuvidas; }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Músicas ouvidas: " + contadorMusicasOuvidas);
    }
}
