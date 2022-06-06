package com.example.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.clientes.model.Clientes;

public abstract interface ClientesRepository  extends JpaRepository<Clientes, Long>{

}
