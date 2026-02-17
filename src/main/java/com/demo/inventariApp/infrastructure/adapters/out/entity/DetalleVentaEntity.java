package com.demo.inventariApp.infrastructure.adapters.out.entity;

import com.demo.inventariApp.domain.model.Venta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name ="detalle_venta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long id;
    @Column(nullable = false)
    private int cantidad;
    private Double precioUnitario;
    private Double subtotal;
    @ManyToOne
    @JoinColumn(name = "id_venta")
    private VentaEntity venta;
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;
    private Double iva;
    private Double total;


}
