package dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;


import model.SociosTransacciones;
import persistencia.HibernateUtil;
import dao.Socios_TransaccionesDAO;

public class Socios_TransaccionesDAOImplement implements Socios_TransaccionesDAO {

	@Override
	public void insertarTransaccion(SociosTransacciones transaccion)
			throws Exception {
		
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(transaccion);
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
	public void modificarTransaccion(SociosTransacciones transaccion)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarTransaccion(SociosTransacciones transaccion)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
