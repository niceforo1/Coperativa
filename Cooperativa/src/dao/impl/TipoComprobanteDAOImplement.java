package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;


import model.TipoComprobante;
import persistencia.HibernateUtil;
import dao.TipoComprobanteDAO;

public class TipoComprobanteDAOImplement implements TipoComprobanteDAO{

	@Override
	public List<TipoComprobante> listaTipoComprobante() throws Exception {
		Session session = null;
		List<TipoComprobante> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from TipoComprobante");
			lista = (List<TipoComprobante>) query.list();
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
	public void insertarTipoComprobante(TipoComprobante tipoComprobante)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarTipoComprobante(TipoComprobante tipoComprobante)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarTipoComprobante(TipoComprobante tipoComprobante)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TipoComprobante buscarTipoComprobanteId(Long id) throws Exception {
		Session session = null;
		TipoComprobante tipoComprobante = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tipoComprobante = (TipoComprobante)session.get(TipoComprobante.class, id.longValue());			
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
		return tipoComprobante;
	}

}
