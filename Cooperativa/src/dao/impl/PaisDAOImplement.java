package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;


import model.Pais;
import model.TipoDomicilio;
import persistencia.HibernateUtil;
import dao.PaisDAO;

public class PaisDAOImplement implements PaisDAO{

	@Override
	public List<Pais> listaPais() throws Exception {
		Session session = null;
		List<Pais> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Pais");
			lista = (List<Pais>) query.list();
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
	public Long insertarPais(Pais pais) throws Exception {
		Session session = null;
		Long id = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			id = (Long)session.save(pais);			
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
		return id;
	}

	@Override
	public void modificarPais(Pais pais) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarPais(Pais pais) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(pais);
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
	public Pais buscarPaisId(Long id) throws Exception {
		Session session = null;
		Pais pais= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			pais = (Pais)session.get(Pais.class, id.longValue());			
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
		return pais;
	}
	
	@Override
	public Pais buscarPaisDescripcion(String desc) throws Exception {
		Session session = null;
		Pais pais= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Pais p"
											+ " where p.descripcion = ?");
			query.setString(0, desc);
			pais = (Pais) query.list().get(0);	
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
		return pais;
	}

}
