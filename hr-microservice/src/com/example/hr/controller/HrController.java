package com.example.hr.controller;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.example.hr.application.HrApplication;

@Path("/employees")
public class HrController {

	@Inject private HrApplication hrApplication;
}
