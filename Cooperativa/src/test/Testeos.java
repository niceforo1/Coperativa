package test;

import java.util.Calendar;

import dao.ConceptoFacturacionDAO;
import dao.ConexionDAO;
import dao.ConfiguracionFacturaDAO;
import dao.EstadoPeriodoDAO;
import dao.FacturaDAO;
import dao.LecturaDAO;
import dao.PeriodoCanonDAO;
import dao.PeriodoFacturacionDAO;
import dao.PeriodoLecturaDAO;
import dao.TipoSuministroDAO;
import dao.impl.ConceptoFacturacionDAOImplement;
import dao.impl.ConexionDAOImplement;
import dao.impl.ConfiguracionFacturaDAOImplement;
import dao.impl.EstadoPeriodoDAOImplement;
import dao.impl.FacturaDAOImplement;
import dao.impl.LecturaDAOImplement;
import dao.impl.PeriodoCanonDAOImplement;
import dao.impl.PeriodoFacturacionDAOImplement;
import dao.impl.PeriodoLecturaDAOImplement;
import dao.impl.TipoSuministroDAOImplement;
import model.ConceptoFacturacion;
import model.Conexion;
import model.ConfiguracionFactura;
import model.EstadoPeriodo;
import model.Factura;
import model.Lectura;
import model.PeriodoCanon;
import model.PeriodoFacturacion;
import model.PeriodoLectura;
import model.TipoSuministro;

public class Testeos {
	public static void main(String[] args) {

		PeriodoLecturaDAO periodoLecturaDAO = new PeriodoLecturaDAOImplement();
		LecturaDAO lecturaDAO = new LecturaDAOImplement();
		ConceptoFacturacionDAO conceptoFacturacionDAO = new ConceptoFacturacionDAOImplement(); 
		PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
		FacturaDAO daoFactura = new FacturaDAOImplement();
		try {
			System.out.println(lecturaDAO.buscarLecturasPorPeriodo(periodoLecturaDAO.buscarPeriodoLecturaAbierto()).size());
			for (Lectura lec : lecturaDAO.buscarLecturasPorPeriodo(periodoLecturaDAO.buscarPeriodoLecturaAbierto())) {
				Factura fact = new Factura();
				long totalConsumido = lec.getLecturaActual()-lec.getLecturaAnterior();
				Float importeTramos= 0F;
				if(totalConsumido >50){
					fact.setTramo6(totalConsumido-50);
					fact.setTramo5(10L);
					fact.setTramo4(10L);
					fact.setTramo3(10L);
					fact.setTramo2(10L);
					fact.setTramo1(10L);
					importeTramos = (0.27F*10F)+(0.41F*10F)+(2.64F*10F)+(3.70F*10F)+(4.26F*10F)+(6.38F*fact.getTramo6());					
				}else if(totalConsumido >40){
					fact.setTramo5(totalConsumido-40);
					fact.setTramo4(10L);
					fact.setTramo3(10L);
					fact.setTramo2(10L);
					fact.setTramo1(10L);
					importeTramos = (0.27F*10F)+(0.41F*10F)+(2.64F*10F)+(3.70F*10F)+(4.26F*fact.getTramo5());
				}else if(totalConsumido >30){
					fact.setTramo4(totalConsumido-30);
					fact.setTramo3(10L);
					fact.setTramo2(10L);
					fact.setTramo1(10L);
					importeTramos = (0.27F*10F)+(0.41F*10F)+(2.64F*10F)+(3.70F*fact.getTramo4());
				}else if(totalConsumido >20){
					fact.setTramo3(totalConsumido-20);
					fact.setTramo2(10L);
					fact.setTramo1(10L);
					importeTramos = (0.27F*10F)+(0.41F*10F)+(2.64F*fact.getTramo3());
				}else if(totalConsumido >10){
					fact.setTramo2(totalConsumido-10);
					fact.setTramo1(10L);
					importeTramos = (0.27F*10F)+(0.41F*fact.getTramo2());
				}else{
					fact.setTramo1(totalConsumido);
					importeTramos = (0.27F*fact.getTramo1());
				}		
				fact.setCargoFijo(lec.getConexion().getTipoSuministro().getImporte());
				fact.setCapitalSocial((0.1F*(fact.getCargoFijo()+importeTramos))+5F);				
				fact.setConceptoFacturacion(conceptoFacturacionDAO.buscarConceptoFacturacionId(1L));				
				fact.setConexion(lec.getConexion());				
				fact.setErsep(0.012F*(fact.getCargoFijo()+importeTramos));								
				fact.setImpresionesOtros(7F);//FIJO
				fact.setInteresesSegVenc(3F);
				fact.setIva(lec.getConexion().getSocio().getCondicionIva().getPorcentaje()*(fact.getCargoFijo()+importeTramos));
				fact.setPeriodoFacturacion(periodoFacturacionDAO.buscarPeriodoFacturacionAbierto());
				fact.setRecuperoInversion(5F);//FIJO
				fact.setTipoFactura("C");	
				fact.setImporteTotal(fact.getCargoFijo()+
						importeTramos+
						fact.getCapitalSocial()+
						fact.getErsep()+
						fact.getRecuperoInversion()+
						fact.getImpresionesOtros()+
						fact.getIva());
				
				daoFactura.insertarFactura(fact);
			}

		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}