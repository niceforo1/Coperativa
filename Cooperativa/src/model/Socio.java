package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "SOCIOS")
public class Socio implements Serializable {
	@Id
	@Column(name = "ID_SOCIO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long numero;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_TIPO_SOCIO", nullable = false)
	private TipoSocio tipoSocio;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_ESTADO_SOCIO", nullable = false)
	private EstadoSocio estadoSocio;

	@Column(name = "FECHA_APROBACION", nullable = false)
	private Date fechaAlta;

	@Column(name = "APELLIDO_RAZON_SOCIAL", nullable = false)
	private String apellidoRazonSocial;

	@Column(name = "NOMBRE_DENOMINACION", nullable = true)
	private String nombreDenominacion;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_TIPO_DOC", nullable = false)
	private TipoDocumento tipoDocumento;

	@Column(name = "ID_NRO_DOC", nullable = false)
	private Long numeroDocumento;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_ESTADO_CIVIL")
	private EstadoCivil estadoCivil;

	@Column(name = "NOMBRE_CONYUGE")
	private String nombreConyuge;

	@Column(name = "TE_CELULAR")
	private String numCelular;

	@Column(name = "EMAIL")
	private String email;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_PAIS", nullable = false)
	private Pais pais;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_PROVINCIA", nullable = true)
	private Provincia provincia;

	@Column(name = "PROFESION_RUBRO")
	private String profesionRubro;

	@Column(name = "OBSERVACIONES", length = 5000)
	private String observaciones;

	@Column(name = "ING_BRUTOS", nullable = true)
	private Long ingBrutos;

	@Column(name = "NRO_IVA", nullable = true)
	private Long numeroIva;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_CONDICION_IVA", nullable = false)
	private CondicionIva condicionIva;

	@Column(name = "INSCRIPTO_GANANCIAS", nullable = true)
	private boolean inscriptoGanancias;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Domicilio> domicilios;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USR_ID", nullable = false)
	private Usuario usuario;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Conexion> conexiones;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<SociosTransacciones> transacciones;

	public Socio() {

	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public TipoSocio getTipoSocio() {
		return tipoSocio;
	}

	public void setTipoSocio(TipoSocio tipoSocio) {
		this.tipoSocio = tipoSocio;
	}

	public EstadoSocio getEstadoSocio() {
		return estadoSocio;
	}

	public void setEstadoSocio(EstadoSocio estadoSocio) {
		this.estadoSocio = estadoSocio;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getApellidoRazonSocial() {
		return apellidoRazonSocial;
	}

	public void setApellidoRazonSocial(String apellidoRazonSocial) {
		this.apellidoRazonSocial = apellidoRazonSocial;
	}

	public String getNombreDenominacion() {
		return nombreDenominacion;
	}

	public void setNombreDenominacion(String nombreDenominacion) {
		this.nombreDenominacion = nombreDenominacion;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getNombreConyuge() {
		return nombreConyuge;
	}

	public void setNombreConyuge(String nombreConyuge) {
		this.nombreConyuge = nombreConyuge;
	}

	public String getNumCelular() {
		return numCelular;
	}

	public void setNumCelular(String numCelular) {
		this.numCelular = numCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getProfesionRubro() {
		return profesionRubro;
	}

	public void setProfesionRubro(String profesionRubro) {
		this.profesionRubro = profesionRubro;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getIngBrutos() {
		return ingBrutos;
	}

	public void setIngBrutos(Long ingBrutos) {
		this.ingBrutos = ingBrutos;
	}

	public Long getNumeroIva() {
		return numeroIva;
	}

	public void setNumeroIva(Long numeroIva) {
		this.numeroIva = numeroIva;
	}

	public CondicionIva getCondicionIva() {
		return condicionIva;
	}

	public void setCondicionIva(CondicionIva condicionIva) {
		this.condicionIva = condicionIva;
	}

	public boolean isInscriptoGanancias() {
		return inscriptoGanancias;
	}

	public void setInscriptoGanancias(boolean inscriptoGanancias) {
		this.inscriptoGanancias = inscriptoGanancias;
	}

	public List<Domicilio> getDomicilios() {
		return domicilios;
	}

	public void setDomicilios(List<Domicilio> domicilios) {
		this.domicilios = domicilios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Conexion> getConexiones() {
		return conexiones;
	}

	public void setConexiones(List<Conexion> conexiones) {
		this.conexiones = conexiones;
	}

	public List<SociosTransacciones> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<SociosTransacciones> transacciones) {
		this.transacciones = transacciones;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}// end Socio