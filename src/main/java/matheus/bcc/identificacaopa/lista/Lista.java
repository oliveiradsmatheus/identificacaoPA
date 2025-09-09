package matheus.bcc.identificacaopa.lista;

public class Lista {
    private No inicio;

    public Lista(char vertice) {
        this.inicio = new No(vertice);
    }

    public No getInicio() {
        return inicio;
    }

    public int getGrau() {
        int cont = 0;
        if (inicio != null) {
            No aux = inicio.getProx();
            while (aux != null) {
                cont++;
                aux = aux.getProx();
            }
        }
        return cont;
    }

    public void inserir(char info) {
        No aux = inicio, novo = new No(info);
        while(aux.getProx() != null)
            aux = aux.getProx();
        aux.setProx(novo);
    }

    public void limparVisitas() {
        No aux = inicio;
        while (aux != null) {
            aux.setVisitado(false);
            aux = aux.getProx();
        }
    }

    public char dfs() {
        No aux = inicio.getProx();
        while (aux != null && aux.isVisitado())
            aux = aux.getProx();
        if(aux != null)
            return aux.getInfo();
        return '\0';
    }

    public void visitar(char info) {
        No aux = inicio;
        while (aux != null) {
            if  (aux.getInfo() == info)
                aux.setVisitado(true);
            aux = aux.getProx();
        }
    }
}
