package dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import dao.ConfiguracionFacturaDAO;
import model.ConfiguracionFactura;
import model.ConfiguracionLectura;
import persistencia.HibernateUtil;

public class ConfiguracionFacturaDAOImplement implements ConfiguracionFacturaDAO {

	@Override
	public ConfiguracionFactura obtenerConfiguracionFactura() throws Exception {
		Session session = null;
		ConfiguracionFactura lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from ConfiguracionFactura");
			lista = (ConfiguracionFactura) query.list().get(0);
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
	public void insertarConfiguracionFactura(ConfiguracionFactura configuracionFactura) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarConfiguracionFactura(ConfiguracionFactura configuracionFactura) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarConfiguracionFactura(ConfiguracionFactura configuracionFactura) throws Exception {
		// TODO Auto-generated method stub

	}

}
