package id.kawahedukasi.controller;

import io.vertx.core.json.JsonObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Map<String, Object> create(JsonObject request){
//        String name = (String) request.get("name");
//        String gender = (String) request.get("gender");

        return Map.of(
                "data", request
        );
    }

    @GET
    public Map<String, Object> getByNameGenderPob(
            @QueryParam("name") String name,
            @QueryParam("gender") String gender,
            @QueryParam("pob") String pob
    ){
        return Map.of(
                "id" , -99,
                "nama", name,
                "gender", gender,
                "pob", pob
        );
    }

    @GET
    @Path("/{id}")
    public Map<String, Object> get(@PathParam("id") Integer id){
        if(id == 1){
            return Map.of(
                    "id" , "1",
                    "nama", "ananda",
                    "gender", "male"
            );
        } else if (id == 2) {
            return Map.of(
                    "id" , "2",
                    "nama", "ninis",
                    "gender", "female"
            );
        } else{
            return Map.of(
                    "message", "unknown_id"
            );
        }
    }

    @GET
    @Path("/name/{name}")
    public Map<String, Object> getByName(@PathParam("name") String name){
        return Map.of(
                "id" , -99,
                "nama", name,
                "gender", ""
        );
    }

    @PUT
    public Map<String, Object> update(){
        return Map.of(
                "message", "UPDATED"
        );
    }

    @DELETE
    public Map<String, Object> delete(){
        return Map.of(
                "message", "DELETED"
        );
    }
}
