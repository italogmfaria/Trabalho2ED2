module com.projetoavl.t2ed2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.projetoavl.t2ed2 to javafx.fxml;
    exports com.projetoavl.t2ed2;
    exports com.projetoavl.t2ed2.view;
    opens com.projetoavl.t2ed2.view to javafx.fxml;
    exports com.projetoavl.t2ed2.model;
    opens com.projetoavl.t2ed2.model to javafx.fxml;
    exports com.projetoavl.t2ed2.controller;
    opens com.projetoavl.t2ed2.controller to javafx.fxml;
}