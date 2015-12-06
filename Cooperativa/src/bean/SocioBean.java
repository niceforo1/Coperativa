package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.EstadoSocioDAO;
import dao.SocioDAO;
import dao.TipoDocumentoDAO;
import dao.TipoSocioDAO;
import dao.impl.EstadoSocioDAOImplement;
import dao.impl.SocioDAOImplement;
import dao.impl.TipoDocumentoDAOImplement;
import dao.impl.TipoSocioDAOImplement;
import model.Socio;
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

	public String getPersonaOEmp() {
		return personaOEmp;
	}

	public void setPersonaOEmp(String personaOEmp) {
		this.personaOEmp = personaOEmp;
	}

	public void insertarSocio() {
		
		
		try {
			SocioDAO socioDAO = new SocioDAOImplement();
			EstadoSocioDAO estadoSocioDAO = new EstadoSocioDAOImplement();
//			socio.setEstadoSocio(estadoSocioDAO
//					.buscarEstadoSocio(estadoSocioID));
			socio.setEstadoSocio(estadoSocioDAO.buscarEstadoSocio("VIG"));
			TipoSocioDAO tipoSocioDAO = new TipoSocioDAOImplement();
			socio.setTipoSocio(tipoSocioDAO.buscarTipoSocioId(tipoSocioId));
			// System.out.println(socio.toString());
			socioDAO.insertarSocio(socio);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Correctamente", "Se agrego correctamente"));
			inicializar();
		} catch (Exception e) {
			System.out.println("Asociar persona: SOCIOBEAN " + e.getMessage());
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
			socioDAO.modificarSocio(socio);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Correctamente", "El usuario se modifico correctamente."));
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
	}

}
