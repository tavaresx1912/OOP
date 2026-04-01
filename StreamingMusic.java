import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusic {

    // Lista de todas as músicas e playlists cadastradas
    static ArrayList<Musica> musicas = new ArrayList<>();
    static ArrayList<Playlist> playlists = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Classe que representa uma música
    static class Musica {
        String titulo;
        String artista;
        int duracao; // em segundos
        String genero;
    }

    // Classe que representa uma playlist
    static class Playlist {
        String nome;
        ArrayList<Musica> musicas = new ArrayList<>();
    }

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 9);
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
        System.out.println("9. Sair");
        System.out.print("Escolha uma opção: ");
    }

    static int lerOpcao() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
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
            case 9: System.out.println("Saindo..."); break;
            default: System.out.println("Opção inválida. Tente novamente.");
        }
    }

    static void adicionarMusica() {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Artista: ");
        String artista = sc.nextLine();
        System.out.print("Duração (em segundos): ");
        int duracao = Integer.parseInt(sc.nextLine());
        System.out.print("Gênero: ");
        String genero = sc.nextLine();

        // Cria o objeto e preenche seus atributos diretamente
        Musica novaMusica = new Musica();
        novaMusica.titulo = titulo;
        novaMusica.artista = artista;
        novaMusica.duracao = duracao;
        novaMusica.genero = genero;

        musicas.add(novaMusica);
        System.out.println("Música adicionada com sucesso!");
    }

    // Converte segundos para o formato mm:ss
    static String formatarDuracao(int duracao) {
        int minutos = duracao / 60;
        int segundos = duracao % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    static void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }
        for (Musica musica : musicas) {
            System.out.printf("Título: %s | Artista: %s | Duração: %s | Gênero: %s%n",
                    musica.titulo, musica.artista, formatarDuracao(musica.duracao), musica.genero);
        }
    }

    static void buscar(String campo) {
        System.out.printf("Digite o %s para buscar: ", campo);
        String busca = sc.nextLine().toLowerCase();
        boolean encontrado = false;

        for (Musica musica : musicas) {
            String valorCampo = "";
            switch (campo) {
                case "título":  valorCampo = musica.titulo.toLowerCase();  break;
                case "artista": valorCampo = musica.artista.toLowerCase(); break;
                case "gênero":  valorCampo = musica.genero.toLowerCase();  break;
            }
            if (valorCampo.contains(busca)) {
                System.out.printf("Título: %s | Artista: %s | Duração: %s | Gênero: %s%n",
                        musica.titulo, musica.artista, formatarDuracao(musica.duracao), musica.genero);
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

        Playlist novaPlaylist = new Playlist();
        novaPlaylist.nome = nome;

        playlists.add(novaPlaylist);
        System.out.println("Playlist criada com sucesso!");
    }

    static void adicionarMusicaAPlaylist() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist cadastrada.");
            return;
        }

        System.out.print("Nome da playlist: ");
        String nomePlaylist = sc.nextLine();

        // Procura a playlist pelo nome
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

        System.out.print("Título da música a adicionar: ");
        String tituloMusica = sc.nextLine();

        // Procura a música pelo título
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

    static void mostrarEstatisticas() {
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        int totalMusicas = musicas.size();
        int duracaoTotal = 0;
        for (Musica m : musicas) {
            duracaoTotal += m.duracao;
        }
        double duracaoMedia = (double) duracaoTotal / totalMusicas;

        System.out.printf("Total de músicas: %d%n", totalMusicas);
        System.out.printf("Duração total: %s%n", formatarDuracao(duracaoTotal));
        System.out.printf("Duração média: %s%n", formatarDuracao((int) duracaoMedia));
    }
}
