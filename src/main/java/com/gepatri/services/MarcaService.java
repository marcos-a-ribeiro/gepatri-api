package com.gepatri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gepatri.dominio.Marca;
import com.gepatri.dominio.Patrimonio;
import com.gepatri.repository.MarcaRepository;
import com.gepatri.services.exception.DataIntegrityException;
import com.gepatri.services.exception.ObjectNotFoundException;

@Service
public class MarcaService {

	@Autowired
	private MarcaRepository repo;
	
	public Marca find(Integer id) {
		Optional<Marca> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Marca.class.getName()));
	}

	public List<Marca> findAll() {
		List<Marca> lista = repo.findAll();
		return lista;
	}
	
	public Page<Marca> findPage(Integer page, Integer linesPerPage, String direction, String orderBy ) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Page<Marca> findPageByNome(Integer page, Integer linesPerPage, String direction, String orderBy, String nome) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findByNomeContaining(nome, pageRequest);
	}
	
	public Marca insert(Marca patri) {
		patri.setId(null);
		patri = repo.save(patri);
		return patri;
	}
	
	public Marca update(Marca obj) {
		Marca newObj = find(obj.getId());
		newObj.setNome(obj.getNome());
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma marca que possui patrimônios");
		}
	}		
}

