package model;

import java.io.Serializable;

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

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PERIODO_FACT_ID")
	private PeriodoFacturacion periodoFacturacion;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_CONCEPTO_FACTURACION")
	private ConceptoFacturacion conceptoFacturacion;

	@Column(name = "INTERESES_2DO_VTO")
	private Float interesesSegVenc;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_CONEXION")
	private Conexion conexion;

	@Column(name = "CARGO_FIJO", nullable = false)
	private Float cargoFijo;

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

	@Column(name = "CAPITAL_SOCIAL", nullable = false)
	private Float capitalSocial;

	@Column(name = "ERSEP", nullable = false)
	private Float ersep;

	@Column(name = "RECUPERO_INVERSION", nullable = false)
	private Float recuperoInversion;

	@Column(name = "IMPRESION_Y_OTROS", nullable = false)
	private Float impresionesOtros;

	@Column(name = "IVA")
	private Float iva;

	@Column(name = "IMPORTE_TOTAL", nullable = false)
	private Float importeTotal;

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

	public Float getInteresesSegVenc() {
		return interesesSegVenc;
	}

	public void setInteresesSegVenc(Float interesesSegVenc) {
		this.interesesSegVenc = interesesSegVenc;
	}

	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

	public Float getCargoFijo() {
		return cargoFijo;
	}

	public void setCargoFijo(Float cargoFijo) {
		this.cargoFijo = cargoFijo;
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

	public Float getCapitalSocial() {
		return capitalSocial;
	}

	public void setCapitalSocial(Float capitalSocial) {
		this.capitalSocial = capitalSocial;
	}

	public Float getErsep() {
		return ersep;
	}

	public void setErsep(Float ersep) {
		this.ersep = ersep;
	}

	public Float getRecuperoInversion() {
		return recuperoInversion;
	}

	public void setRecuperoInversion(Float recuperoInversion) {
		this.recuperoInversion = recuperoInversion;
	}

	public Float getImpresionesOtros() {
		return impresionesOtros;
	}

	public void setImpresionesOtros(Float impresionesOtros) {
		this.impresionesOtros = impresionesOtros;
	}

	public Float getIva() {
		return iva;
	}

	public void setIva(Float iva) {
		this.iva = iva;
	}

	public Float getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Float importeTotal) {
		this.importeTotal = importeTotal;
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
