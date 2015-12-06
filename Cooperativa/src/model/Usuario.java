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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import utils.UtilidadesVarias;

@Entity
@Table(name = "USUARIOS")
public class Usuario implements Serializable {
	@Id
	@Column(name = "USR_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_USUARIO")
	@SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO")
	private long id;

	@Column(name = "USR_USUARIO", nullable = false, unique = true)
	private String usuario;

	@Column(name = "USR_PASSWORD", nullable = false, length = 1000)
	private String password;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "TPO_USR_ID")
	private UsuarioTipo usuarioTipo;

	public Usuario() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = UtilidadesVarias.getStringMessageDigest(password);
	}

	public UsuarioTipo getUsuarioTipo() {
		return usuarioTipo;
	}

	public void setUsuarioTipo(UsuarioTipo usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", password="
				+ password + "]";
	}

}
