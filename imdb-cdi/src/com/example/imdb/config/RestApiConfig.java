package com.example.imdb.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// Context-root: imdb
// http://localhost:5100/imdb/api/v1
@ApplicationPath("/api/v1")
public class RestApiConfig extends Application {

}
