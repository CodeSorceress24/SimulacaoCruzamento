import controller.CarroController;
import view.CruzamentoView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        CruzamentoView view = new CruzamentoView();
        view.mostrarCabecalho();
        
        String[] sentidos = {"Norte", "Leste", "Sul", "Oeste"};
        ExecutorService executor = Executors.newFixedThreadPool(4);
        
        for (String sentido : sentidos) {
            executor.execute(new CarroController(sentido));
        }
        
        executor.shutdown();
        
        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("Tempo limite excedido. Forcando finalizacao...");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        
        view.mostrarEstatisticas();
    }
}