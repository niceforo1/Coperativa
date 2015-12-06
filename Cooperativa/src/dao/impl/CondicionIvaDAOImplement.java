package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import dao.CondicionIvaDAO;
import persistencia.HibernateUtil;
import model.CondicionIva;

public class CondicionIvaDAOImplement implements CondicionIvaDAO {

	@Override
	public List<CondicionIva> listaCondicionesIva() throws Exception {
		Session session = null;
		List<CondicionIva> lstCondicionIva = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from CondicionIva");
			lstCondicionIva = (List<CondicionIva>)query.list();
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
		return lstCondicionIva;
	}

	@Override
	public void insertarCondicionIva(CondicionIva condicionIva) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(condicionIva);
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
	public void modificarCondicionIva(CondicionIva condicionIva) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCondicionIva(CondicionIva condicionIva) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();			
			session.delete(condicionIva);
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
	public CondicionIva buscarCondicionIvaId(Long id) throws Exception {
		Session session = null;
		CondicionIva condicionIva = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			condicionIva = (CondicionIva)session.get(CondicionIva.class, id.intValue());			
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
		return condicionIva;
	}

}
