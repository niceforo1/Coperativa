package dao;

import model.generadores.GenNotaDebitoA;

public interface GeneradorNotaDebADAO {
	public Long insertarNotaDebA(GenNotaDebitoA genNotaDebitoA) throws Exception;
}
