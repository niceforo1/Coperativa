package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import dao.PeriodoLecturaDAO;
import model.EstadoPeriodo;
import model.PeriodoLectura;
import model.Socio;
import persistencia.HibernateUtil;

public class PeriodoLecturaDAOImplement implements PeriodoLecturaDAO{

	@Override
	public List<PeriodoLectura> listaPeriodoLectura() throws Exception {
		Session session = null;
		List<PeriodoLectura> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from PeriodoLectura");
			lista = (List<PeriodoLectura>) query.list();
		}catch(ConstraintViolationException e){			
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
	public void insertarPeriodoLectura(PeriodoLectura periodo) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(periodo);
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
	public void modificarPeriodoLectura(PeriodoLectura periodo) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(periodo);
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
	public void eliminarPeriodoLectura(PeriodoLectura periodo) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(periodo);
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
	public PeriodoLectura buscarPeriodoLecturaId(Long id) throws Exception {
		Session session = null;
		PeriodoLectura periodoLectura= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			periodoLectura = (PeriodoLectura)session.get(PeriodoLectura.class, id.longValue());			
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
		return periodoLectura;	
	}

	@Override
	public PeriodoLectura buscarPeriodoLecturaAbierto() throws Exception {
		Session session = null;
		PeriodoLectura periodo= null;
		String estado = "EN PROCESO";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from PeriodoLectura as s" 
											// + " inner join EstadoSocio as es"
											// + " on es.id = s.estadoSocio"
											 + " where s.estadoPeriodo.descripcion = ?");
			query.setString(0, estado);
			periodo = (PeriodoLectura) query.list().get(0);
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
		return periodo;		
	}

}
