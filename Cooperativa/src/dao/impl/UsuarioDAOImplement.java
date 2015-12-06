package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import persistencia.HibernateUtil;
import model.Usuario;
import dao.UsuarioDAO;

public class UsuarioDAOImplement implements UsuarioDAO{

	@Override
	public List<Usuario> listaUsuarios() throws Exception {
		Session session = null;
		List<Usuario> lstUsuario = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Usuario");
			lstUsuario = (List<Usuario>)query.list();
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
		return lstUsuario;		
	}

	@Override
	public void insertarUsuario(Usuario usuario) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(usuario);
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
	public void modificarUsuario(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarUsuario(Usuario usuario) throws Exception {
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(usuario);
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
	}

}
