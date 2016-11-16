package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;


import model.Provincia;
import model.TipoConexion;
import model.TipoUbicacionCatastral;
import persistencia.HibernateUtil;
import dao.TipoUbicacionCatastralDAO;

public class TipoUbicacionCatastralDAOImplement implements TipoUbicacionCatastralDAO {

	@Override
	public List<TipoUbicacionCatastral> listaTipoUbicacionCatastral()
			throws Exception {
		Session session = null;
		List<TipoUbicacionCatastral> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from TipoUbicacionCatastral");
			lista = (List<TipoUbicacionCatastral>) query.list();
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
	public void insertarTipoUbicacionCatastral(TipoUbicacionCatastral tipo)
			throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(tipo);
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
	public void modificarTipoUbicacionCatastral(TipoUbicacionCatastral tipo)
			throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(tipo);
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
	public void eliminarTipoUbicacionCatastral(TipoUbicacionCatastral tipo)
			throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(tipo);
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
	public TipoUbicacionCatastral buscarTipoUbicacionCatastralId(Long id)
			throws Exception {
		Session session = null;
		TipoUbicacionCatastral tipo = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tipo = (TipoUbicacionCatastral)session.get(TipoUbicacionCatastral.class, id.longValue());			
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
		return tipo;
	}

	@Override
	public TipoUbicacionCatastral buscarTipoUbicacionCatastralDescripcion(
			String desc) throws Exception {
		Session session = null;
		TipoUbicacionCatastral tipo	= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from TipoUbicacionCatastral tUC"
											+ " where tUC.descripcion = ?");
			query.setString(0, desc);
			tipo = (TipoUbicacionCatastral) query.list().get(0);	
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
		return tipo;
	}

}
