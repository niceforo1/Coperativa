package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import dao.EstadoPeriodoDAO;
import model.EstadoCivil;
import model.EstadoConexion;
import model.EstadoPeriodo;
import persistencia.HibernateUtil;


public class EstadoPeriodoDAOImplement implements EstadoPeriodoDAO {

	@Override
	public List<EstadoPeriodo> listaEstados() throws Exception {
		Session session = null;
		List<EstadoPeriodo> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from EstadoPeriodo");
			lista = (List<EstadoPeriodo>) query.list();
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
	public void insertarEstadoPeriodo(EstadoPeriodo estado) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(estado);
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
	public void modificarEstadoPeriodo(EstadoPeriodo estado) throws Exception {
				
	}

	@Override
	public void eliminarEstadoPeriodo(EstadoPeriodo estado) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(estado);
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
	public EstadoPeriodo buscarEstadoPeriodoId(Long id) throws Exception {
		Session session = null;
		EstadoPeriodo estadoPeriodo= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			estadoPeriodo = (EstadoPeriodo)session.get(EstadoPeriodo.class, id.longValue());			
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
		return estadoPeriodo;		
	}
	
	public EstadoPeriodo buscarEstadoPeriodo(String descripcion) throws Exception {
		Session session = null;
		EstadoPeriodo estado = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from EstadoPeriodo ec"
											+ " where ec.descripcion = ?");
			query.setString(0, descripcion);
			estado = (EstadoPeriodo) query.list().get(0);			
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
