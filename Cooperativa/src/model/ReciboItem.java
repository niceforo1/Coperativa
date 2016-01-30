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
import javax.persistence.Table;

@Entity
@Table(name = "RECIBOS_ITEMS")
public class ReciboItem implements Serializable{

	@Id
	@Column(name = "ID_RECIBO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_TIPO_COMPROBANTE")
	private TipoComprobante tipoComprobante;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_RECIBO")
	private Recibo recibo;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_NOTA_DE_DEBITO")
	private NotaDebito notaDebito;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "NOTAS_DE_CREDITO")
	private NotaCredito notaCredito;
	
	@Column(name = "ID_PERIODO_MES")
	private long mes;

	@Column(name = "ID_PERIODO_ANIO")
	private long anio;
	
	
	public ReciboItem() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public TipoComprobante getTipoComprobante() {
		return tipoComprobante;
	}


	public void setTipoComprobante(TipoComprobante tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}


	public Recibo getRecibo() {
		return recibo;
	}


	public void setRecibo(Recibo recibo) {
		this.recibo = recibo;
	}


	public NotaDebito getNotaDebito() {
		return notaDebito;
	}


	public void setNotaDebito(NotaDebito notaDebito) {
		this.notaDebito = notaDebito;
	}


	public NotaCredito getNotaCredito() {
		return notaCredito;
	}


	public void setNotaCredito(NotaCredito notaCredito) {
		this.notaCredito = notaCredito;
	}


	public long getMes() {
		return mes;
	}


	public void setMes(long mes) {
		this.mes = mes;
	}


	public long getAnio() {
		return anio;
	}


	public void setAnio(long anio) {
		this.anio = anio;
	}
	
}
