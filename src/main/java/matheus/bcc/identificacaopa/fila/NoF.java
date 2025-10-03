package matheus.bcc.identificacaopa.fila;

public class NoF {
    char info;
    NoF prox;

    public NoF(char info) {
        this.info = info;
    }

    public char getInfo() {
        return info;
    }

    public void setInfo(char info) {
        this.info = info;
    }

    public NoF getProx() {
        return prox;
    }

    public void setProx(NoF prox) {
        this.prox = prox;
    }
}
