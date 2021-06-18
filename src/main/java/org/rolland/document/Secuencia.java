package org.rolland.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "secuencia")
@Getter
@Setter
@NoArgsConstructor
public class Secuencia {

    @Id
    private String id;

    private int seq;
}
