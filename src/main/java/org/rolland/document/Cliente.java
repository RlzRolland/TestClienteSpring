package org.rolland.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "cliente")
public class Cliente {

    @Transient
    public static final String NOMBRE_SECUENCIA = "secuencia_cliente";

    @Id
    private Integer cliente_id;
    private String nombre_usuario;
    private String contraseña;
    private String nombre;
    private String apellidos;
    private String correo_electronico;
    private Integer edad;
    private Double estatura;
    private Double peso;
    private Double IMC;
    private Double GEB;
    private Double ETA;
    private Date fecha_creacion;
    private Date fecha_actualizacion;
}
