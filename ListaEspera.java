import java.util.LinkedList;
import java.util.Queue;

class ListaEspera {
    private Queue<String> fila = new LinkedList<>();
    
    public void entrar(String cpf) {
        fila.add(cpf); 
    }
    
    public String sair() {
        return fila.poll(); 
    }
    
    public boolean temAlguem() { 
        return !fila.isEmpty(); 
    }
    
    public int tamanho() {
        return fila.size(); 
    }
    
    public Queue<String> getFila() { 
        return fila; 
    }
}