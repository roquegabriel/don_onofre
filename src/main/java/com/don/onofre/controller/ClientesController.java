/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.don.onofre.controller;

import com.don.onofre.model.Clientes;
import com.don.onofre.repository.ClientesRepository;
import com.don.onofre.repository.DeudasRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author roque
 */
@RestController
@RequestMapping("/api/v1/")
public class ClientesController {
    
    @Autowired
    private DeudasRepository debtRepo;

    @Autowired
    private ClientesRepository clientesRepository;

    @PostMapping("/cliente")
    public Clientes create(@RequestBody Clientes cliente) {
        return clientesRepository.save(cliente);
    }

    @GetMapping("/clientes")
    public List<Clientes> allClients() {
        return clientesRepository.findAll();
    }
}
