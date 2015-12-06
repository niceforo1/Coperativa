package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TIPO_DOMICILIO")
public class TipoDomicilio implements Serializable {

	@Id
	@Column(name = "ID_TIPO_DOMICILIO")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TPO_DOM")
	@SequenceGenerator(name="SEQ_TPO_DOM", sequenceName="SEQ_TPO_DOM")
	private long id;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	public TipoDomicilio() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}