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

	@Column(name = "CARGO_FIJO", nullable = false)
	private Float cargoFijo;

	@Column(name = "TRAMO_1", nullable = false)
	private int tramo1;

	@Column(name = "TRAMO_2", nullable = false)
	private int tramo2;

	@Column(name = "TRAMO_3", nullable = false)
	private int tramo3;

	@Column(name = "TRAMO_4", nullable = false)
	private int tramo4;

	@Column(name = "TRAMO_5", nullable = false)
	private int tramo5;

	@Column(name = "TRAMO_6", nullable = false)
	private int tramo6;

	@Column(name = "CAPITAL_SOCIAL", nullable = false)
	private int capitalSocial;

	@Column(name = "ERSEP", nullable = false)
	private int ersep;

	@Column(name = "RECUPERO_INVERSION", nullable = false)
	private int recuperoInversion;

	@Column(name = "IMPRESION_Y_OTROS", nullable = false)
	private int impresionesOtros;

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

	public int getTramo1() {
		return tramo1;
	}

	public void setTramo1(int tramo1) {
		this.tramo1 = tramo1;
	}

	public int getTramo2() {
		return tramo2;
	}

	public void setTramo2(int tramo2) {
		this.tramo2 = tramo2;
	}

	public int getTramo3() {
		return tramo3;
	}

	public void setTramo3(int tramo3) {
		this.tramo3 = tramo3;
	}

	public int getTramo4() {
		return tramo4;
	}

	public void setTramo4(int tramo4) {
		this.tramo4 = tramo4;
	}

	public int getTramo5() {
		return tramo5;
	}

	public void setTramo5(int tramo5) {
		this.tramo5 = tramo5;
	}

	public int getTramo6() {
		return tramo6;
	}

	public void setTramo6(int tramo6) {
		this.tramo6 = tramo6;
	}

	public int getCapitalSocial() {
		return capitalSocial;
	}

	public void setCapitalSocial(int capitalSocial) {
		this.capitalSocial = capitalSocial;
	}

	public int getErsep() {
		return ersep;
	}

	public void setErsep(int ersep) {
		this.ersep = ersep;
	}

	public int getRecuperoInversion() {
		return recuperoInversion;
	}

	public void setRecuperoInversion(int recuperoInversion) {
		this.recuperoInversion = recuperoInversion;
	}

	public int getImpresionesOtros() {
		return impresionesOtros;
	}

	public void setImpresionesOtros(int impresionesOtros) {
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
