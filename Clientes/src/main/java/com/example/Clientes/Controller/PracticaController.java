package com.example.Clientes.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Clientes.Repository.ClientesRepository;
import com.example.Clientes.model.Clientes;

@CrossOrigin(origins = "http://localhost:8889")
@RestController
@RequestMapping(path = "/clientes")

public class PracticaController {

	@Autowired
	ClientesRepository clientesRepository;

	@GetMapping(path = "taulaClientes")
	public String taulaClientes(Model model) {
		model.addAttribute("insert", "insertar clientes");
		model.addAttribute("clientes", clientesRepository.findAll());
		return "mostraTaula";
	}

	@GetMapping(path = "insertCliente")
	public String insertCliente(Model model) {
		model.addAttribute("update", false);
		model.addAttribute("intupdate", 1);
		model.addAttribute("cliente", new Clientes());
		return "updateClientes";
	}

	@GetMapping(path = "delete")
	public String delete(Model model, @RequestParam long idClientes) {
		clientesRepository.deleteById(idClientes);
		return "redirect:/Clientes/taulaClientes";
	}

	@GetMapping(path = "updateClientes")
	public String updateTutorial(Model model, @RequestParam long idClientes) {
		model.addAttribute("update", true);
		model.addAttribute("intupdate", 0);
		model.addAttribute("tutorial", clientesRepository.findById(idClientes));
		return "updateClientes";

	}

	@PostMapping(path = "saveClientes")
	public String saveTutorial(Model model, @ModelAttribute Clientes cliente, @RequestParam int update) {

		if (cliente.getEmail().length() < 5) {
			if (update == 1) {
				model.addAttribute("update", false);
				model.addAttribute("intupdate", 1);
			} else {
				model.addAttribute("update", true);
				model.addAttribute("intupdate", 0);
			}
			model.addAttribute("Cliente", cliente);
			model.addAttribute("isError", true);
			model.addAttribute("error", "El email tiene que ser mayor que 5");

			return "updateClientes";
		}

		clientesRepository.save(cliente);

		return "redirect:/Clientes/taulaClientes";
	}

}
