package com.campusland.respository;

import java.util.List;

import com.campusland.respository.models.Cliente;

public interface CrudRepositoryCliente {

    List<Cliente> listar();

    Cliente porDocumento(String documento);

    void crear(Cliente cliente);

    void editar(Cliente cliente);

    void eliminar(String id);

}
