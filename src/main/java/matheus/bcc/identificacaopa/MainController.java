package matheus.bcc.identificacaopa;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import matheus.bcc.identificacaopa.arquivo.Arquivo;
import matheus.bcc.identificacaopa.lista.Lista;
import matheus.bcc.identificacaopa.lista.No;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class MainController {
    public VBox listaContainer;
    public VBox lista;
    private Lista[] listaVertices;

    private void exibirLista() {
        lista.getChildren().clear();
        listaContainer.setVisible(true);
        listaContainer.setMaxWidth(Region.USE_PREF_SIZE);

        for (Lista v : listaVertices) {
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
                b.setText(aux.getInfo() + " - " + aux.getPeso());
                hb.getChildren().add(b);
                aux = aux.getProx();
            }
            lista.getChildren().add(hb);
        }
    }

    public void onCarregarArquivo(ActionEvent actionEvent) {
        File arq = Arquivo.carregarArquivo();
        if (arq != null) {
            try {
                List<String> linhas = Files.readAllLines(arq.toPath());
                listaVertices = new Lista[linhas.size()];

                for (int i = 0; i < linhas.size(); i++) {
                    char prox;
                    StringBuilder peso = new StringBuilder();
                    listaVertices[i] = new Lista(linhas.get(i).charAt(0));
                    for(int j = 2; j < linhas.get(i).length(); j++) {
                        prox = linhas.get(i).charAt(j);
                        j+=2;
                        while(j < linhas.get(i).length() && linhas.get(i).charAt(j) != ' ')
                            peso.append(linhas.get(i).charAt(j++));
                        listaVertices[i].inserir(prox, peso.toString());
                        peso = new StringBuilder();
                    }
                }
                exibirLista();
            } catch (Exception e) {
                lista.getChildren().clear();
            }
        }
    }
}
