package model;

import java.io.Serializable;
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
@Table(name = "UBICACIONES_CATASTRALES")
public class UbicacionCatastral implements Serializable {

	@Id
	@Column(name = "ID_UBICACION_CATASTRAL")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_TIPO_UBICACION_CATASTRAL")
	private TipoUbicacionCatastral tipoUbicacionCatastral;

	@Column(name = "CIRCUITO")
	private long circuito;

	@Column(name = "REGION")
	private long region;

	@Column(name = "SECCIONAL")
	private long seccional;

	@Column(name = "MANZANA")
	private long manzana;

	@Column(name = "LOTE")
	private String lote;

	@Column(name = "PH")
	private String ph;

	public UbicacionCatastral() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public long getCircuito() {
		return circuito;
	}

	public void setCircuito(long circuito) {
		this.circuito = circuito;
	}

	public long getRegion() {
		return region;
	}

	public void setRegion(long region) {
		this.region = region;
	}

	public long getSeccional() {
		return seccional;
	}

	public void setSeccional(long seccional) {
		this.seccional = seccional;
	}

	public long getManzana() {
		return manzana;
	}

	public void setManzana(long manzana) {
		this.manzana = manzana;
	}

	public TipoUbicacionCatastral getTipoUbicacionCatastral() {
		return tipoUbicacionCatastral;
	}

	public void setTipoUbicacionCatastral(TipoUbicacionCatastral tipoUbicacionCatastral) {
		this.tipoUbicacionCatastral = tipoUbicacionCatastral;
	}
}
