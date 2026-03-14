import java.util.Scanner;
import java.util.Scanner;

public class ControleEstoque {
  // Arrays para armazenar os dados dos produtos
  private static String[] nomesProdutos = new String[100];
  private static double[] precosProdutos = new double[100];
  private static int[] quantidadesProdutos = new int[100];
  private static int totalProdutos = 0;
  static Scanner sc = new Scanner(System.in);
 
  public static void main(String[] args) {
    int opcao;
      do {
          exibirMenu();
          opcao = lerOpcao();
          processarOpcao(opcao);
      } while (opcao != 4);
  }

      public static int lerOpcao() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void processarOpcao(int opcao) {
        Scanner scanner = null;
        switch (opcao) {
            case 1: cadastrarProduto(); break;
            case 2: consultarEstoque(); break;
            case 3: registrarVenda(); break;
            case 4: System.out.println("Saindo..."); break;
            default: System.out.println("Opção inválida. Tente novamente.");
        }
    }
 
  private static void exibirMenu() {
    System.out.println("Menu:");
    System.out.println("1. Cadastrar produto");
    System.out.println("2. Consultar estoque");
    System.out.println("3. Registrar venda");
    System.out.println("4. Sair");
  }
 
  private static void cadastrarProduto() {
    if (totalProdutos >= 100) {
      System.out.println("Limite de produtos atingido.");
      return;
    } else {
      System.out.print("Digite o nome do produto: ");
      nomesProdutos[totalProdutos] = sc.nextLine();
      System.out.print("Digite o preço do produto: ");
      precosProdutos[totalProdutos] = Double.parseDouble(sc.nextLine());
      System.out.print("Digite a quantidade em estoque: ");
      quantidadesProdutos[totalProdutos] = Integer.parseInt(sc.nextLine());
      totalProdutos++;
      System.out.println("Produto cadastrado com sucesso!");}
  }
 
  private static void consultarEstoque() {
    System.out.println("Estoque atual:");
    for (int i = 0; i < totalProdutos; i++) {
      System.out.println("Produto: " + nomesProdutos[i] + " | Preço: R$" + precosProdutos[i] + " | Quantidade: " + quantidadesProdutos[i]);
    }
  }
 
  private static void registrarVenda() {
    System.out.print("Digite o nome do produto vendido: ");
    String nomeProduto = sc.nextLine().toLowerCase();
    int indiceProduto = -1;
    for (int i = 0; i < totalProdutos; i++) {
      if (nomesProdutos[i].equalsIgnoreCase(nomeProduto)) {
        indiceProduto = i;
        break;
      }
    }
    if (indiceProduto == -1) {
      System.out.println("Produto não encontrado.");
      return;
    }
    System.out.print("Digite a quantidade vendida: ");
    int quantidadeVendida = Integer.parseInt(sc.nextLine());
    if (quantidadeVendida > quantidadesProdutos[indiceProduto]) {
      System.out.println("Quantidade insuficiente em estoque.");
      return;
    }
    quantidadesProdutos[indiceProduto] -= quantidadeVendida;
    double valorVenda = quantidadeVendida * precosProdutos[indiceProduto];
    System.out.println("Venda registrada! Valor total: R$" + valorVenda);
  }
}