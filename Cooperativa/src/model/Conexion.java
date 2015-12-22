package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONEXIONES")
public class Conexion implements Serializable {

	@Id
	@Column(name = "ID_CONEXION")
	private int id;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_ESTADO_CONEXION", nullable = false)
	private EstadoConexion estadoConexion;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_SOCIO")
	private Socio socio;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_NRO_ZONA", nullable = false)
	private ZonaConexion zonaConexion;

	@Column(name = "FECHA_ALTA", nullable = false)
	private Date fechaAlta;

	@Column(name = "TERRENO")
	private long terreno;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_TIPO_TERRENO", nullable = false)
	private TipoTerreno tipoTerreno;

	@Column(name = "PILETA")
	private long pileta;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_REGIMEN_PROPIEDAD", nullable = false)
	private RegimenPropiedad regimenPropiedad;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_FORMA_PAGO", nullable = false)
	private FormaPago formaPago;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_TIPO_SUMINISTRO", nullable = false)
	private TipoSuministro tipoSuministro;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_CATEGORIA_CONEXION", nullable = false)
	private CategoriaConexion categoriaConexion;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_TIPO_CONEXION", nullable = false)
	private TipoConexion tipoConexion;

	@Column(name = "NRO_MEDIDOR")
	private Long nroMedidor;

	@Column(name = "EMITE_FACTURA")
	private Boolean emiteFactura;

	@Column(name = "DOM_SERV_FACT_IGUALES")
	private Boolean domServFactIguales;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_DOMICILIO_SERV", nullable = false)
	private Domicilio domicilioServicio;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_DOMICILIO_FACT")
	private Domicilio domicilioFacturacion;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USR_ID")
	private Usuario usuario;

	public Conexion() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EstadoConexion getEstadoConexion() {
		return estadoConexion;
	}

	public void setEstadoConexion(EstadoConexion estadoConexion) {
		this.estadoConexion = estadoConexion;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public ZonaConexion getZonaConexion() {
		return zonaConexion;
	}

	public void setZonaConexion(ZonaConexion zonaConexion) {
		this.zonaConexion = zonaConexion;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public long getTerreno() {
		return terreno;
	}

	public void setTerreno(long terreno) {
		this.terreno = terreno;
	}

	public TipoTerreno getTipoTerreno() {
		return tipoTerreno;
	}

	public void setTipoTerreno(TipoTerreno tipoTerreno) {
		this.tipoTerreno = tipoTerreno;
	}

	public long getPileta() {
		return pileta;
	}

	public void setPileta(long pileta) {
		this.pileta = pileta;
	}

	public RegimenPropiedad getRegimenPropiedad() {
		return regimenPropiedad;
	}

	public void setRegimenPropiedad(RegimenPropiedad regimenPropiedad) {
		this.regimenPropiedad = regimenPropiedad;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public TipoSuministro getTipoSuministro() {
		return tipoSuministro;
	}

	public void setTipoSuministro(TipoSuministro tipoSuministro) {
		this.tipoSuministro = tipoSuministro;
	}

	public CategoriaConexion getCategoriaConexion() {
		return categoriaConexion;
	}

	public void setCategoriaConexion(CategoriaConexion categoriaConexion) {
		this.categoriaConexion = categoriaConexion;
	}

	public TipoConexion getTipoConexion() {
		return tipoConexion;
	}

	public void setTipoConexion(TipoConexion tipoConexion) {
		this.tipoConexion = tipoConexion;
	}

	public Long getNroMedidor() {
		return nroMedidor;
	}

	public void setNroMedidor(Long nroMedidor) {
		this.nroMedidor = nroMedidor;
	}

	public Boolean getEmiteFactura() {
		return emiteFactura;
	}

	public void setEmiteFactura(Boolean emiteFactura) {
		this.emiteFactura = emiteFactura;
	}

	public Boolean getDomServFactIguales() {
		return domServFactIguales;
	}

	public void setDomServFactIguales(Boolean domServFactIguales) {
		this.domServFactIguales = domServFactIguales;
	}

	public Domicilio getDomicilioServicio() {
		return domicilioServicio;
	}

	public void setDomicilioServicio(Domicilio domicilioServicio) {
		this.domicilioServicio = domicilioServicio;
	}

	public Domicilio getDomicilioFacturacion() {
		return domicilioFacturacion;
	}

	public void setDomicilioFacturacion(Domicilio domicilioFacturacion) {
		this.domicilioFacturacion = domicilioFacturacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
