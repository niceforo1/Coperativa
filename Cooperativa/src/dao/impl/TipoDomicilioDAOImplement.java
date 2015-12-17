package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import persistencia.HibernateUtil;
import model.EstadoSocio;
import model.TipoDomicilio;
import dao.TipoDomicilioDAO;

public class TipoDomicilioDAOImplement implements TipoDomicilioDAO{

	@Override
	public List<TipoDomicilio> listaTipoDomicilio() throws Exception {
		Session session = null;
		List<TipoDomicilio> lstTipoDomicilio = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from TipoDomicilio");
			lstTipoDomicilio = (List<TipoDomicilio>)query.list();
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
		return lstTipoDomicilio;
	}

	@Override
	public void insertarTipoDomicilio(TipoDomicilio tipoDomicilio) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(tipoDomicilio);
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
	public void modificarTipoDomicilio(TipoDomicilio tipoDomicilio)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarTipoDomicilio(TipoDomicilio tipoDomicilio) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();			
			session.delete(tipoDomicilio);
			session.getTransaction().commit();
		}catch(ConstraintViolationException e){
			//System.out.println("ConstraintViolationException: "+ "\n " + e.getSQLException() +" -> " + e.getMessage());			
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());		
		}catch(HibernateException e){
			//System.out.println("putisimio"+ e.getMessage());			
			session.getTransaction().rollback();
			throw new Exception(e);		
		}finally{
			if(session != null){
				session.close();
			}
		}
		
	}

	@Override
	public TipoDomicilio buscarTipoDomicilioId(Long id) throws Exception {
		Session session = null;
		TipoDomicilio tipoDomicilio = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tipoDomicilio = (TipoDomicilio)session.get(TipoDomicilio.class, id.longValue());
			session.getTransaction().commit();
		}catch(ConstraintViolationException e){
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
		return tipoDomicilio;
	}

	@Override
	public TipoDomicilio buscarTipoDomicilio(String descripcion)
			throws Exception {
		Session session = null;
		TipoDomicilio tipoDomicilio = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from TipoDomicilio td"
											+ " where td.descripcion = ?");
			query.setString(0, descripcion);
			tipoDomicilio = (TipoDomicilio) query.list().get(0);			
		}catch(ConstraintViolationException e){
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());
		}catch(HibernateException e){
			System.out.println("error: " +e.getMessage());
			throw new Exception(e);
		}finally{
			if(session != null){
				System.out.println("CIERRA LA SESION");
				session.close();
			}
		}
		return tipoDomicilio;
	}

}
