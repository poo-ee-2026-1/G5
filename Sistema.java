import java.util.Scanner;

public class Sistema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gerenciador gerenciador = new Gerenciador();
        int contador = 0;
        
        // Definimos o limite máximo aqui

        while (true) {
            System.out.println("\n[ Valor Atual: " + contador + " ]");
            System.out.println("1 - Entrada (Gerar CPF)");
            System.out.println("2 - Saída (Usar CPF)");
            System.out.println("3 - Visualizar lista de presentes"); // NOVO CASE
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    if (gerenciador.totalAtivo() >= gerenciador.getLimite()) {
                        System.out.println(">>> Erro: Limite atingido!");
                    } else {
                        GeradorCPF novo = new GeradorCPF(); // Usa a classe que criamos antes
                        String cpfGerado = novo.getCpf();
                        System.out.println("\nCPF GERADO: " + cpfGerado);
                        System.out.print("Confirme: ");
                        if (scanner.nextLine().equals(cpfGerado)) {
                            contador++;
                            gerenciador.adicionar(cpfGerado);
                            System.out.println(">>> Entrada efetuada!");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Digite o CPF para subtrair: ");
                    if (gerenciador.remover(scanner.nextLine())) {
                        contador--;
                        System.out.println(">>> Saída efetuada!");
                    } else {
                        System.out.println(">>> Erro: CPF não autorizado ou já usado.");
                    }
                    break;

                case 3: // EXIBIÇÃO DA LISTA
                    System.out.println("\n--- LISTA DE CPFs AUTORIZADOS ---");
                    if (gerenciador.estaVazia()) {
                        System.out.println("Nenhum CPF armazenado no momento.");
                    } else {
                        for (int i = 0; i < gerenciador.getLista().size(); i++) {
                            System.out.println((i + 1) + ". " + gerenciador.getLista().get(i));
                        }
                    }
                    System.out.println("---------------------------------");
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    return;
            }
        }
    }
}