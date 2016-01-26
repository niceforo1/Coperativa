package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONFIGURACION_FACTURAS")
public class ConfiguracionFactura implements Serializable {

	@Id
	@Column(name = "ID_CONFIGURACION_FACT")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "CARGO_FIJO")
	private Float cargoFijo;

	@Column(name = "TRAMO_1", nullable = false)
	private Float tramo1;

	@Column(name = "TRAMO_2", nullable = false)
	private Float tramo2;

	@Column(name = "TRAMO_3", nullable = false)
	private Float tramo3;

	@Column(name = "TRAMO_4", nullable = false)
	private Float tramo4;

	@Column(name = "TRAMO_5", nullable = false)
	private Float tramo5;

	@Column(name = "TRAMO_6", nullable = false)
	private Float tramo6;

	@Column(name = "CAPITAL_SOCIAL", nullable = false)
	private Float capitalSocial;

	@Column(name = "ERSEP", nullable = false)
	private Float ersep;

	@Column(name = "RECUPERO_INVERSION", nullable = false)
	private Float recuperoInversion;

	@Column(name = "IMPRESION_Y_OTROS", nullable = false)
	private Float impresionesOtros;

	@Column(name = "LEYENDA_COMPROBANTE", nullable = false)
	private String leyendaComprobante;

	public ConfiguracionFactura() {

	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getCargoFijo() {
		return cargoFijo;
	}

	public void setCargoFijo(Float cargoFijo) {
		this.cargoFijo = cargoFijo;
	}

	public Float getTramo1() {
		return tramo1;
	}

	public void setTramo1(Float tramo1) {
		this.tramo1 = tramo1;
	}

	public Float getTramo2() {
		return tramo2;
	}

	public void setTramo2(Float tramo2) {
		this.tramo2 = tramo2;
	}

	public Float getTramo3() {
		return tramo3;
	}

	public void setTramo3(Float tramo3) {
		this.tramo3 = tramo3;
	}

	public Float getTramo4() {
		return tramo4;
	}

	public void setTramo4(Float tramo4) {
		this.tramo4 = tramo4;
	}

	public Float getTramo5() {
		return tramo5;
	}

	public void setTramo5(Float tramo5) {
		this.tramo5 = tramo5;
	}

	public Float getTramo6() {
		return tramo6;
	}

	public void setTramo6(Float tramo6) {
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

	public String getLeyendaComprobante() {
		return leyendaComprobante;
	}

	public void setLeyendaComprobante(String leyendaComprobante) {
		this.leyendaComprobante = leyendaComprobante;
	}

	@Override
	public String toString() {
		return "ConfiguracionFactura [id=" + id + ", cargoFijo=" + cargoFijo + ", tramo1=" + tramo1 + ", tramo2="
				+ tramo2 + ", tramo3=" + tramo3 + ", tramo4=" + tramo4 + ", tramo5=" + tramo5 + ", tramo6=" + tramo6
				+ ", capitalSocial=" + capitalSocial + ", ersep=" + ersep + ", recuperoInversion=" + recuperoInversion
				+ ", impresionesOtros=" + impresionesOtros + ", leyendaComprobante=" + leyendaComprobante + "]";
	}

}
