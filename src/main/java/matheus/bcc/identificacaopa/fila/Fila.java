package matheus.bcc.identificacaopa.fila;

public class Fila {
    private NoF inicio;

    public Fila () {
        this.inicio = null;
    }

    public boolean isEmpty () {
        return inicio == null;
    }

    public void inserir (char info) {
        NoF novo = new NoF(info);
        if (inicio == null)
            inicio = novo;
        else {
            NoF aux = inicio;
            if (aux.getInfo() != info) {
                while (aux.getProx() != null && aux.getProx().getInfo() != info)
                    aux = aux.getProx();
                if (aux.getProx() == null)
                    aux.setProx(novo);
            }
        }
    }

    public NoF remover () {
        NoF aux = null;
        if (!isEmpty()) {
            aux = inicio;
            inicio = inicio.getProx();
        }
        return aux;
    }
}
