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

import g6c006.Classes.Tecnicos;
import g6c006.Misc.NonexistentEntityException;
import g6c006.Misc.PreexistingEntityException;

@SuppressWarnings("all")
public class TecnicosJPA implements Serializable {
	public TecnicosJPA(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public TecnicosJPA() {
		emf = Persistence.createEntityManagerFactory("tIntegradorPU");
	}

	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Tecnicos tecnicos) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(tecnicos);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTecnicos(tecnicos.getIdentificacion()) != null) {
				throw new PreexistingEntityException("Tecnicos " + tecnicos + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Tecnicos tecnicos) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			tecnicos = em.merge(tecnicos);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = tecnicos.getIdentificacion();
				if (findTecnicos(id) == null) {
					throw new NonexistentEntityException("The tecnicos with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(Integer id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tecnicos tecnicos;
			try {
				tecnicos = em.getReference(Tecnicos.class, id);
				tecnicos.getIdentificacion();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The tecnicos with id " + id + " no longer exists.", enfe);
			}
			em.remove(tecnicos);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Tecnicos> findTecnicosEntities() {
		return findTecnicosEntities(true, -1, -1);
	}

	public List<Tecnicos> findTecnicosEntities(int maxResults, int firstResult) {
		return findTecnicosEntities(false, maxResults, firstResult);
	}

	private List<Tecnicos> findTecnicosEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Tecnicos.class));
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

	public Tecnicos findTecnicos(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Tecnicos.class, id);
		} finally {
			em.close();
		}
	}

	public int getTecnicosCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Tecnicos> rt = cq.from(Tecnicos.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
}
