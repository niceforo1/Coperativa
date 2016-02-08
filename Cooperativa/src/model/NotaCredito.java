package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NOTAS_DE_CREDITO")
public class NotaCredito implements Serializable{

	@Id
	@Column(name = "ID_NOTA_DE_CREDITO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_TIPO_NOTA_CREDITO")
	private TipoComprobante tipoComprobante;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_CONEXION")
	private Conexion conexion;
	
	@Column(name = "ID_PERIODO_MES")
	private long mes;

	@Column(name = "ID_PERIODO_ANIO")
	private long anio;
	
	@Column(name = "FECHA_EMISION")
	private Date fechaEmision;
	
	@Column(name = "CESP")
	private long cesp;

	@Column(name = "VTO_CESP")
	private Date fechaVtoCesp;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_CONCEPTO_FACTURACION")
	private ConceptoFacturacion conceptoFacturacion;
	
	@Column(name = "IVA")
	private Float iva;
	
	@Column(name = "IMPORTE")
	private Float importe;
	
	@Column(name = "OBSERVACIONES", length=5000)
	private String observaciones;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USR_ID")
	private Usuario usuario;
	
	public NotaCredito() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoComprobante getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(TipoComprobante tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

	public long getMes() {
		return mes;
	}

	public void setMes(long mes) {
		this.mes = mes;
	}

	public long getAnio() {
		return anio;
	}

	public void setAnio(long anio) {
		this.anio = anio;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public long getCesp() {
		return cesp;
	}

	public void setCesp(long cesp) {
		this.cesp = cesp;
	}

	public Date getFechaVtoCesp() {
		return fechaVtoCesp;
	}

	public void setFechaVtoCesp(Date fechaVtoCesp) {
		this.fechaVtoCesp = fechaVtoCesp;
	}

	public ConceptoFacturacion getConceptoFacturacion() {
		return conceptoFacturacion;
	}

	public void setConceptoFacturacion(ConceptoFacturacion conceptoFacturacion) {
		this.conceptoFacturacion = conceptoFacturacion;
	}

	public Float getIva() {
		return iva;
	}

	public void setIva(Float iva) {
		this.iva = iva;
	}

	public Float getImporte() {
		return importe;
	}

	public void setImporte(Float importe) {
		this.importe = importe;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}