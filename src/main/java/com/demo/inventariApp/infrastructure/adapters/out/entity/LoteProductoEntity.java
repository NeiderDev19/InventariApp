package com.demo.inventariApp.infrastructure.adapters.out.entity;

import java.time.LocalDateTime;

import com.demo.inventariApp.domain.model.Producto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name ="lote_producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lote")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_producto",nullable = false)
    private ProductoEntity producto;
    @Column(nullable = false)
    private Double precioCompra;
    @Column(nullable = false)
    private Double precioVentaSugerido;
    private Double iva;
    @Column(nullable = false)
    private int cantidad;
    private LocalDateTime fechaCompra;
    private String proveedor;

    
}
