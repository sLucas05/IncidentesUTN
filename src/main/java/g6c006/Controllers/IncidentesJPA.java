package g6c006.Controllers;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import g6c006.Classes.Incidentes;
import g6c006.Misc.NonexistentEntityException;
import g6c006.Misc.PreexistingEntityException;

@SuppressWarnings("all")
public class IncidentesJPA implements Serializable {
	public IncidentesJPA(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public IncidentesJPA() {
		emf = Persistence.createEntityManagerFactory("tIntegradorPU");
	}

	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Incidentes incidentes) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(incidentes);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findIncidentes(incidentes.getId()) != null) {
				throw new PreexistingEntityException("Incidentes " + incidentes + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Incidentes incidentes) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			incidentes = em.merge(incidentes);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				String id = incidentes.getId();
				if (findIncidentes(id) == null) {
					throw new NonexistentEntityException("The incidentes with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(String id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Incidentes incidentes;
			try {
				incidentes = em.getReference(Incidentes.class, id);
				incidentes.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The incidentes with id " + id + " no longer exists.", enfe);
			}
			em.remove(incidentes);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Incidentes> findIncidentesEntities() {
		return findIncidentesEntities(true, -1, -1);
	}

	public List<Incidentes> findIncidentesEntities(int maxResults, int firstResult) {
		return findIncidentesEntities(false, maxResults, firstResult);
	}

	private List<Incidentes> findIncidentesEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Incidentes.class));
			Query q = em.createQuery(cq);
			if (!all) {
				q.setMaxResults(maxResults);
				q.setFirstResult(firstResult);
			}
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	public Incidentes findIncidentes(String id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Incidentes.class, id);
		} finally {
			em.close();
		}
	}

	public int getIncidentesCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Incidentes> rt = cq.from(Incidentes.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
}
