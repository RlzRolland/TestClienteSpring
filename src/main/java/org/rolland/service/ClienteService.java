package org.rolland.service;

import org.rolland.document.Cliente;
import org.rolland.models.Mensajes;

import java.util.List;

public interface ClienteService {

    Mensajes agregarCliente(Cliente cliente);
    List<Cliente> obtenerTodos();
    Mensajes obtenerPorId(Integer id);
    Mensajes borrarUno(Integer id);
    Mensajes actualizar(Integer id, Cliente cliente);
}
