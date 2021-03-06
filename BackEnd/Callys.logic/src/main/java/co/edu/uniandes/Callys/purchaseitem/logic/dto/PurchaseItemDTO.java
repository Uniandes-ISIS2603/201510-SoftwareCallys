package co.edu.uniandes.Callys.purchaseitem.logic.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PurchaseItemDTO {
    private Long id;
    private Long idPurchase;
    private Double monto;
    private Long idCamiseta;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getIdPurchase() {
        return idPurchase;
    }
    
    public void setIdPurchase(Long idPurchase) {
        this.idPurchase=idPurchase;
    }
    
    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
    
    public Long getIdCamiseta() {
        return idCamiseta;
    }

    public void setIdCamiseta(Long idCamiseta) {
        this.idCamiseta = idCamiseta;
    }
}