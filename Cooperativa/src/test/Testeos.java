package test;

import dao.EstadoPeriodoDAO;
import dao.impl.EstadoPeriodoDAOImplement;
import model.EstadoPeriodo;

public class Testeos {
	public static void main(String[] args) {
		EstadoPeriodoDAO estadoPeriodoDAO = new EstadoPeriodoDAOImplement();
		try {
			System.out.println(estadoPeriodoDAO.buscarEstadoPeriodo("EN PROCESO").getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}