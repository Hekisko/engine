/*
 * Lumeer: Modern Data Definition and Processing Platform
 *
 * Copyright (C) since 2017 Lumeer.io, s.r.o. and/or its affiliates.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.lumeer.remote.rest;

import io.lumeer.core.facade.*;
import io.lumeer.core.model.AiTableRequest;
import io.lumeer.core.model.AiTableResponse;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/ai/{organizationId:[0-9a-fA-F]{24}}")
public class AiService extends AbstractService {

    @Inject
    private AiFacade aiFacade;

    @PathParam("organizationId")
    private String organizationId;

    @PostConstruct
    public void init() {
        workspaceKeeper.setOrganizationId(organizationId);
    }

    @GET
    @Path("tables")
    public AiTableResponse getAiTables(AiTableRequest aiTableRequest) {
        return aiFacade.createTablesWithAi(aiTableRequest.getTablesDescription());
    }
}
