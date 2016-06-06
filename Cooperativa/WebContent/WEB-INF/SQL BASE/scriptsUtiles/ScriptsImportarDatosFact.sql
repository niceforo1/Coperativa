/****************************** ARMADO PARA PERIODOS SALDOS *********************************************************/
insert into PERIODOS_SALDOS (ID_CONEXION, PERIODO_MES, PERIODO_ANIO, VTO, CONSUMO, SALDO)
select f.ID_CONEXION, pf.MES, pf.ANIO, f.FECHA_VENCIMIENTO, 0, (sum(f.IMPORTE_TOTAL)*-1)
 from FACTURA f, PERIODO_FACTURACION pf
where f.PERIODO_FACT_ID = pf.PERIODO_FACT_ID
group by f.ID_CONEXION, f.PERIODO_FACT_ID, pf.MES, pf.ANIO, f.FECHA_VENCIMIENTO



/****************************** ARMADO PARA CONEXIONES SALDOS *********************************************************/
insert into CONEXIONES_SALDOS(ID_CONEXION,ULTIMO_VENC_REGIS,SALDO_TOTAL)
select f.ID_CONEXION, max(f.FECHA_VENCIMIENTO), (sum(f.IMPORTE_TOTAL)*-1) total
 from FACTURA f, PERIODO_FACTURACION pf
where f.PERIODO_FACT_ID = pf.PERIODO_FACT_ID
group by f.ID_CONEXION
