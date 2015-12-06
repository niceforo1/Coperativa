package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import dao.TipoSocioDAO;
import persistencia.HibernateUtil;
import model.TipoSocio;

public class TipoSocioDAOImplement implements TipoSocioDAO {

	@Override
	public List<TipoSocio> listaTipoSocio() throws Exception{
		Session session = null;
		List<TipoSocio> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from TipoSocio");
			lista = (List<TipoSocio>) query.list();
		}catch(ConstraintViolationException e){
			//System.out.println("ConstraintViolationException: "+ "\n " + e.getSQLException() + e.getMessage());
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());		
		}catch(HibernateException e){
			System.out.println("error puto: " +e.getMessage());			
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
	public void insertarTipoSocio(TipoSocio tipoSocio) throws Exception{
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(tipoSocio);
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
	public void modificarTipoSocio(TipoSocio tipoSocio) throws Exception{
				
	}
	
	@Override
	public void eliminarTipoSocio(TipoSocio tipoSocio) throws Exception{
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();			
			session.delete(tipoSocio);
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
	public TipoSocio buscarTipoSocioId(Long id) throws Exception {
		Session session = null;
		TipoSocio tipoSocio = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tipoSocio = (TipoSocio)session.get(TipoSocio.class, id.longValue());			
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
		return tipoSocio;
	}
}
