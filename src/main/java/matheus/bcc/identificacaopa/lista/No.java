package matheus.bcc.identificacaopa.lista;

public class No {
    private char info;
    private boolean visitado;
    private No prox;
    private int cor;

    public No(char info) {
        this.info = info;
        this.visitado = false;
        this.prox = null;
        this.cor = 0;
    }

    public char getInfo() {
        return info;
    }

    public void setInfo(char info) {
        this.info = info;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
}
