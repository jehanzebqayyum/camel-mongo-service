package org.mycompany;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultRoute extends RouteBuilder {
	@Value("${server.address}")
	private String host;

	@Value("${http.port}")
	private Integer port;

	@Override
	public void configure() throws Exception {
		restConfiguration().component("jetty").host(host).port(port).bindingMode(RestBindingMode.auto);

		rest("/data").get("/{collection}").consumes("application/json").produces("application/json").
		toD("mongodb:mongoClient?database={{spring.data.mongodb.database}}&collection=${header.collection}&operation=findAll");

	}

}
