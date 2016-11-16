package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;


import model.TipoDocumento;
import persistencia.HibernateUtil;
import dao.TipoDocumentoDAO;

public class TipoDocumentoDAOImplement implements TipoDocumentoDAO{

	@Override
	public List<TipoDocumento> listaTipoDocumento() throws Exception {
		Session session = null;
		List<TipoDocumento> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from TipoDocumento td"
											+ " where not td.descripcion = ?");
			query.setString(0, "CUIT");
			lista = (List<TipoDocumento>) query.list();
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
	public List<TipoDocumento> listaTipoDocumentoCUIT() throws Exception {
		Session session = null;
		List<TipoDocumento> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from TipoDocumento td"
											+ " where td.descripcion = ?");
			query.setString(0, "CUIT");
			lista = (List<TipoDocumento>) query.list();
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
	public void insertarTipoDocumento(TipoDocumento tipoDocumento) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(tipoDocumento);
			session.getTransaction().commit();
		}catch (ConstraintViolationException e){
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
	public void modificarTipoDocumento(TipoDocumento tipoDocumento)	throws Exception {
				
	}

	@Override
	public void eliminarTipoDocumento(TipoDocumento tipoDocumento) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(tipoDocumento);
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
	public TipoDocumento buscarTipoDocumentoID(Long id) throws Exception {
		Session session = null;
		TipoDocumento tipoDocumento = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tipoDocumento = (TipoDocumento)session.get(TipoDocumento.class,id.longValue());
			session.getTransaction().commit();
		}catch(ConstraintViolationException e){
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
		return tipoDocumento;
	}
	
}
