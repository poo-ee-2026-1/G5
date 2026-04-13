public class ValidadorCPF {
    
    public static boolean Valido(String cpf) {
        // Remove pontos e traços para facilitar o cálculo
        cpf = cpf.replaceAll("\\D", "");

        // Um CPF deve ter 11 dígitos e não pode ser uma sequência repetida (ex: 111.111...)
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int[] num = new int[11];
            for (int i = 0; i < 11; i++) {
                num[i] = Character.getNumericValue(cpf.charAt(i));
            }

            // Calcula o primeiro dígito verificador
            int d1 = calcularDigito(num, 10);
            // Calcula o segundo dígito verificador
            int d2 = calcularDigito(num, 11);

            // Verifica se os dígitos calculados batem com os digitados
            return (num[9] == d1 && num[10] == d2);
        } catch (Exception e) {
            return false;
        }
    }

    private static int calcularDigito(int[] num, int pesoMax) {
        int soma = 0, peso = pesoMax;
        for (int i = 0; i < (pesoMax - 1); i++) {
            soma += num[i] * peso--;
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : (11 - resto);
    }
}