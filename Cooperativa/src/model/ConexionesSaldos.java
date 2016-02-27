package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONEXIONES_SALDOS")
public class ConexionesSaldos implements Serializable{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_CONEXION", nullable=false)
	private Conexion conexion;
	
	@Column(name = "ULTIMO_VENC_REGIS")
	private Date ultimoVencRegistrado;
	
	@Column(name = "SALDO_TOTAL")
	private Float saldoTotal;

	public ConexionesSaldos() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

	public Date getUltimoVencRegistrado() {
		return ultimoVencRegistrado;
	}

	public void setUltimoVencRegistrado(Date ultimoVencRegistrado) {
		this.ultimoVencRegistrado = ultimoVencRegistrado;
	}

	public Float getSaldoTotal() {
		return(float)(Math.rint(saldoTotal*100)/100) ;
	}

	public void setSaldoTotal(Float saldoTotal) {
		this.saldoTotal = (float)(Math.rint(saldoTotal*100)/100);
	}

}
