package com.example.crud.services;

import com.example.crud.dto.ResponseDTO;
import com.example.crud.models.ClientModel;
import org.springframework.http.ResponseEntity;

public interface IClientService {
    //Get clients
    ResponseEntity<ResponseDTO> getAll();

    //Add client
    ResponseEntity<ResponseDTO> add(ClientModel client);

    //Get by id
    ResponseEntity<ResponseDTO> getById(Integer id);

    //Update client
    ResponseEntity<ResponseDTO> update(Integer id, ClientModel client);

    //Remove client
    ResponseEntity<ResponseDTO> remove(Integer id);
}
