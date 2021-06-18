package org.rolland.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import org.rolland.document.Secuencia;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GeneradorSecuenciaService {
    private MongoOperations mongoOperations;

    @Autowired
    public GeneradorSecuenciaService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Integer generarSecuencia(String secNombre) {

        Secuencia counter = mongoOperations.findAndModify(query(where("_id").is(secNombre)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                Secuencia.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
