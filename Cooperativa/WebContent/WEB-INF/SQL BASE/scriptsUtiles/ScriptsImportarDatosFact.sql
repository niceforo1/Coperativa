/****************************** ARMADO PARA PERIODOS SALDOS *********************************************************/
select f.ID_CONEXION, f.PERIODO_FACT_ID, pf.MES, pf.ANIO, f.FECHA_VENCIMIENTO, f.IMPORTE_TOTAL, 0
 from FACTURA f, PERIODO_FACTURACION pf
where f.PERIODO_FACT_ID = pf.PERIODO_FACT_ID



/****************************** ARMADO PARA CONEXIONES SALDOS *********************************************************/
select f.ID_CONEXION, max(f.FECHA_VENCIMIENTO), sum(f.IMPORTE_TOTAL) total
 from FACTURA f, PERIODO_FACTURACION pf
where f.PERIODO_FACT_ID = pf.PERIODO_FACT_ID
group by f.ID_CONEXION