package dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import persistencia.HibernateUtil;
import model.Socios_Transacciones;
import dao.Socios_TransaccionesDAO;

public class Socios_TransaccionesDAOImplement implements Socios_TransaccionesDAO {

	@Override
	public void insertarTransaccion(Socios_Transacciones transaccion)
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
	public void modificarTransaccion(Socios_Transacciones transaccion)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarTransaccion(Socios_Transacciones transaccion)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
