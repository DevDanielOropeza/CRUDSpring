package com.example.crud.controllers;

import com.example.crud.dto.ResponseDTO;
import com.example.crud.models.ClientModel;
import com.example.crud.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Configuration
@RestController
@RequestMapping("service/v1/client")
public class ClientController {
    @Autowired
    IClientService service;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllClients (){
        return service.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO> getClientById (@PathVariable Integer id){
        return  service.getById(id);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addClient (@RequestBody ClientModel client) {
        return service.add(client);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDTO> updateClient (@PathVariable Integer id, @RequestBody ClientModel model){
        //Validar que sea integer
        return  service.update(id, model);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDTO> deleteClient (@PathVariable Integer id){
        return service.remove(id);
    }

}
