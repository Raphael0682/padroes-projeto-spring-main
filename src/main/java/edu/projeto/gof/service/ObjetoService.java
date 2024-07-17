package edu.projeto.gof.service;

import edu.projeto.gof.model.Objeto;

public interface ObjetoService {

	Iterable<Objeto> buscarTodos();

	Objeto buscarPorId(Long id);

	void inserir(Objeto objeto);

	void atualizar(Long id, Objeto objeto);

	void deletar(Long id);

}
