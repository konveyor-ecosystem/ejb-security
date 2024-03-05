/*
 * JBoss, Home of Professional Open Source
 * Copyright 2017, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.ejb_security;

import java.util.Base64;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.logging.Log;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.ws.rs.WebApplicationException;

/**
 * The remote client responsible for making invoking the intermediate bean to demonstrate security context propagation
 * in EJB to remote EJB calls.
 *
 * @author <a href="mailto:sguilhen@redhat.com">Stefan Guilhen</a>
 */
@QuarkusMain
public class RemoteClient implements QuarkusApplication {

    @RestClient
    SecuredServiceClient securedServiceClient;


    @Override
    public int run(String... args) throws Exception {
        Log.info("\n\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n");
        Log.info("Successfully called secured bean, caller principal "
                + securedServiceClient.getSecurityInfo());

        try {
            boolean hasAdminPermission = false;
            hasAdminPermission = securedServiceClient.administrativeMethod();
            Log.info("\nPrincipal has admin permission: " + hasAdminPermission);
        } catch (WebApplicationException we) {
            Log.info("Unauthorized, as expected");
        }

        Log.info("\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n\n");

        return 0;
    }
}
