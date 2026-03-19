import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusic {

    static ArrayList<Musica> musicas = new ArrayList<>();
    static ArrayList<Playlist> playlists = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 9);
    }

    static class Playlist {
        String nome;
        ArrayList<Musica> musicas;

        public Playlist(String nome) {
            this.nome = nome;
            this.musicas = new ArrayList<>();
        }
    }

    static class Musica {
        String titulo;
        String artista;
        int duracao;
        String genero;

        public Musica(String titulo, String artista, int duracao, String genero) {
            this.titulo = titulo;
            this.artista = artista;
            this.duracao = duracao;
            this.genero = genero;
        }
    }

    public static void exibirMenu() {
        System.out.println("Menu:");
        System.out.println("1. Adicionar música");
        System.out.println("2. Listar músicas");
        System.out.println("3. Buscar por Título");
        System.out.println("4. Buscar por Artista");
        System.out.println("5. Buscar por Genero");
        System.out.println("6. Buscar estasticas");
        System.out.println("7. Adicionar playlist");
        System.out.println("8. Adicionar música a playlist");
        System.out.println("9. Sair");
    }

    public static int lerOpcao() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: adicionarMusica(); break;
            case 2: listarMusicas(); break;
            case 3: buscar("título"); break;
            case 4: buscar("artista"); break;
            case 5: buscar("gênero"); break;
            case 6: mostrarEstatisticas(); break;
            case 7: adicionarPlaylist(); break;
            case 8: adicionarMusicaAPlaylist(); break;
            case 9: System.out.println("Saindo..."); break;
            default: System.out.println("Opção inválida. Tente novamente.");
        }
    }

    public static void adicionarMusica() { /* implementar */ 
        System.out.print("Digite o título da música: ");
        String titulo = sc.nextLine();
        System.out.print("Digite o artista da música: ");
        String artista = sc.nextLine();
        System.out.print("Digite a duração da música (em segundos): ");
        int duracao = Integer.parseInt(sc.nextLine());
        System.out.print("Digite o gênero da música: ");
        String genero = sc.nextLine();
        musicas.add(new Musica(titulo, artista, duracao, genero));
        FormatarDuracao(duracao);
        System.out.println("Música adicionada com sucesso!");
    }

    public static void FormatarDuracao(int duracao) {
        int minutos = duracao / 60;
        int segundos = duracao % 60;
        System.out.printf("%02d:%02d%n", minutos, segundos);
    }

    public static void listarMusicas() { /* implementar */ 
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }
        for (Musica musica : musicas) {
             System.out.printf("Título: %s, Artista: %s, Duração: %d segundos, Gênero: %s%n",
                    musica.titulo, musica.artista, musica.duracao, musica.genero);
        }
    }

    public static void buscar(String campo) {
        System.out.printf("Digite o %s da música que deseja buscar: ", campo);
        String busca = sc.nextLine().toLowerCase();
        boolean encontrado = false;
        for (Musica musica : musicas) {
            String valorCampo = "";
            switch (campo.toLowerCase()) {
                case "título": valorCampo = musica.titulo.toLowerCase(); break;
                case "artista": valorCampo = musica.artista.toLowerCase(); break;
                case "gênero": valorCampo = musica.genero.toLowerCase(); break;
            }
            if (valorCampo.contains(busca)) {
                System.out.printf("Título: %s, Artista: %s, Duração: %d segundos, Gênero: %s%n",
                        musica.titulo, musica.artista, musica.duracao, musica.genero);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Música não encontrada.");
        }

    }

    public static void adicionarPlaylist() {
        System.out.print("Digite o nome da playlist: ");
        String nome = sc.nextLine();
        playlists.add(new Playlist(nome));
        System.out.println("Playlist adicionada com sucesso!");
    }

    public static void adicionarMusicaAPlaylist() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist cadastrada.");
            return;
        }
        System.out.print("Digite o nome da playlist: ");
        String nomePlaylist = sc.nextLine();
        Playlist playlist = null;
        for (Playlist p : playlists) {
            if (p.nome.equalsIgnoreCase(nomePlaylist)) {
                playlist = p;
                break;
            }
        }
        if (playlist == null) {
            System.out.println("Playlist não encontrada.");
            return;
        }
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }
        System.out.print("Digite o título da música que deseja adicionar: ");
        String tituloMusica = sc.nextLine();
        Musica musica = null;
        for (Musica m : musicas) {
            if (m.titulo.equalsIgnoreCase(tituloMusica)) {
                musica = m;
                break;
            }
        }
        if (musica == null) {
            System.out.println("Música não encontrada.");
            return;
        }
        playlist.musicas.add(musica);
        System.out.println("Música adicionada à playlist com sucesso!");
    }

    public static void mostrarEstatisticas() { /* implementar */
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }
        int totalMusicas = musicas.size();
        int duracaoTotal = musicas.stream().mapToInt(m -> m.duracao).sum();
        double duracaoMedia = (double) duracaoTotal / totalMusicas;
        System.out.printf("Total de músicas: %d%n", totalMusicas);
        System.out.printf("Duração total: %d segundos%n", duracaoTotal);
        System.out.printf("Duração média: %.2f segundos%n", duracaoMedia);
    }
}
