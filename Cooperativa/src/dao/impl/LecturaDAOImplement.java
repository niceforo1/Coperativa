package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import dao.LecturaDAO;
import model.Conexion;
import model.Lectura;
import model.PeriodoLectura;
import model.Socio;
import persistencia.HibernateUtil;


public class LecturaDAOImplement implements LecturaDAO {

	@Override
	public List<Lectura> listaLectura() throws Exception {
		Session session = null;
		List<Lectura> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Lectura");
			lista = (List<Lectura>) query.list();
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
	public void insertarLectura(Lectura lectura) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(lectura);
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
	public void modificarLectura(Lectura lectura) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(lectura);
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
	public void eliminarLectura(Lectura lectura) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(lectura);
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
	public Lectura buscarLecturaId(Long id) throws Exception {
		Session session = null;
		Lectura lectura = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			lectura = (Lectura)session.get(Lectura.class, id.longValue());			
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
		return lectura;
	}

	@Override
	public List<Lectura> buscarLecturasPorPeriodo(PeriodoLectura perido)throws Exception {
		Session session = null;
		List<Lectura> lstLectura = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Lectura le"
					  					  + " where le.periodoLectura.id = ? ");
			query.setLong(0, perido.getId());
			lstLectura = (List<Lectura>) query.list();		
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
		return lstLectura;
	}

	@Override
	public List<Lectura> buscarLecturasPorConexion(Conexion conexion) throws Exception {
		Session session = null;
		List<Lectura> lstLectura = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Lectura le"
					  					  + " where le.conexion.id = ? ");
			query.setLong(0, conexion.getId());
			lstLectura = (List<Lectura>) query.list();		
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
		return lstLectura;
	}

}
