import java.util.ArrayList;

class Gerenciador {
    private ArrayList<String> listaCpfs = new ArrayList<>();
    private final int LIMITE = 45;

    // Adiciona um CPF se houver espaço
    public boolean adicionar(String cpf) {
        if (listaCpfs.size() < LIMITE) {
            listaCpfs.add(cpf);
            return true;
        }
        return false;
    }

    // Tenta remover um CPF (usado na subtração)
    public boolean remover(String cpf) {
        return listaCpfs.remove(cpf);
    }

    // Retorna a lista para exibição
    public ArrayList<String> getLista() {
        return listaCpfs;
    }

    public int totalAtivo() {
        return listaCpfs.size();
    }

    public int getLimite() {
        return LIMITE;
    }

    public boolean estaVazia() {
        return listaCpfs.isEmpty();
    }
}