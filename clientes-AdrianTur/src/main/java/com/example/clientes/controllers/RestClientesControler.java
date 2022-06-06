package com.example.clientes.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.clientes.model.Clientes;
import com.example.clientes.repository.ClientesRepository;




@RestController
@RequestMapping("/api")
public class RestClientesControler {
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	@PostMapping("/client")
	public ResponseEntity<Clientes> createclientes(@RequestBody Clientes clientes) {

		try {
			Clientes clientesSave = clientesRepository.save(clientes);
			return new ResponseEntity<>(clientesSave, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/client")
	public ResponseEntity<List<Clientes>> getclient() {

		try {
			List<Clientes> clientesSave = clientesRepository.findAll();
			return new ResponseEntity<>(clientesSave, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/client/{id}")
	public ResponseEntity<Clientes> getclientes(@PathVariable("id") long id) {

		Optional<Clientes> clientesData = clientesRepository.findById(id);

		if (clientesData.isPresent()) {
			return new ResponseEntity<>(clientesData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/client")
	public ResponseEntity<Clientes>  putclientes(@RequestBody Clientes clientes) {
		Optional<Clientes> clientesData = clientesRepository.findById(clientes.getId());

		if (clientesData.isPresent()) {
			Clientes clientesupdate = clientesData.get();
			clientesupdate.setNombres(clientes.getNombres());
			clientesupdate.setApellidos(clientes.getApellidos());
			clientesupdate.setEmail(clientes.getEmail());
			Clientes clientesSave = clientesRepository.save(clientesupdate);
			return new ResponseEntity<>(clientesSave, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/client/{id}")
	public ResponseEntity<Clientes>  deletclientes(@PathVariable("id") long id) {
		try {
			clientesRepository.deleteById(id);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
}
