package com.example.clientes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.clientes.model.Clientes;
import com.example.clientes.repository.ClientesRepository;

@CrossOrigin(origins = "http://localhost:9090")
@Controller
@RequestMapping(path = "clientes")
public class PracticaController {
	
	 @Autowired
	 ClientesRepository clientesRepository;
	 
	 
	 @GetMapping(path = "taulaClient")
	    public String taulaClient(Model model){
	        model.addAttribute("insert", "insertar clientes");
	        model.addAttribute("client", clientesRepository.findAll());
	        return "mostraTaula";
	    }
	 
	 @GetMapping(path = "delete")
	    public String delete (Model model, @RequestParam long idClientes){
		 clientesRepository.deleteById(idClientes);
	        return  "redirect:/practica/taulaClient";
	    }
	 
	 @GetMapping(path = "updateClientes")
	    public String updateTutorial (Model model, @RequestParam long idClientes){
	        model.addAttribute("update", true);
	        model.addAttribute("intupdate", 0);
	        model.addAttribute("clientes", clientesRepository.findById(idClientes));
	        return "updateClientes";
	    }

	 
	 @GetMapping(path = "insertClientes")
	    public String insertClientes (Model model){
	        model.addAttribute("update", false);
	        model.addAttribute("intupdate", 1);
	        model.addAttribute("clientes", new Clientes());
	        return "updateClientes";
	    }
	 
	 @PostMapping(path = "saveClientes")
	    public String saveClientes( Model model,@ModelAttribute Clientes clientes,@RequestParam int update){

	        if (clientes.getNombres().length() < 2) {
	            if (update == 1) {
	                model.addAttribute("update", false);
	                model.addAttribute("intupdate", 1);
	            } else {
	                model.addAttribute("update", true);
	                model.addAttribute("intupdate", 0);
	            }
	            model.addAttribute("tutorial", clientes);
	            model.addAttribute("isError", true);
	            model.addAttribute("error", "el nombre no puede tener menos de 2 letras");

	            return "updateClientes";
	        }

	        clientesRepository.save(clientes);

	        return "redirect:/practica/taulaClient";
	    }
	 

}
