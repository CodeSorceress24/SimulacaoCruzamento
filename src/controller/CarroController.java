package controller;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import view.CruzamentoView;

public class CarroController implements Runnable {
    private static String sentidoAtual = null;
    private static final Lock lock = new ReentrantLock(true);
    private static final CruzamentoView view = new CruzamentoView();
    
    private final String sentido;
    private final long threadId;
    private boolean executando = true;

    public CarroController(String sentido) {
        this.sentido = sentido;
        this.threadId = Thread.currentThread().getId();
    }

    @Override
    public void run() {
        view.mostrarInicio(threadId, sentido);
        
        while (executando) {
            try {
                if (tentarPassarCruzamento()) {
                    break;
                }
                Thread.sleep(200);
            } catch (InterruptedException e) {
                view.mostrarInterrupcao(threadId);
                Thread.currentThread().interrupt();
                break;
            }
        }
        
        view.mostrarFinalizacao(threadId);
    }

    private boolean tentarPassarCruzamento() throws InterruptedException {
        if (lock.tryLock()) {
            try {
                if (sentidoAtual == null || sentidoAtual.equals(sentido)) {
                    sentidoAtual = sentido;
                    view.mostrarPassagem(threadId, sentido);
                    Thread.sleep(1500);
                    sentidoAtual = null;
                    return true;
                }
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

    public void pararExecucao() {
        this.executando = false;
    }
}