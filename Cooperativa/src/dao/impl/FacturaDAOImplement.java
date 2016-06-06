package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import dao.FacturaDAO;
import model.Conexion;
import model.EstadoSocio;
import model.Factura;
import model.PeriodosSaldos;
import persistencia.HibernateUtil;

public class FacturaDAOImplement implements FacturaDAO {

	@Override
	public List<Factura> listaFactura() throws Exception {
		Session session = null;
		List<Factura> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Factura");
			lista = (List<Factura>) query.list();
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
	public List<Factura> listaFacturaConexion(Long conexionID) throws Exception {
		Session session = null;
		List<Factura> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Factura as fc"
											+ " where fc.conexion.id = ?");
			query.setLong(0, conexionID);
			lista = (List<Factura>) query.list();
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
	public List<Factura> listaFacturaPeriodoFact(Long periodoFactID) throws Exception {
		Session session = null;
		List<Factura> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Factura as fc"
											+ " where fc.periodoFacturacion.id = ?");
			query.setLong(0, periodoFactID);
			lista = (List<Factura>) query.list();
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
	public void insertarFactura(Factura factura) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(factura);
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
	public void modificarFactura(Factura factura) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(factura);
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
	public void eliminarFactura(Factura factura) throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(factura);
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
	public Factura buscarFactura(Long id) throws Exception {
		Session session = null;
		Factura factura = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			factura = (Factura)session.get(Factura.class, id.longValue());			
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
		return factura;		
	}

	@Override
	public List<Factura> buscarFacturaPerSaldo(PeriodosSaldos periodosSaldos) throws Exception {
		Session session = null;
		List<Factura> lstFacturas = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Factura f"
										  + " where f.periodoFacturacion.mes = ?"
										  + " and f.periodoFacturacion.anio = ?"
										  + " and f.conexion.id = ?");
			query.setLong(0, periodosSaldos.getMes());
			query.setLong(1, periodosSaldos.getAnio());
			query.setLong(2, periodosSaldos.getConexion().getId());
			lstFacturas = (List<Factura>)query.list();
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
		return lstFacturas;
	}


}
