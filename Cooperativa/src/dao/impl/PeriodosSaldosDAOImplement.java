package dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;


import model.Conexion;
import model.ConexionesSaldos;
import model.PeriodosSaldos;
import persistencia.HibernateUtil;
import dao.PeriodosSaldosDAO;

public class PeriodosSaldosDAOImplement implements PeriodosSaldosDAO{

	@Override
	public List<PeriodosSaldos> listaPeriodosSaldos() throws Exception {
		Session session = null;
		List<PeriodosSaldos> lista = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from PeriodosSaldos");
			lista = (List<PeriodosSaldos>) query.list();
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
	public void insertarPeriodosSaldos(PeriodosSaldos periodosSaldos)
			throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(periodosSaldos);
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
	public void modificarPeriodosSaldos(PeriodosSaldos periodosSaldos)
			throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(periodosSaldos);
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
	public void eliminarPeriodosSaldos(PeriodosSaldos periodosSaldos)
			throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(periodosSaldos);
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
	public PeriodosSaldos buscarPeriodosSaldosId(Long id) throws Exception {
		Session session = null;
		PeriodosSaldos periodoSaldos = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			periodoSaldos = (PeriodosSaldos)session.get(PeriodosSaldos.class, id.longValue());			
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
		return periodoSaldos;
	}
	
	public PeriodosSaldos buscarPeriodosSaldosMesAnio(Long id, long mes, long anio) throws Exception {
		Session session = null;
		PeriodosSaldos periodoSaldos = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from PeriodosSaldos ps"
					+ " where ps.conexion.id = ?"
					+ " and ps.mes = ?"
					+ " and ps.anio =?");
			query.setLong(0,id);
			query.setLong(1,mes);
			query.setLong(2,anio);			
			periodoSaldos = (PeriodosSaldos) query.list().get(0);			
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
		return periodoSaldos;
	}

	@Override
	public List<PeriodosSaldos> buscarPeriodosSaldosConexion(Long id, Date fechaDesde,Date fechaHasta)
			throws Exception {
		Session session = null;
		PeriodosSaldos periodoSaldos = null;
		List<PeriodosSaldos> lista = null;
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		//Calendar fecDesde = Calendar.getInstance();
		//Calendar fecHasta = Calendar.getInstance();
		//fecDesde.setTime(fechaDesde);
		//fecHasta.setTime(fechaHasta);
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			//System.out.println(fechaDesde.toString() + " MES: " + (fecDesde.get(Calendar.MONTH)+1)+ " ANIO: "+fecDesde.get(Calendar.YEAR));
			//System.out.println(fechaDesde.toString() + " MES: " + (fecHasta.get(Calendar.MONTH)+1)+ " ANIO: "+fecHasta.get(Calendar.YEAR));
			/*Query query = session.createQuery("from PeriodosSaldos ps"
											+ " where ps.conexion.id = ?"
											+ " and (ps.mes between ? and ?)"
											+ " and (ps.anio between ? and ?)");*/
			Query query = session.createQuery("from PeriodosSaldos ps"
										+ " where ps.conexion.id = ?"
										+ " AND CONVERT(SMALLDATETIME, '01/'+convert(varchar(10),ps.mes)+'/'+convert(varchar(10),ps.anio)) between ? and ?");
			
			query.setLong(0,id);
			
			query.setString(1, format1.format(fechaDesde));
			query.setString(2, format1.format(fechaHasta));
			/*if(fecDesde.get(Calendar.MONTH)+1 > fecHasta.get(Calendar.MONTH)+1){
				query.setLong(1,fecHasta.get(Calendar.MONTH)+1);
				query.setLong(2,fecDesde.get(Calendar.MONTH)+1);
			}else{				
				query.setLong(1,fecDesde.get(Calendar.MONTH)+1);
				query.setLong(2,fecHasta.get(Calendar.MONTH)+1);
			}
			if(fecDesde.get(Calendar.YEAR) > fecHasta.get(Calendar.YEAR)){
				query.setLong(3,fecHasta.get(Calendar.YEAR));
				query.setLong(4,fecDesde.get(Calendar.YEAR));				
			}else{				
				query.setLong(3,fecDesde.get(Calendar.YEAR));
				query.setLong(4,fecHasta.get(Calendar.YEAR));
			}	*/		
			lista = query.list();			
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
		return lista;
	}

}
