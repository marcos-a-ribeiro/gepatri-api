package com.gepatri.resources;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gepatri.dominio.Patrimonio;
import com.gepatri.dto.PatrimonioDTO;
import com.gepatri.dto.PatrimonioNewDTO;
import com.gepatri.services.PatrimonioService;

@RestController
@RequestMapping(value = "/patrimonios")
public class PatrimonioResource {
	
	@Autowired
	private PatrimonioService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PatrimonioDTO>> findAll() {
		List<Patrimonio> lista = service.findAll();
		List<PatrimonioDTO> listaDTO = lista.stream().map(patri -> new PatrimonioDTO(patri)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Patrimonio> findById(@PathVariable Integer id) {
		Patrimonio categ = service.findById(id);
		return ResponseEntity.ok().body(categ);
	}

	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<PatrimonioDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="12") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="nome", defaultValue="*") String nome) {
		
		Page<Patrimonio> lista;

		/*
		 * RECUPERA TODOS OS ITENS DA TABELA OU SOMENTE OS ITENS QUE CONTEM O PARÂMETRO "nome"
		 */
		if (nome.equals("*") || nome.isBlank()) {
			lista = service.findPage(page, linesPerPage, direction, orderBy);
			
		} else {
			lista = service.findPageByNome(page, linesPerPage, direction, orderBy, nome);
			
		}
		Page<PatrimonioDTO> listaDTO = lista.map(patrimonio -> new PatrimonioDTO(patrimonio));
		return ResponseEntity.ok().body(listaDTO);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PatrimonioDTO> insert(@Valid @RequestBody PatrimonioNewDTO patriNewDTO) {
		Patrimonio patri = service.fromDTO(patriNewDTO);
		patri = service.insert(patri);
		PatrimonioDTO patrimonioDTO = new PatrimonioDTO(patri);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patri.getId()).toUri();
		return ResponseEntity.created(uri).body(patrimonioDTO);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PatrimonioDTO patriDTO, @PathVariable Integer id) {
		Patrimonio patrimonio = service.fromDTO(patriDTO);
		patrimonio.setId(id);
		patrimonio = service.update(patrimonio);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/upload/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Integer id) {
		Patrimonio patrimonio = service.findById(id);
		try {
			patrimonio.setImagem(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		patrimonio = service.update(patrimonio);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/patrimonios/download/{id}").buildAndExpand(patrimonio.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/download/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> downloadImage(@PathVariable Integer id) {
		Patrimonio patrimonio = service.findById(id);
		String fileName = patrimonio.getNome()+"-"+patrimonio.getId() + ".jpeg";
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body(patrimonio.getImagem());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
