package dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import dao.CuentaCorrienteDAO;
import model.ConfiguracionLectura;
import model.CuentaCorriente;
import persistencia.HibernateUtil;

public class CuentaCorrienteDAOImplement implements CuentaCorrienteDAO{
	@Override
	public List obtenerCuentaCorriente(Long conexionId,Date fechaDesde,Date fechaHasta) throws Exception {
		Session session = null;
		List<CuentaCorriente> result;
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			result =session.createSQLQuery("select ID_CONEXION, IMPORTE_TOTAL, MES, ANIO, TIPO, NRO_COMPROBANTE"
					+ " from cuenta_corriente"
					+ " where ID_CONEXION = ?"
					+ " AND CONVERT(SMALLDATETIME, '01/'+convert(varchar(10),mes)+'/'+convert(varchar(10),anio)) between ? and ?"
					+ " order by ANIO desc, mes desc")
						.setLong(0,conexionId)	
						.setString(1, format1.format(fechaDesde))
						.setString(2, format1.format(fechaHasta)).list();
		}catch(ConstraintViolationException e){
			//System.out.println("ConstraintViolationException: "+ "\n " + e.getSQLException() + e.getMessage());
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());		
		}catch(HibernateException e){
			System.out.println("error : " +e.getMessage());			
			throw new Exception(e);		
		}finally{
			if(session != null){
				System.out.println("CIERRA LA SESION");
				session.close();
			}
		}
		return result; 
	}
}
