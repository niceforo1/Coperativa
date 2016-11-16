package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;


import model.NotaCredito;
import model.NotaDebito;
import model.generadores.GenFacturaA;
import model.generadores.GenNotaCreditoA;
import model.generadores.GenNotaDebitoA;
import persistencia.HibernateUtil;
import dao.GeneradorFacturaADAO;
import dao.GeneradorNotaCredADAO;
import dao.GeneradorNotaDebADAO;
import dao.NotaCreditoDAO;

public class GeneradorNotaCredADAOImplement implements GeneradorNotaCredADAO{

	@Override
	public Long insertarNotaCredA(GenNotaCreditoA genNotaCreditoA) throws Exception {
		Session session = null;
		Long id = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			id = (Long)session.save(genNotaCreditoA);
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
