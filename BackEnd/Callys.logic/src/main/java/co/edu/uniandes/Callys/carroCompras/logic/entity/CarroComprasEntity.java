package co.edu.uniandes.Callys.carroCompras.logic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import co.edu.uniandes.Callys.item.logic.entity.ItemEntity;
import javax.persistence.CascadeType;
import java.util.List;

@Entity
public class CarroComprasEntity {
    @Id
    @GeneratedValue(generator = "CarroCompras")
    private Long id;
    private Double monto;
    private String formaPago;
    private String datosEnvio;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<ItemEntity> items;
    
    public List<ItemEntity> getItems()
    {
       return items; 
    }
    
     public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public void addItem(ItemEntity item) {
        if (!getItems().contains(item)) {
            items.add(item);
        }
    }
            
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getDatosEnvio() {
        return datosEnvio;
    }

    public void setDatosEnvio(String datosEnvio) {
        this.datosEnvio = datosEnvio;
    }
}