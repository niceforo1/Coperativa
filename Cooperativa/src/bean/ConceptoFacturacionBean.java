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
import dao.FacturaDAO;
import dao.impl.ConceptoFacturacionDAOImplement;
import dao.impl.FacturaDAOImplement;
import model.ConceptoFacturacion;
import model.Conexion;
import model.ConfiguracionFactura;
import model.Factura;
import model.Socio;

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
		Double importeTotal; 
		try {
			fact.setConceptoFacturacion(conceptoFacturacionDAO.buscarConceptoFacturacionId(conceptoID));

			fact.setConexion(conexion);

			if (fact.getConexion().getSocio().getCondicionIva().getId().equals(4)) {
				fact.setTipoFactura("A");
			} else {
				fact.setTipoFactura("B");
			}
			fact.setIva((fact.getConceptoFacturacion().getMontoPrecio()*fact.getConexion().getSocio().getCondicionIva().getIvaOtrosConceptos())/100);
			importeTotal = fact.getConceptoFacturacion().getMontoPrecio()+ fact.getIva();
			
			fact.setImporteTotal(importeTotal);
			fact.setFechaVencimiento(new Date());
			
			facturaDAO.insertarFactura(fact);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al facturar otros conceptos verifique todos los campos"));
			LOG.error("Error al facturar otros conceptos. ");
		}
	}

	private void inicializar() {
		lstConceptos = new ArrayList<ConceptoFacturacion>();
		conceptoID = 0L;
		conexion = new Conexion();
		habilitado = false;
	}
}
