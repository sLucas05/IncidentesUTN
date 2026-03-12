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

import g6c006.Classes.Clientes;
import g6c006.Misc.NonexistentEntityException;
import g6c006.Misc.PreexistingEntityException;

@SuppressWarnings("all")
public class ClientesJPA implements Serializable {
	public ClientesJPA(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public ClientesJPA() {
		emf = Persistence.createEntityManagerFactory("tIntegradorPU");
	}

	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Clientes clientes) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(clientes);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findClientes(clientes.getIdentificacion()) != null) {
				throw new PreexistingEntityException("Clientes " + clientes + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Clientes clientes) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			clientes = em.merge(clientes);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = clientes.getIdentificacion();
				if (findClientes(id) == null) {
					throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.");
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
			Clientes clientes;
			try {
				clientes = em.getReference(Clientes.class, id);
				clientes.getIdentificacion();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.", enfe);
			}
			em.remove(clientes);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Clientes> findClientesEntities() {
		return findClientesEntities(true, -1, -1);
	}

	public List<Clientes> findClientesEntities(int maxResults, int firstResult) {
		return findClientesEntities(false, maxResults, firstResult);
	}

	private List<Clientes> findClientesEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Clientes.class));
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

	public Clientes findClientes(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Clientes.class, id);
		} finally {
			em.close();
		}
	}

	public int getClientesCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Clientes> rt = cq.from(Clientes.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
}
