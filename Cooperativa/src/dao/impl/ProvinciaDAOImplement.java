package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;


import model.Pais;
import model.Provincia;
import persistencia.HibernateUtil;
import dao.ProvinciaDAO;

public class ProvinciaDAOImplement implements ProvinciaDAO {

	@Override
	public List<Provincia> listaProvincias() throws Exception {
		Session session = null;
		List<Provincia> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Provincia");
			lista = (List<Provincia>) query.list();
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
	public void insertarProvincia(Provincia provincia) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(provincia);
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
	public void modificarProvincia(Provincia provincia) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarProvincia(Provincia provincia) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(provincia);
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
	public Provincia buscarProvinciaId(Long id) throws Exception {
		Session session = null;
		Provincia provincia = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			provincia = (Provincia)session.get(Provincia.class, id.longValue());			
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
		return provincia;
	}

	@Override
	public Provincia buscarProvinciaDescripcion(String desc) throws Exception {
		Session session = null;
		Provincia provincia	= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Provincia p"
											+ " where p.descripcion = ?");
			query.setString(0, desc);
			provincia = (Provincia) query.list().get(0);	
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
		return provincia;
	}
}
