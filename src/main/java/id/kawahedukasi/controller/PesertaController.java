package id.kawahedukasi.controller;

import id.kawahedukasi.model.Peserta;
import id.kawahedukasi.model.RekapTugas;
import id.kawahedukasi.model.dto.UploadPesertaRequest;
import id.kawahedukasi.service.PesertaService;
import id.kawahedukasi.service.ReportService;
import id.kawahedukasi.service.ExcelService;
import io.vertx.core.json.JsonObject;
import net.sf.jasperreports.engine.JRException;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Path("/peserta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PesertaController {

    @Inject
    PesertaService pesertaService;

    @Inject
    ReportService reportService;

    @Inject
    ExcelService excelService;

//    public PesertaController(){
//        pesertaService = new PesertaService();
//    }

    @GET
    @Path("/report")
    @Produces("application/pdf")
    public Response create() throws JRException {
        return reportService.exportJasper();
    }


    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@MultipartForm UploadPesertaRequest request) throws IOException {
        return excelService.upload(request);
    }

    @GET
    @Path("/download")
    @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public Response download() throws IOException {
        return excelService.download();
    }

    //rekap tugas peserta id
    @POST
    @Path("/tugas/{id}")
    @Transactional
    public Response submitNilai(@PathParam("id") Integer id, JsonObject request){
        Peserta peserta = Peserta.findById(id);
        if(peserta == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "PESERTA_NOT_FOUND"))
                    .build();
        }

        RekapTugas rekapTugas = new RekapTugas();
        rekapTugas.nilai = request.getInteger("nilai");
        rekapTugas.minggu = request.getInteger("minggu");
        rekapTugas.peserta = peserta;

        //save
        rekapTugas.persist();

        return Response.ok().entity(new HashMap<>()).build();
    }

    //get semua data yang ada di database
    @GET
    public Response list(){
        return Response.ok().entity(Peserta.listAll()).build();
    }

    //get by id
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id){
        Peserta peserta = Peserta.findById(id);
        if(peserta == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "PESERTA_NOT_FOUND"))
                    .build();
        }

        return Response.ok().entity(peserta).build();
    }

    //get by role
    @GET
    @Path("/role/{role}")
    public Response getByRole(@PathParam("role") String role){
        if(!role.equalsIgnoreCase("frontend") && !role.equalsIgnoreCase("backend")){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "INVALID_ROLE"))
                    .build();
        }
        List<Peserta> pesertaList = Peserta.find("UPPER(role) = ?1", role.toUpperCase(Locale.ROOT)).list();//"SELECT * FROM peserta WHERE role = 'backend'"
        return Response.ok().entity(pesertaList).build();
    }

    @GET
    @Path("/tugas/{id}")
    public Response getRekapTugas(@PathParam("id") Integer id){
        List<RekapTugas> rekapTugasList = RekapTugas.find("peserta_id = ?1", id).list();//"SELECT * FROM peserta WHERE role = 'backend'"

        List<Map<String, Object>> result = new ArrayList<>();
        for(RekapTugas rekapTugas : rekapTugasList){
            Map<String, Object> map = new HashMap<>();
            map.put("id", rekapTugas.id);
            map.put("nilai", rekapTugas.nilai);
            map.put("minggu", rekapTugas.minggu);

            result.add(map);
        }

        return Response.ok().entity(result).build();
    }

    //update data by id
    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, JsonObject request){
        Peserta peserta = Peserta.findById(id); //select * from peserta where id = :1
        peserta.name = request.getString("name");
        peserta.role = request.getString("role");
        peserta.pob = request.getString("pob");
        peserta.batch = request.getInteger("batch");
        peserta.dob = LocalDate.parse(request.getString("dob"), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        //save
        peserta.persist();

        return Response.ok().entity(new HashMap<>()).build();
    }

    //delete by id
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id){
        Peserta.deleteById(id);
        return Response.ok().entity(new HashMap<>()).build();
    }
}
