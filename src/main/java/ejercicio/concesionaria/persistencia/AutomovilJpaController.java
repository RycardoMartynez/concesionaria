package ejercicio.concesionaria.persistencia;

import ejercicio.concesionaria.logica.Automovil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;

import java.io.Serializable;
import java.util.List;

public class AutomovilJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public AutomovilJpaController() {
        this.emf = Persistence.createEntityManagerFactory("AutoPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // ── CREATE ──────────────────────────────────────────
    public void create(Automovil auto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(auto);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    // ── READ (todos) ─────────────────────────────────────
    public List<Automovil> findAutomovilEntities() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Automovil.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    // ── READ (uno por ID) ────────────────────────────────
    public Automovil findAutomovil(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Automovil.class, id);
        } finally {
            em.close();
        }
    }

    // ── UPDATE ───────────────────────────────────────────
    public void edit(Automovil auto) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(auto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            if (em != null) em.close();
        }
    }

    // ── DELETE ───────────────────────────────────────────
    public void destroy(Automovil auto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Automovil autoABorrar = em.merge(auto); // sincronizar con el contexto
            em.remove(autoABorrar);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (em != null) em.close();
        }
    }
}