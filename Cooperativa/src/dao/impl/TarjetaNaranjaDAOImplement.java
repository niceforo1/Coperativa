package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;


import model.TarjetaNaranja;
import persistencia.HibernateUtil;
import dao.TarjetaNaranjaDAO;

public class TarjetaNaranjaDAOImplement implements TarjetaNaranjaDAO{

	@Override
	public List<TarjetaNaranja> listaTarjetaNaranja() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertarTarjetaNaranja(TarjetaNaranja tarjetaNaranja)
			throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(tarjetaNaranja);
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
	public void modificarTarjetaNaranja(TarjetaNaranja tarjetaNaranja)
			throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(tarjetaNaranja);
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
	public void eliminarTarjetaNaranja(TarjetaNaranja tarjetaNaranja)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TarjetaNaranja buscarTarjetaNaranjaID(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
