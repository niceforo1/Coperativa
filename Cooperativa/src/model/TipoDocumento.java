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
@Table(name = "TIPO_DOCUMENTO")
public class TipoDocumento implements Serializable {

	@Id
	@Column(name = "ID_TIPO_DOC")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TPO_DOC")
	@SequenceGenerator(name = "SEQ_TPO_DOC", sequenceName = "SEQ_TPO_DOC")
	private long id;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	public TipoDocumento() {

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

}// end TipoDocumento