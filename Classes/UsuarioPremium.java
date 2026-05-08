package Classes;

import java.util.ArrayList;

// Implementação concreta da classe abstrata Usuario para o plano premium.
public class UsuarioPremium extends Usuario {

    private static final int LIMITE_PLAYLISTS = 100;
    private double mensalidade;
    private ArrayList<Musica> downloads;

    public UsuarioPremium(String nome, String email, String senha, double mensalidade) {
        super(nome, email, senha);
        setMensalidade(mensalidade);
        this.downloads = new ArrayList<>();
    }

    public void setMensalidade(double mensalidade) {
        if (mensalidade < 0)
            throw new IllegalArgumentException("Mensalidade não pode ser negativa");
        this.mensalidade = mensalidade;
    }

    public double getMensalidade() { return mensalidade; }

    @Override
    public String getTipo() { return "Premium"; }

    @Override
    public int getLimitePlaylists() { return LIMITE_PLAYLISTS; }

    @Override
    public boolean podeBaixar() { return true; }

    @Override
    public void ouvirMusica(Musica musica) {
        if (musica == null) { System.out.println("Música inválida."); return; }
        System.out.println("[Premium] Tocando (sem anúncios): "
                + musica.getTitulo() + " - " + musica.getArtista());
    }

    public void baixarMusica(Musica musica) {
        if (musica == null) { System.out.println("Música inválida."); return; }
        if (downloads.contains(musica)) {
            System.out.println("Já baixada: " + musica.getTitulo());
            return;
        }
        downloads.add(musica);
        System.out.println("Download concluído: " + musica.getTitulo());
    }

    public ArrayList<Musica> getDownloads() { return new ArrayList<>(downloads); }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Mensalidade: R$ " + String.format("%.2f", mensalidade));
        System.out.println("Downloads: " + downloads.size());
    }
}
