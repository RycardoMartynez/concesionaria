package ejercicio.concesionaria.logica;

import ejercicio.concesionaria.persistencia.ControladoraPersistencia;

import java.util.List;

public class Controladora {


        private ControladoraPersistencia controlPersis;

        public Controladora() {
            this.controlPersis = new ControladoraPersistencia();
        }

    public void guardar(String marca, String modelo, String color,
                        String motor, String patente, int cantPuertas
                        ) {

        // 1. Creamos el dueño y asignamos sus datos
        Automovil automovil = new Automovil();
        automovil.setMarca(marca);
        automovil.setModelo(modelo);
        automovil.setColor(color);
        automovil.setMotor(motor);
        automovil.setPatente(patente);
        automovil.setCantPuertas(cantPuertas);

        // 3. Mandamos a guardar
        controlPersis.guardarAutomovil(automovil);
    }

    public void editar(long id, String marca, String modelo, String color,
                       String motor, String patente, int cantPuertas
                       ) {

        // 1. Buscamos la mascota existente en la base de datos por su ID
        Automovil automovil = controlPersis.buscarAutomovil(id);

        // 2. Actualizamos los datos de la mascota
        automovil.setMarca(marca);
        automovil.setModelo(modelo);
        automovil.setColor(color);
        automovil.setMotor(motor);
        automovil.setPatente(patente);
        automovil.setCantPuertas(cantPuertas);


        // 4. Mandamos a actualizar en la base de datos
        controlPersis.editarAutomovil(automovil);
    }

    public List<Automovil> traerAutomovil() {
        return controlPersis.traerAutomovil();
    }

    public void borrarAutomovil(long id) {
        controlPersis.borrarAutomovil(id);
    }


}
