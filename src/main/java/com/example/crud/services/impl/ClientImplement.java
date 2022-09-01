package com.example.crud.services.impl;

import com.example.crud.dao.ClientDAO;
import com.example.crud.dto.ResponseDTO;
import com.example.crud.models.ClientModel;
import com.example.crud.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientImplement implements IClientService {
    @Autowired
    ClientDAO clientDAO;

    @Override
    public ResponseEntity<ResponseDTO> getAll() {
        try {
            return new ResponseEntity<>(ResponseDTO.builder().result(clientDAO.getAll()).success(true).message("Success").build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ResponseDTO.builder().success(false).message(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> getById(Integer id) {
        try {
            return new ResponseEntity<>(ResponseDTO.builder().result(clientDAO.getById(id)).success(true).message("Success").build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ResponseDTO.builder().success(false).message(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> add(ClientModel client) {
        try {
            return new ResponseEntity<>(ResponseDTO.builder().result(clientDAO.add(client)).success(true).message("Added").build(), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ResponseDTO.builder().success(false).message(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> update(Integer id, ClientModel client) {
        try {
            return new ResponseEntity<>(ResponseDTO.builder().result(clientDAO.update(id, client)).success(true).message("Updated").build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ResponseDTO.builder().success(false).message(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> remove(Integer id) {
        try {
            return new ResponseEntity<>(ResponseDTO.builder().result(clientDAO.remove(id)).success(true).message("Removed").build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ResponseDTO.builder().success(false).message(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
