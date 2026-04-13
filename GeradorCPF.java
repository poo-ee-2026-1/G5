import java.util.Random;

class GeradorCPF {
    private String cpfFormatado;

    public GeradorCPF() {
        int[] cpf = new int[11];
        Random random = new Random();
        for (int i = 0; i < 9; i++) cpf[i] = random.nextInt(10);
        cpf[9] = calcularDigito(cpf, 10);
        cpf[10] = calcularDigito(cpf, 11);
        this.cpfFormatado = formatar(cpf);
    }

    private int calcularDigito(int[] num, int pesoMax) {
        int soma = 0, peso = pesoMax;
        for (int i = 0; i < (pesoMax - 1); i++) soma += num[i] * peso--;
        int resto = soma % 11;
        return (resto < 2) ? 0 : (11 - resto);
    }

    private String formatar(int[] num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            sb.append(num[i]);
            if (i == 2 || i == 5) sb.append(".");
            if (i == 8) sb.append("-");
        }
        return sb.toString();
    }

    public String getCpfFormatado() { 
        return cpfFormatado; 
    }
}