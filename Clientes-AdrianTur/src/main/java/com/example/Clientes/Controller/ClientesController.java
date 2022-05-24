package com.example.Clientes.Controller;

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

import com.example.Clientes.Repository.ClientesRepository;
import com.example.Clientes.model.Clientes;

@RestController
@RequestMapping("/api")
public class ClientesController {

	@Autowired
	ClientesRepository clientesRepository;

	@PostMapping("/Clientes")
	public ResponseEntity<Clientes> createclientes(@RequestBody Clientes clientes) {
		try {
			Clientes clientesSave = clientesRepository.save(clientes);
			return new ResponseEntity<>(clientesSave, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Clientes")
	public ResponseEntity<List<Clientes>> getclientes() {

		try {
			List<Clientes> clientesSave = clientesRepository.findAll();
			return new ResponseEntity<>(clientesSave, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Clientes/{id}")
	public ResponseEntity<Clientes> getclientes(@PathVariable("id") long id) {

		Optional<Clientes> clientesData = clientesRepository.findById(id);

		if (clientesData.isPresent()) {
			return new ResponseEntity<>(clientesData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping("/Clientes")
	public ResponseEntity<Clientes> updatecliente(@PathVariable("id") long id, @RequestBody Clientes clientes) {
		Optional<Clientes> clientesData = clientesRepository.findById(id);

		if (clientesData.isPresent()) {
			Clientes clientesupdate = clientesData.get();
			clientesupdate.setNombre(clientes.getNombre());
			clientesupdate.setApellidos(clientes.getApellidos());
			clientesupdate.setEmail(clientes.getEmail());
			Clientes clientesSave = clientesRepository.save(clientesupdate);
			return new ResponseEntity<>(clientesRepository.save(clientesupdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/Clientes/{id}")
	public ResponseEntity<HttpStatus> deleteclientes(@PathVariable("id") long id) {
		try {
			clientesRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
