import java.util.ArrayList;
import java.util.Scanner;
import Classes.Playlist;
import Classes.Musica;

public class StreamingMusic {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Playlist> playlists = new ArrayList<>();
    static ArrayList<Musica> musicas = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 10);
    }

    static void exibirMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Adicionar música");
        System.out.println("2. Listar músicas");
        System.out.println("3. Buscar por título");
        System.out.println("4. Buscar por artista");
        System.out.println("5. Buscar por gênero");
        System.out.println("6. Ver estatísticas");
        System.out.println("7. Adicionar playlist");
        System.out.println("8. Adicionar música a playlist");
        System.out.println("9. Remover música da playlist");
        System.out.println("10. Sair");
        System.out.print("Escolha uma opção: ");
    }

    static int lerOpcao() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static int lerDuracao() {
        try {
            int duracao = Integer.parseInt(sc.nextLine());
            return duracao;
        } catch (NumberFormatException e) {
            System.out.println("Duração inválida. Tente novamente.");
            return lerDuracao();
        }
    }

    static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: adicionarMusica(); break;
            case 2: listarMusicas(); break;
            case 3: buscar("título"); break;
            case 4: buscar("artista"); break;
            case 5: buscar("gênero"); break;
            case 6: mostrarEstatisticas(); break;
            case 7: adicionarPlaylist(); break;
            case 8: adicionarMusicaAPlaylist(); break;
            case 9: removerMusicaAPlaylist(); break;
            case 10: System.out.println("Saindo..."); break;
            default: System.out.println("Opção inválida. Tente novamente.");
        }
    }

    static void adicionarMusica() {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Artista: ");
        String artista = sc.nextLine();
        System.out.print("Duração (em segundos): ");
        int duracao = lerDuracao();
        System.out.print("Gênero: ");
        String genero = sc.nextLine();

        // Cria o objeto e preenche seus atributos diretamente
        Musica novaMusica = new Classes.Musica(titulo, artista, duracao, genero);
        musicas.add(novaMusica);

        System.out.println("Música adicionada com sucesso!");
    }

    static void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }
        for (Musica musica : musicas) {
            System.out.printf("Título: %s | Artista: %s | Duração: %s | Gênero: %s%n", musica.getTitulo(), musica.getArtista(), formatarSegundos(musica.getDuracao()) , musica.getGenero());
        }
    }

    static void buscar(String campo) {
        System.out.printf("Digite o %s para buscar: ", campo);
        String busca = sc.nextLine().toLowerCase();
        boolean encontrado = false;

        for (Musica musica : musicas) {
            String valorCampo = "";
            switch (campo) {
                case "título":  valorCampo = musica.getTitulo().toLowerCase();  break;
                case "artista": valorCampo = musica.getArtista().toLowerCase(); break;
                case "gênero":  valorCampo = musica.getGenero().toLowerCase();  break;
            }
            if (valorCampo.contains(busca)) {
                System.out.printf("Título: %s | Artista: %s | Duração: %s | Gênero: %s%n",
                        musica.getTitulo(), musica.getArtista(), formatarSegundos(musica.getDuracao()) , musica.getGenero());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhuma música encontrada.");
        }
    }

    static void adicionarPlaylist() {
        System.out.print("Nome da playlist: ");
        String nome = sc.nextLine();

        Playlist novaPlaylist = new Playlist(nome);
        playlists.add(novaPlaylist);

        System.out.println("Playlist criada com sucesso!");
    }

    static void adicionarMusicaAPlaylist() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist cadastrada.");
        } else {
            for (Playlist playlist : playlists) {
                System.out.println("Indice: " + playlists.indexOf(playlist) + " - " + playlist.getNome());
            }
            System.out.print("Escolha uma playlist: ");
            int indicePlaylist = Integer.parseInt(sc.nextLine());
            if (indicePlaylist >= 0 && indicePlaylist < playlists.size()) {
                Playlist playlistSelecionada = playlists.get(indicePlaylist);
                for (Musica musica : musicas) {
                    System.out.println("Indice: " + musicas.indexOf(musica) + " - " + musica.getTitulo());
                }
                System.out.print("Escolha uma música: ");
                int indiceMusica = Integer.parseInt(sc.nextLine());
                if (indiceMusica >= 0 && indiceMusica < musicas.size()) {
                    Musica musicaSelecionada = musicas.get(indiceMusica);
                    playlistSelecionada.addMusica(musicaSelecionada);
                    System.out.println("Música adicionada à playlist com sucesso!");
                }
            }
        }
    }

    static void removerMusicaAPlaylist() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist cadastrada.");
        } else {
            for (Playlist playlist : playlists) {
                System.out.println("Indice: " + playlists.indexOf(playlist) + " - " + playlist.getNome());
            }
            System.out.print("Escolha uma playlist: ");
            int indicePlaylist = Integer.parseInt(sc.nextLine());
            if (indicePlaylist >= 0 && indicePlaylist < playlists.size()) {
                Playlist playlistSelecionada = playlists.get(indicePlaylist);
                for (Musica musica : musicas) {
                    System.out.println("Indice: " + musicas.indexOf(musica) + " - " + musica.getTitulo());
                }
                System.out.print("Escolha uma música: ");
                int indiceMusica = Integer.parseInt(sc.nextLine());
                if (indiceMusica >= 0 && indiceMusica < musicas.size()) {
                    Musica musicaSelecionada = musicas.get(indiceMusica);
                    playlistSelecionada.removeMusica(musicaSelecionada);
                    System.out.println("Música adicionada à playlist com sucesso!");
                }
            }
        }
    }

    static String formatarSegundos(int segundos) {
    int min = segundos / 60;
    int seg = segundos % 60;
    return String.format("%02d:%02d", min, seg);
}

    static void mostrarEstatisticas() {
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        int totalMusicas = musicas.size();
        int duracaoTotal = musicas.stream().mapToInt(Musica::getDuracao).sum();
        double duracaoMedia = (double) duracaoTotal / totalMusicas;

        System.out.printf("Total de músicas: %d%n", totalMusicas);
        System.out.printf("Duração total: %s%n", formatarSegundos(duracaoTotal));
        System.out.printf("Duração média: %s%n", formatarSegundos((int) duracaoMedia));
    }
}