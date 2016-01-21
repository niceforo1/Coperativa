package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import dao.FacturaDAO;
import model.EstadoSocio;
import model.Factura;
import persistencia.HibernateUtil;

public class FacturaDAOImplement implements FacturaDAO {

	@Override
	public List<Factura> listaFactura() throws Exception {
		Session session = null;
		List<Factura> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Factura");
			lista = (List<Factura>) query.list();
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
	public void insertarFactura(Factura factura) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(factura);
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
	public void modificarFactura(Factura factura) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(factura);
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
	public void eliminarFactura(Factura factura) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(factura);
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
	public Factura buscarFactura(Long id) throws Exception {
		Session session = null;
		Factura factura = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			factura = (Factura)session.get(Factura.class, id.longValue());			
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
		return factura;		
	}

}