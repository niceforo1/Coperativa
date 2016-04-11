USE [BASE_DATOS_FAC]
GO

/****** Object:  StoredProcedure [dbo].[SP_SELECT_DATOS_CONSUMOS_IND]    Script Date: 04/06/2016 02:19:50 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


	--------------------------------------------------------
	
	-- CONSUMOS POR AÑO
	CREATE PROCEDURE [dbo].[SP_SELECT_DATOS_CONSUMOS_IND] @DESDE int, @HASTA INT, @ID int AS
	
	BEGIN
	
	SELECT SUM(ps.CONSUMO) as consumo,ps.PERIODO_ANIO,ps.PERIODO_MES
	INTO #TEMPTABLA1
	FROM
	CONEXIONES c
	INNER JOIN PERIODOS_SALDOS ps
		ON ps.ID_CONEXION=c.ID_CONEXION
	WHERE (c.ID_CONEXION=@ID and (ps.PERIODO_ANIO BETWEEN @DESDE AND @HASTA))
	GROUP BY ps.PERIODO_ANIO,ps.PERIODO_MES
	ORDER BY ps.PERIODO_MES
	
	DECLARE @des int=@DESDE
	
	DECLARE @has int=@HASTA
	
	DECLARE @MES INT
	DECLARE @ANIO INT
	DECLARE @CONSUMO INT
	declare @sql nvarchar (1000);
	DECLARE @columna varchar(10)
	

	CREATE TABLE #TEMPCONSULTA(
    		ANIO INT,
			PER1 INT,
			PER2  INT,
			PER3  INT,
			PER4  INT,
			PER5  INT,
			PER6  INT,
			PER7  INT,
			PER8  INT,
			PER9  INT,
			PER10  INT,
			PER11  INT,
			PER12  INT)
			
	WHILE @des<=@has
	begin

	INSERT INTO #TEMPCONSULTA
			VALUES (@des,0,0,0,0,0,0,0,0,0,0,0,0)

	set @des=@des+1

	end	
	
	DECLARE CURSOR1 CURSOR FOR 
		SELECT t1.PERIODO_ANIO,t1.PERIODO_MES,t1.consumo FROM #TEMPTABLA1 t1
		
		
	OPEN CURSOR1
			
		FETCH NEXT FROM CURSOR1
		INTO @ANIO,@MES,@CONSUMO
		
		WHILE @@FETCH_STATUS = 0
		BEGIN
		
		SET @columna= N'PER'+convert(varchar(1),@MES);
		
		
		set @sql= N'update #TEMPCONSULTA set [#TEMPCONSULTA].['+@columna+']='+convert(varchar(10),@CONSUMO)+' where #TEMPCONSULTA.anio='+convert(varchar(10),@ANIO);

		exec sp_executesql @sql;

		FETCH NEXT FROM CURSOR1 INTO @ANIO,@MES,@CONSUMO
		END
	
		CLOSE CURSOR1
		DEALLOCATE CURSOR1
	
	SELECT * FROM #TEMPCONSULTA
	
	DROP TABLE #TEMPCONSULTA
	DROP TABLE #TEMPTABLA1
	END

GO


