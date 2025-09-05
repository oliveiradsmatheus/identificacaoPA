package matheus.bcc.identificacaopa.narea;

public class NArea {
    No raiz;

    public NArea() {
    }

    public void inserir(int info) {
        if (raiz == null)
            raiz = new No(info);
        else {
            boolean inserido = false;
            No aux = raiz;
            int pos;
            while (!inserido) {
                pos = aux.buscarPos(info);
                if (aux.getTl() < No.N - 1) {
                    aux.remanejar(pos);
                    aux.setvInfo(pos, info);
                    aux.setTl(aux.getTl() + 1);
                    inserido = true;
                } else
                if (aux.getvLig(pos) == null) {
                    aux.setvLig(pos, new No(info));
                    inserido = true;
                } else
                    aux = aux.getvLig(pos);
            }
        }
    }

    public void emOrdem() {
        emOrdem(raiz);
    }

    private void emOrdem(No no) {
        if (no != null) {
            for(int i = 0; i < no.getTl(); i ++) {
                emOrdem(no.getvLig(i));
                System.out.println(no.getvInfo(i));
            }
            emOrdem(no.getvLig(no.getTl()));
        }
    }
}
