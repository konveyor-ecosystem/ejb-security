package org.jboss.as.quickstarts.ejb_security;

import java.util.Base64;

import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;

@ApplicationScoped
public class BasicAuthHeadersFactory implements ClientHeadersFactory {

    // Hard-coding credentials for demo; don't try this at home!
    private final String USERNAME = "quickstartUser";
    private final String PASSWORD = "quickstartPwd1!";

    @Override
    public MultivaluedMap<String, String> update(
            MultivaluedMap<String, String> incomingHeaders,
            MultivaluedMap<String, String> clientOutgoingHeaders) {

        MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
        result.add("Authorization", getAuthString());

        return result;
    }

    private String getAuthString() {
        String encodedCredentials = Base64.getEncoder()
                .encodeToString((USERNAME + ":" + PASSWORD).getBytes());

        return "Basic " + encodedCredentials;
    }
}
