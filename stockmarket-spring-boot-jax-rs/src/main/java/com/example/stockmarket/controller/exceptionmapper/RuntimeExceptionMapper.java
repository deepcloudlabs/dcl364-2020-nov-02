package com.example.stockmarket.controller.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException>{

	@Override
	public Response toResponse(RuntimeException e) {
		return Response.status(Response.Status.BAD_GATEWAY)
				   .entity(this.runtimeExceptionToJson(e))
				   .type(MediaType.APPLICATION_JSON)
				   .build();
	}

	private String runtimeExceptionToJson(RuntimeException e) {
		return "{\"message\": \""+e.getMessage() +"\"}";
	}

}
