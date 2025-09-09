package matheus.bcc.identificacaopa;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import matheus.bcc.identificacaopa.arquivo.Arquivo;
import matheus.bcc.identificacaopa.lista.Lista;
import matheus.bcc.identificacaopa.lista.No;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    public VBox listaContainer;
    public VBox lista;
    public GridPane matrizGrid;
    public VBox matrizContainer;
    public Label lbTabela;
    public Label lbMsg;
    public Label lbPontos;

    private VBox tabelaContainer;

    private Lista[] vertices;
    private int linhasV;
    private int[][] matriz;

    private void exibirLista() {
        lista.getChildren().clear();
        listaContainer.setVisible(true);
        listaContainer.setMaxWidth(Region.USE_PREF_SIZE);

        for (Lista v : vertices) {
            HBox hb = new HBox();
            hb.setAlignment(Pos.CENTER_LEFT);
            hb.setSpacing(10);

            Button b = new Button();
            b.setPrefSize(75, 25);
            b.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 15px;-fx-background-color: #17c3b2;");
            b.setText("" + v.getInicio().getInfo());
            hb.getChildren().add(b);

            No aux = v.getInicio().getProx();
            while(aux != null) {
                b = new Button();
                b.setPrefSize(40, 25);
                b.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;-fx-background-color: #fef9ef;");
                b.setText("→");
                hb.getChildren().add(b);

                b = new Button();
                b.setPrefSize(75, 25);
                b.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;-fx-background-color: #ffb5a7;");
                b.setText("" + aux.getInfo());
                hb.getChildren().add(b);
                aux = aux.getProx();
            }
            lista.getChildren().add(hb);
        }
    }

    private StackPane criarPainel(Label label, String bgCor) {
        StackPane pane = new StackPane(label);
        pane.setPrefSize(130, 40);
        pane.setStyle("-fx-background-color: " + bgCor + "; -fx-border-color: #cccccc; -fx-border-width: 0.5;");
        pane.setStyle(
                "-fx-background-color: " + bgCor + "; " +
                        "-fx-background-radius: 5; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 18px; " +
                        "-fx-alignment: center;"
        );
        StackPane.setAlignment(label, Pos.CENTER);
        return pane;
    }

    private void exibirMatriz() {
        matrizGrid.getChildren().clear();
        lbTabela.setVisible(true);
        matrizContainer.setVisible(true);
        Label cabecalho;
        String[] valor = {"Tarefa", "Pai", "Prenum Busca", "Prenum (--)", "Menor", "Análise"}; // Cabeçalhos da matriz de informação.
        for (int i = 0; i < 6; i++) {
            if (i != 1) {
                cabecalho = new Label(valor[i]);
                cabecalho.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-font-size: 15px;");
                matrizGrid.add(criarPainel(cabecalho, "#17c3b2"), i + 1, 0);
            }
        }
        for (int i = 0; i < linhasV; i++) {
            String corTexto = matriz[i][6] != 0 ? "#000000" : "#666666";
            String corFundo = matriz[i][6] != 0 ? "#ffb5a7" : "#f5e6e6";
            for (int j = 0; j < 6; j++) {
                if (j != 1) {
                    Label lbValor;
                    if (j == 0)
                        lbValor = new Label("Tarefa " + (char) matriz[i][j]);
                    else
                        lbValor = new Label(String.valueOf(matriz[i][j]));
                    lbValor.setStyle("-fx-font-weight: bold; -fx-text-fill: " + corTexto + ";");
                    matrizGrid.add(criarPainel(lbValor, corFundo), j + 1, i + 1);
                }
            }
        }

        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        ArrayList<String> tarefas = new ArrayList<>();

        for (int i = 0; i < linhasV; i++)
            if(matriz[i][6] == 1)
                tarefas.add(String.valueOf((char) matriz[i][0]));

        if (tarefas.isEmpty()) {
            lbMsg.setText("Não existem tarefas que gerem dependência no projeto");
            lbPontos.setText("");
        } else if (tarefas.size() == 1) {
            lbMsg.setText("A única tarefa crítica para o desenvolvimento projeto é a tarefa ");
            lbPontos.setText(tarefas.getFirst());
        } else {
            lbMsg.setText("As tarefas críticas do projeto são as tarefas ");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(tarefas.getFirst());
            for(int i = 1; i < tarefas.size() - 1; i++)
                stringBuilder.append(", ").append(tarefas.get(i));
            stringBuilder.append(" e ").append(tarefas.getLast());
            lbPontos.setText(stringBuilder.toString());
        }
        hb.getChildren().add(lbMsg);
        hb.getChildren().add(lbPontos);
        matrizContainer.getChildren().add(hb);
    }

    public void onCarregarArquivo(ActionEvent actionEvent) {
        File arq = Arquivo.carregarArquivo();
        if (arq != null) {
            try {
                matrizGrid.getChildren().clear();
                lbTabela.setVisible(false);
                List<String> linhas = Files.readAllLines(arq.toPath());
                linhasV = linhas.size() - 1;
                vertices = new Lista[linhasV];
                int i = 0;
                for (int j = 0; j < linhas.getFirst().length(); j++)
                    if (linhas.getFirst().charAt(j) != '\t')
                        vertices[i++] = new Lista(linhas.getFirst().charAt(j));
                for (i = 1; i < linhas.size(); i ++) {
                    int j = 0;
                    while (j < linhas.get(i).length()) {
                        if (linhas.get(i).charAt(j) != '\t')
                            vertices[i - 1].inserir(linhas.get(i).charAt(j));
                        j++;
                    }
                }
                exibirLista();
            } catch (Exception e) {
                lista.getChildren().clear();
            }
        }
    }

    private int buscaNo (char info) {
        int i = 0;
        while (i < linhasV && (vertices[i].getInicio().isVisitado() || vertices[i].getInicio().getInfo() != info))
            i++;
        return i;
    }

    private int buscaPos (int[][] mat) {
        int i = 0;
        while (i < linhasV && mat[i][1] != 0)
            i++;
        if (i < linhasV)
            return i;
        return -1;
    }

    private int buscaPosVet(char info) {
        int i = 0;
        while (i < linhasV && vertices[i].getInicio().getInfo() != info)
            i++;
        if (i < linhasV)
            return i;
        return -1;
    }

    private int buscaElemento(int[][] matriz, char info) {
        int i = 0;
        while (i < linhasV && matriz[i][2] != info)
            i++;
        if (i < linhasV)
            return matriz[i][2];
        return 0;
    }

    public void onBuscarPontos(ActionEvent actionEvent) {
        matriz = new int[linhasV][7];
        int lm = 0;
        int visita = 0;
        for (Lista v : vertices)
            v.limparVisitas();
        for (int i = 0; i < linhasV; i++)
            matriz[i][0] = vertices[i].getInicio().getInfo();
        prenumBusca(matriz, 0, vertices[0].getInicio(), 1);
        prenum(matriz);
        analisar(matriz);
        exibirMatriz();
    }

    private void identificarFolhas(int[][] matriz) {
        for (int i = 0; i < linhasV; i ++)
            matriz[i][4] = -1;

        for (int i = 0; i < linhasV; i++) {
            boolean pai = false;
            int j = 0;
            while (j < linhasV && !pai) {
                if (matriz[i][0] == matriz[j][1])
                    pai = true;
                j++;
            }
            if (!pai)
                matriz[i][4] = 0;
        }
    }

    private int identificarMenor(int[] linha) {
        int i = 2;
        int menor = linha[i];
        while (i < 5) {
            if (linha[i] < menor && linha[i] > 0)
                menor = linha[i];
            i++;
        }
        return menor;
    }

    private int menorDosFilhos(int[][] matriz, int pos) {
        int menor = 0;
        for (int i = 0; i < linhasV; i++) {
            if (matriz[i][1] == matriz[pos][0]) {
                if (menor == 0)
                    menor = matriz[i][5];
                if (matriz[i][5] < menor)
                    menor = matriz[i][5];
            }
        }
        return menor;
    }

    private int[] ordemGrafo(int[][] matriz) {
        int[] ordem = new int[linhasV];
        int pos = linhasV, TL = 0;
        while (pos > 0)
            for (int i = 0; i < linhasV; i++)
                if (matriz[i][2] == pos) {
                    ordem[TL++] = i;
                    pos--;
                }
        return ordem;
    }

    private void analise(int[][] matriz) {
        for (int i = 0; i < linhasV; i++)
            if (matriz[i][4] == 0) // É folha
                matriz[i][5] = identificarMenor(matriz[i]);
        int[] ordem = ordemGrafo(matriz);
        int qtde = linhasV;
        int i = 0;
        while (i < linhasV) {
            if (matriz[ordem[i]][4] != 0) { // Não é folha
                matriz[ordem[i]][4] = menorDosFilhos(matriz, ordem[i]);
                matriz[ordem[i]][5] = identificarMenor(matriz[ordem[i]]);
            }
            i++;
        }
    }

    private void analisar(int[][] matriz) {
        identificarFolhas(matriz);
        analise(matriz);
        comparar(matriz); // posição 6 1 para PA e 0 para não PA.
    }

    private void comparar (int[][] matriz) {
        int filhosRaiz = 0, raiz = matriz[0][0], i;
        for (i = 1; i < linhasV; i++)
            if (matriz[i][1] == raiz)
                filhosRaiz++;
        i = 1;
        if (filhosRaiz > 1)
            i = 0;
        while (i < linhasV) {
            if (matriz[i][4] != 0) { // Se não é folha
                for (int j = 0; j < linhasV; j++) {
                    if (matriz[i][0] == matriz[j][1])
                        if (matriz[j][5] >= matriz[i][2]) // Se o menor do filho for maior ou igual o prenum do pai
                            matriz[i][6] = 1; // Então o pai é um vértice de corte
                }
            }
            i++;
        }
    }

    private boolean buscaLig(int[][] matriz, char pA, char pB) {
        boolean achou = false;
        int i = 0;
        while (i < linhasV && !achou) {
            if (matriz[i][0] == pA && matriz[i][1] == pB || matriz[i][0] == pB && matriz[i][1] == pA)
                achou = true;
            i++;
        }
        return achou;
    }

    private int buscarPosMatriz(int[][] matriz, char info) {
        int i = 0;
        while (i < linhasV && matriz[i][0] != info)
            i++;
        if (i < linhasV)
            return matriz[i][2];
        return -1;
    }

    private void prenum(int[][] matriz) {
        for (int i = 0; i < linhasV; i++) {
            No no = vertices[i].getInicio();
            No prox = no.getProx();
            while (prox != null && matriz[i][3] == 0) {
                if (!buscaLig(matriz, no.getInfo(), prox.getInfo()))
                    matriz[i][3] = buscarPosMatriz(matriz, prox.getInfo());
                prox = prox.getProx();
            }
        }
    }

    private void prenumBusca(int[][] mat, int pos, No pai, int cont) {
        if (pos < linhasV && vertices[pos] != null) {
            No info = vertices[pos].getInicio();
            if  (!info.isVisitado()) {
                for (Lista v : vertices)
                    v.visitar(info.getInfo());
                int i = buscaPos(mat);
                if (i != -1) {
                    mat[pos][1] = pai.getInfo();
                    mat[pos][2] = cont;
                    char infoChar = vertices[pos].dfs();
                    int novaPos = buscaNo(infoChar);
                    while (novaPos < linhasV) {
                        prenumBusca(mat, novaPos, info, cont + 1);
                        infoChar = vertices[pos].dfs();
                        novaPos = buscaNo(infoChar);
                        cont++;
                    }
                }
            }
        }
    }
}
