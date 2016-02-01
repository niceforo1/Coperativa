package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERIODO_CESP")
public class PeriodoCesp implements Serializable {
	
	@Id
	@Column(name = "PERIODO_CESP_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "CESP")
	private long cesp;

	@Column(name = "VTO_CESP")
	private Date fechaVtoCesp;

	public PeriodoCesp() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
