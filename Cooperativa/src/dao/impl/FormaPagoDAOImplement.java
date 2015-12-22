package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import persistencia.HibernateUtil;
import model.FormaPago;
import model.RegimenPropiedad;
import dao.FormaPagoDAO;


public class FormaPagoDAOImplement implements FormaPagoDAO {

	@Override
	public List<FormaPago> listaFormaPago() throws Exception {
		Session session = null;
		List<FormaPago> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from FormaPago");
			lista = (List<FormaPago>) query.list();
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
	public void insertarFormaPago(FormaPago forma) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(forma);
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
	public void modificarFormaPago(FormaPago forma) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(forma);
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
	public void eliminarFormaPago(FormaPago forma) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(forma);
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
	public FormaPago buscarFormaPagoId(Long id) throws Exception {
		Session session = null;
		FormaPago forma = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			forma = (FormaPago)session.get(FormaPago.class, id.longValue());			
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
		return forma;
	}
}
