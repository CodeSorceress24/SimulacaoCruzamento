package view;

public class CruzamentoView {
    
    public void mostrarCabecalho() {
        System.out.println("SIMULACAO DE CRUZAMENTO");
        System.out.println("=======================");
        System.out.println("Cada carro deve aguardar sua vez para cruzar");
        System.out.println("Apenas um sentido por vez no cruzamento");
        System.out.println("=======================");
        System.out.println();
    }
    
    public void mostrarInicio(long threadId, String sentido) {
        System.out.println("Carro " + threadId + " (" + sentido + ") chegou ao cruzamento");
    }
    
    public void mostrarPassagem(long threadId, String sentido) {
        System.out.println(">>> Carro " + threadId + " passando no sentido " + sentido + " <<<");
    }
    
    public void mostrarFinalizacao(long threadId) {
        System.out.println("Carro " + threadId + " finalizou seu percurso");
    }
    
    public void mostrarInterrupcao(long threadId) {
        System.out.println("Carro " + threadId + " foi interrompido");
    }
    
    public void mostrarEstatisticas() {
        System.out.println();
        System.out.println("=== ESTATISTICAS ===");
        System.out.println("Simulacao concluida com sucesso");
    }
}