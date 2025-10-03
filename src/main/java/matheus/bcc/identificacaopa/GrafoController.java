package matheus.bcc.identificacaopa;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import matheus.bcc.identificacaopa.lista.Lista;
import matheus.bcc.identificacaopa.lista.No;

import java.net.URL;
import java.util.ResourceBundle;

public class GrafoController implements Initializable {
    public VBox listaContainer;
    public VBox lista;
    public VBox matrizContainer;
    public VBox tabela;
    public GridPane matrizGrid;
    public Label lbMsg;
    public Label lbPontos;

    private Lista[] vertices;
    private int[][] matriz;

    public String[] cores = {
            "#9B59B6", // roxo
            "#F39C12",  // dourado
            "#2980B9", // azul oceano
            "#1ABC9C", // turquesa
            "#2ECC71", // verde
            "#F1C40F", // amarelo
            "#33FF57", // verde claro
            "#7F8C8D", // cinza chumbo
            "#34495E", // azul acinzentado
            "#FF5733", // laranja avermelhado
            "#3357FF", // azul forte
            "#8E44AD", // roxo escuro
            "#C0392B", // vermelho
            "#BDC3C7", // cinza claro
            "#16A085", // verde petróleo
            "#2C3E50", // azul noite
            "#E67E22", // laranja
            "#27AE60", // verde esmeralda
            "#E91E63" // rosa
    };

    public void initialize(URL url, ResourceBundle rb)  {
        vertices = MainController.vertices;
        matriz = MainController.cores;
        exibirLista();
        exibirMatriz();
    }

    public void onVoltar(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    private void exibirLista() {
        for (Lista v : vertices) {
            HBox hb = new HBox();
            hb.setAlignment(Pos.CENTER_LEFT);
            hb.setSpacing(10);

            Button b = new Button();
            b.setPrefSize(75, 25);
            b.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 15px;-fx-background-color: " + cores[v.getInicio().getCor() - 1] + ";");
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
                b.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 15px;-fx-background-color: " +  cores[aux.getCor() - 1] + ";");
                b.setText("" + aux.getInfo());
                hb.getChildren().add(b);
                aux = aux.getProx();
            }
            lista.getChildren().add(hb);
        }
    }

    private StackPane criarPainel(Label label, String bgCor) {
        StackPane pane = new StackPane(label);
        pane.setPrefSize(40, 40);
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
        tabela.setVisible(true);
        matrizContainer.setVisible(true);
        int linhasV = vertices.length;

        Label cabecalho;

        cabecalho = new Label("");
        cabecalho.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-font-size: 15px;");
        matrizGrid.add(criarPainel(cabecalho, "#17c3b2"), 0, 0);

        for (int i = 0; i < linhasV; i++) {
            cabecalho = new Label("" + (i + 1));
            cabecalho.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-font-size: 15px;");
            matrizGrid.add(criarPainel(cabecalho, "#17c3b2"), i + 1, 0);
        }

        for (int i = 0; i < linhasV; i++) {
            cabecalho = new Label("" + vertices[i].getInicio().getInfo());
            cabecalho.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-font-size: 15px;");
            matrizGrid.add(criarPainel(cabecalho, "#17c3b2"), 0, i + 1);
        }

        for (int i = 0; i < linhasV; i++) {
            for (int j = 0; j < linhasV; j++) {
                if (matriz[i][j] != 0) {
                    Label lbValor;
                    String corTexto = matriz[i][j] != -1 && matriz[i][j] != 0 ? "#000000" : "#666666";
                    String corFundo = matriz[i][j] != -1 && matriz[i][j] != 0 ? "#ffb5a7" : "#f5e6e6";

                    lbValor = new Label(matriz[i][j] == -1 ? "x" : String.valueOf(matriz[i][j]));
                    lbValor.setStyle("-fx-font-weight: bold; -fx-text-fill: " + corTexto + ";");
                    matrizGrid.add(criarPainel(lbValor, corFundo), j + 1, i + 1);
                }
            }
        }
    }
}
