package matheus.bcc.identificacaopa.narea;

public class No {
    public static final int N = 3;
    private int[] vInfo;
    private No[] vLig;
    private int tl;

    public No() {
        vInfo = new int[N - 1];
        vLig = new No[N];
        tl = 0;
    }

    public No(int info) {
        this();
        vInfo[0] = info;
        tl = 1;
    }

    public int getvInfo(int pos) {
        return vInfo[pos];
    }

    public void setvInfo(int pos, int info) {
        vInfo[pos] = info;
    }

    public No getvLig(int pos) {
        return vLig[pos];
    }

    public void setvLig(int pos, No lig) {
        vLig[pos] = lig;
    }

    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }

    public int buscarPos(int info) {
        int i = 0;
        while (i < tl && vInfo[i] <= info)
            i++;
        return i;
    }

    public void remanejar(int pos) {
        for (int i = tl; i > pos; i--)
            vInfo[i] = vInfo[i - 1];
    }
}
