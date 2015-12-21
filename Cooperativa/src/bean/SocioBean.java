package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.CondicionIvaDAO;
import dao.DomicilioDAO;
import dao.EstadoCivilDAO;
import dao.EstadoSocioDAO;
import dao.PaisDAO;
import dao.ProvinciaDAO;
import dao.SocioDAO;
import dao.TipoDocumentoDAO;
import dao.TipoDomicilioDAO;
import dao.TipoSocioDAO;
import dao.impl.CondicionIvaDAOImplement;
import dao.impl.DomicilioDAOImplement;
import dao.impl.EstadoCivilDAOImplement;
import dao.impl.EstadoSocioDAOImplement;
import dao.impl.PaisDAOImplement;
import dao.impl.ProvinciaDAOImplement;
import dao.impl.SocioDAOImplement;
import dao.impl.TipoDocumentoDAOImplement;
import dao.impl.TipoDomicilioDAOImplement;
import dao.impl.TipoSocioDAOImplement;
import model.Domicilio;
import model.Pais;
import model.Socio;
import model.SociosTransacciones;
import model.TipoDocumento;
import model.TipoSocio;

@ManagedBean(name = "socioBean")
@ViewScoped
public class SocioBean implements Serializable {
	private Socio socio;
	
	
	private List<Socio> lstSocio;
	private Socio socioSeleccionado;
	private Socio socioEditar;
	
	//
	private Long tipoDocId;
	private TipoDocumento tipoDocumento;
	private Long estadoSocioId;
	private Long tipoSocioId;
	private Long estCivilId;
	private Long paisId;
	private Long condicionIvaId;
	
	private Domicilio domicilio;
	
	private Long paisDomId;
	private Long provinciaDomId;
	private String localidad;
	
	
	private String personaOEmp;
	
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean login;

	public SocioBean() {
		inicializar();
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}


	public Socio getSocioSeleccionado() {
		return socioSeleccionado;
	}

	public void setSocioSeleccionado(Socio socioSeleccionado) {
		this.socioSeleccionado = socioSeleccionado;
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public List<Socio> getLstSocio() {
		try {
			SocioDAO daoSocio = new SocioDAOImplement();
			lstSocio = daoSocio.listaSocio();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
		}
		return lstSocio;
	}

	public void setLstSocio(List<Socio> lstSocio) {
		this.lstSocio = lstSocio;
	}

	public Socio getSocioEditar() {
		return socioEditar;
	}

	public void setSocioEditar(Socio socioEditar) {
		this.socioEditar = socioEditar;
	}
	
	public Long getTipoDocId() {
		return tipoDocId;
	}

	public void setTipoDocId(Long tipoDocId) {
		this.tipoDocId = tipoDocId;
	}

	public TipoDocumento getTipoDocumento() {
		try {
			TipoDocumentoDAO daoTipoDocumento = new TipoDocumentoDAOImplement();
			tipoDocumento = daoTipoDocumento.buscarTipoDocumentoID(tipoDocId);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
		}
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Long getEstadoSocioId() {
		return estadoSocioId;
	}

	public void setEstadoSocioId(Long estadoSocioId) {
		this.estadoSocioId = estadoSocioId;
	}

	public Long getTipoSocioId() {
		return tipoSocioId;
	}

	public void setTipoSocioId(Long tipoSocioId) {
		this.tipoSocioId = tipoSocioId;
	}

	public Long getEstCivilId() {
		return estCivilId;
	}

	public void setEstCivilId(Long estCivilId) {
		this.estCivilId = estCivilId;
	}

	public Long getPaisId() {
		return paisId;
	}

	public void setPaisId(Long paisId) {
		this.paisId = paisId;
	}
	
	public Long getCondicionIvaId() {
		return condicionIvaId;
	}

	public void setCondicionIvaId(Long condicionIvaId) {
		this.condicionIvaId = condicionIvaId;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public String getPersonaOEmp() {
		return personaOEmp;
	}

	public void setPersonaOEmp(String personaOEmp) {
		this.personaOEmp = personaOEmp;
	}
	
	public Long getPaisDomId() {
		return paisDomId;
	}

	public void setPaisDomId(Long paisDomId) {
		this.paisDomId = paisDomId;
	}

	public Long getProvinciaDomId() {
		return provinciaDomId;
	}

	public void setProvinciaDomId(Long provinciaDomId) {
		this.provinciaDomId = provinciaDomId;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public void insertarSocio() {
		
		
		try {
			// se setean datos del Socio //
			
			TipoDocumentoDAO tipoDocDAO = new TipoDocumentoDAOImplement();
			socio.setTipoDocumento(tipoDocDAO.buscarTipoDocumentoID(tipoDocId));
			
			TipoSocioDAO tipoSocioDAO = new TipoSocioDAOImplement();
			socio.setTipoSocio(tipoSocioDAO.buscarTipoSocioId(tipoSocioId));
			
			EstadoSocioDAO estadoSocioDAO = new EstadoSocioDAOImplement();
			socio.setEstadoSocio(estadoSocioDAO.buscarEstadoSocio("ACTIVO"));
			
			if(personaOEmp.equals("persona")){
				EstadoCivilDAO estCivilDAO = new EstadoCivilDAOImplement();
				socio.setEstadoCivil(estCivilDAO.buscarEstadoCivilID(estCivilId));
			}
			
			if(personaOEmp.equals("emp")){
				socio.setNombreConyuge("NO CORRESPONDE");
			}
			
			PaisDAO paisDAO = new PaisDAOImplement();
			socio.setPais(paisDAO.buscarPaisId(paisId));
			
			CondicionIvaDAO condicionIvaDAO = new CondicionIvaDAOImplement();
			socio.setCondicionIva(condicionIvaDAO.buscarCondicionIvaId(condicionIvaId));
			
			socio.setUsuario(login.getUsuario());	
			
			// se setean datos del domicilio //
			
			domicilio.setLocalidad(localidad);
			
			domicilio.setPais(paisDAO.buscarPaisId(paisDomId));
			
			ProvinciaDAO provinciaDAO = new ProvinciaDAOImplement();
			domicilio.setProvincia(provinciaDAO.buscarProvinciaId(provinciaDomId));
			
			TipoDomicilioDAO tipoDomDAO = new TipoDomicilioDAOImplement();
			domicilio.setTipoDomicilio(tipoDomDAO.buscarTipoDomicilio("LEGAL"));
			
			
			List<Domicilio> listaDom = new ArrayList<Domicilio>();
			listaDom.add(domicilio);
			socio.setDomicilios(listaDom);
			
			SocioDAO socioDAO = new SocioDAOImplement();
			socioDAO.insertarSocio(socio);
			
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Correctamente", "Se agrego correctamente"));
			inicializar();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error al procesar: " + e.getMessage()));
		}
	}

	public void eliminarSocio(Socio socio) {
		try {
			SocioDAO socioDAO = new SocioDAOImplement();
			socioDAO.eliminarSocio(socio);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Correctamente", "Se eliminó correctamente."));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error al procesar: " + e.getMessage()));
		}

	}

	public void cambiarEstadoSocio(String estado, Socio socio) {
		try {
			SocioDAO socioDAO = new SocioDAOImplement();
			EstadoSocioDAO estadoSocioDAO = new EstadoSocioDAOImplement();						
			socio.setEstadoSocio(estadoSocioDAO.buscarEstadoSocio(estado));						
						
			//se guarda registro de la transaccion
			SociosTransacciones transaccion = new SociosTransacciones();
			
			if(estado.equals("BAJA")){
				transaccion.setTipoTransaccion("BAJA");
			}
			if(estado.equals("ACTIVO")){
				transaccion.setTipoTransaccion("ALTA");
			}
			transaccion.setFecha(Calendar.getInstance().getTime());
			transaccion.setUsuario(login.getUsuario());
			
			socio.getTransacciones().add(transaccion);
			
			socioDAO.modificarSocio(socio);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Correctamente", "El socio se modifico correctamente."));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error al procesar: " + e.getMessage()));
		}
	}
	
	public void editarSocio(){
		SocioDAO daoSocio = new SocioDAOImplement();
		
		
		try {
			daoSocio.modificarSocio(socioEditar);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Correctamente", "Se editó correctamente."));
			inicializar();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error al procesar: " + e.getMessage()));
		}
	}
	
	public long totalSocios(){
		long total = lstSocio.size();
		return total;
	}
	
	public void mostrarSocio(Socio socio) {
		this.socioSeleccionado = socio;
	}
	
	public void editarSocio(Socio socio){
		this.socioEditar = socio;
	}

	
	private void inicializar() {
		socio = new Socio();
		socioSeleccionado = new Socio();
		socioEditar = new Socio();
		estadoSocioId = null;
		tipoSocioId = null;
		estCivilId = null;
		paisId = null;
		condicionIvaId = null;
		domicilio = new Domicilio();
		paisDomId = null;
		provinciaDomId = null;
		localidad = "";
	}

}
