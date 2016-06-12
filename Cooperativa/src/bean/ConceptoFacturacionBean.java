package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import dao.ConceptoFacturacionDAO;
import dao.ConexionesSaldosDAO;
import dao.FacturaDAO;
import dao.GeneradorFacturaADAO;
import dao.GeneradorFacturaBDAO;
import dao.PeriodoFacturacionDAO;
import dao.PeriodosSaldosDAO;
import dao.impl.ConceptoFacturacionDAOImplement;
import dao.impl.ConexionesSaldosDAOImplement;
import dao.impl.FacturaDAOImplement;
import dao.impl.GeneradorFacturaADAOImplement;
import dao.impl.GeneradorFacturaBDAOImplement;
import dao.impl.PeriodoFacturacionDAOImplement;
import dao.impl.PeriodosSaldosDAOImplement;
import model.ConceptoFacturacion;
import model.Conexion;
import model.ConexionesSaldos;
import model.ConfiguracionFactura;
import model.Factura;
import model.PeriodosSaldos;
import model.Socio;
import model.generadores.GenFacturaA;
import model.generadores.GenFacturaB;

@ManagedBean(name = "conceptoFacturacionBean")
@ViewScoped
public class ConceptoFacturacionBean implements Serializable {
	private static final Logger LOG = Logger.getLogger(ConceptoFacturacionBean.class);
	private List<ConceptoFacturacion> lstConceptos;
	private Long conceptoID;
	private Conexion conexion;
	private boolean habilitado;

	public ConceptoFacturacionBean() {
		inicializar();
	}

	public Long getConceptoID() {
		return conceptoID;
	}

	public void setConceptoID(Long conceptoID) {
		this.conceptoID = conceptoID;
	}

	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public List<ConceptoFacturacion> getLstConceptos() {
		ConceptoFacturacionDAO conceptoFacturacionDAO = new ConceptoFacturacionDAOImplement();
		try {
			lstConceptos = conceptoFacturacionDAO.listaConceptoFacturacion();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: " + e.getMessage()));
			LOG.error("Error al obtener Lista Concepto Facturacion: " + e.getMessage());
		}
		return lstConceptos;
	}

	public void setLstConceptos(List<ConceptoFacturacion> lstConceptos) {
		this.lstConceptos = lstConceptos;
	}

	// SELECT DE LA PAGINA FACTURACION INDIVIDUAL OTROS CONCEPTOS
	public void onRowSelect(SelectEvent event) {
		conexion = (Conexion) event.getObject();
		habilitado = true;
	}

	public void onRowUnselect(UnselectEvent event) {
		conexion = new Conexion();
		habilitado = false;
	}

	public void facturarOtrosConceptos() {
		FacturaDAO facturaDAO = new FacturaDAOImplement();
		if(conceptoID== null || conceptoID == 0){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Verifique el ingreso, faltan ingresar campos. "));
			LOG.error("Verifique el ingreso, faltan ingresar campos. ");
		}
		Factura fact = new Factura();
		ConceptoFacturacionDAO conceptoFacturacionDAO = new ConceptoFacturacionDAOImplement();
		PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
		ConexionesSaldosDAO conexionesSaldosDAO = new ConexionesSaldosDAOImplement();
		PeriodosSaldosDAO periodosSaldosDAO = new PeriodosSaldosDAOImplement(); 
		Double importeTotal; 
		try {
			fact.setConceptoFacturacion(conceptoFacturacionDAO.buscarConceptoFacturacionId(conceptoID));

			fact.setConexion(conexion);

			if (fact.getConexion().getSocio().getCondicionIva().getId().equals(4)) {
				fact.setTipoFactura("A");
				GeneradorFacturaADAO facturaADAO = new GeneradorFacturaADAOImplement();
				fact.setNumeroFactura(facturaADAO.insertarFacturaA(new GenFacturaA()).toString());
			} else {
				GeneradorFacturaBDAO facturaBDAO = new GeneradorFacturaBDAOImplement();
				fact.setNumeroFactura(facturaBDAO.insertarFacturaB(new GenFacturaB()).toString());
				fact.setTipoFactura("B");
			}
			fact.setIva((fact.getConceptoFacturacion().getMontoPrecio()*fact.getConexion().getSocio().getCondicionIva().getIvaOtrosConceptos())/100);
			importeTotal = fact.getConceptoFacturacion().getMontoPrecio()+ fact.getIva();
			
			fact.setImporteTotal(importeTotal);
			fact.setFechaVencimiento(new Date());
			fact.setPeriodoFacturacion(periodoFacturacionDAO.listaPeriodoFacturacion().get(0));
			facturaDAO.insertarFactura(fact);
			ConexionesSaldos conSaldo = null;
			try {
				conSaldo = conexionesSaldosDAO.buscarConexionesSaldosConexion(fact.getConexion().getId());
			} catch (Exception ex) {
				LOG.error("Error al obtener Saldo Conexion: " + ex.getMessage());
				throw new Exception("Error al obtener Saldo Conexion: " + ex.getMessage());
			}
			try {
				if (conSaldo != null) {
					conSaldo.setSaldoTotal(conSaldo.getSaldoTotal() - fact.getImporteTotal());
					conSaldo.setUltimoVencRegistrado(
							fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura());
					conexionesSaldosDAO.modificarConexionesSaldos(conSaldo);
				} else {
					conSaldo = new ConexionesSaldos();
					conSaldo.setConexion(fact.getConexion());
					conSaldo.setUltimoVencRegistrado(
							fact.getFechaVencimiento());
					conSaldo.setSaldoTotal(0F - fact.getImporteTotal());
					conexionesSaldosDAO.insertarConexionesSaldos(conSaldo);
				}
			} catch (Exception ex) {
				LOG.error("Error modificar/grabar Saldo Conexion: " + ex.getMessage());
				throw new Exception("Error modificar/grabar Saldo Conexion: " + ex.getMessage());
			}
			try {
				PeriodosSaldos perSaldo = null;
				try {
					perSaldo = periodosSaldosDAO.buscarPeriodosSaldosMesAnio(fact.getConexion().getId(), fact.getPeriodoFacturacion().getMes(), fact.getPeriodoFacturacion().getAnio());
				} catch (Exception ex) {
					
				}
				if(perSaldo !=null){
					perSaldo.setConexion(fact.getConexion());
					perSaldo.setMes(fact.getPeriodoFacturacion().getMes());
					perSaldo.setAnio(fact.getPeriodoFacturacion().getAnio());
					perSaldo.setFechaVencimiento(fact.getFechaVencimiento());
					perSaldo.setConsumo(0);
					perSaldo.setSaldo(perSaldo.getSaldo() - fact.getImporteTotal());
					periodosSaldosDAO.modificarPeriodosSaldos(perSaldo);
				}else{
					perSaldo = new PeriodosSaldos();
					perSaldo.setConexion(fact.getConexion());
					perSaldo.setMes(fact.getPeriodoFacturacion().getMes());
					perSaldo.setAnio(fact.getPeriodoFacturacion().getAnio());
					perSaldo.setFechaVencimiento(fact.getFechaVencimiento());
					perSaldo.setConsumo(0);
					perSaldo.setSaldo(0F - fact.getImporteTotal());
					periodosSaldosDAO.insertarPeriodosSaldos(perSaldo);
				}
			} catch (Exception ex) {
				LOG.error("Error modificar/grabar Saldo Periodo Conexion: " + ex.getMessage());
				throw new Exception("Error modificar/grabar Saldo Periodo Conexion: " + ex.getMessage());
			}
			
			
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Factura generada correctamente. "));
			inicializar();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al facturar otros conceptos. "+e.getMessage()));
			LOG.error("Error al facturar otros conceptos. "+e.getMessage());
		}
	}

	private void inicializar() {
		lstConceptos = new ArrayList<ConceptoFacturacion>();
		conceptoID = 0L;
		conexion = new Conexion();
		habilitado = false;
	}
}
