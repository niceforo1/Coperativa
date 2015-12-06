package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import persistencia.HibernateUtil;
import model.Usuario;
import model.UsuarioTipo;
import dao.UsuarioDAO;
import dao.UsuarioTiposDAO;

public class UsuarioTiposDAOImplement implements UsuarioTiposDAO{

	@Override
	public List<UsuarioTipo> listaUsuariosTipo() throws Exception {
		Session session = null;
		List<UsuarioTipo> lstUsuarioTipo = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from UsuarioTipo");
			lstUsuarioTipo = (List<UsuarioTipo>)query.list();
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
		return lstUsuarioTipo;
		
	}

	@Override
	public void insertarUsuarioTipo(UsuarioTipo tipoUsuario) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(tipoUsuario);
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
	public void modificarUsuarioTipo(UsuarioTipo tipoUsuario) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarUsuarioTipo(UsuarioTipo tipoUsuario) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(tipoUsuario);
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
	public UsuarioTipo buscarUsuarioTipoId(Long tipoUsuarioId) throws Exception {
		Session session = null;
		UsuarioTipo tipoUsuario = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();			
			tipoUsuario = (UsuarioTipo)session.get(UsuarioTipo.class,tipoUsuarioId.longValue());
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
		return tipoUsuario;		
	}

	/*
		
	
	@Override
	public Usuario buscarUsuario(String username, String password) throws Exception {
		Session session = null;
		Usuario usuario= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();			
			Query query = session.createQuery("from Usuario as usu"
											 + " where usu.usuario = ? "
											 + " and usu.password = ? ");
			query.setString(0, username);
			query.setString(1, password);
			if (query.list().size()>0){
				usuario = (Usuario)query.list().get(0);	
			}				
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
		return usuario;		
	}*/

}
