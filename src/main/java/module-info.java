module matheus.bcc.identificacaopa {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens matheus.bcc.identificacaopa to javafx.fxml;
    exports matheus.bcc.identificacaopa;
}