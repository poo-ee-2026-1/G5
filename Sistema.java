import java.util.Scanner;
import java.util.ArrayList;

public class Sistema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int contador = 0;
        ArrayList<String> cpfsAutorizados = new ArrayList<>();
        
        // Definimos o limite máximo aqui
        final int LIMITE_MAXIMO = 45;

        while (true) {
            System.out.println("\nVALOR ATUAL: " + contador);
            System.out.println("ESPAÇOS OCUPADOS: " + cpfsAutorizados.size() + "/" + LIMITE_MAXIMO);
            System.out.println("CPFs NA LISTA: " + (cpfsAutorizados.isEmpty() ? "[VAZIA]" : cpfsAutorizados));
            System.out.println("1 - Somar 1 (Gera novo CPF)");
            System.out.println("2 - Subtrair 1 (Usa CPF da lista)");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    // Verificação de limite
                    if (cpfsAutorizados.size() >= LIMITE_MAXIMO) {
                        System.out.println(">>> ERRO: Limite de " + LIMITE_MAXIMO + " CPFs atingido! Remova um para continuar.");
                    } else {
                        GeradorCPF novo = new GeradorCPF();
                        String novoCpf = novo.getCpf();
                        System.out.println("\nNOVO CPF GERADO: " + novoCpf);
                        System.out.print("Confirme o CPF para somar: ");
                        System.out.println("CPF deve ser digitado na forma XXX.XXX.XXX-XX");
                        if (scanner.nextLine().equals(novoCpf)) {
                            contador++;
                            cpfsAutorizados.add(novoCpf);
                            System.out.println(">>> SOMA REALIZADA!");
                        } else {
                            System.out.println(">>> ERRO: CPF incorreto.");
                        }
                    }
                    break;

                case 2:
                    if (cpfsAutorizados.isEmpty()) {
                        System.out.println(">>> ERRO: Nenhum CPF disponível para subtrair.");
                    } else {
                        System.out.print("Digite um CPF da lista para usar: ");
                        System.out.println("CPF deve ser digitado na forma XXX.XXX.XXX-XX");
                        String tentativa = scanner.nextLine();
                        
                        // O método .remove() retorna 'true' se encontrou e removeu o item
                        if (cpfsAutorizados.remove(tentativa)) {
                            contador--;
                            System.out.println(">>> SUBTRAÇÃO REALIZADA! Chave utilizada e descartada.");
                        } else {
                            System.out.println(">>> ACESSO NEGADO: Chave inválida ou já utilizada.");
                        }
                    }
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}