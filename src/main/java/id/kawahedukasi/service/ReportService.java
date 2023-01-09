package id.kawahedukasi.service;

import id.kawahedukasi.model.Peserta;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ReportService {

    public Response exportJasper() throws JRException {
        File file = new File("src/main/resources/peserta.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(getAllPeserta());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter(), dataSource);
        byte[] jasperResult = JasperExportManager.exportReportToPdf(jasperPrint);
        return Response.ok().type("application/pdf").entity(jasperResult).build();
    }

    public List<Peserta> getAllPeserta(){
        List<Peserta> list = Peserta.listAll();
        return list;
    }

    public Map<String, Object> jasperParameter(){
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("createBy", "ananda");
        return parameter;
    }
}
