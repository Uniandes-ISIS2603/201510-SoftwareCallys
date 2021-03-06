package co.edu.uniandes.Callys.services;

import co.edu.uniandes.Callys.estampa.logic.api.IStampLogic;
import co.edu.uniandes.Callys.estampa.logic.dto.StampDTO;
import co.edu.uniandes.Callys.estampa.logic.dto.StampPageDTO;
import java.util.List;
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

@Path("/stamp")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
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
          return  stampLogicService.getStamps(page, maxRecords);
    }

    @GET
    @Path("{id}")
    public StampDTO getStamp(@PathParam("id") Long id) {
        return stampLogicService.getStamp(id);
    }
    
    @GET
    @Path("byArtist")
    public List<StampDTO> getStampsByArtist(@QueryParam("idArtist") Long artistId) {
        return stampLogicService.getStampsByArtist(artistId);
    }
    
    @GET
    @Path("byPrice")
    public List<StampDTO> getStampsByPrice(@QueryParam("minPrice") Integer minPrice,@QueryParam("maxPrice") Integer maxPrice) {
        return stampLogicService.getStampsByPrice(minPrice,maxPrice);
    }
    
    @GET
    @Path("byTopic")
    public List<StampDTO> getStampsByTopic(@QueryParam("topic") String topic) {
        return stampLogicService.getStampsByTopic(topic);
    }
    
    @GET
    @Path("bestRating")
    public StampDTO getBestStampByRating() {
        return stampLogicService.getStampWithBestRating();
    }
    
    @PUT
    @Path("{id}") 
    public void updateStamp(StampDTO stamp) {
        stampLogicService.updateStamp(stamp);
    }
}