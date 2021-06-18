package org.rolland.service;

import org.rolland.document.Cliente;
import org.rolland.models.Mensajes;
import org.rolland.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    ClienteRepository repository;
    MongoTemplate mongoTemplate;
    GeneradorSecuenciaService service;

    @Autowired
    ClienteServiceImpl(ClienteRepository repository, MongoTemplate mongoTemplate, GeneradorSecuenciaService service) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
        this.service = service;
    }

    @Override
    public Mensajes agregarCliente(Cliente cliente) {

        Cliente clienteDB = new Cliente();
        Mensajes mensaje;

        clienteDB.setCliente_id(service.generarSecuencia(Cliente.NOMBRE_SECUENCIA));
        retornarObjeto(clienteDB, cliente);

        if (realizarConsulta("correo_electronico", clienteDB.getCorreo_electronico()) &&
                realizarConsulta("usuario", clienteDB.getNombre_usuario())) {

            repository.insert(clienteDB);

            mensaje = new Mensajes(clienteDB, "Cliente Insertado Correctamente", 0);

            return mensaje;
        }
        else {

            mensaje = new Mensajes("Hubo un error al insertar el cliente: Verifique que el correo o el usuario " +
                    "no este agregado", 1);

            return mensaje;
        }
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return repository.findAll();
    }

    private void retornarObjeto(Cliente c1, Cliente c2) {
        c1.setNombre(c2.getNombre());
        c1.setApellidos(c2.getApellidos());
        c1.setCorreo_electronico(c2.getCorreo_electronico());
        c1.setContraseña(c2.getContraseña());
        c1.setNombre_usuario(c2.getNombre_usuario());
        c1.setEdad(c2.getEdad());
        c1.setEstatura(c2.getEstatura());
        c1.setPeso(c2.getPeso());
        c1.setIMC(c2.getIMC());
        c1.setGEB(c2.getGEB());
        c1.setETA(c2.getETA());
        c1.setFecha_creacion(c2.getFecha_creacion());
        c1.setFecha_actualizacion(c2.getFecha_actualizacion());
    }

    @Override
    public Mensajes obtenerPorId(Integer id) {
        Cliente cliente = new Cliente();
        Mensajes mensaje = new Mensajes();

        if (repository.findById(id).isPresent()) {
            repository.findById(id).ifPresent(c -> {
                retornarObjeto(cliente, c);

                mensaje.setCliente(cliente);
                mensaje.setMensaje("Cliente obtenido correctamente");
                mensaje.setCodigo(0);
            });
        }
        else {
            mensaje.setMensaje("Hubo un error al obtener el id: " + id + " del cliente verifique que exista");
            mensaje.setCodigo(2);
        }

        return mensaje;
    }

    @Override
    public Mensajes borrarUno(Integer id) {
        Mensajes mensaje = new Mensajes();
        if (repository.findById(id).isPresent()) {
            repository.findById(id).ifPresent(c -> {
                repository.deleteById(id);

                mensaje.setMensaje("Registro con el id: " + id + " eliminado correctamente");
                mensaje.setCodigo(0);
            });
        }
        else {
            mensaje.setMensaje("Hubo un error al eliminar el registro con id: " + id + " verifique que exista");
            mensaje.setCodigo(4);
        }

        return mensaje;
    }

    @Override
    public Mensajes actualizar(Integer id, Cliente cliente) {
        Mensajes mensaje = new Mensajes();

        if (repository.findById(id).isPresent()) {
            repository.findById(id).ifPresent(c -> {
                retornarObjeto(c, cliente);
                repository.save(c);

                mensaje.setCliente(c);
                mensaje.setMensaje("Datos actualizados correctamente");
                mensaje.setCodigo(0);
            });
        }
        else {
            mensaje.setMensaje("Hubo un error al actualizar el cliente con id: " + id + ", verique que exista");
            mensaje.setCodigo(3);
        }

        return mensaje;
    }

    private boolean realizarConsulta(String campo, String valor) {
        Query consulta = new Query();
        consulta.addCriteria(Criteria.where(campo).is(valor));

        List<Cliente> clientes = mongoTemplate.find(consulta, Cliente.class);

        if (clientes.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }
}
