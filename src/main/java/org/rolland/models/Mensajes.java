package org.rolland.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rolland.document.Cliente;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mensajes {

    private Cliente cliente;
    private String mensaje;
    private Integer codigo;

    public Mensajes(String mensaje, Integer codigo) {
        this.mensaje = mensaje;
        this.codigo = codigo;
    }

}
