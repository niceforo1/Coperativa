package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;


import model.ConfiguracionLectura;
import model.EstadoCivil;
import persistencia.HibernateUtil;
import dao.ConfiguracionLecturaDAO;

public class ConfiguracionLecturaDAOImplement implements ConfiguracionLecturaDAO{

	@Override
	public ConfiguracionLectura obtenerConfiguracionLectura() throws Exception {
		Session session = null;
		ConfiguracionLectura lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from ConfiguracionLectura");
			lista = (ConfiguracionLectura) query.list().get(0);
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
	public void insertarConfiguracionLectura(
			ConfiguracionLectura configuracionLectura) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarConfiguracionLectura(
			ConfiguracionLectura configuracionLectura) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarConfiguracionLectura(
			ConfiguracionLectura configuracionLectura) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
