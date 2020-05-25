package com.gepatri;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gepatri.dominio.Marca;
import com.gepatri.dominio.Patrimonio;
import com.gepatri.dominio.TomboNumero;
import com.gepatri.repository.MarcaRepository;
import com.gepatri.repository.PatrimonioRepository;
import com.gepatri.repository.TomboNumberRepository;

@SpringBootApplication
public class GepatriApiApplication implements CommandLineRunner {

	@Autowired
	MarcaRepository marcaRepository;
	
	@Autowired
	PatrimonioRepository patrimonioRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(GepatriApiApplication.class, args);
	}

	// INSERE ALGUNS REGISTROS PARA TESTES

	@Override
	public void run(String... args) throws Exception {

		Marca m1 = new Marca(null, "Sony");
		Marca m2 = new Marca(null, "Eletrolux");
		Marca m3 = new Marca(null, "Brastemp");
		Marca m4 = new Marca(null, "LG Eletronics");
		
		marcaRepository.saveAll(Arrays.asList(m1, m2, m3, m4));
		
		Patrimonio p1 = new Patrimonio(null, "TV", "47 4k HD", m1);
		Patrimonio p2 = new Patrimonio(null, "TV", "55 4k", m1);
		
		patrimonioRepository.saveAll(Arrays.asList(p1, p2));
	}
}
