package co.edu.uniandes.Callys.services;

import co.edu.uniandes.Callys.carroCompras.logic.api.ICarroComprasLogic;
import co.edu.uniandes.Callys.carroCompras.logic.dto.CarroComprasDTO;
import co.edu.uniandes.Callys.carroCompras.logic.dto.CarroComprasPageDTO;
import co.edu.uniandes.Callys.purchase.logic.api.IPurchaseLogic;
import co.edu.uniandes.Callys.purchase.logic.dto.PurchaseDTO;
import co.edu.uniandes.Callys.item.logic.entity.ItemEntity;
import co.edu.uniandes.Callys.purchaseitem.logic.entity.PurchaseItemEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
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

@Path("/carroCompras")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarroComprasService {
    
    @Inject
    protected ICarroComprasLogic carroComprasLogicService;
   
    @Inject
    protected IPurchaseLogic purchaseLogicService;

    @POST
    public CarroComprasDTO createCarroCompras(CarroComprasDTO carroCompras) {
        return carroComprasLogicService.createCarroCompras(carroCompras);
    }

    @DELETE
    @Path("{id}")
    public void deleteCarroCompras(@PathParam("id") Long id) {
        carroComprasLogicService.deleteCarroCompras(id);
    }

    @GET
    public CarroComprasPageDTO getCarroCompras(@QueryParam("page") Integer page, @QueryParam("maxRecords") Integer maxRecords) {
        return carroComprasLogicService.getCarrosCompras(page, maxRecords);
    }

    @GET
    @Path("{id}")
    public CarroComprasDTO getCarroCompras(@PathParam("id") Long id) {
        return carroComprasLogicService.getCarroCompras(id);
    }

    @PUT
    @Path("{id}")
    public void updateCarroCompras(CarroComprasDTO carroCompras) {
        carroComprasLogicService.updateCarroCompras(carroCompras);
    }
    
    @POST
    public PurchaseDTO registrarCompra(CarroComprasDTO carroCompras){
        PurchaseDTO pur = new PurchaseDTO();
        pur.setDate(new Date());
        pur.setDatosDeEnvio(carroCompras.getDatosEnvio());
        pur.setFormaDePago(carroCompras.getFormaPago());
        List<ItemEntity> items = (List)carroCompras.getItems();
        List<PurchaseItemEntity> purItems = new ArrayList<PurchaseItemEntity>();
        for(ItemEntity e: items){
            PurchaseItemEntity p = new PurchaseItemEntity();
            p.setIdCamiseta(e.getCamiseta().getId());
            p.setMonto(e.getMonto());
            purItems.add(p);
        }
        pur.setPurchaseItems(purItems);
        deleteCarroCompras(carroCompras.getId());
        return purchaseLogicService.createPurchase(pur);
    }
}
