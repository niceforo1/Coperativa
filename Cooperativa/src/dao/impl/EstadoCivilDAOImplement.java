package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import persistencia.HibernateUtil;
import model.EstadoCivil;
import dao.EstadoCivilDAO;

public class EstadoCivilDAOImplement implements EstadoCivilDAO {

	@Override
	public List<EstadoCivil> listaEstadoCivil() throws Exception {
		Session session = null;
		List<EstadoCivil> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from EstadoCivil");
			lista = (List<EstadoCivil>) query.list();
		}catch(ConstraintViolationException e){
			//System.out.println("ConstraintViolationException: "+ "\n " + e.getSQLException() + e.getMessage());
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());		
		}catch(HibernateException e){
			System.out.println("error : " +e.getMessage());			
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
	public void insertarEstadoCivil(EstadoCivil estadoCivil) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(estadoCivil);
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
	public void modificarEstadoCivil(EstadoCivil estadoCivil) throws Exception {
				
	}

	@Override
	public void eliminarEstadoCivil(EstadoCivil estadoCivil) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(estadoCivil);
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
	public EstadoCivil buscarEstadoCivilID(Long id) throws Exception {
		Session session = null;
		EstadoCivil estadoCivil= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			estadoCivil = (EstadoCivil)session.get(EstadoCivil.class, id.longValue());			
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
		return estadoCivil;
	}

	
}
