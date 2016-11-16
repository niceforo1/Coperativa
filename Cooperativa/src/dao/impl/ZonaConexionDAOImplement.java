package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;


import model.ZonaConexion;
import persistencia.HibernateUtil;
import dao.ZonaConexionDAO;

public class ZonaConexionDAOImplement implements ZonaConexionDAO{

	@Override
	public List<ZonaConexion> listaZonas() throws Exception {
		Session session = null;
		List<ZonaConexion> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from ZonaConexion");
			lista = (List<ZonaConexion>) query.list();
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
	public void insertarZonaConexion(ZonaConexion zona) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(zona);
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
	public void modificarZonaConexion(ZonaConexion zona) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(zona);
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
	public void eliminarZonaConexion(ZonaConexion zona) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(zona);
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
	public ZonaConexion buscarZonaConexionId(Long id) throws Exception {
		Session session = null;
		ZonaConexion zona= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			zona = (ZonaConexion)session.get(ZonaConexion.class, id.longValue());			
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
		return zona;
	}

}
