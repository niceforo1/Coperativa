delete from SOCIOS_DOMICILIOS where SOCIOS_ID_SOCIO = 5145;
delete from SOCIOS where ID_SOCIO = 5145;

SET IDENTITY_INSERT SOCIOS ON;
INSERT INTO [Coperativa].[dbo].[SOCIOS]
           (ID_SOCIO
           ,[APELLIDO_RAZON_SOCIAL]
           ,[EMAIL]
           ,[FECHA_APROBACION]
           ,[ING_BRUTOS]
           ,[INSCRIPTO_GANANCIAS]
           ,[NOMBRE_CONYUGE]
           ,[NOMBRE_DENOMINACION]
           ,[TE_CELULAR]
           ,[ID_NRO_DOC]
           ,[NRO_IVA]
           ,[OBSERVACIONES]
           ,[PROFESION_RUBRO]
           ,[ID_CONDICION_IVA]
           ,[ID_ESTADO_CIVIL]
           ,[ID_ESTADO_SOCIO]
           ,[ID_PAIS]
           ,[ID_PROVINCIA]
           ,[ID_TIPO_DOC]
           ,[ID_TIPO_SOCIO]
           ,[USR_ID])
     VALUES
           (9999
           ,'COOP.A.POT.Y OSP BIALET MASSE'
           ,null
           ,'28-08-1970'
           ,null
           ,0
           ,null
           ,null
           ,'0541-40029'
           ,1192
           ,null
           ,''
           ,null
           ,2
           ,null
           ,1
           ,1
           ,null
           ,1
           ,2
           ,1);
SET IDENTITY_INSERT SOCIOS OFF;

DBCC CHECKIDENT ('SOCIOS', RESEED, 5144);

insert into SOCIOS_DOMICILIOS values (9999, 2821);


