module ejercicio.concesionaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires javafx.base;
    requires java.desktop;


    opens ejercicio.concesionaria.igu to javafx.fxml;
    opens ejercicio.concesionaria.logica;
    opens ejercicio.concesionaria.persistencia;

    exports ejercicio.concesionaria.igu;
    exports ejercicio.concesionaria.logica;
    exports ejercicio.concesionaria.persistencia;
}