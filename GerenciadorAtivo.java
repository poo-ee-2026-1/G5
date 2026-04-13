import java.util.ArrayList;

class GerenciadorAtivo {
    private ArrayList<String> ativos = new ArrayList<>();
    private final int LIMITE = 5;

    public boolean adicionar(String cpf) {
        if (ativos.size() < LIMITE) {
            ativos.add(cpf);
            return true;
        }
        return false;
    }

    public boolean remover(String cpf) {
        return ativos.remove(cpf); 
    }
    
    public ArrayList<String> getAtivos() {
        return ativos; 
    }
    
    public int total() {
        return ativos.size(); 
    }
    
    public int getLimite() {
        return LIMITE; 
    }
}