package org.jboss.as.quickstarts.ejb_security;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;

@Path("/")
@RegisterRestClient(configKey="secured-service-api")
@RegisterClientHeaders(BasicAuthHeadersFactory.class)
public interface SecuredServiceClient {

    @GET
    @Path("/securityInfo")
    String getSecurityInfo();

    @GET
    @Path("/admin")
    Boolean administrativeMethod();
}
