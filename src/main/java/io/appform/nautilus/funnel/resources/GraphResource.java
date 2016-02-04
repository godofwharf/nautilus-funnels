/*
 * Copyright 2016 Santanu Sinha <santanu.sinha@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appform.nautilus.funnel.resources;

import io.appform.nautilus.funnel.graphmanagement.GraphBuilder;
import io.appform.nautilus.funnel.graphmanagement.GraphRequest;
import io.appform.nautilus.funnel.graphmanagement.PathsRequest;
import io.appform.nautilus.funnel.model.support.Context;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

/**
 * Main resource to return graphs
 */
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1/graphs")
@Slf4j
public class GraphResource {
    private final Context context;
    private final GraphBuilder graphBuilder;

    public GraphResource(Context context, GraphBuilder graphBuilder) {
        this.context = context;
        this.graphBuilder = graphBuilder;
    }

    @POST
    @Path("/{tenant}")
    public ApiResponse calculateGraph(@PathParam("tenant") final String tenant, @Valid final GraphRequest request) {
        try {
            return ApiResponse.builder()
                    .error(false)
                    .data(graphBuilder.build(tenant, context, request))
                    .build();
        } catch (Exception e) {
            log.error("Error getting attribute mappings for {}", tenant, e);
            throw new WebApplicationException(
                    Response.status(500)
                            .entity(ApiResponse
                                    .builder()
                                    .error(true)
                                    .data(Collections.singletonMap("message", "Could not get attribute mappings"))
                                    .build())
                            .build()
            );
        }
    }

    @POST
    @Path("/paths/{tenant}")
    public ApiResponse calculatePaths(@PathParam("tenant") final String tenant, @Valid final PathsRequest request) {
        try {
            return ApiResponse.builder()
                    .error(false)
                    .data(graphBuilder.build(tenant, context, request))
                    .build();
        } catch (Exception e) {
            log.error("Error getting attribute mappings for {}", tenant, e);
            throw new WebApplicationException(
                    Response.status(500)
                            .entity(ApiResponse
                                    .builder()
                                    .error(true)
                                    .data(Collections.singletonMap("message", "Could not get attribute mappings"))
                                    .build())
                            .build()
            );
        }
    }
}
