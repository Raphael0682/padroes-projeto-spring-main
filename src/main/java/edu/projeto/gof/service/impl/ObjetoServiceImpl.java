package edu.projeto.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.projeto.gof.model.Endereco;
import edu.projeto.gof.model.EnderecoRepository;
import edu.projeto.gof.model.Objeto;
import edu.projeto.gof.model.ObjetoRepository;
import edu.projeto.gof.service.ObjetoService;
import edu.projeto.gof.service.ViaCepService;

@Service
public class ObjetoServiceImpl implements ObjetoService {

	@Autowired
	private ObjetoRepository objetoRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;

	@Override
	public Iterable<Objeto> buscarTodos() {
		return objetoRepository.findAll();
	}

	@Override
	public Objeto buscarPorId(Long id) {
		Optional<Objeto> objeto = objetoRepository.findById(id);
		return objeto.get();
	}

	@Override
	public void inserir(Objeto objeto) {
		salvarObjetoComCep(objeto);
	}

	@Override
	public void atualizar(Long id, Objeto objeto) {
		Optional<Objeto> objetoBd = objetoRepository.findById(id);
		if (objetoBd.isPresent()) {
			salvarObjetoComCep(objeto);
		}
	}

	@Override
	public void deletar(Long id) {
		objetoRepository.deleteById(id);
	}

	private void salvarObjetoComCep(Objeto objeto) {
		String cep = objeto.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		objeto.setEndereco(endereco);
		objetoRepository.save(objeto);
	}

}
