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
@Table(name = "FACTURA")
public class Factura implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "TIPO_FACTURA", nullable = false)
	private String tipoFactura;

	@Column(name = "NRO_FACTURA", nullable = false)
	private String numeroFactura;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PERIODO_FACT_ID")
	private PeriodoFacturacion periodoFacturacion;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_CONCEPTO_FACTURACION")
	private ConceptoFacturacion conceptoFacturacion;

	@Column(name = "INTERESES_2DO_VTO", precision = 15, scale = 2)
	private Double interesesSegVenc;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_CONEXION")
	private Conexion conexion;

	@Column(name = "CARGO_FIJO", nullable = true, precision = 18, scale = 2)
	private Double cargoFijo;

	@Column(name = "TRAMO_1")
	private long tramo1;

	@Column(name = "TRAMO_2")
	private long tramo2;

	@Column(name = "TRAMO_3")
	private long tramo3;

	@Column(name = "TRAMO_4")
	private long tramo4;

	@Column(name = "TRAMO_5")
	private long tramo5;

	@Column(name = "TRAMO_6")
	private long tramo6;

	@Column(name = "CAPITAL_SOCIAL", nullable = true, precision = 18, scale = 2)
	private Double capitalSocial;

	@Column(name = "ERSEP", nullable = true, precision = 18, scale = 2)
	private Double ersep;

	@Column(name = "RECUPERO_INVERSION", nullable = true, precision = 18, scale = 2)
	private Double recuperoInversion;

	@Column(name = "IMPRESION_Y_OTROS", nullable = true, precision = 18, scale = 2)
	private Double impresionesOtros;

	@Column(name = "IVA", precision = 18, scale = 2)
	private Double iva;

	@Column(name = "IMPORTE_TOTAL", nullable = false, precision = 18, scale = 2)
	private Double importeTotal;

	@Column(name = "FECHA_VENCIMIENTO")
	private Date fechaVencimiento;

	public Factura() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public PeriodoFacturacion getPeriodoFacturacion() {
		return periodoFacturacion;
	}

	public void setPeriodoFacturacion(PeriodoFacturacion periodoFacturacion) {
		this.periodoFacturacion = periodoFacturacion;
	}

	public ConceptoFacturacion getConceptoFacturacion() {
		return conceptoFacturacion;
	}

	public void setConceptoFacturacion(ConceptoFacturacion conceptoFacturacion) {
		this.conceptoFacturacion = conceptoFacturacion;
	}

	public Double getInteresesSegVenc() {
		return (Math.rint(interesesSegVenc * 100) / 100);
	}

	public void setInteresesSegVenc(Double interesesSegVenc) {
		this.interesesSegVenc = (Math.rint(interesesSegVenc * 100) / 100);
	}

	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

	public Double getCargoFijo() {
		return (Math.rint(cargoFijo * 100) / 100);
	}

	public void setCargoFijo(Double cargoFijo) {
		this.cargoFijo = (Math.rint(cargoFijo * 100) / 100);
	}

	public long getTramo1() {
		return tramo1;
	}

	public void setTramo1(long tramo1) {
		this.tramo1 = tramo1;
	}

	public long getTramo2() {
		return tramo2;
	}

	public void setTramo2(long tramo2) {
		this.tramo2 = tramo2;
	}

	public long getTramo3() {
		return tramo3;
	}

	public void setTramo3(long tramo3) {
		this.tramo3 = tramo3;
	}

	public long getTramo4() {
		return tramo4;
	}

	public void setTramo4(long tramo4) {
		this.tramo4 = tramo4;
	}

	public long getTramo5() {
		return tramo5;
	}

	public void setTramo5(long tramo5) {
		this.tramo5 = tramo5;
	}

	public long getTramo6() {
		return tramo6;
	}

	public void setTramo6(long tramo6) {
		this.tramo6 = tramo6;
	}

	public Double getCapitalSocial() {
		return (Math.rint(capitalSocial * 100) / 100);
	}

	public void setCapitalSocial(Double capitalSocial) {
		this.capitalSocial = (Math.rint(capitalSocial * 100) / 100);
	}

	public Double getErsep() {
		return (Math.rint(ersep * 100) / 100);
	}

	public void setErsep(Double ersep) {
		this.ersep = (Math.rint(ersep * 100) / 100);
	}

	public Double getRecuperoInversion() {
		return (Math.rint(recuperoInversion * 100) / 100);
	}

	public void setRecuperoInversion(Double recuperoInversion) {
		this.recuperoInversion = (Math.rint(recuperoInversion * 100) / 100);
	}

	public Double getImpresionesOtros() {
		return (Math.rint(impresionesOtros * 100) / 100);
	}

	public void setImpresionesOtros(Double impresionesOtros) {
		this.impresionesOtros = (Math.rint(impresionesOtros * 100) / 100);
	}

	public Double getIva() {
		return (Math.rint(iva * 100) / 100);
	}

	public void setIva(Double iva) {
		this.iva = (Math.rint(iva * 100) / 100);
	}

	public Double getImporteTotal() {
		return (Math.rint(importeTotal * 100) / 100);
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = (Math.rint(importeTotal * 100) / 100);
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", tipoFactura=" + tipoFactura + ", periodoFacturacion=" + periodoFacturacion
				+ ", conceptoFacturacion=" + conceptoFacturacion + ", interesesSegVenc=" + interesesSegVenc
				+ ", conexion=" + conexion + ", cargoFijo=" + cargoFijo + ", tramo1=" + tramo1 + ", tramo2=" + tramo2
				+ ", tramo3=" + tramo3 + ", tramo4=" + tramo4 + ", tramo5=" + tramo5 + ", tramo6=" + tramo6
				+ ", capitalSocial=" + capitalSocial + ", ersep=" + ersep + ", recuperoInversion=" + recuperoInversion
				+ ", impresionesOtros=" + impresionesOtros + ", iva=" + iva + ", importeTotal=" + importeTotal + "]";
	}

}
