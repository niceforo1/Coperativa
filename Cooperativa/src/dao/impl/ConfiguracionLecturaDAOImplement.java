package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import persistencia.HibernateUtil;
import model.ConfiguracionLectura;
import model.EstadoCivil;
import dao.ConfiguracionLecturaDAO;

public class ConfiguracionLecturaDAOImplement implements ConfiguracionLecturaDAO{

	@Override
	public List<ConfiguracionLectura> listaConfiguracionLectura()
			throws Exception {
		Session session = null;
		List<ConfiguracionLectura> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from ConfiguracionLectura");
			lista = (List<ConfiguracionLectura>) query.list();
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
