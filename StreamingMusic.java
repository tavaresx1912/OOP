import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusic {

    static ArrayList<String> Titulo = new ArrayList<>();
    static ArrayList<String> Artista = new ArrayList<>();
    static ArrayList<Integer> Duracao = new ArrayList<>();
    static ArrayList<String> Genero = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 3);
    }

    public static void exibirMenu() {
        System.out.println("Menu:");
        System.out.println("1. Adicionar música");
        System.out.println("2. Listar músicas");
        System.out.println("3. Buscar por Título");
        System.out.println("4. Sair");
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
            case 3: buscarPorTitulo(); break;
            case 4: System.out.println("Saindo..."); break;
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
        Titulo.add(titulo);
        Artista.add(artista);
        FormatarDuracao(duracao);
        Duracao.add(duracao);
        Genero.add(genero);
        System.out.println("Música adicionada com sucesso!");
    }

    public static void FormatarDuracao(int duracao) {
        int minutos = duracao / 60;
        int segundos = duracao % 60;
        System.out.printf("%02d:%02d%n", minutos, segundos);
    }

    public static void listarMusicas() { /* implementar */ 
        if (Titulo.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }
        for (int i = 0; i < Titulo.size(); i++) {
            System.out.printf("Título: %s, Artista: %s, Duração: %d segundos, Gênero: %s%n",
                    Titulo.get(i), Artista.get(i), Duracao.get(i), Genero.get(i));
        }
    }

    public static void buscarPorTitulo() { /* implementar */
        System.out.print("Digite o título da música que deseja buscar: ");
        String tituloBusca = sc.nextLine();
        boolean encontrado = false;
        for (int i = 0; i < Titulo.size(); i++) {
            if (Titulo.get(i).contains(tituloBusca)) {
                System.out.printf("Título: %s, Artista: %s, Duração: %d segundos, Gênero: %s%n",
                        Titulo.get(i), Artista.get(i), Duracao.get(i), Genero.get(i));
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Música não encontrada.");
        }
    }
}
