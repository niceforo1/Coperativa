package dao;

import java.util.List;

import model.PeriodoCesp;

public interface PeriodoCespDAO {
	public List<PeriodoCesp> listaPeriodoCesp() throws Exception;
	public void insertarPeriodoCesp(PeriodoCesp periodoCesp) throws Exception;
	public void modificarPeriodoCesp(PeriodoCesp periodoCesp)throws Exception;
	public void eliminarPeriodoCesp(PeriodoCesp periodoCesp) throws Exception;
	public PeriodoCesp buscarPeriodoCespId(Long id) throws Exception;
	public PeriodoCesp buscarUltimoPeriodoCesp() throws Exception;
}
