package ejercicio.concesionaria.igu;

import ejercicio.concesionaria.logica.Automovil;
import ejercicio.concesionaria.logica.Controladora;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML private TextField txtPatente;
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtMotor;
    @FXML private TextField txtColor;
    @FXML private ComboBox<Integer> cmbPuertas;
    @FXML private TableView<Automovil>  tablaAutos;


    // Columnas de la tabla
    @FXML private TableColumn<Automovil, String> colPatente;
    @FXML private TableColumn<Automovil, String> colMarca;
    @FXML private TableColumn<Automovil, String> colModelo;
    @FXML private TableColumn<Automovil, String> colMotor;
    @FXML private TableColumn<Automovil, String> colColor;
    @FXML private TableColumn<Automovil, Integer> colPuertas;
    @FXML private TableColumn<Automovil, Long>  colId;

    private Controladora controladora = new Controladora();

    // -1 = modo nuevo registro | cualquier otro valor = ID de la mascota a editar
    private long idAutomovilAEditar = -1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        controladora = new Controladora();
        ObservableList<Integer> opciones = FXCollections.observableArrayList(2,3,4,5);
        cmbPuertas.setItems(opciones);
        cmbPuertas.setValue(4);

        // Mapeo de columnas
        colPatente.setCellValueFactory(new PropertyValueFactory<>("patente"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colMotor.setCellValueFactory(new PropertyValueFactory<>("motor"));
        colPuertas.setCellValueFactory(new PropertyValueFactory<>("cantPuertas"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        cargarTabla();

    }
    @FXML
    private void cargarTabla() {
        List<Automovil> listaAutomovil = controladora.traerAutomovil();
        ObservableList<Automovil> data = FXCollections.observableArrayList(listaAutomovil);
        tablaAutos.setItems(data);
    }

    // Carga los datos de la fila seleccionada en el formulario para editarlos
    @FXML
    private void btnCargarDatosParaEditar() {
        Automovil seleccionado = tablaAutos.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            idAutomovilAEditar = seleccionado.getId();

            txtPatente.setText(seleccionado.getPatente());
            txtMotor.setText(seleccionado.getMotor());
            txtColor.setText(seleccionado.getColor());
            txtMarca.setText(seleccionado.getMarca());
            txtModelo.setText(seleccionado.getModelo());
            cmbPuertas.setValue(seleccionado.getCantPuertas());



            System.out.println("Datos cargados para editar. ID: " + idAutomovilAEditar);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una mascota de la tabla para editar.");
        }
    }
    @FXML
    private void btnGuardar(){

        String marca   = txtMarca.getText();
        String modelo          = txtModelo.getText();
        String color         = txtColor.getText();
        String motor  = txtMotor.getText();
        String patente     = txtPatente.getText();
        int cantPuertas = cmbPuertas.getValue();

        if (idAutomovilAEditar != -1) {
            // ── MODO EDICIÓN ──
            controladora.editar(idAutomovilAEditar, marca, modelo, color,
                    motor, patente, cantPuertas);
            JOptionPane.showMessageDialog(null, "Automovil actualizado con éxito!");
            System.out.println("Automovil ID " + idAutomovilAEditar + " actualizado.");
            idAutomovilAEditar = -1;
        } else {
            // ── MODO NUEVO ──
            controladora.guardar(marca, modelo, color, motor, patente, cantPuertas);
            JOptionPane.showMessageDialog(null, "Se ha guardado correctamente");
            System.out.println("¡Guardado con éxito en la base de datos!");
        }

        cargarTabla();
        limpiarFormulario();

    }
    @FXML
    private void limpiarFormulario() {
        txtMarca.clear();
        txtModelo.clear();
        txtColor.clear();
        txtMotor.clear();
        txtPatente.clear();
        cmbPuertas.setValue(2);
        idAutomovilAEditar = -1;
    }
    @FXML
    private void btnEliminar() {
        Automovil seleccionado = tablaAutos.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar eliminación");
            alert.setHeaderText("¿Estás seguro de que querés eliminar a " + seleccionado.getPatente() + "?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    controladora.borrarAutomovil(seleccionado.getId());
                    cargarTabla();
                    JOptionPane.showMessageDialog(null, "Automovil eliminado con éxito");
                    System.out.println("Automovil eliminado correctamente.");
                }
            });
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un Automovil de la tabla para eliminar.");
        }
    }




}
