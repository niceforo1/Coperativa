package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import dao.ConceptoFacturacionDAO;
import model.ConceptoFacturacion;
import model.CondicionIva;
import persistencia.HibernateUtil;


public class ConceptoFacturacionDAOImplement implements ConceptoFacturacionDAO{
	
	@Override
	public List<ConceptoFacturacion> listaConceptoFacturacion() throws Exception {
		Session session = null;
		List<ConceptoFacturacion> lstConceptoFacturacion = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from ConceptoFacturacion");
			lstConceptoFacturacion = (List<ConceptoFacturacion>)query.list();
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
		return lstConceptoFacturacion;
	}
	
	@Override
	public void insertarConceptoFacturacion(ConceptoFacturacion conceptoFacturacion) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(conceptoFacturacion);
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
	public void modificarConceptoFacturacion(ConceptoFacturacion conceptoFacturacion) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarConceptoFacturacion(ConceptoFacturacion conceptoFacturacion) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();			
			session.delete(conceptoFacturacion);
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
	public ConceptoFacturacion buscarConceptoFacturacionId(Long id) throws Exception {
		Session session = null;
		ConceptoFacturacion conceptoFacturacion = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			conceptoFacturacion = (ConceptoFacturacion)session.get(ConceptoFacturacion.class, id.longValue());			
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
		return conceptoFacturacion;
	}

	

}
