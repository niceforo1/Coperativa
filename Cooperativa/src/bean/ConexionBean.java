package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.CategoriaConexionDAO;
import dao.DomicilioDAO;
import dao.EstadoConexionDAO;
import dao.FormaPagoDAO;
import dao.PaisDAO;
import dao.ProvinciaDAO;
import dao.RegimenPropiedadDAO;
import dao.SocioDAO;
import dao.TipoConexionDAO;
import dao.TipoDomicilioDAO;
import dao.TipoSuministroDAO;
import dao.TipoTerrenoDAO;
import dao.TipoUbicacionCatastralDAO;
import dao.ZonaConexionDAO;
import dao.impl.CategoriaConexionDAOImplement;
import dao.impl.DomicilioDAOImplement;
import dao.impl.EstadoConexionDAOImplement;
import dao.impl.FormaPagoDAOImplement;
import dao.impl.PaisDAOImplement;
import dao.impl.ProvinciaDAOImplement;
import dao.impl.RegimenPropiedadDAOImplement;
import dao.impl.SocioDAOImplement;
import dao.impl.TipoConexionDAOImplement;
import dao.impl.TipoDomicilioDAOImplement;
import dao.impl.TipoSuministroDAOImplement;
import dao.impl.TipoTerrenoDAOImplement;
import dao.impl.TipoUbicacionCatastralDAOImplement;
import dao.impl.ZonaConexionDAOImplement;
import model.Conexion;
import model.Domicilio;
import model.Socio;
import model.UbicacionCatastral;

@ManagedBean(name = "conexionBean")
@ViewScoped
public class ConexionBean implements Serializable {

	private List<Socio> lstSociosActivos;
	private Socio socio;
	
	private boolean domServFactIguales;
	
	private Domicilio domServ;
	private Domicilio domFact;
	
	private long paisDomFactId;
	private long provinciaDomFactId;
	
	private UbicacionCatastral ubicOficial;
	private UbicacionCatastral ubicCatastral;
	
	private Conexion conexion;
	private long estadoConexionId;
	private long zonaConexionId;
	private long tipoTerrenoConexionId;
	private long regimenConexionId;
	private long tipoSuministroConexionId;
	private long categoriaConexionId;
	private long tipoConexionId;
	private long fPagoConexionId;
	
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean login;
	
	public ConexionBean() {
		inicializar();
	}

	public List<Socio> getLstSociosActivos() {
		try {
			SocioDAO daoSocio = new SocioDAOImplement();
			lstSociosActivos = daoSocio.listaSociosActivos("ACTIVO");
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
		}
		return lstSociosActivos;
	}

	public void setLstSociosActivos(List<Socio> lstSociosActivos) {
		this.lstSociosActivos = lstSociosActivos;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	
	public long totalSocios(){
		long total = lstSociosActivos.size();
		return total;
	}
	
	public boolean isDomServFactIguales() {
		return domServFactIguales;
	}

	public void setDomServFactIguales(boolean domServFactIguales) {
		this.domServFactIguales = domServFactIguales;
	}
	
	public Domicilio getDomServ() {
		return domServ;
	}

	public void setDomServ(Domicilio domServ) {
		this.domServ = domServ;
	}

	public Domicilio getDomFact() {
		return domFact;
	}

	public void setDomFact(Domicilio domFact) {
		this.domFact = domFact;
	}

	public long getPaisDomFactId() {
		return paisDomFactId;
	}

	public void setPaisDomFactId(long paisDomFactId) {
		this.paisDomFactId = paisDomFactId;
	}

	public long getProvinciaDomFactId() {
		return provinciaDomFactId;
	}

	public void setProvinciaDomFactId(long provinciaDomFactId) {
		this.provinciaDomFactId = provinciaDomFactId;
	}
	
	public UbicacionCatastral getUbicOficial() {
		return ubicOficial;
	}

	public void setUbicOficial(UbicacionCatastral ubicOficial) {
		this.ubicOficial = ubicOficial;
	}

	public UbicacionCatastral getUbicCatastral() {
		return ubicCatastral;
	}

	public void setUbicCatastral(UbicacionCatastral ubicCatastral) {
		this.ubicCatastral = ubicCatastral;
	}

	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

	public long getEstadoConexionId() {
		return estadoConexionId;
	}

	public void setEstadoConexionId(long estadoConexionId) {
		this.estadoConexionId = estadoConexionId;
	}
	
	public long getZonaConexionId() {
		return zonaConexionId;
	}

	public void setZonaConexionId(long zonaConexionId) {
		this.zonaConexionId = zonaConexionId;
	}

	public long getTipoTerrenoConexionId() {
		return tipoTerrenoConexionId;
	}

	public void setTipoTerrenoConexionId(long tipoTerrenoConexionId) {
		this.tipoTerrenoConexionId = tipoTerrenoConexionId;
	}

	public long getRegimenConexionId() {
		return regimenConexionId;
	}

	public void setRegimenConexionId(long regimenConexionId) {
		this.regimenConexionId = regimenConexionId;
	}

	public long getTipoSuministroConexionId() {
		return tipoSuministroConexionId;
	}

	public void setTipoSuministroConexionId(long tipoSuministroConexionId) {
		this.tipoSuministroConexionId = tipoSuministroConexionId;
	}

	public long getCategoriaConexionId() {
		return categoriaConexionId;
	}

	public void setCategoriaConexionId(long categoriaConexionId) {
		this.categoriaConexionId = categoriaConexionId;
	}

	public long getTipoConexionId() {
		return tipoConexionId;
	}

	public void setTipoConexionId(long tipoConexionId) {
		this.tipoConexionId = tipoConexionId;
	}

	public long getfPagoConexionId() {
		return fPagoConexionId;
	}

	public void setfPagoConexionId(long fPagoConexionId) {
		this.fPagoConexionId = fPagoConexionId;
	}
	
	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public void insertarConexion() {
		// datos Dom Servicio, se setea localidad,provincia y pais ya que son fijos y no depende de combos
		
		PaisDAO paisDAO = new PaisDAOImplement();
		ProvinciaDAO provinciaDAO = new ProvinciaDAOImplement();
		TipoDomicilioDAO daoTipoDomicilio = new TipoDomicilioDAOImplement();
		try {
			domServ.setLocalidad("BIALET MASSE");
			domServ.setProvincia(provinciaDAO.buscarProvinciaDescripcion("CORDOBA"));
			domServ.setPais(paisDAO.buscarPaisDescripcion("ARGENTINA"));
			domServ.setTipoDomicilio(daoTipoDomicilio.buscarTipoDomicilio("SERVICIO"));
			
			// se iguala dom facturacion al de servicio
			if(domServFactIguales == true){
				//domFact = domServ;
				
				domFact.setTipoDomicilio(daoTipoDomicilio.buscarTipoDomicilio("FACTURACION"));
				domFact.setCalle(domServ.getCalle());
				domFact.setNumero(domServ.getNumero());
				domFact.setDepartamento(domServ.getDepartamento());
				domFact.setBarrio(domServ.getBarrio());
				domFact.setLocalidad(domServ.getLocalidad());
				domFact.setProvincia(domServ.getProvincia());
				domFact.setCodPostal(domServ.getCodPostal());
				domFact.setPais(domServ.getPais());
				domFact.setTelFijo(domServ.getTelFijo());
			}
			else{
				domFact.setProvincia(provinciaDAO.buscarProvinciaId(provinciaDomFactId));
				domFact.setPais(paisDAO.buscarPaisId(paisDomFactId));
				domFact.setTipoDomicilio(daoTipoDomicilio.buscarTipoDomicilio("FACTURACION"));
			}
			
			// se ingresan los domicilios de serv y fact
			
			DomicilioDAO daoDomicilio = new DomicilioDAOImplement();
			
			daoDomicilio.insertarDomicilio(domServ);
			daoDomicilio.insertarDomicilio(domFact);
			
			// se setea a conexion los domicilios y boolean Dom Fact Iguales
			conexion.setDomicilioServicio(domServ);
			conexion.setDomicilioFacturacion(domFact);
			conexion.setDomServFactIguales(domServFactIguales);
			
			
			// se setean los tipos a las ubicaciones catastrales y las mismas a la conexion
			
			TipoUbicacionCatastralDAO daoTipoUbicacionCatastral = new TipoUbicacionCatastralDAOImplement();
			
			ubicOficial.setTipoUbicacionCatastral(daoTipoUbicacionCatastral.buscarTipoUbicacionCatastralDescripcion("OFICIAL"));
			ubicCatastral.setTipoUbicacionCatastral(daoTipoUbicacionCatastral.buscarTipoUbicacionCatastralDescripcion("CATASTRAL"));
			
			List<UbicacionCatastral> listaUbicaciones = new ArrayList<UbicacionCatastral>();
			listaUbicaciones.add(ubicOficial);
			listaUbicaciones.add(ubicCatastral);
			
			conexion.setUbicacionesCatastrales(listaUbicaciones);
			
			// se setea la informacion restante a la conexion
			
			EstadoConexionDAO daoEstadoConexion = new EstadoConexionDAOImplement();
			ZonaConexionDAO daoZonaConexion = new ZonaConexionDAOImplement();
			TipoTerrenoDAO daoTipoTerreno = new TipoTerrenoDAOImplement();
			RegimenPropiedadDAO daoRegimenPropiedad = new RegimenPropiedadDAOImplement();
			TipoSuministroDAO daoTipoSuministro = new TipoSuministroDAOImplement();
			CategoriaConexionDAO daoCategoriaConexion = new CategoriaConexionDAOImplement();
			TipoConexionDAO daoTipoConexion = new TipoConexionDAOImplement();
			FormaPagoDAO daoFormaPago = new FormaPagoDAOImplement();
			
			conexion.setEstadoConexion(daoEstadoConexion.buscarEstadoConexionId(estadoConexionId));
			conexion.setZonaConexion(daoZonaConexion.buscarZonaConexionId(zonaConexionId));
			conexion.setTipoTerreno(daoTipoTerreno.buscarTipoTerrenoId(tipoTerrenoConexionId));
			conexion.setRegimenPropiedad(daoRegimenPropiedad.buscarRegimenPropiedadId(regimenConexionId));
			conexion.setTipoSuministro(daoTipoSuministro.buscarTipoSuministroId(tipoSuministroConexionId));
			conexion.setCategoriaConexion(daoCategoriaConexion.buscarCategoriaConexionId(categoriaConexionId));
			conexion.setTipoConexion(daoTipoConexion.buscarTipoConexionId(tipoConexionId));
			conexion.setFormaPago(daoFormaPago.buscarFormaPagoId(fPagoConexionId));
			conexion.setUsuario(login.getUsuario());
			
			// se agrega la conexion al socio
			
			socio.getConexiones().add(conexion);
			
			SocioDAO daoSocio = new SocioDAOImplement();
			
			daoSocio.modificarSocio(socio);
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
		
//		// se iguala dom facturacion al de servicio
//		if(domServFactIguales == true){
//			domFact = domServ;
//			try {
//				domFact.setTipoDomicilio(daoTipoDomicilio.buscarTipoDomicilio("FACTURACION"));
//			} catch (Exception e) {
//				FacesContext.getCurrentInstance().addMessage(
//						null,
//						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
//								"Error al procesar: " + e.getMessage()));
//			}
//		}
//		else{
//			try {
//				domFact.setProvincia(provinciaDAO.buscarProvinciaId(provinciaDomFactId));
//				domFact.setPais(paisDAO.buscarPaisId(paisDomFactId));
//				domFact.setTipoDomicilio(daoTipoDomicilio.buscarTipoDomicilio("FACTURACION"));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
		
//		// se ingresan los domicilios de serv y fact
//		
//		DomicilioDAO daoDomicilio = new DomicilioDAOImplement();
//		
//		try {
//			daoDomicilio.insertarDomicilio(domServ);
//			daoDomicilio.insertarDomicilio(domFact);
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(
//					null,
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
//							"Error al procesar: " + e.getMessage()));
//		}
		
		
//		// se setea a conexion los domicilios y boolean Dom Fact Iguales
//		conexion.setDomicilioServicio(domServ);
//		conexion.setDomicilioFacturacion(domFact);
//		conexion.setDomServFactIguales(domServFactIguales);
		
//		// se setean los tipos a las ubicaciones catastrales y las mismas a la conexion
//		
//		TipoUbicacionCatastralDAO daoTipoUbicacionCatastral = new TipoUbicacionCatastralDAOImplement();
//		
//		try {
//			ubicOficial.setTipoUbicacionCatastral(daoTipoUbicacionCatastral.buscarTipoUbicacionCatastralDescripcion("OFICIAL"));
//			ubicCatastral.setTipoUbicacionCatastral(daoTipoUbicacionCatastral.buscarTipoUbicacionCatastralDescripcion("CATASTRAL"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		List<UbicacionCatastral> listaUbicaciones = new ArrayList();
//		listaUbicaciones.add(ubicOficial);
//		listaUbicaciones.add(ubicCatastral);
//		
//		conexion.setUbicacionesCatastrales(listaUbicaciones);
		
//		// se setea la informacion restante a la conexion
//		
//		EstadoConexionDAO daoEstadoConexion = new EstadoConexionDAOImplement();
//		ZonaConexionDAO daoZonaConexion = new ZonaConexionDAOImplement();
//		TipoTerrenoDAO daoTipoTerreno = new TipoTerrenoDAOImplement();
//		RegimenPropiedadDAO daoRegimenPropiedad = new RegimenPropiedadDAOImplement();
//		TipoSuministroDAO daoTipoSuministro = new TipoSuministroDAOImplement();
//		CategoriaConexionDAO daoCategoriaConexion = new CategoriaConexionDAOImplement();
//		TipoConexionDAO daoTipoConexion = new TipoConexionDAOImplement();
//		FormaPagoDAO daoFormaPago = new FormaPagoDAOImplement();
//		
//		try {
//			conexion.setEstadoConexion(daoEstadoConexion.buscarEstadoConexionId(estadoConexionId));
//			conexion.setZonaConexion(daoZonaConexion.buscarZonaConexionId(zonaConexionId));
//			conexion.setTipoTerreno(daoTipoTerreno.buscarTipoTerrenoId(tipoTerrenoConexionId));
//			conexion.setRegimenPropiedad(daoRegimenPropiedad.buscarRegimenPropiedadId(regimenConexionId));
//			conexion.setTipoSuministro(daoTipoSuministro.buscarTipoSuministroId(tipoSuministroConexionId));
//			conexion.setCategoriaConexion(daoCategoriaConexion.buscarCategoriaConexionId(categoriaConexionId));
//			conexion.setTipoConexion(daoTipoConexion.buscarTipoConexionId(tipoConexionId));
//			conexion.setFormaPago(daoFormaPago.buscarFormaPagoId(fPagoConexionId));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		socio.getConexiones().add(conexion);
//		
		// se agrega la conexion al socio
		
//		SocioDAO daoSocio = new SocioDAOImplement();
//		
//		try {
//			daoSocio.modificarSocio(socio);
//			FacesContext.getCurrentInstance().addMessage(
//					null,
//					new FacesMessage(FacesMessage.SEVERITY_INFO,
//							"Correctamente", "Se agrego correctamente"));
//			inicializar();
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(
//					null,
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
//							"Error al procesar: " + e.getMessage()));
//		}
	

	private void inicializar(){
		socio = new Socio();
		domServ = new Domicilio();
		domFact = new Domicilio();
		paisDomFactId = 0;
		provinciaDomFactId = 0;
		ubicOficial = new UbicacionCatastral();
		ubicCatastral = new UbicacionCatastral();
		conexion = new Conexion();
		estadoConexionId = 0;
		zonaConexionId = 0;
		tipoTerrenoConexionId = 0;
		regimenConexionId = 0;
		tipoSuministroConexionId = 0;
		categoriaConexionId = 0;
		tipoConexionId = 0;
		fPagoConexionId = 0;
	}
}
