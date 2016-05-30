package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import persistencia.HibernateUtil;
import model.NotaCredito;
import model.NotaDebito;
import model.generadores.GenFacturaA;
import dao.GeneradorFacturaADAO;
import dao.NotaCreditoDAO;

public class GeneradorFacturaADAOImplement implements GeneradorFacturaADAO{

	@Override
	public Long insertarFacturaA(GenFacturaA genFacturaA) throws Exception {
		Session session = null;
		Long id = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			id = (Long)session.save(genFacturaA);
			session.getTransaction().commit();
		}catch(ConstraintViolationException e){
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());
		}catch(HibernateException e){
			session.getTransaction().rollback();
			throw new Exception(e);
		}finally{
			if(session != null){
				session.close();
			}
		}
		return id;
	}
	
}
