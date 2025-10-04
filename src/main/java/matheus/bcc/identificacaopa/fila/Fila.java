package matheus.bcc.identificacaopa.fila;

import matheus.bcc.identificacaopa.lista.Lista;
import matheus.bcc.identificacaopa.lista.No;

public class Fila {
    private NoF inicio;

    public Fila () {
        this.inicio = null;
    }

    public boolean isEmpty () {
        return inicio == null;
    }

    public void inserir (char info, int grauNovo) {
        NoF novo = new NoF(info,grauNovo);

        if (inicio == null)
            inicio = novo;
        else {
            NoF aux = inicio;
            NoF ant;

            if(aux.getInfo() != info && grauNovo > aux.getGrau())//insere inicio da fila
            {
                ant = aux;//anterior recebe inicio
                inicio = novo;
                inicio.setProx(ant);
            }
            else
            {
                ant = aux;
                aux = inicio.getProx();
                //se grau igual insere em ordem alfabetica
                while (aux != null &&
                        (grauNovo < aux.getGrau() ||
                        (grauNovo == aux.getGrau() && novo.getInfo() > aux.getInfo())))
                {
                    ant = aux;
                    aux = aux.getProx();
                }

                if (aux == null)//insere final
                    ant.setProx(novo);
                else {
                    novo.setProx(aux);
                    ant.setProx(novo);
                }

            }
        }
    }

    public void exibirFila(){
        NoF aux = inicio;
        while(aux != null)
        {
            System.out.printf("(%c,%d)  ", aux.getInfo(), aux.getGrau());
            aux = aux.getProx();
        }
        System.out.printf("\n");
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
