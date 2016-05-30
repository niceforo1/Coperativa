package dao.impl;

import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import persistencia.HibernateUtil;
import model.generadores.GenFacturaB;
import dao.GeneradorFacturaBDAO;

public class GeneradorFacturaBDAOImplement implements GeneradorFacturaBDAO {

	@Override
	public Long insertarFacturaB(GenFacturaB genFacturaB) throws Exception {
		Session session = null;
		Long id = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			id = (Long) session.save(genFacturaB);
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return id;
	}
}
