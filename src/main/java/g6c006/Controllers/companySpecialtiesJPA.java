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

import g6c006.Classes.companySpecialties;
import g6c006.Misc.NonexistentEntityException;
import g6c006.Misc.PreexistingEntityException;

@SuppressWarnings("all")
public class companySpecialtiesJPA implements Serializable {
	public companySpecialtiesJPA(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public companySpecialtiesJPA() {
		emf = Persistence.createEntityManagerFactory("tIntegradorPU");
	}

	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(companySpecialties companySpecialties) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(companySpecialties);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findcompanySpecialties(companySpecialties.getEspecialidad()) != null) {
				throw new PreexistingEntityException("companySpecialties " + companySpecialties + " already exists.",
						ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(companySpecialties companySpecialties) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			companySpecialties = em.merge(companySpecialties);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				String id = companySpecialties.getEspecialidad();
				if (findcompanySpecialties(id) == null) {
					throw new NonexistentEntityException("The companySpecialties with id " + id + " no longer exists.");
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
			companySpecialties companySpecialties;
			try {
				companySpecialties = em.getReference(companySpecialties.class, id);
				companySpecialties.getEspecialidad();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The companySpecialties with id " + id + " no longer exists.",
						enfe);
			}
			em.remove(companySpecialties);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<companySpecialties> findcompanySpecialtiesEntities() {
		return findcompanySpecialtiesEntities(true, -1, -1);
	}

	public List<companySpecialties> findcompanySpecialtiesEntities(int maxResults, int firstResult) {
		return findcompanySpecialtiesEntities(false, maxResults, firstResult);
	}

	private List<companySpecialties> findcompanySpecialtiesEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(companySpecialties.class));
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

	public companySpecialties findcompanySpecialties(String id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(companySpecialties.class, id);
		} finally {
			em.close();
		}
	}

	public int getcompanySpecialtiesCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<companySpecialties> rt = cq.from(companySpecialties.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
}
