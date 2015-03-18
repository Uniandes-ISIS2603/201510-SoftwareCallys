/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.Callys.services;

import java.software.logic.api.IStampLogic;
import java.software.logic.dto.StampDTO;
import java.software.logic.dto.StampPageDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/stamps")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
/**
 *
 * @author estudiante
 */
public class StampService {
    
     @Inject
    protected IStampLogic stampLogicService;

    @POST
    public StampDTO createStamp(StampDTO stamp) {
        return stampLogicService.createStamp(stamp);
    }

    @DELETE
    @Path("{id}")
    public void deleteStamp(@PathParam("id") Long id) {
        stampLogicService.deleteStamp(id);
    }

    @GET
    public StampPageDTO getStamps(@QueryParam("page") Integer page, @QueryParam("maxRecords") Integer maxRecords) {
        return stampLogicService.getStamp(page, maxRecords);
    }

    @GET
    @Path("{id}")
    public StamptDTO getStamp(@PathParam("id") Long id) {
        return stampLogicService.getStamp(id);
    }

    @PUT
    public void updateStamp(@PathParam("id") Long id, StampDTO stamp) {
        stampLogicService.updateStamp(stamp);
    }

}
