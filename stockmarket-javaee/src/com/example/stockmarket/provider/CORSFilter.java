package com.example.stockmarket.provider;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext crs, ContainerResponseContext crc) throws IOException {
		crc.getHeaders().add("Access-Control-Allow-Origin", "*");
		crc.getHeaders().add("Access-Control-Allow-Headers", "x-powered-by, origin, content-type, accept, authorization");
		crc.getHeaders().add("Access-Control-Allow-Credentials", "true");
		crc.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH, OPTIONS, HEAD");		
	}

}
