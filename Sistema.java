import java.util.Scanner;

public class Sistema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorAtivo gerenciador = new GerenciadorAtivo();
        ListaNegra blacklist = new ListaNegra();
        ListaEspera filaEspera = new ListaEspera();
        int contador = 0;

        while (true) {
            System.out.println("\n========================================");
            System.out.println(" CONTADOR: " + contador);
            System.out.println(" ATIVOS: " + gerenciador.total() + "/" + gerenciador.getLimite());
            System.out.println(" EM ESPERA: " + filaEspera.tamanho());
            System.out.println("========================================");
            System.out.println("1 - Entrada (Gerar CPF)");
            System.out.println("2 - Entrada (Inserir próprio CPF)");
            System.out.println("3 - Saída (Usar CPF)");
            System.out.println("4 - Ver Listas (Ativos/Espera)");
            System.out.println("5 - Gerenciar Lista Negra");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: // ENTRADA AUTOMÁTICA
                    String gerado = new GeradorCPF().getCpfFormatado();
                    System.out.println("\nCPF GERADO: " + gerado);
                    
                    if (blacklist.estaBloqueado(gerado)) {
                        System.out.println(">>> AVISO: O CPF gerado [" + gerado + "] está BLOQUEADO na Lista Negra!");
                    }
                    
                    System.out.print("Confirme o CPF: ");
                    if (scanner.nextLine().equals(gerado)) {
                        processarEntrada(gerado, gerenciador, filaEspera, blacklist, true);
                        if (!blacklist.estaBloqueado(gerado)) contador++;
                    }
                    break;
                    
                case 2: // ENTRADA MANUAL
                    System.out.print("Seu CPF: ");
                    String manual = scanner.nextLine();
                    
                    if (blacklist.estaBloqueado(manual)) {
                        System.out.println(">>> ALERTA: Acesso Negado! O CPF [" + manual + "] está na Lista Negra!");
                    } else if (ValidadorCPF.Valido(manual)) {
                        if (processarEntrada(manual, gerenciador, filaEspera, blacklist, false)) {
                            // Só aumenta o contador se NÃO foi para a fila de espera agora
                            if (gerenciador.getAtivos().contains(manual)) contador++;
                        }
                    } else { System.out.println(">>> CPF Inválido!"); }
                    break;

                case 3: // SAÍDA E AUTOMAÇÃO DE FILA
                    System.out.print("Digite CPF para sair: ");
                    String cpfSub = scanner.nextLine();
                    if (gerenciador.remover(cpfSub)) {
                        contador--;
                        System.out.println(">>> SAÍDA OK!");
                        
                        // Lógica de Fila: Se alguém sair, o próximo da fila entra
                        if (filaEspera.temAlguem()) {
                            String proximo = filaEspera.sair();
                            gerenciador.adicionar(proximo);
                            contador++; 
                            System.out.println(">>> FILA ANDOU: " + proximo + " agora está ATIVO. (Contador compensado)");
                        }
                    } else { System.out.println(">>> ERRO: CPF não autorizado."); }
                    break;

                case 4: // VER LISTAS
                    System.out.println("\nATIVOS: " + gerenciador.getAtivos());
                    System.out.println("ESPERA: " + filaEspera.getFila());
                    break;

                case 5: // BLACKLIST
                    System.out.println("\n1- Bloquear CPF | 2- Ver Blacklist");
                    int sub = scanner.nextInt(); scanner.nextLine();
                    if (sub == 1) {
                        System.out.print("CPF para bloquear: ");
                        blacklist.bloquear(scanner.nextLine());
                        System.out.println(">>> CPF Bloqueado!");
                    } else { System.out.println("BLACKLIST: " + blacklist.getLista()); }
                    break;
                    
            case 0:
                    System.out.println("Encerrando...");
                    return;
            }
        }
    }

    // Método auxiliar para decidir se vai para Ativos ou Espera
    private static boolean processarEntrada(String cpf, GerenciadorAtivo g, ListaEspera e, ListaNegra b, boolean silencioso) {
        if (b.estaBloqueado(cpf)) {
            System.out.println(">>> BLOQUEADO PELA BLACKLIST!");
            return false;
        }
        if (g.total() < g.getLimite()) {
            g.adicionar(cpf);
            if (!silencioso) System.out.println(">>> Adicionado aos ATIVOS!");
            return true;
        } else {
            e.entrar(cpf);
            System.out.println(">>> LIMITE ATINGIDO! Enviado para LISTA DE ESPERA.");
            return true;
        }
    }
}