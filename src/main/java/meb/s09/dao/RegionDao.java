package meb.s09.dao;

import java.util.List;
import java.util.Optional;

import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model
public class RegionDao {
/*
	static final Logger LOG = LoggerFactory.getLogger(RegionDao.class);
	@PersistenceContext(unitName = "me")
	private EntityManager em;

	public List<Region> readAll() {
		return em.createQuery("SELECT r FROM Region r", Region.class).getResultList();
	}

	public Optional<Region> read(Integer id) {
		return Optional.ofNullable(em.find(Region.class, id));
	}

	public boolean create(Region region) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(region);
		et.commit();
		return true;
	}

	public boolean update(Region region) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(region);
		et.commit();
		return true;
	}

	public boolean delete(Integer id) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		Region region = em.find(Region.class, id);
		if (region != null) {
			em.remove(region);
			et.commit();
			return true;
		} else {
			LOG.info("Can't remove missing region " + id);
			et.rollback();
			return false;
		}
	}
 */
}
