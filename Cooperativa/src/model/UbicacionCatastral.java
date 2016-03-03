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

	@Column(name = "CIRCUITO", nullable=true)
	private Long circuito;

	@Column(name = "REGION", nullable=true)
	private Long region;

	@Column(name = "SECCIONAL", nullable=true)
	private Long seccional;

	@Column(name = "MANZANA", nullable=true)
	private Long manzana;

	@Column(name = "LOTE", nullable=true)
	private String lote;

	@Column(name = "PH", nullable=true)
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

	public Long getCircuito() {
		return circuito;
	}

	public void setCircuito(Long circuito) {
		this.circuito = circuito;
	}

	public Long getRegion() {
		return region;
	}

	public void setRegion(Long region) {
		this.region = region;
	}

	public Long getSeccional() {
		return seccional;
	}

	public void setSeccional(Long seccional) {
		this.seccional = seccional;
	}

	public Long getManzana() {
		return manzana;
	}

	public void setManzana(Long manzana) {
		this.manzana = manzana;
	}

	public TipoUbicacionCatastral getTipoUbicacionCatastral() {
		return tipoUbicacionCatastral;
	}

	public void setTipoUbicacionCatastral(TipoUbicacionCatastral tipoUbicacionCatastral) {
		this.tipoUbicacionCatastral = tipoUbicacionCatastral;
	}
}
