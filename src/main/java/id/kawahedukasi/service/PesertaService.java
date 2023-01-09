package id.kawahedukasi.service;

import id.kawahedukasi.model.Peserta;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@ApplicationScoped
public class PesertaService {

    @Transactional
    public Response create(JsonObject request){
        Peserta peserta = new Peserta();
        peserta.name = request.getString("name");
        peserta.role = request.getString("role");
        peserta.pob = request.getString("pob");
        peserta.batch = request.getInteger("batch");
        peserta.dob = LocalDate.parse(request.getString("dob"), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        //save
        peserta.persist();

        return Response.ok().entity(new HashMap<>()).build();
    }
}
