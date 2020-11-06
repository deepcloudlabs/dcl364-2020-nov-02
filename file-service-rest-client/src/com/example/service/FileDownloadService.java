package com.example.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Stateless
public class FileDownloadService {

	@Schedule(second = "*/5", hour = "*", minute = "*")
	public void download() {
		var filesToBeDownloaded = List.of("photo1.jpg", "photo2.jpg");
		var client = ClientBuilder.newClient();
		try {
			for (var file : filesToBeDownloaded) {
				var target = client.target("http://localhost:8080/fileservice/api/v1/files/download/{file}")
						.resolveTemplate("file", file);
				var response = target.request(MediaType.APPLICATION_OCTET_STREAM).get();
				try (InputStream in = response.readEntity(InputStream.class)) {
					var bytes = in.readAllBytes();
					var path = Paths.get("c:/tmp", file);
					var fos = new FileOutputStream(path.toFile());
					var baos = new ByteArrayOutputStream();
					baos.write(bytes);
					baos.writeTo(fos);
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
