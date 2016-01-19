package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import dao.CategoriaConexionDAO;
import dao.ConexionDAO;
import dao.DomicilioDAO;
import dao.EstadoConexionDAO;
import dao.FormaPagoDAO;
import dao.PaisDAO;
import dao.ProvinciaDAO;
import dao.RegimenPropiedadDAO;
import dao.SocioDAO;
import dao.TarjetaNaranjaDAO;
import dao.TipoConexionDAO;
import dao.TipoDomicilioDAO;
import dao.TipoSocioDAO;
import dao.TipoSuministroDAO;
import dao.TipoTerrenoDAO;
import dao.TipoUbicacionCatastralDAO;
import dao.ZonaConexionDAO;
import dao.impl.CategoriaConexionDAOImplement;
import dao.impl.ConexionDAOImplement;
import dao.impl.DomicilioDAOImplement;
import dao.impl.EstadoConexionDAOImplement;
import dao.impl.FormaPagoDAOImplement;
import dao.impl.PaisDAOImplement;
import dao.impl.ProvinciaDAOImplement;
import dao.impl.RegimenPropiedadDAOImplement;
import dao.impl.SocioDAOImplement;
import dao.impl.TarjetaNaranjaDAOImplement;
import dao.impl.TipoConexionDAOImplement;
import dao.impl.TipoDomicilioDAOImplement;
import dao.impl.TipoSocioDAOImplement;
import dao.impl.TipoSuministroDAOImplement;
import dao.impl.TipoTerrenoDAOImplement;
import dao.impl.TipoUbicacionCatastralDAOImplement;
import dao.impl.ZonaConexionDAOImplement;
import model.Conexion;
import model.Domicilio;
import model.Socio;
import model.TarjetaNaranja;
import model.UbicacionCatastral;

@ManagedBean(name = "conexionBean")
@ViewScoped
public class ConexionBean implements Serializable {

	private List<Socio> lstSociosActivos;
	private Socio socio;
	private Socio socioSeleccionado;
	
	private boolean domServFactIguales;
	
	private Domicilio domServ;
	private Domicilio domFact;
	
	private long paisDomFactId;
	private long provinciaDomFactId;
	
	private UbicacionCatastral ubicOficial;
	private UbicacionCatastral ubicCatastral;
	
	private Conexion conexion;
	private Conexion conexionSeleccionada;
	private Conexion conexionEditar;
	private List<Conexion> lstConexiones;
	private long estadoConexionId;
	private long zonaConexionId;
	private long tipoTerrenoConexionId;
	private long regimenConexionId;
	private long tipoSuministroConexionId;
	private long categoriaConexionId;
	private long tipoConexionId;
	private long fPagoConexionId;
	
	private boolean checkDatosParametrizables;
	private boolean checkEstConex;
	private boolean checkZonaConex;
	private boolean checkTipoTerreno;
	private boolean checkRegimenPropiedad;
	private boolean checkFormaPago;
	private boolean checkTipoSuministro;
	private boolean checkCategoriaConex;
	private boolean checkTipoConex;
	
	private boolean checkDatosNoParametrizables;
	
	private boolean checkDomServFactIguales;
	
	private TarjetaNaranja datosTarjeta;
	
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

	public Socio getSocioSeleccionado() {
		return socioSeleccionado;
	}

	public void setSocioSeleccionado(Socio socioSeleccionado) {
		this.socioSeleccionado = socioSeleccionado;
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

	public Conexion getConexionSeleccionada() {
		return conexionSeleccionada;
	}

	public void setConexionSeleccionada(Conexion conexionSeleccionada) {
		this.conexionSeleccionada = conexionSeleccionada;
	}

	public Conexion getConexionEditar() {
		return conexionEditar;
	}

	public void setConexionEditar(Conexion conexionEditar) {
		this.conexionEditar = conexionEditar;
	}

	public List<Conexion> getLstConexiones() {
		try {
			ConexionDAO daoConexion = new ConexionDAOImplement();
			lstConexiones = daoConexion.listaConexion();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
		}
		return lstConexiones;
	}

	public void setLstConexiones(List<Conexion> lstConexiones) {
		this.lstConexiones = lstConexiones;
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
	
	public boolean isCheckDatosParametrizables() {
		return checkDatosParametrizables;
	}

	public void setCheckDatosParametrizables(boolean checkDatosParametrizables) {
		this.checkDatosParametrizables = checkDatosParametrizables;
	}

	public boolean isCheckEstConex() {
		return checkEstConex;
	}

	public void setCheckEstConex(boolean checkEstConex) {
		this.checkEstConex = checkEstConex;
	}

	public boolean isCheckZonaConex() {
		return checkZonaConex;
	}

	public void setCheckZonaConex(boolean checkZonaConex) {
		this.checkZonaConex = checkZonaConex;
	}

	public boolean isCheckTipoTerreno() {
		return checkTipoTerreno;
	}

	public void setCheckTipoTerreno(boolean checkTipoTerreno) {
		this.checkTipoTerreno = checkTipoTerreno;
	}

	public boolean isCheckRegimenPropiedad() {
		return checkRegimenPropiedad;
	}

	public void setCheckRegimenPropiedad(boolean checkRegimenPropiedad) {
		this.checkRegimenPropiedad = checkRegimenPropiedad;
	}

	public boolean isCheckFormaPago() {
		return checkFormaPago;
	}

	public void setCheckFormaPago(boolean checkFormaPago) {
		this.checkFormaPago = checkFormaPago;
	}

	public boolean isCheckTipoSuministro() {
		return checkTipoSuministro;
	}

	public void setCheckTipoSuministro(boolean checkTipoSuministro) {
		this.checkTipoSuministro = checkTipoSuministro;
	}

	public boolean isCheckCategoriaConex() {
		return checkCategoriaConex;
	}

	public void setCheckCategoriaConex(boolean checkCategoriaConex) {
		this.checkCategoriaConex = checkCategoriaConex;
	}

	public boolean isCheckTipoConex() {
		return checkTipoConex;
	}

	public void setCheckTipoConex(boolean checkTipoConex) {
		this.checkTipoConex = checkTipoConex;
	}

	public boolean isCheckDatosNoParametrizables() {
		return checkDatosNoParametrizables;
	}

	public void setCheckDatosNoParametrizables(boolean checkDatosNoParametrizables) {
		this.checkDatosNoParametrizables = checkDatosNoParametrizables;
	}


	public boolean isCheckDomServFactIguales() {
		return checkDomServFactIguales;
	}

	public void setCheckDomServFactIguales(boolean checkDomServFactIguales) {
		this.checkDomServFactIguales = checkDomServFactIguales;
	}

	public TarjetaNaranja getDatosTarjeta() {
		return datosTarjeta;
	}

	public void setDatosTarjeta(TarjetaNaranja datosTarjeta) {
		this.datosTarjeta = datosTarjeta;
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
			
			ConexionDAO conexionDAO = new ConexionDAOImplement();
			conexion.setSocio(socio);
			conexionDAO.insertarConexion(conexion);
			
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

	public void mostrarSocio(Socio socio) {
		this.socioSeleccionado = socio;
	}
	
	public void mostrarConexion(Conexion conexion) {
		this.conexionSeleccionada = conexion;
	}

	public List<Conexion> obtenerConexionesSocio(){
		List<Conexion> lista = new ArrayList<Conexion>();
		
		try{
			lista = socioSeleccionado.getConexiones();
		}
		catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error al procesar: " + e.getMessage()));
		}
		
		return lista;
	}
	
	public void cambiarEstadoConexion(String estado, Conexion conexion){
		ConexionDAO daoConexion = new ConexionDAOImplement();
		EstadoConexionDAO daoEstadoConexion = new EstadoConexionDAOImplement();
		
		try {
			conexion.setEstadoConexion(daoEstadoConexion.buscarEstadoConexion(estado));
			
			//faltan las transacciones
			
			daoConexion.modificarConexion(conexion);
			
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Correctamente", "La conexión se modifico correctamente."));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error al procesar: " + e.getMessage()));
		}
	}
	
	public void editarConexion(){
		
		checkDomServFactIguales= false;
		
		try {
			
			if(checkEstConex == true){
				EstadoConexionDAO daoEstadoConexion = new EstadoConexionDAOImplement();
				conexionEditar.setEstadoConexion(daoEstadoConexion.buscarEstadoConexionId(estadoConexionId));
			}
			
			if(checkZonaConex == true){
				ZonaConexionDAO daoZonaConexion = new ZonaConexionDAOImplement();
				conexionEditar.setZonaConexion(daoZonaConexion.buscarZonaConexionId(zonaConexionId));
			}
			
			if(checkTipoTerreno == true){
				TipoTerrenoDAO daoTipoTerreno = new TipoTerrenoDAOImplement();
				conexionEditar.setTipoTerreno(daoTipoTerreno.buscarTipoTerrenoId(tipoTerrenoConexionId));
			}
			
			if(checkRegimenPropiedad == true){
				RegimenPropiedadDAO daoRegimenPropiedad = new RegimenPropiedadDAOImplement();
				conexionEditar.setRegimenPropiedad(daoRegimenPropiedad.buscarRegimenPropiedadId(regimenConexionId));
			}
			
			if(checkTipoSuministro == true){
				TipoSuministroDAO daoTipoSuministro = new TipoSuministroDAOImplement();
				conexionEditar.setTipoSuministro(daoTipoSuministro.buscarTipoSuministroId(tipoSuministroConexionId));
			}
			
			if(checkCategoriaConex == true){
				CategoriaConexionDAO daoCategoriaConexion = new CategoriaConexionDAOImplement();
				conexionEditar.setCategoriaConexion(daoCategoriaConexion.buscarCategoriaConexionId(categoriaConexionId));
			}
			
			if(checkTipoConex == true){
				TipoConexionDAO daoTipoConexion = new TipoConexionDAOImplement();
				conexionEditar.setTipoConexion(daoTipoConexion.buscarTipoConexionId(tipoConexionId));
			}
			
			if(checkFormaPago == true){
				FormaPagoDAO daoFormaPago = new FormaPagoDAOImplement();
				conexionEditar.setFormaPago(daoFormaPago.buscarFormaPagoId(fPagoConexionId));
			}
			
			ConexionDAO conexionDAO = new ConexionDAOImplement();
			conexionDAO.modificarConexion(conexionEditar);
			
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
	
	public void editarDatosTarjeta(){
		
		try {
			TarjetaNaranjaDAO tarjetaDAO = new TarjetaNaranjaDAOImplement();
			
			tarjetaDAO.insertarTarjetaNaranja(datosTarjeta);
			
			conexionEditar.setDatosTarjetaNaranja(datosTarjeta);
						
			ConexionDAO conexionDAO = new ConexionDAOImplement();
			conexionDAO.modificarConexion(conexionEditar);
			
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
	
	public void onRowEdit(RowEditEvent event) {
		System.out.println("HOLA: " + conexionEditar.getDomicilioFacturacion().getLocalidad() + " " + conexionEditar.getDomicilioFacturacion().getProvincia().getDescripcion()); 
		try {
			//ProvinciaDAO provinciaDAO = new ProvinciaDAOImplement();
			//conexionEditar.getDomicilioFacturacion().setProvincia(provinciaDAO.buscarProvinciaId(conexionEditar.getDomicilioFacturacion().getProvincia().getId()));
			
			DomicilioDAO daoDomicilio = new DomicilioDAOImplement();
			daoDomicilio.modificarDomicilio(conexionEditar.getDomicilioFacturacion());
			
			ConexionDAO conexionDAO = new ConexionDAOImplement();
			conexionDAO.modificarConexion(conexionEditar);
			
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
	
	public void editarConexion(Conexion conexion){
		this.conexionEditar = conexion;
	}
	
	public void inicializar(){
		socio = new Socio();
		socioSeleccionado = new Socio();
		domServ = new Domicilio();
		domFact = new Domicilio();
		paisDomFactId = 0;
		provinciaDomFactId = 0;
		ubicOficial = new UbicacionCatastral();
		ubicCatastral = new UbicacionCatastral();
		conexion = new Conexion();
		conexionSeleccionada = new Conexion();
		conexionEditar = new Conexion();
		lstConexiones = new ArrayList<Conexion>();
		estadoConexionId = 0;
		zonaConexionId = 0;
		tipoTerrenoConexionId = 0;
		regimenConexionId = 0;
		tipoSuministroConexionId = 0;
		categoriaConexionId = 0;
		tipoConexionId = 0;
		fPagoConexionId = 0;
		
		checkDatosParametrizables= false;
		checkEstConex = false;
		checkZonaConex= false;
		checkTipoTerreno= false;
		checkRegimenPropiedad= false;
		checkFormaPago= false;
		checkTipoSuministro= false;
		checkCategoriaConex= false;
		checkTipoConex= false;
		
		checkDatosNoParametrizables = false;
		
		checkDomServFactIguales= false;
		
		datosTarjeta = new TarjetaNaranja();
	}
}
