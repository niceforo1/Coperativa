package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;


import model.CategoriaConexion;
import model.TipoSuministro;
import persistencia.HibernateUtil;
import dao.CategoriaConexionDAO;

public class CategoriaConexionDAOImplement implements CategoriaConexionDAO{

	@Override
	public List<CategoriaConexion> listaCategoriaConexion() throws Exception {
		Session session = null;
		List<CategoriaConexion> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from CategoriaConexion");
			lista = (List<CategoriaConexion>) query.list();
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
	public void insertarCategoriaConexion(CategoriaConexion categoria)
			throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(categoria);
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
	public void modificarCategoriaConexion(CategoriaConexion categoria)
			throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(categoria);
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
	public void eliminarCategoriaConexion(CategoriaConexion categoria)
			throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(categoria);
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
	public CategoriaConexion buscarCategoriaConexionId(Long id)
			throws Exception {
		Session session = null;
		CategoriaConexion categoria = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			categoria = (CategoriaConexion)session.get(CategoriaConexion.class, id.longValue());			
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
		return categoria;
	}

}
