package com.example.crud.dao;

import com.example.crud.models.ClientModel;
import com.google.gson.Gson;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Repository
public class ClientDAO {
    private static List<ClientModel> listClients = new ArrayList<>();

    public List<ClientModel> getAll() throws Exception {
        try {
            refreshList();
            if (listClients.isEmpty()) throw new Exception("No data");
            return listClients;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public ClientModel getById(Integer id) throws Exception {
        try {
            refreshList();
            if (listClients.isEmpty() || !listClients.stream().anyMatch(
                    clientModel -> clientModel.getId().equals(id)))
                throw new Exception("Unable to find client");
            return listClients.stream().filter(c -> c.getId().equals(id)).findFirst().get();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public ClientModel add(ClientModel client) throws Exception {
        try {

            Integer idCliente = 1;
            if (!listClients.isEmpty()) {
                idCliente = listClients.stream().max(Comparator.comparing(c -> c.getId())).get().getId() + 1;
            }
            client.setId(idCliente);
            ClientDAO.listClients.add(client);
            saveJson();
            return client;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public ClientModel update(Integer id, ClientModel client) throws Exception {
        try {
            refreshList();
            removeElementFromList(id);
            client.setId(id);
            listClients.add(client);
            saveJson();
            return client;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public Boolean remove(Integer id) throws Exception {
        try {
            refreshList();
            removeElementFromList(id);
            saveJson();
            return true;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    private void saveJson() {
        try {
            Path filepath = Path.of("output.json");
            Files.write(filepath, new Gson().toJson(listClients).getBytes(StandardCharsets.UTF_8));
        } catch (IOException ex) {

        }
    }

    private void refreshList() {
        try {
            Path filepath = Path.of("output.json");
            listClients = new ArrayList(Arrays.asList(new Gson().fromJson(Files.readString(filepath), ClientModel[].class)));
        } catch (IOException ex) {

        }
    }

    private void removeElementFromList(Integer id) throws Exception {
        if (listClients.isEmpty() || !listClients.stream().anyMatch(clientModel -> clientModel.getId().equals(id)))
            throw new Exception("Client not found");

        try {
            listClients.removeIf(c -> c.getId().equals(id));
        } catch (Exception ex) {
            throw new Exception("Unable to remove client");
        }
    }
}
