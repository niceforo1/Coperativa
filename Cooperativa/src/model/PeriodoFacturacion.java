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
@Table(name = "PERIODO_FACTURACION")
public class PeriodoFacturacion implements Serializable {
	@Id
	@Column(name = "PERIODO_FACT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "MES", nullable = false)
	private long mes;

	@Column(name = "ANIO", nullable = false)
	private long anio;

	@Column(name = "CESP")
	private long cesp;

	@Column(name = "VTO_CESP")
	private Date fechaVtoCesp;

	@Column(name = "FECHA_EMISION_FACTURA")
	private Date fechaEmisionFactura;

	@Column(name = "VENCIMIENTO_FACT_1")
	private Date fechaPrimerVencimientoFactura;

	@Column(name = "VENCIMIENTO_FACT_2")
	private Date fechaSegundoVencimientoFactura;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USR_ID_CIERRE_FACT")
	private Usuario usuarioCierreFact;

	public PeriodoFacturacion() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getFechaEmisionFactura() {
		return fechaEmisionFactura;
	}

	public void setFechaEmisionFactura(Date fechaEmisionFactura) {
		this.fechaEmisionFactura = fechaEmisionFactura;
	}

	public Date getFechaPrimerVencimientoFactura() {
		return fechaPrimerVencimientoFactura;
	}

	public void setFechaPrimerVencimientoFactura(Date fechaPrimerVencimientoFactura) {
		this.fechaPrimerVencimientoFactura = fechaPrimerVencimientoFactura;
	}

	public Date getFechaSegundoVencimientoFactura() {
		return fechaSegundoVencimientoFactura;
	}

	public void setFechaSegundoVencimientoFactura(Date fechaSegundoVencimientoFactura) {
		this.fechaSegundoVencimientoFactura = fechaSegundoVencimientoFactura;
	}

	public Usuario getUsuarioCierreFact() {
		return usuarioCierreFact;
	}

	public void setUsuarioCierreFact(Usuario usuarioCierreFact) {
		this.usuarioCierreFact = usuarioCierreFact;
	}

}
