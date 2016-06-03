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

	@Column(name = "CARGO_FIJO", precision = 15, scale = 2)
	private Double cargoFijo;

	@Column(name = "TRAMO_1", nullable = false, precision = 15, scale = 2)
	private Double tramo1;

	@Column(name = "TRAMO_2", nullable = false, precision = 15, scale = 2)
	private Double tramo2;

	@Column(name = "TRAMO_3", nullable = false, precision = 15, scale = 2)
	private Double tramo3;

	@Column(name = "TRAMO_4", nullable = false, precision = 15, scale = 2)
	private Double tramo4;

	@Column(name = "TRAMO_5", nullable = false, precision = 15, scale = 2)
	private Double tramo5;

	@Column(name = "TRAMO_6", nullable = false, precision = 15, scale = 2)
	private Double tramo6;

	@Column(name = "CAPITAL_SOCIAL", nullable = false, precision = 15, scale = 2)
	private Double capitalSocial;

	@Column(name = "ERSEP", nullable = false, precision = 15, scale = 2)
	private Double ersep;

	@Column(name = "RECUPERO_INVERSION", nullable = false, precision = 15, scale = 2)
	private Double recuperoInversion;

	@Column(name = "IMPRESION_Y_OTROS", nullable = false, precision = 15, scale = 2)
	private Double impresionesOtros;

	@Column(name = "LEYENDA_COMPROBANTE", nullable = false)
	private String leyendaComprobante;

	public ConfiguracionFactura() {

	}
	
	/*
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCargoFijo() {
		return (Math.rint(cargoFijo*100)/100);
	}

	public void setCargoFijo(Double cargoFijo) {
		this.cargoFijo = (Math.rint(cargoFijo*100)/100);
	}

	public Double getTramo1() {
		return (Math.rint(tramo1*100)/100);
	}

	public void setTramo1(Double tramo1) {
		this.tramo1 = (Math.rint(tramo1*100)/100);
	}

	public Double getTramo2() {
		return (Math.rint(tramo2*100)/100);
	}

	public void setTramo2(Double tramo2) {
		this.tramo2 = (Math.rint(tramo2*100)/100);
	}

	public Double getTramo3() {
		return (Math.rint(tramo3*100)/100);
	}

	public void setTramo3(Double tramo3) {
		this.tramo3 = (Math.rint(tramo3*100)/100);
	}

	public Double getTramo4() {
		return (Math.rint(tramo4*100)/100);
	}

	public void setTramo4(Double tramo4) {
		this.tramo4 = (Math.rint(tramo4*100)/100);
	}

	public Double getTramo5() {
		return (Math.rint(tramo5*100)/100);
	}

	public void setTramo5(Double tramo5) {
		this.tramo5 = (Math.rint(tramo5*100)/100);
	}

	public Double getTramo6() {
		return (Math.rint(tramo6*100)/100);
	}

	public void setTramo6(Double tramo6) {
		this.tramo6 = (Math.rint(tramo6*100)/100);
	}

	public Double getCapitalSocial() {
		return (Math.rint(capitalSocial*100)/100);
	}

	public void setCapitalSocial(Double capitalSocial) {
		this.capitalSocial = (Math.rint(capitalSocial*100)/100);
	}

	public Double getErsep() {
		return (Math.rint(ersep*100)/100);
	}

	public void setErsep(Double ersep) {
		this.ersep = (Math.rint(ersep*100)/100);
	}

	public Double getRecuperoInversion() {
		return (Math.rint(recuperoInversion*100)/100);
	}

	public void setRecuperoInversion(Double recuperoInversion) {
		this.recuperoInversion = (Math.rint(recuperoInversion*100)/100);
	}

	public Double getImpresionesOtros() {
		return (Math.rint(impresionesOtros*100)/100);
	}

	public void setImpresionesOtros(Double impresionesOtros) {
		this.impresionesOtros = (Math.rint(impresionesOtros*100)/100);
	}

	public String getLeyendaComprobante() {
		return leyendaComprobante;
	}

	public void setLeyendaComprobante(String leyendaComprobante) {
		this.leyendaComprobante = leyendaComprobante;
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCargoFijo() {
		return cargoFijo;
	}

	public void setCargoFijo(Double cargoFijo) {
		this.cargoFijo = cargoFijo;
	}

	public Double getTramo1() {
		return tramo1;
	}

	public void setTramo1(Double tramo1) {
		this.tramo1 = tramo1;
	}

	public Double getTramo2() {
		return tramo2;
	}

	public void setTramo2(Double tramo2) {
		this.tramo2 = tramo2;
	}

	public Double getTramo3() {
		return tramo3;
	}

	public void setTramo3(Double tramo3) {
		this.tramo3 = tramo3;
	}

	public Double getTramo4() {
		return tramo4;
	}

	public void setTramo4(Double tramo4) {
		this.tramo4 = tramo4;
	}

	public Double getTramo5() {
		return tramo5;
	}

	public void setTramo5(Double tramo5) {
		this.tramo5 = tramo5;
	}

	public Double getTramo6() {
		return tramo6;
	}

	public void setTramo6(Double tramo6) {
		this.tramo6 = tramo6;
	}

	public Double getCapitalSocial() {
		return capitalSocial;
	}

	public void setCapitalSocial(Double capitalSocial) {
		this.capitalSocial = capitalSocial;
	}

	public Double getErsep() {
		return ersep;
	}

	public void setErsep(Double ersep) {
		this.ersep = ersep;
	}

	public Double getRecuperoInversion() {
		return recuperoInversion;
	}

	public void setRecuperoInversion(Double recuperoInversion) {
		this.recuperoInversion = recuperoInversion;
	}

	public Double getImpresionesOtros() {
		return impresionesOtros;
	}

	public void setImpresionesOtros(Double impresionesOtros) {
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
