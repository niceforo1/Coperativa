package dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.exception.ConstraintViolationException;

import dao.PeriodoCespDAO;
import model.ConfiguracionFactura;
import model.PeriodoCesp;
import persistencia.HibernateUtil;

public class PeriodoCespDAOImplement implements PeriodoCespDAO {

	@Override
	public List<PeriodoCesp> listaPeriodoCesp() throws Exception {
		Session session = null;
		List<PeriodoCesp> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from PeriodoCesp");
			lista = (List<PeriodoCesp>) query.list();
		} catch (ConstraintViolationException e) {
			// System.out.println("ConstraintViolationException: "+ "\n " +
			// e.getSQLException() + e.getMessage());
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());
		} catch (HibernateException e) {
			System.out.println("error: " + e.getMessage());
			throw new Exception(e);
		} finally {
			if (session != null) {
				System.out.println("CIERRA LA SESION");
				session.close();
			}
		}
		return lista;
	}

	@Override
	public void insertarPeriodoCesp(PeriodoCesp periodoCesp) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarPeriodoCesp(PeriodoCesp periodoCesp) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarPeriodoCesp(PeriodoCesp periodoCesp) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public PeriodoCesp buscarPeriodoCespId(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PeriodoCesp buscarUltimoPeriodoCesp() throws Exception {
		Session session = null;
		PeriodoCesp periodoCesp = null;		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from PeriodoCesp p"
											+ " order by p.fechaVtoCesp desc");
			periodoCesp = (PeriodoCesp) query.list().get(0);
		    
		} catch (ConstraintViolationException e) {
			// System.out.println("ConstraintViolationException: "+ "\n " +
			// e.getSQLException() + e.getMessage());
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());
		} catch (HibernateException e) {
			System.out.println("error: " + e.getMessage());
			throw new Exception(e);
		} finally {
			if (session != null) {
				System.out.println("CIERRA LA SESION");
				session.close();
			}
		}
		return periodoCesp;

	}

}
