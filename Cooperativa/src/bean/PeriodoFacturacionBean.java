package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.ConceptoFacturacionDAO;
import dao.ConexionDAO;
import dao.ConexionesSaldosDAO;
import dao.ConfiguracionFacturaDAO;
import dao.EstadoPeriodoDAO;
import dao.EstadoSocioDAO;
import dao.FacturaDAO;
import dao.LecturaDAO;
import dao.PeriodoCanonDAO;
import dao.PeriodoFacturacionDAO;
import dao.PeriodoLecturaDAO;
import dao.PeriodosSaldosDAO;
import dao.SocioDAO;
import dao.impl.ConceptoFacturacionDAOImplement;
import dao.impl.ConexionDAOImplement;
import dao.impl.ConexionesSaldosDAOImplement;
import dao.impl.ConfiguracionFacturaDAOImplement;
import dao.impl.EstadoPeriodoDAOImplement;
import dao.impl.EstadoSocioDAOImplement;
import dao.impl.FacturaDAOImplement;
import dao.impl.LecturaDAOImplement;
import dao.impl.PeriodoCanonDAOImplement;
import dao.impl.PeriodoFacturacionDAOImplement;
import dao.impl.PeriodoLecturaDAOImplement;
import dao.impl.PeriodosSaldosDAOImplement;
import dao.impl.SocioDAOImplement;
import model.Conexion;
import model.ConexionesSaldos;
import model.ConfiguracionFactura;
import model.Factura;
import model.Lectura;
import model.PeriodoCesp;
import model.PeriodoFacturacion;
import model.PeriodoLectura;
import model.PeriodosSaldos;
import model.Socio;
import model.SociosTransacciones;

@ManagedBean(name = "periodoFacturacionBean")
@ViewScoped
public class PeriodoFacturacionBean implements Serializable {
	final static String NORMAL= "NORMAL";
	final static String CANON = "CANON";
	private PeriodoFacturacion periodoFacturacion;
	private List<PeriodoFacturacion> lstPeriodoFacturacion;
	private PeriodoFacturacion periodoFacturacionEnProceso;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean login;

	public PeriodoFacturacionBean() {
		inicializar();
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public PeriodoFacturacion getPeriodoFacturacion() {
		return periodoFacturacion;
	}

	public void setPeriodoFacturacion(PeriodoFacturacion periodoFacturacion) {
		this.periodoFacturacion = periodoFacturacion;
	}

	public List<PeriodoFacturacion> getLstPeriodoFacturacion() {
		try {
			PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
			lstPeriodoFacturacion = periodoFacturacionDAO.listaPeriodoFacturacion();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		return lstPeriodoFacturacion;
		
	}

	public void setLstPeriodoFacturacion(
			List<PeriodoFacturacion> lstPeriodoFacturacion) {
		this.lstPeriodoFacturacion = lstPeriodoFacturacion;
	}
	
	public PeriodoFacturacion getPeriodoFacturacionEnProceso() {
		try {
			PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
			periodoFacturacionEnProceso = periodoFacturacionDAO.buscarPeriodoFacturacionAbierto();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		return periodoFacturacionEnProceso;
	}

	public void setPeriodoFacturacionEnProceso(
			PeriodoFacturacion periodoFacturacionEnProceso) {
		this.periodoFacturacionEnProceso = periodoFacturacionEnProceso;
	}

	public boolean periodoAbiertoCons(){
		
		if(periodoFacturacionEnProceso != null){
			return true;
		}else{
			return false;
		}
	}
	
	public void insertarPeriodoFacturacion() {
		EstadoPeriodoDAO estadoPeriodoDAO = new EstadoPeriodoDAOImplement();
		PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
		List<PeriodoFacturacion> lstPer = new ArrayList<PeriodoFacturacion>();
		boolean existe = false;

		try {
			lstPer = periodoFacturacionDAO.listaPeriodoFacturacion();			
			for (PeriodoFacturacion per : lstPer) {				
				if ((per.getAnio() == periodoFacturacion.getAnio()) && (per.getMes() == periodoFacturacion.getMes())) {
					existe = true;
				}
			}			
			if (existe == false) {
			periodoFacturacion.setEstadoPeriodo(estadoPeriodoDAO.buscarEstadoPeriodo("EN PROCESO"));
			periodoFacturacionDAO.insertarPeriodoFacturacion(periodoFacturacion);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
			"Correctamente", "El Período se agregó correctamente."));
			}
			else{
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: Período ya cargado. "));
			}
			inicializar();
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: " + e.getMessage()));
		}
	}

	public void cambiarEstadoPeriodo(String estado, PeriodoFacturacion periodo) {
		EstadoPeriodoDAO estadoPeriodoDAO = new EstadoPeriodoDAOImplement();
		PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
		try {
			periodo.setEstadoPeriodo(estadoPeriodoDAO.buscarEstadoPeriodo(estado));
			if(estado.equals("FACTURADO")){
				facturar();
			}
		
			periodoFacturacionDAO.modificarPeriodoFacturacion(periodo);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Correctamente", "El período se modifico correctamente."));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: " + e.getMessage()));
		}
	}

	private void inicializar() {
		getPeriodoFacturacionEnProceso();
		periodoFacturacion = new PeriodoFacturacion();
		periodoFacturacion.setPeriodoCesp(new PeriodoCesp());
	}
	
	private void facturar() throws Exception{
		PeriodoLecturaDAO periodoLecturaDAO = new PeriodoLecturaDAOImplement();
		LecturaDAO lecturaDAO = new LecturaDAOImplement();
		ConceptoFacturacionDAO conceptoFacturacionDAO = new ConceptoFacturacionDAOImplement(); 
		PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
		ConfiguracionFacturaDAO daoConfiguracionFactura= new ConfiguracionFacturaDAOImplement();
		FacturaDAO daoFactura = new FacturaDAOImplement();
		PeriodoCanonDAO periodoCanonDAO = new PeriodoCanonDAOImplement();
		ConexionesSaldosDAO conexionesSaldosDAO = new ConexionesSaldosDAOImplement();
		PeriodosSaldosDAO periodosSaldosDAO = new PeriodosSaldosDAOImplement();
		try {
			ConfiguracionFactura configFactura = daoConfiguracionFactura.obtenerConfiguracionFactura().get(0);			
			for (Lectura lec : lecturaDAO.buscarLecturasPorPeriodo(periodoLecturaDAO.buscarPeriodoLecturaAbierto())) {
				Factura fact = new Factura();
				long totalConsumido = lec.getLecturaActual()-lec.getLecturaAnterior();
				Double importeTramos= 0D;
				if(lec.getConexion().getCategoriaConexion().getDescripcion().equals(NORMAL)){
					if(totalConsumido >50){
						fact.setTramo6(totalConsumido-50);
						fact.setTramo5(10L);
						fact.setTramo4(10L);
						fact.setTramo3(10L);
						fact.setTramo2(10L);
						fact.setTramo1(10L);
						importeTramos = (configFactura.getTramo1()*10F)+(configFactura.getTramo2()*10F)
										+(configFactura.getTramo3()*10F)+(configFactura.getTramo4()*10F)
										+(configFactura.getTramo5()*10F)+(configFactura.getTramo6()*fact.getTramo6());					
					}else if(totalConsumido >40){
						fact.setTramo5(totalConsumido-40);
						fact.setTramo4(10L);
						fact.setTramo3(10L);
						fact.setTramo2(10L);
						fact.setTramo1(10L);
						importeTramos = (configFactura.getTramo1()*10F)+(configFactura.getTramo2()*10F)
										+(configFactura.getTramo3()*10F)+(configFactura.getTramo4()*10F)
										+(configFactura.getTramo5()*fact.getTramo5());
					}else if(totalConsumido >30){
						fact.setTramo4(totalConsumido-30);
						fact.setTramo3(10L);
						fact.setTramo2(10L);
						fact.setTramo1(10L);
						importeTramos = (configFactura.getTramo1()*10F)+(configFactura.getTramo2()*10F)
										+(configFactura.getTramo3()*10F)+(configFactura.getTramo4()*fact.getTramo4());
					}else if(totalConsumido >20){
						fact.setTramo3(totalConsumido-20);
						fact.setTramo2(10L);
						fact.setTramo1(10L);
						importeTramos = (configFactura.getTramo1()*10F)+(configFactura.getTramo2()*10F)
										+(configFactura.getTramo3()*fact.getTramo3());
					}else if(totalConsumido >10){
						fact.setTramo2(totalConsumido-10);
						fact.setTramo1(10L);
						importeTramos = (configFactura.getTramo1()*10F)+(configFactura.getTramo2()*fact.getTramo2());
					}else{
						fact.setTramo1(totalConsumido);
						importeTramos = (configFactura.getTramo1()*fact.getTramo1());
					}		
					fact.setCargoFijo(lec.getConexion().getTipoSuministro().getImporte());
					fact.setCapitalSocial((0.1F*(fact.getCargoFijo()+importeTramos))+ configFactura.getCapitalSocial()); // se deja el calculo de 10%, solo se obtiene el monto fijo desde la configuracion 				
					fact.setConceptoFacturacion(conceptoFacturacionDAO.buscarConceptoFacturacionId(1L));				
					fact.setConexion(lec.getConexion());				
					fact.setErsep((configFactura.getErsep()/100)*(fact.getCargoFijo()+importeTramos));	// se divide en 100 el porcentaje obtenido de la configuracion							
					fact.setImpresionesOtros(configFactura.getImpresionesOtros());//FIJO
					fact.setInteresesSegVenc(3D);///viene de configuracion factura
					fact.setIva((lec.getConexion().getSocio().getCondicionIva().getPorcentaje()*(fact.getCargoFijo()+importeTramos)/100));
					fact.setPeriodoFacturacion(periodoFacturacionDAO.buscarPeriodoFacturacionAbierto());
					fact.setRecuperoInversion(configFactura.getRecuperoInversion());//FIJO
					
					if(fact.getConexion().getSocio().getCondicionIva().getId().equals(4)){
						fact.setTipoFactura("A");
					}else{
						fact.setTipoFactura("B");
					}
					
					fact.setImporteTotal(fact.getCargoFijo()+
							importeTramos+
							fact.getCapitalSocial()+
							fact.getErsep()+
							fact.getRecuperoInversion()+
							fact.getImpresionesOtros()+
							fact.getIva());
					
				}
				try{
					daoFactura.insertarFactura(fact);				
				}catch(Exception ex){
					System.out.println(("Error al grabar factura: " +ex.getMessage()));
					throw new Exception("Error al grabar factura: " +ex.getMessage());
				}
				ConexionesSaldos conSaldo = null;
				try{
					conSaldo = conexionesSaldosDAO.buscarConexionesSaldosConexion(fact.getConexion().getId());
				}catch(Exception ex){
					System.out.println(("Error al obtener Saldo Conexion: " +ex.getMessage()));
					throw new Exception("Error al obtener Saldo Conexion: " +ex.getMessage());
				}
				try{
					if(conSaldo != null){
						conSaldo.setSaldoTotal(conSaldo.getSaldoTotal() - fact.getImporteTotal());
						conSaldo.setUltimoVencRegistrado(fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura());
						conexionesSaldosDAO.modificarConexionesSaldos(conSaldo);
					}else{
						conSaldo = new ConexionesSaldos();
						conSaldo.setConexion(fact.getConexion());
						conSaldo.setUltimoVencRegistrado(fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura());
						conSaldo.setSaldoTotal(0F - fact.getImporteTotal());
						conexionesSaldosDAO.insertarConexionesSaldos(conSaldo);
					}					
				}catch(Exception ex){
					System.out.println(("Error modificar/grabar Saldo Conexion: " +ex.getMessage()));
					throw new Exception("Error modificar/grabar Saldo Conexion: " +ex.getMessage());
				}					
				try{
					PeriodosSaldos perSaldo = new PeriodosSaldos();
					perSaldo.setConexion(fact.getConexion());
					perSaldo.setMes(fact.getPeriodoFacturacion().getMes());
					perSaldo.setAnio(fact.getPeriodoFacturacion().getAnio());
					perSaldo.setFechaVencimiento(fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura());
					perSaldo.setConsumo(totalConsumido);
					perSaldo.setSaldo(0F - fact.getImporteTotal());
					periodosSaldosDAO.insertarPeriodosSaldos(perSaldo);		
				}catch(Exception ex){
					System.out.println(("Error modificar/grabar Saldo Periodo Conexion: " +ex.getMessage()));
					throw new Exception("Error modificar/grabar Saldo Periodo Conexion: " +ex.getMessage());
				}
						
			}
			if(periodoCanonDAO.buscarPeriodosCanonMes((int) periodoLecturaDAO.buscarPeriodoLecturaAbierto().getMes()) != null){			
				ConexionDAO conexionDAO = new ConexionDAOImplement();
				for(Conexion conexion: conexionDAO.buscarConexionesCanon()){
					Factura fact = new Factura();				
					fact.setTramo6(0L);
					fact.setTramo5(0L);
					fact.setTramo4(0L);
					fact.setTramo3(0L);
					fact.setTramo2(0L);
					fact.setTramo1(0L);
					fact.setCargoFijo(conexion.getTipoSuministro().getImporte());
					fact.setCapitalSocial((0.1F*(fact.getCargoFijo()))+ configFactura.getCapitalSocial()); // se deja el calculo de 10%, solo se obtiene el monto fijo desde la configuracion 				
					fact.setConceptoFacturacion(conceptoFacturacionDAO.buscarConceptoFacturacionId(1L));				
					fact.setConexion(conexion);				
					fact.setErsep((configFactura.getErsep()/100)*(fact.getCargoFijo()));	// se divide en 100 el porcentaje obtenido de la configuracion							
					fact.setImpresionesOtros(configFactura.getImpresionesOtros());//FIJO
					fact.setInteresesSegVenc(3D);
					fact.setIva(conexion.getSocio().getCondicionIva().getPorcentaje()*(fact.getCargoFijo()));
					fact.setPeriodoFacturacion(periodoFacturacionDAO.buscarPeriodoFacturacionAbierto());
					fact.setRecuperoInversion(configFactura.getRecuperoInversion());//FIJO
					if(conexion.getSocio().getCondicionIva().getId().equals(4)){
						fact.setTipoFactura("A");
					}else{
						fact.setTipoFactura("B");
					}					
					fact.setImporteTotal(fact.getCargoFijo()+
							fact.getCapitalSocial()+
							fact.getErsep()+
							fact.getRecuperoInversion()+
							fact.getImpresionesOtros()+
							fact.getIva());
					System.out.println(fact.toString());
					
					try{
						daoFactura.insertarFactura(fact);				
					}catch(Exception ex){
						System.out.println(("Error al grabar factura Canon: " +ex.getMessage()));
						throw new Exception("Error al grabar factura Canon: " +ex.getMessage());
					}
					ConexionesSaldos conSaldo =null;
					try{
						conSaldo= conexionesSaldosDAO.buscarConexionesSaldosConexion(fact.getConexion().getId());
					}catch(Exception ex){
						System.out.println(("Error al Obtener Saldo Conexion Canon: " +ex.getMessage()));
						throw new Exception("Error al Obtener Saldo Conexion Canon: " +ex.getMessage());
					}
					try{
						if(conSaldo != null){
							conSaldo.setSaldoTotal(conSaldo.getSaldoTotal() - fact.getImporteTotal());
							conSaldo.setUltimoVencRegistrado(fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura());
							conexionesSaldosDAO.modificarConexionesSaldos(conSaldo);
						}else{
							conSaldo = new ConexionesSaldos();
							conSaldo.setConexion(fact.getConexion());
							conSaldo.setUltimoVencRegistrado(fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura());
							conSaldo.setSaldoTotal(0F - fact.getImporteTotal());
							conexionesSaldosDAO.insertarConexionesSaldos(conSaldo);
						}
					}catch(Exception ex){
						System.out.println(("Error al modificar/grabar Saldo Conexion Canon: " +ex.getMessage()));
						throw new Exception("Error al modificar/grabar Saldo Conexion Canon: " +ex.getMessage());
					}
					try{
						PeriodosSaldos perSaldo = new PeriodosSaldos();
						perSaldo.setConexion(fact.getConexion());
						perSaldo.setMes(fact.getPeriodoFacturacion().getMes());
						perSaldo.setAnio(fact.getPeriodoFacturacion().getAnio());
						perSaldo.setFechaVencimiento(fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura());
						perSaldo.setConsumo(0);
						perSaldo.setSaldo(0F - fact.getImporteTotal());
						periodosSaldosDAO.insertarPeriodosSaldos(perSaldo);
					}catch(Exception ex){
						System.out.println(("Error al modificar/grabar Periodos Saldo Conexion Canon: " +ex.getMessage()));
						throw new Exception("Error al modificar/grabar Periodos Saldo Conexion Canon: " +ex.getMessage());
					}
				}			
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
}
