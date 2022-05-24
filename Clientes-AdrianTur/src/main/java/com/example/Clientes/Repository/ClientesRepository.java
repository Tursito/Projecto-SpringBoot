package com.example.Clientes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Clientes.model.Clientes;


public abstract interface ClientesRepository extends JpaRepository<Clientes, Long> {

}
