package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;


import model.NotaCredito;
import model.NotaDebito;
import persistencia.HibernateUtil;
import dao.NotaCreditoDAO;

public class NotaCreditoDAOImplement implements NotaCreditoDAO{

	@Override
	public List<NotaCredito> listaNotaCredito() throws Exception {
		Session session = null;
		List<NotaCredito> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from NotaCredito");
			lista = (List<NotaCredito>) query.list();
		}catch(ConstraintViolationException e){
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());
		}catch(HibernateException e){
			System.out.println("error: " + e.getMessage());
			throw new Exception(e);
		}finally{
			if(session != null){
				System.out.println("CIERRA LA SESION");
				session.close();
			}
		}
		return lista;
	}

	@Override
	public Long insertarNotaCredito(NotaCredito notaCredito) throws Exception {
		Session session = null;
		Long id = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			id = (Long)session.save(notaCredito);
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

	@Override
	public void modificarNotaCredito(NotaCredito notaCredito) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(notaCredito);
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
		
	}

	@Override
	public void eliminarNotaCredito(NotaCredito notaCredito) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(notaCredito);
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
		
	}

	@Override
	public NotaCredito buscarNotaCreditoId(Long id) throws Exception {
		Session session = null;
		NotaCredito notaCredito = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			notaCredito = (NotaCredito)session.get(NotaCredito.class, id.longValue());			
			session.getTransaction().commit();						
		}catch(ConstraintViolationException e){
			//System.out.println("ConstraintViolationException: "+ "\n " + e.getSQLException() + e.getMessage());
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());		
		}catch(HibernateException e){						
			throw new Exception(e);		
		}finally{
			if(session != null){
				System.out.println("CIERRA LA SESION");
				session.close();
			}
		}		
		return notaCredito;
	}


}
