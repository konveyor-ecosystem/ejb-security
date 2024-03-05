/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
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

import java.security.Principal;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Simple secured EJB using EJB security annotations
 *
 * @author Sherif Makary
 *
 */
/**
 *
 * Annotate this EJB for authorization. Allow only those in the "guest" role. For EJB authorization, you must also specify the
 * security domain. This example uses the "other" security domain which is provided by default in the standalone.xml file.
 *
 */
@Path("/")
@RolesAllowed({ "guest" })
public class SecuredService {

    @Inject
    SecurityIdentity identity;

    /**
     * Secured EJB method using security annotations
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/securityInfo")
    public String getSecurityInfo() {
        Principal principal = identity.getPrincipal();
        return principal.getName();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/admin")
    @RolesAllowed("admin")
    public boolean administrativeMethod() {
        return true;
    }
}
