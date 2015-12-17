package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import persistencia.HibernateUtil;

import model.EstadoSocio;
import dao.EstadoSocioDAO;

public class EstadoSocioDAOImplement implements EstadoSocioDAO {

	@Override
	public List<EstadoSocio> listaEstadoSocio() throws Exception {
		Session session = null;
		List<EstadoSocio> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from EstadoSocio");
			lista = (List<EstadoSocio>) query.list();
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
		return lista;
	}

	@Override
	public void insertarEstadoSocio(EstadoSocio estadoSocio) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(estadoSocio);
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
	public void modificarEstadoSocio(EstadoSocio estadoSocio) throws Exception {
		
		
	}

	@Override
	public void eliminarEstadoSocio(EstadoSocio estadoSocio) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(estadoSocio);
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
	
	public EstadoSocio buscarEstadoSocio(Long id) throws Exception {
		Session session = null;
		EstadoSocio estadoSocio = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			estadoSocio = (EstadoSocio)session.get(EstadoSocio.class, id.longValue());			
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
		return estadoSocio;
	}

	@Override
	public EstadoSocio buscarEstadoSocio(String descripcion) throws Exception {
		Session session = null;
		EstadoSocio estado = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from EstadoSocio es"
											+ " where es.descripcion = ?");
			query.setString(0, descripcion);
			estado = (EstadoSocio) query.list().get(0);			
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
		return estado;
	}

}
