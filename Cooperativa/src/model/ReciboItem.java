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
public class ReciboItem implements Serializable {

	@Id
	@Column(name = "ID_RECIBO_ITEM")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "TIPO_COMPROBANTE")
	private TipoComprobante tipoComprobante; /// A O B

	@Column(name = "NRO_COMPROBANTE")
	private long nroComprobante; // NUMERO DE FACTURA, NOTA DEBITO O NOTA
									// CREDITO

	@Column(name = "COMPROBANTE_ORIGEN")
	private String comprobanteOrigen; // FACTURA, ND, NC

	@Column(name = "PERIODO_MES")
	private long mes;

	@Column(name = "PERIODO_ANIO")
	private long anio;

	@Column(name = "TIPO_PAGO")
	private String tipoPago;

	@Column(name = "IMPORTE", precision = 15, scale = 2)
	private Double importe; // importe pago SI ES NOTA DEBITO IMPORTE = NOTA
							// DEBITO
							// importe pago = FACTURA si TIPO PAGO TOTAL

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

	public long getNroComprobante() {
		return nroComprobante;
	}

	public void setNroComprobante(long nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	public String getComprobanteOrigen() {
		return comprobanteOrigen;
	}

	public void setComprobanteOrigen(String comprobanteOrigen) {
		this.comprobanteOrigen = comprobanteOrigen;
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

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Double getImporte() {
		return (Math.rint(importe * 100) / 100);
	}

	public void setImporte(Double importe) {
		this.importe = (Math.rint(importe * 100) / 100);
	}

}
