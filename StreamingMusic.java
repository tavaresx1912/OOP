import java.util.ArrayList;
import java.util.Scanner;

import Classes.Musica;
import Classes.Playlist;
import Classes.PlaylistAutomatica;
import Classes.Usuario;
import Classes.UsuarioFree;
import Classes.UsuarioPremium;

// Sistema principal — Checkpoint 5 (polimorfismo, abstração, casting).
//
// Pontos demonstrados:
//   • ArrayList<Usuario> polimórfico (Free e Premium juntos)
//   • Login que retorna o Usuario base e usa instanceof / casting
//   • Hierarquia de Playlist (Playlist e PlaylistAutomatica)
//   • Métodos sobrescritos com @Override
//   • Estatísticas separadas por tipo de usuário
public class StreamingMusic {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Musica> catalogo = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>(); // ArrayList polimórfico
    static Usuario logado;

    public static void main(String[] args) {
        System.out.println("=== Sistema de Streaming - CP5 ===");
        carregarDadosExemplo();

        boolean rodando = true;
        while (rodando) {
            if (logado == null) {
                exibirMenuInicial();
                int op = lerInt();
                if (op == 0) rodando = false;
                else processarMenuInicial(op);
            } else {
                exibirMenuLogado();
                int op = lerInt();
                processarMenuLogado(op);
            }
        }
        System.out.println("Encerrando...");
    }

    // --------------------- MENU INICIAL ---------------------
    static void exibirMenuInicial() {
        System.out.println("\n=== Início ===");
        System.out.println("1. Cadastrar usuário");
        System.out.println("2. Login");
        System.out.println("3. Listar usuários cadastrados");
        System.out.println("4. Estatísticas globais");
        System.out.println("0. Sair");
        System.out.print("Opção: ");
    }

    static void processarMenuInicial(int op) {
        switch (op) {
            case 1: cadastrarUsuario(); break;
            case 2: login(); break;
            case 3: listarUsuarios(); break;
            case 4: estatisticasGlobais(); break;
            default: System.out.println("Opção inválida.");
        }
    }

    // --------------------- MENU DO USUÁRIO LOGADO ---------------------
    static void exibirMenuLogado() {
        System.out.println("\n=== Menu (" + logado.getNome() + " - " + logado.getTipo() + ") ===");
        System.out.println("1. Adicionar música ao catálogo");
        System.out.println("2. Listar catálogo");
        System.out.println("3. Criar playlist manual");
        System.out.println("4. Criar playlist automática");
        System.out.println("5. Listar minhas playlists");
        System.out.println("6. Adicionar música a playlist (manual)");
        System.out.println("7. Regerar playlists automáticas");
        System.out.println("8. Ouvir música");
        System.out.println("9. Ver meus dados");
        if (logado.podeBaixar()) System.out.println("10. Baixar música");
        System.out.println("0. Logout");
        System.out.print("Opção: ");
    }

    static void processarMenuLogado(int op) {
        switch (op) {
            case 1: adicionarMusica(); break;
            case 2: listarCatalogo(); break;
            case 3: criarPlaylistManual(); break;
            case 4: criarPlaylistAutomatica(); break;
            case 5: listarMinhasPlaylists(); break;
            case 6: adicionarMusicaPlaylist(); break;
            case 7: regerarAutomaticas(); break;
            case 8: ouvirMusica(); break;
            case 9: logado.exibirInfo(); break;
            case 10:
                if (logado.podeBaixar()) baixarMusica();
                else System.out.println("Sem permissão para download.");
                break;
            case 0: System.out.println("Logout."); logado = null; break;
            default: System.out.println("Opção inválida.");
        }
    }

    // --------------------- CADASTRO E LOGIN ---------------------
    static void cadastrarUsuario() {
        try {
            System.out.print("Nome: ");  String nome = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("Senha: "); String senha = sc.nextLine();
            System.out.print("Plano (1-Free / 2-Premium): ");
            int t = lerInt();

            Usuario novo;
            if (t == 2) {
                System.out.print("Mensalidade: ");
                double m = Double.parseDouble(sc.nextLine());
                novo = new UsuarioPremium(nome, email, senha, m);
            } else {
                novo = new UsuarioFree(nome, email, senha);
            }
            usuarios.add(novo); // upcasting implícito — Usuario base
            System.out.println("Cadastrado: " + novo.getNome() + " (" + novo.getTipo() + ")");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void login() {
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Senha: "); String senha = sc.nextLine();
        for (Usuario u : usuarios) { // iteração polimórfica
            if (u.autenticar(email, senha)) {
                logado = u;
                System.out.println("Login OK — bem-vindo " + u.getNome());

                // Demonstração de instanceof + downcasting
                if (logado instanceof UsuarioPremium) {
                    UsuarioPremium p = (UsuarioPremium) logado;
                    System.out.println("Mensalidade ativa: R$ "
                            + String.format("%.2f", p.getMensalidade()));
                } else if (logado instanceof UsuarioFree) {
                    ((UsuarioFree) logado).mostrarAnuncio();
                }
                return;
            }
        }
        System.out.println("Credenciais inválidas.");
    }

    static void listarUsuarios() {
        if (usuarios.isEmpty()) { System.out.println("Nenhum usuário."); return; }
        for (Usuario u : usuarios) {
            System.out.println("• " + u.getNome() + " <" + u.getEmail() + "> ["
                    + u.getTipo() + "]");
        }
    }

    // Estatísticas separadas por tipo (instanceof + casting)
    static void estatisticasGlobais() {
        int free = 0, premium = 0;
        double receita = 0;
        int ouvidasFree = 0;
        int downloadsPremium = 0;

        for (Usuario u : usuarios) {
            if (u instanceof UsuarioFree) {
                free++;
                ouvidasFree += ((UsuarioFree) u).getMusicasOuvidas();
            } else if (u instanceof UsuarioPremium) {
                premium++;
                UsuarioPremium p = (UsuarioPremium) u;
                receita += p.getMensalidade();
                downloadsPremium += p.getDownloads().size();
            }
        }

        System.out.println("\n--- Estatísticas globais ---");
        System.out.println("Total de usuários: " + usuarios.size());
        System.out.println("Free:    " + free + " | músicas ouvidas: " + ouvidasFree);
        System.out.println("Premium: " + premium + " | receita: R$ "
                + String.format("%.2f", receita)
                + " | downloads: " + downloadsPremium);
        System.out.println("Catálogo: " + catalogo.size() + " músicas");
    }

    // --------------------- CATÁLOGO ---------------------
    static void adicionarMusica() {
        try {
            System.out.print("Título: ");  String t = sc.nextLine();
            System.out.print("Artista: "); String a = sc.nextLine();
            System.out.print("Duração (s): "); int d = lerInt();
            System.out.print("Gênero: "); String g = sc.nextLine();
            catalogo.add(new Musica(t, a, d, g));
            System.out.println("Música adicionada.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void listarCatalogo() {
        if (catalogo.isEmpty()) { System.out.println("Catálogo vazio."); return; }
        for (int i = 0; i < catalogo.size(); i++)
            System.out.println(i + ". " + catalogo.get(i));
    }

    // --------------------- PLAYLISTS ---------------------
    static void criarPlaylistManual() {
        System.out.print("Nome: "); String nome = sc.nextLine();
        try {
            logado.adicionarPlaylist(new Playlist(nome));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void criarPlaylistAutomatica() {
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("Critério (1-Gênero / 2-Artista): "); int c = lerInt();
        System.out.print("Valor: "); String v = sc.nextLine();
        PlaylistAutomatica.Criterio crit = (c == 2)
                ? PlaylistAutomatica.Criterio.ARTISTA
                : PlaylistAutomatica.Criterio.GENERO;
        try {
            PlaylistAutomatica pa = new PlaylistAutomatica(nome, crit, v);
            pa.gerarMusicas(catalogo); // gera já com o catálogo atual
            if (logado.adicionarPlaylist(pa)) {
                System.out.println("Playlist automática criada com "
                        + pa.getTamanho() + " músicas.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void listarMinhasPlaylists() {
        ArrayList<Playlist> pls = logado.getPlaylists();
        if (pls.isEmpty()) { System.out.println("Sem playlists."); return; }
        for (Playlist p : pls) { // ArrayList polimórfico de Playlists
            System.out.println("» " + p);
            for (Musica m : p.getMusicas()) System.out.println("   - " + m);
        }
    }

    static void adicionarMusicaPlaylist() {
        ArrayList<Playlist> pls = logado.getPlaylists();
        if (pls.isEmpty()) { System.out.println("Sem playlists."); return; }
        if (catalogo.isEmpty()) { System.out.println("Catálogo vazio."); return; }

        for (int i = 0; i < pls.size(); i++) System.out.println(i + ". " + pls.get(i));
        System.out.print("Playlist: "); int ip = lerInt();
        if (ip < 0 || ip >= pls.size()) { System.out.println("Inválido."); return; }

        Playlist p = pls.get(ip);
        // Polimorfismo: addMusica em PlaylistAutomatica está sobrescrita
        // para impedir adição manual.
        if (p instanceof PlaylistAutomatica) {
            System.out.println("Esta é uma playlist automática — use a opção 7 para regerar.");
            return;
        }
        listarCatalogo();
        System.out.print("Música: "); int im = lerInt();
        if (im < 0 || im >= catalogo.size()) { System.out.println("Inválido."); return; }
        p.addMusica(catalogo.get(im));
        System.out.println("Adicionada.");
    }

    static void regerarAutomaticas() {
        int n = 0;
        for (Playlist p : logado.getPlaylists()) {
            if (p instanceof PlaylistAutomatica) {
                p.gerarMusicas(catalogo);
                n++;
            }
        }
        System.out.println(n + " playlist(s) automática(s) regerada(s).");
    }

    // --------------------- AÇÕES SOBRE MÚSICA ---------------------
    static void ouvirMusica() {
        if (catalogo.isEmpty()) { System.out.println("Catálogo vazio."); return; }
        listarCatalogo();
        System.out.print("Música: "); int im = lerInt();
        if (im < 0 || im >= catalogo.size()) { System.out.println("Inválido."); return; }
        // Chamada polimórfica — despacho dinâmico para Free ou Premium
        logado.ouvirMusica(catalogo.get(im));
    }

    static void baixarMusica() {
        if (catalogo.isEmpty()) { System.out.println("Catálogo vazio."); return; }
        listarCatalogo();
        System.out.print("Música: "); int im = lerInt();
        if (im < 0 || im >= catalogo.size()) { System.out.println("Inválido."); return; }
        // Downcast seguro: já checamos podeBaixar() antes de chegar aqui.
        ((UsuarioPremium) logado).baixarMusica(catalogo.get(im));
    }

    // --------------------- HELPERS / DADOS DEMO ---------------------
    static int lerInt() {
        try { return Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { return -1; }
    }

    static void carregarDadosExemplo() {
        try {
            catalogo.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
            catalogo.add(new Musica("Take Five", "Dave Brubeck", 324, "Jazz"));
            catalogo.add(new Musica("Lose Yourself", "Eminem", 326, "Hip-Hop"));
            catalogo.add(new Musica("Smells Like Teen Spirit", "Nirvana", 301, "Rock"));
            catalogo.add(new Musica("Strobe", "Deadmau5", 634, "Eletrônica"));

            usuarios.add(new UsuarioFree("Ana", "ana@mail.com", "123"));
            usuarios.add(new UsuarioPremium("Bruno", "bruno@mail.com", "123", 19.90));
        } catch (IllegalArgumentException ignored) {}
    }
}
