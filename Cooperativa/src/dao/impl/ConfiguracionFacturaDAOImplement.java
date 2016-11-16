package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import dao.ConfiguracionFacturaDAO;
import model.CategoriaConexion;
import model.ConfiguracionFactura;
import model.ConfiguracionLectura;
import persistencia.HibernateUtil;


public class ConfiguracionFacturaDAOImplement implements ConfiguracionFacturaDAO {

	@Override
	public List<ConfiguracionFactura> obtenerConfiguracionFactura() throws Exception {
		Session session = null;
		List<ConfiguracionFactura> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from ConfiguracionFactura");
			lista = (List<ConfiguracionFactura>) query.list();
		}catch(ConstraintViolationException e){
			//System.out.println("ConstraintViolationException: "+ "\n " + e.getSQLException() + e.getMessage());
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
	public void insertarConfiguracionFactura(ConfiguracionFactura configuracionFactura) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(configuracionFactura);
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
	public void modificarConfiguracionFactura(ConfiguracionFactura configuracionFactura) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(configuracionFactura);
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
	public void eliminarConfiguracionFactura(ConfiguracionFactura configuracionFactura) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public ConfiguracionFactura buscarConfiguracionFacturaId(Long id)
			throws Exception {
		Session session = null;
		ConfiguracionFactura confFact = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			confFact = (ConfiguracionFactura)session.get(ConfiguracionFactura.class, id.longValue());			
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
		return confFact;
	}

}
