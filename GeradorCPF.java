import java.util.Random;

class GeradorCPF {
    private int[] cpf = new int[11]; 
    
    public GeradorCPF () {
        Random random = new Random();
        
        // 1. Gera os 9 primeiros dígitos aleatórios
        for (int i = 0; i < 9; i++) {
            cpf[i] = random.nextInt(10);
        }
        
        // 2. Calcula o 10º dígito (Primeiro Verificador)
        cpf[9] = calcularDigito(cpf, 10);

        // 3. Calcula o 11º dígito (Segundo Verificador)
        cpf[10] = calcularDigito(cpf, 11);
    }
    
    private int calcularDigito(int[] num, int pesoMax) {
        int soma = 0;
        int peso = pesoMax;
        
        // Multiplica cada dígito pelo seu peso decrescente
        for (int i = 0; i < (pesoMax - 1); i++) {
            soma += num[i] * peso--;
        }
        
        int resto = soma % 11;
        
        // Regra do CPF: se o resto for menor que 2, o dígito é 0
        // Caso contrário, é 11 menos o resto.
        return (resto < 2) ? 0 : (11 - resto);
    }
    
    // Retorna o CPF formatado: XXX.XXX.XXX-XX
    public String getCpf() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            sb.append(cpf[i]);
            if (i == 2 || i == 5) sb.append(".");
            if (i == 8) sb.append("-");
        }
        return sb.toString();
    }
}
   