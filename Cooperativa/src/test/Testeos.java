package test;

import java.util.Calendar;

import dao.ConceptoFacturacionDAO;
import dao.ConfiguracionFacturaDAO;
import dao.EstadoPeriodoDAO;
import dao.PeriodoCanonDAO;
import dao.PeriodoFacturacionDAO;
import dao.impl.ConceptoFacturacionDAOImplement;
import dao.impl.ConfiguracionFacturaDAOImplement;
import dao.impl.EstadoPeriodoDAOImplement;
import dao.impl.PeriodoCanonDAOImplement;
import dao.impl.PeriodoFacturacionDAOImplement;
import model.ConceptoFacturacion;
import model.ConfiguracionFactura;
import model.EstadoPeriodo;
import model.PeriodoCanon;
import model.PeriodoFacturacion;

public class Testeos {
	public static void main(String[] args) {
		ConfiguracionFacturaDAO configuracionFacturaDAO = new ConfiguracionFacturaDAOImplement();
		ConfiguracionFactura configuracionFactura = new ConfiguracionFactura();
		try {
			System.out.println(configuracionFacturaDAO.obtenerConfiguracionFactura().toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}