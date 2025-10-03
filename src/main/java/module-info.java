module matheus.bcc.identificacaopa {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires jdk.compiler;


    opens matheus.bcc.identificacaopa to javafx.fxml;
    exports matheus.bcc.identificacaopa;
}