package matheus.bcc.identificacaopa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Representação Computacional de Grafos - Identificação do Ponto de Articulação");
        stage.setScene(scene);
        try {
            InputStream caminhoIcone = getClass().getResourceAsStream("/icone/grafo.png");
            if (caminhoIcone != null) {
                Image icone = new Image(caminhoIcone);
                stage.getIcons().add(icone);
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar o ícone: " + e.getMessage());
        }
        stage.setMaximized(true);
        stage.show();
    }
}
