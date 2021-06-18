package org.rolland.controller;

import org.rolland.document.Cliente;
import org.rolland.models.Mensajes;
import org.rolland.service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/nutrinet")
public class ClienteController {

    ClienteServiceImpl service;

    @Autowired
    public ClienteController(ClienteServiceImpl service) {
        this.service = service;
    }

    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public ResponseEntity<Mensajes> crearCliente(@RequestBody Cliente dc) {
        Mensajes cliente = service.agregarCliente(dc);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @RequestMapping(value = "/obtenerClientes", method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        List<Cliente> clientes = service.obtenerTodos();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @RequestMapping(value = "/obtenerCliente/{id}", method = RequestMethod.GET)
    public ResponseEntity<Mensajes> obtenerCliente(@PathVariable Integer id) {
        Mensajes cliente = service.obtenerPorId(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @RequestMapping(value = "/eliminarCliente/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Mensajes> borrarCliente(@PathVariable Integer id) {
        Mensajes mensajes = service.borrarUno(id);
        return new ResponseEntity<>(mensajes, HttpStatus.OK);
    }

    @RequestMapping(value = "/actualizarCliente/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Mensajes> actualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        Mensajes valor = service.actualizar(id, cliente);
        return new ResponseEntity<>(valor, HttpStatus.OK);
    }

}
