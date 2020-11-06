package com.example.fileservice.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@Path("/files")
@ApplicationScoped
public class FileResource {

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(MultipartFormDataInput input) throws IOException {

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();

		var parts = uploadForm.get("attachment");

		for (var part : parts) {
			try {

				MultivaluedMap<String, String> header = part.getHeaders();
				var fileName = getFileName(header);

				var inputStream = part.getBody(InputStream.class, null);

				var bytes = inputStream.readAllBytes();

				var uploadPath = Paths.get(System.getProperty("user.home"), "uploads");

				if (!Files.exists(uploadPath)) {
					Files.createDirectory(uploadPath);
				}
				writeFile(bytes, fileName);
				Files.write(Paths.get(System.getProperty("user.home"), "uploads", fileName), bytes);
			} catch (Exception e) {
				return Response.status(500).entity("File upload error: ".concat(e.getMessage())).build();
			}
		}
		return Response.status(200).entity("Files uploaded successfully.").build();
	}

	@GET
	@Path("/download/{file}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response download(@PathParam("file") String file) {
		var path = System.getProperty("user.home") + File.separator + "uploads";
		var fileDownload = new File(path + File.separator + file);
		var response = Response.ok((Object) fileDownload);
		response.header("Content-Disposition", "attachment;filename=" + file);
		return response.build();
	}

	private void writeFile(byte[] content, String filename) throws IOException {
		var file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}
		try (var fop = new FileOutputStream(file)) {
			fop.write(content);
		}
	}

	private String getFileName(MultivaluedMap<String, String> header) {

		var contentDisposition = header.getFirst("Content-Disposition").split(";");

		for (var filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
				var name = filename.split("=");
				var finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}
}
