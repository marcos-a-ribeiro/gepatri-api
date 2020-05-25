package com.gepatri.services.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.gepatri.dominio.Marca;
import com.gepatri.dto.MarcaDTO;
import com.gepatri.repository.MarcaRepository;

public class MarcaValidator implements ConstraintValidator<MarcaValid, MarcaDTO> {
	
	@Autowired
	private MarcaRepository repo;
	
	@Override
	public void initialize(MarcaValid ann) {
	}

	@Override
	public boolean isValid(MarcaDTO marca, ConstraintValidatorContext context) {
		
		System.out.println(marca == null?"Objeto marca = NULL":"Objeto marca ok");
		System.out.println(marca.getNome() == null?"marca.getNome = NULL":"marca.getNome ok");

		System.out.println(repo == null?"repo = NULL":"repo ok");
			
		// Verifica se já existe marca com o respectivo nome
		Marca marcaJaExistente = repo.findByNome(marca.getNome());
		
		// Caso já exista outra marca com o mesmo nome 
		if (marcaJaExistente != null && (marcaJaExistente.getId() != marca.getId()) ) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Já existe marca cadastrada com este nome!")
				.addPropertyNode("Nome").addConstraintViolation();
			return false;
		}
		return true;
	}

}
