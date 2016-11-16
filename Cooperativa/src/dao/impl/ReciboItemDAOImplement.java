package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;


import model.Recibo;
import model.ReciboItem;
import persistencia.HibernateUtil;
import dao.ReciboItemDAO;

public class ReciboItemDAOImplement implements ReciboItemDAO{

	@Override
	public List<ReciboItem> listaReciboItem() throws Exception {
		Session session = null;
		List<ReciboItem> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from ReciboItem");
			lista = (List<ReciboItem>) query.list();
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
	public Long insertarReciboItem(ReciboItem recibo) throws Exception {
		Session session = null;
		Long id = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			id = (Long)session.save(recibo);
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
	public void modificarReciboItem(ReciboItem recibo) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(recibo);
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
	public void eliminarReciboItem(ReciboItem recibo) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(recibo);
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
	public ReciboItem buscarReciboItemId(Long id) throws Exception {
		Session session = null;
		ReciboItem recibo = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			recibo = (ReciboItem)session.get(ReciboItem.class, id.longValue());			
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
		return recibo;
	}

}
