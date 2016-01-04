package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import persistencia.HibernateUtil;
import model.Conexion;
import model.Socio;
import dao.ConexionDAO;

public class ConexionDAOImplement implements ConexionDAO{

	@Override
	public List<Conexion> listaConexion() throws Exception {
		Session session = null;
		List<Conexion> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Conexion");
			lista = (List<Conexion>) query.list();
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
	public void insertarConexion(Conexion conexion) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(conexion);
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
	public void modificarConexion(Conexion conexion) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(conexion);
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
	public void eliminarConexion(Conexion conexion) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(conexion);
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
	public Conexion buscarConexionID(Long id) throws Exception {
		Session session = null;
		Conexion conexion = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			conexion = (Conexion)session.get(Conexion.class, id.longValue());
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
		return conexion;
	}

	@Override
	public List<Socio> buscarSocioPorConexion(Conexion conexion) throws Exception {
		Session session = null;
		List<Socio> socio= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Socio s"
											+ " where s.conexiones.id = ?");
//											+ " where c.id = ?");
//											+ " INNER JOIN SOCIOS_CONEXIONES sc"
//											+ "	ON s.ID_SOCIO = sc.SOCIOS_ID_SOCIO"
//											+ "	INNER JOIN CONEXIONES c"
//											+ "	ON c.ID_CONEXION = sc.conexiones_ID_CONEXION"
//											+ "	WHERE c.ID_CONEXION = ?");
			query.setLong(0, conexion.getId());
			socio = (List<Socio>) query.list();
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
		return socio;
	}

}
