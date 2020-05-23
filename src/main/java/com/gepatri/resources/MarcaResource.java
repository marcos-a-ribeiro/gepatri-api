package com.gepatri.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gepatri.dominio.Marca;
import com.gepatri.dominio.Patrimonio;
import com.gepatri.dto.MarcaDTO;
import com.gepatri.services.MarcaService;

@RestController
@RequestMapping(value = "/marcas")
public class MarcaResource {
	
	@Autowired
	private MarcaService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Marca>> findAll() {
		List<Marca> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Marca> find(@PathVariable Integer id) {
		Marca marca = service.find(id);
		return ResponseEntity.ok().body(marca);
	}

	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Marca>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="12") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="nome", defaultValue="*") String nome) {
		
		Page<Marca> lista;
		
		/*
		 * RECUPERA TODOS OS ITENS DA TABELA OU SOMENTE OS ITENS QUE CONTEM O PARAMETRO "nome"
		 */
		if (nome.equals("*") || nome.isBlank()) {
			lista = service.findPage(page, linesPerPage, direction, orderBy);
			
		} else {
			lista = service.findPageByNome(page, linesPerPage, direction, orderBy, nome);
			
		}
		
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value = "/{id}/patrimonios", method = RequestMethod.GET)
	public ResponseEntity<List<Patrimonio>> getPatrimonios(@PathVariable Integer id) {
		Marca marca = service.find(id);
		return ResponseEntity.ok().body(marca.getPatrimonios());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Marca> insert(@RequestBody Marca marca) {
		marca = service.insert(marca);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(marca.getId()).toUri();
		return ResponseEntity.created(uri).body(marca);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Marca marca, @PathVariable Integer id) {
		marca.setId(id);
		marca = service.update(marca);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
