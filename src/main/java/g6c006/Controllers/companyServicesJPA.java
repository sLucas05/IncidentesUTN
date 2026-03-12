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

import g6c006.Classes.companyServices;
import g6c006.Misc.NonexistentEntityException;
import g6c006.Misc.PreexistingEntityException;

@SuppressWarnings("all")
public class companyServicesJPA implements Serializable {
	public companyServicesJPA(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public companyServicesJPA() {
		emf = Persistence.createEntityManagerFactory("tIntegradorPU");
	}

	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(companyServices companyServices) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(companyServices);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findcompanyServices(companyServices.getServicio()) != null) {
				throw new PreexistingEntityException("companyServices " + companyServices + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(companyServices companyServices) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			companyServices = em.merge(companyServices);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				String id = companyServices.getServicio();
				if (findcompanyServices(id) == null) {
					throw new NonexistentEntityException("The companyServices with id " + id + " no longer exists.");
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
			companyServices companyServices;
			try {
				companyServices = em.getReference(companyServices.class, id);
				companyServices.getServicio();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The companyServices with id " + id + " no longer exists.", enfe);
			}
			em.remove(companyServices);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<companyServices> findcompanyServicesEntities() {
		return findcompanyServicesEntities(true, -1, -1);
	}

	public List<companyServices> findcompanyServicesEntities(int maxResults, int firstResult) {
		return findcompanyServicesEntities(false, maxResults, firstResult);
	}

	private List<companyServices> findcompanyServicesEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(companyServices.class));
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

	public companyServices findcompanyServices(String id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(companyServices.class, id);
		} finally {
			em.close();
		}
	}

	public int getcompanyServicesCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<companyServices> rt = cq.from(companyServices.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
}
