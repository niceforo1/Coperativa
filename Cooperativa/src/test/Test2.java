package test;

import java.util.Date;

import dao.ConexionDAO;
import dao.ConexionesSaldosDAO;
import dao.PeriodoCespDAO;
import dao.impl.ConexionDAOImplement;
import dao.impl.ConexionesSaldosDAOImplement;
import dao.impl.PeriodoCespDAOImplement;
import model.ConexionesSaldos;

public class Test2 {

	public static void main(String[] args) {
		ConexionesSaldosDAO conexionesSaldosDAO= new ConexionesSaldosDAOImplement();
		ConexionDAO conexionDAO = new ConexionDAOImplement();
		try {
			ConexionesSaldos conexionesSaldos = new ConexionesSaldos();
			conexionesSaldos.setSaldoTotal(100F);
			conexionesSaldos.setUltimoVencRegistrado(new Date());
			conexionesSaldos.setConexion(conexionDAO.buscarConexionID(6L));
			conexionesSaldosDAO.insertarConexionesSaldos(conexionesSaldos);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
