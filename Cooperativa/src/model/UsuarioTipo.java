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
@Table(name = "USUARIO_TIPOS")
public class UsuarioTipo implements Serializable {
	@Id
	@Column(name = "TPO_USR_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "TPO_USR_CODIGO", unique = true, nullable = false)
	private String codigo;

	@Column(name = "TPO_USR_DESC")
	private String descripcion;

	public UsuarioTipo() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
