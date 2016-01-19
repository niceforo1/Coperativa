package dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import persistencia.HibernateUtil;
import model.Domicilio;
import dao.DomicilioDAO;

public class DomicilioDAOImplement implements DomicilioDAO{

	@Override
	public void insertarDomicilio(Domicilio domicilio) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(domicilio);
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
	public void modificarDomicilio(Domicilio domicilio) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(domicilio);
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
	public void eliminarDomicilio(Domicilio domicilio) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(domicilio);
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
	public Domicilio buscarDomicilioId(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
