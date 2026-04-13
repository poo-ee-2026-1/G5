import java.util.ArrayList;

class ListaNegra {
    private ArrayList<String> bloqueados = new ArrayList<>();
    
    public ListaNegra() {
        // CPFs bloqueados nativos (já iniciam com o sistema)
        bloquear("111.111.111-11");
        bloquear("222.222.222-22");
        bloquear("333.333.333-33");
        bloquear("000.000.000-00");
    }
    
    public void bloquear(String cpf) { 
        if (!bloqueados.contains(cpf)) bloqueados.add(cpf); 
    }
    
    public boolean estaBloqueado(String cpf) {
        return bloqueados.contains(cpf); 
    }
    
    public ArrayList<String> getLista() {
        return bloqueados; 
    }
}