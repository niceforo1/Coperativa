package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;


import model.Socio;
import persistencia.HibernateUtil;
import dao.SocioDAO;

public class SocioDAOImplement implements SocioDAO {

	@Override
	public List<Socio> listaSocio() throws Exception {
		Session session = null;
		List<Socio> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Socio");
			lista = (List<Socio>) query.list();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());
		} catch (HibernateException e) {
			System.out.println("error: " + e.getMessage());
			throw new Exception(e);
		} finally {
			if (session != null) {
				System.out.println("CIERRA LA SESION");
				session.close();
			}
		}
		return lista;
	}

	@Override
	public List<Socio> listaSociosActivos(String estado) throws Exception {
		Session session = null;
		List<Socio> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Socio as s"
					// + " inner join EstadoSocio as es"
					// + " on es.id = s.estadoSocio"
					+ " where s.estadoSocio.descripcion = ?");
			query.setString(0, estado);
			lista = (List<Socio>) query.list();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());
		} catch (HibernateException e) {
			System.out.println("error: " + e.getMessage());
			throw new Exception(e);
		} finally {
			if (session != null) {
				System.out.println("CIERRA LA SESION");
				session.close();
			}
		}
		return lista;
	}

	@Override
	public void insertarSocio(Socio socio) throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(socio);
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void modificarSocio(Socio socio) throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(socio);
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void eliminarSocio(Socio socio) throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(socio);
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public Socio buscarSocioID(Long id) throws Exception {
		Session session = null;
		Socio socio = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			socio = (Socio) session.get(Socio.class, id.longValue());
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			// System.out.println("ConstraintViolationException: "+ "\n " +
			// e.getSQLException() + e.getMessage());
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());
		} catch (HibernateException e) {
			throw new Exception(e);
		} finally {
			if (session != null) {
				System.out.println("CIERRA LA SESION");
				session.close();
			}
		}
		return socio;
	}

	@Override
	public List<Socio> buscarSocioNombre(String nombre) throws Exception {
		Session session = null;
		List<Socio> lstSocio = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Socio as s"
					// + " inner join EstadoSocio as es"
					// + " on es.id = s.estadoSocio"
					+ " where s.apellidoRazonSocial like  ?");
			query.setString(0, "%"+nombre+"%");
			lstSocio = (List<Socio>)query.list();
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw new Exception(e.getSQLException());
		} catch (HibernateException e) {
			throw new Exception(e);
		} finally {
			if (session != null) {
				System.out.println("CIERRA LA SESION");
				session.close();
			}
		}
		return lstSocio;

	}

}
