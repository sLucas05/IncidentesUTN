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

import g6c006.Classes.Servicios;
import g6c006.Misc.NonexistentEntityException;
import g6c006.Misc.PreexistingEntityException;

@SuppressWarnings("all")
public class ServiciosJPA implements Serializable {
	public ServiciosJPA(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public ServiciosJPA() {
		emf = Persistence.createEntityManagerFactory("tIntegradorPU");
	}

	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Servicios servicios) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(servicios);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findServicios(servicios.getId()) != null) {
				throw new PreexistingEntityException("Servicios " + servicios + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Servicios servicios) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			servicios = em.merge(servicios);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				String id = servicios.getId();
				if (findServicios(id) == null) {
					throw new NonexistentEntityException("The servicios with id " + id + " no longer exists.");
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
			Servicios servicios;
			try {
				servicios = em.getReference(Servicios.class, id);
				servicios.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The servicios with id " + id + " no longer exists.", enfe);
			}
			em.remove(servicios);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Servicios> findServiciosEntities() {
		return findServiciosEntities(true, -1, -1);
	}

	public List<Servicios> findServiciosEntities(int maxResults, int firstResult) {
		return findServiciosEntities(false, maxResults, firstResult);
	}

	private List<Servicios> findServiciosEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Servicios.class));
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

	public Servicios findServicios(String id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Servicios.class, id);
		} finally {
			em.close();
		}
	}

	public int getServiciosCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Servicios> rt = cq.from(Servicios.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
}
