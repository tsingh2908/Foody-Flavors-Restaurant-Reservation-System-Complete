package solutions.foodyflavors.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/** REST API Configuration file for Jersey Framework **/
@ApplicationPath("/api")
public class RestAppConfig extends ResourceConfig {
	
	public RestAppConfig () {
		packages("solutions.foodyflavors.rest");
	}

}
