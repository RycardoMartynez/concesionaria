package ejercicio.concesionaria.persistencia;

import ejercicio.concesionaria.logica.Automovil;

import java.util.List;

public class ControladoraPersistencia {

    AutomovilJpaController automovilJpa = new AutomovilJpaController();


    public void guardarAutomovil(Automovil automovil) {
        automovilJpa.create(automovil);

    }

    public Automovil buscarAutomovil(long id) {
        return automovilJpa.findAutomovil(id);
    }

    public void editarAutomovil(Automovil automovil) {
        try {
            automovilJpa.edit(automovil);

        } catch (Exception e) {
            System.out.println("Error al editar en la persistencia: " + e.getMessage());
        }
    }

    public List<Automovil> traerAutomovil() {
        return automovilJpa.findAutomovilEntities();
    }

    public void borrarAutomovil(long id) {
        try {
            Automovil automovil = automovilJpa.findAutomovil(id);
            if (automovil != null) {
                automovilJpa.destroy(automovil);
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar en la persistencia: " + e.getMessage());
        }
    }

}
