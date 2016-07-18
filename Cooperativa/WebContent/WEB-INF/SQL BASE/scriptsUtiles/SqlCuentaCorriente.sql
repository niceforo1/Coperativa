USE [CopNew]
GO

/****** Object:  View [dbo].[cuenta_corriente]    Script Date: 06/14/2016 19:53:58 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


alter view [dbo].[cuenta_corriente]
as 
select * 
from (select ID_CONEXION, (IMPORTE_TOTAL*-1) IMPORTE_TOTAL, pf.MES,pf.ANIO, 'FACTURA' TIPO, f.NRO_FACTURA NRO_COMPROBANTE
  from FACTURA f, PERIODO_FACTURACION pf
 where 1 = 1
   and f.PERIODO_FACT_ID = pf.PERIODO_FACT_ID
union
select r.ID_CONEXION, ri.IMPORTE,ri.PERIODO_MES, ri.PERIODO_ANIO, 'RECIBO' TIPO, r.ID_RECIBO NRO_COMPROBANTE
  from RECIBOS r, RECIBOS_ITEMS ri, RECIBOS_RECIBOS_ITEMS rri
 where 1 =1
   and r.ID_RECIBO = rri.RECIBOS_ID_RECIBO
   and rri.lstReciboItems_ID_RECIBO_ITEM = ri.ID_RECIBO_ITEM
union
select nd.ID_CONEXION, (nd.IMPORTE*-1), nd.ID_PERIODO_MES, nd.ID_PERIODO_ANIO, 'NOTA DEBITO' TIPO, nd.NRO_NOTA NRO_COMPROBANTE
   from NOTAS_DE_DEBITO nd
union 
select nc.ID_CONEXION, nc.IMPORTE, nc.ID_PERIODO_MES, nc.ID_PERIODO_ANIO, 'NOTA CREDITO' TIPO, nc.NRO_NOTA NRO_COMPROBANTE
from NOTAS_DE_CREDITO nc) cc

  

GO


