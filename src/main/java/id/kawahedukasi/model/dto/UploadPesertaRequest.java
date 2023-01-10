package id.kawahedukasi.model.dto;

import javax.ws.rs.FormParam;

public class UploadPesertaRequest {

    @FormParam("file")
    public byte[] file;
}
