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
@Table(name = "PERIODO_FACTURACION")
public class PeriodoFacturacion implements Serializable {
	@Id
	@Column(name = "PERIODO_FACT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "MES", nullable = false)
	private long mes;

	@Column(name = "ANIO", nullable = false)
	private long anio;
	
	@Column(name = "CESP")
	private long cesp;
	
	@Column(name = "VTO_CESP")
	private Date fechaVtoCesp;
	
	@Column(name = "FECHA_EMISION_FACTURA")
	private Date fechaEmisionFactura;
	
	@Column(name = "VENCIMIENTO_FACT_1")
	private Date fechaPrimerVencimientoFactura;
	
	@Column(name = "VENCIMIENTO_FACT_2")
	private Date fechaSegundoVencimientoFactura;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USR_ID_CIERRE_FACT")
	private Usuario usuarioCierreFact;
}
