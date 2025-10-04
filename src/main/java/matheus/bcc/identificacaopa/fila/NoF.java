package matheus.bcc.identificacaopa.fila;

public class NoF {
    char info;
    int grau;
    NoF prox;

    public NoF(char info, int grau) {
        this.info = info;
        this.grau = grau;
    }

    public char getInfo() {
        return info;
    }

    public void setInfo(char info) {
        this.info = info;
    }

    public int getGrau() {
        return grau;
    }

    public void setGrau(int grau) {
        this.grau = grau;
    }

    public NoF getProx() {
        return prox;
    }

    public void setProx(NoF prox) {
        this.prox = prox;
    }
}
