package com.gepatri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gepatri.dominio.Marca;
import com.gepatri.dominio.Patrimonio;
import com.gepatri.dominio.TomboNumero;
import com.gepatri.dto.PatrimonioDTO;
import com.gepatri.dto.PatrimonioNewDTO;

import com.gepatri.repository.MarcaRepository;
import com.gepatri.repository.PatrimonioRepository;
import com.gepatri.repository.TomboNumberRepository;
import com.gepatri.services.exception.DataIntegrityException;
import com.gepatri.services.exception.ObjectNotFoundException;


@Service
public class PatrimonioService {

	@Autowired
	private PatrimonioRepository repo;
	
	@Autowired
	private TomboNumberRepository tomboNumberRepository;
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	public Patrimonio findById(Integer id) {
		Optional<Patrimonio> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Patrimonio.class.getName())); 		
	}

	public List<Patrimonio> findAll() {
		List<Patrimonio> lista = repo.findAll();
		return lista;
	}

	public Page<Patrimonio> findPage(Integer page, Integer linesPerPage, String direction, String orderBy ) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Page<Patrimonio> findPageByNome(Integer page, Integer linesPerPage, String direction, String orderBy, String nome) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findByNomeContaining(nome, pageRequest);
	}
	
	@Transactional
	public Patrimonio insert(Patrimonio patri) {
		patri.setId(null);
		patri = repo.save(patri);
		tomboNumberRepository.save(patri.getTombo());
		return patri;
	}
	
	public Patrimonio update(Patrimonio obj) {
		Patrimonio newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir patrimônio - erro de integridade referencial");
		}
	}	
	
	public Patrimonio fromDTO(PatrimonioDTO objDTO) {
		Patrimonio patri = new Patrimonio();
		patri.setId(objDTO.getId());
		patri.setNome(objDTO.getNome());
		patri.setDescricao(objDTO.getDescricao());
		if (objDTO.getMarcaId()!=null) {
			patri.setMarca( new Marca(objDTO.getMarcaId(), "") );
		}
		return patri;
	}

	public Patrimonio fromDTO(PatrimonioNewDTO objNewDTO) {
		Patrimonio patri = new Patrimonio();
		patri.setId(null);
		patri.setNome(objNewDTO.getNome());
		patri.setDescricao(objNewDTO.getDescricao());
		if (objNewDTO.getMarcaId() != null) {
			patri.setMarca( new Marca(objNewDTO.getMarcaId(), "") );
		}
		patri.setTombo(new TomboNumero() );
		return patri;
	}
	
	private void updateData(Patrimonio newObj, Patrimonio obj) {
		newObj.setNome(obj.getNome());
		newObj.setDescricao(obj.getDescricao());
		
		if (obj.getMarca() != null && obj.getMarca().getId() != null ) {
			Optional<Marca> marcaOp = marcaRepository.findById(obj.getMarca().getId());
			
			Marca marca =  marcaOp.orElseThrow(() -> new ObjectNotFoundException(
					 "Objeto não encontrado! Id: " + obj.getMarca().getId() + ", Tipo: " + Marca.class.getName()));	
			newObj.setMarca( marca );
		}
	}

}

