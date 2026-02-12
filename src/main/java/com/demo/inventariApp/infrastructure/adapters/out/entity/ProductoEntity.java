package com.demo.inventariApp.infrastructure.adapters.out.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.demo.inventariApp.domain.model.LoteProducto;
import com.demo.inventariApp.domain.model.UnidadProducto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name ="producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    @Column(nullable = false)
    private String nombre;
    private String descripcion;
    @Column(name = "stock_actual")
    private int stockActual;
    private int stockMinimo;
    @Enumerated(EnumType.STRING)
    private UnidadProducto unidad;
    private String codigoBarras;
    private boolean activo  = true;
    @ManyToOne
    @JoinColumn(name = "categoria_id",nullable = false)
    private CategoriaEntity categoria;
    private LocalDateTime fechaCreacion;
    @OneToMany(mappedBy = "producto")
    private List<LoteProductoEntity> loteProductos;

}
