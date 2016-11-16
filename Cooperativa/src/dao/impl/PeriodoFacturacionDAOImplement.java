package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import dao.PeriodoFacturacionDAO;
import model.PeriodoFacturacion;
import model.PeriodoLectura;
import persistencia.HibernateUtil;


public class PeriodoFacturacionDAOImplement implements PeriodoFacturacionDAO{

	@Override
	public List<PeriodoFacturacion> listaPeriodoFacturacion() throws Exception {
		Session session = null;
		List<PeriodoFacturacion> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from PeriodoFacturacion pf order by pf.id desc");
			lista = (List<PeriodoFacturacion>) query.list();
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
		return lista;
	}

	@Override
	public void insertarPeriodoFacturacion(PeriodoFacturacion periodoFacturacion) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(periodoFacturacion);
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
	public void modificarPeriodoFacturacion(PeriodoFacturacion periodoFacturacion) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(periodoFacturacion);
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
	public void eliminarPeriodoFacturacion(PeriodoFacturacion periodoFacturacion) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(periodoFacturacion);
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
	public PeriodoFacturacion buscarPeriodoFacturacionId(Long id) throws Exception {
		Session session = null;
		PeriodoFacturacion periodoFacturacion= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			periodoFacturacion = (PeriodoFacturacion)session.get(PeriodoFacturacion.class, id.longValue());			
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
		return periodoFacturacion;	
	}

	@Override
	public PeriodoFacturacion buscarPeriodoFacturacionAbierto()
			throws Exception {
		Session session = null;
		PeriodoFacturacion periodo= null;
		String estado = "CERRADO";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from PeriodoFacturacion as s" 
											// + " inner join EstadoSocio as es"
											// + " on es.id = s.estadoSocio"
											 + " where s.estadoPeriodo.descripcion = ?");
			query.setString(0, estado);
			periodo = (PeriodoFacturacion) query.list().get(0);
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
	@Override
	public List<PeriodoFacturacion> lstPeriodosFacturacionNoHist() throws Exception {
		Session session = null;
		List<PeriodoFacturacion> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from PeriodoFacturacion pf where pf.periodoCesp.id != 1 order by pf.id desc");
			lista = (List<PeriodoFacturacion>) query.list();
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
		return lista;
	}
}
