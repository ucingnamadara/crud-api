package id.kawahedukasi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
public class GreetingResource {

    @GET
    public String hello() {
        return "Hello RESTEasy";
    }

    @GET
    @Path("/world")
    public String helloWorld(){
        return "Hello World";
    }

    @GET
    @Path("/title")
    @Produces(MediaType.TEXT_HTML)
    public String title(){
        return "<h1>KAWAH EDUKASI</h1>";
    }
}