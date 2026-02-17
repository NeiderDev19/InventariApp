package com.demo.inventariApp.infrastructure.adapters.out.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.demo.inventariApp.domain.model.TipoPago;
import com.demo.inventariApp.domain.model.Usuario;

import jakarta.persistence.CascadeType;
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
@Table(name ="venta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long id;
    @Column(name = "fecha")
    private LocalDateTime fechaVenta;
    private int total;
    @Enumerated(EnumType.STRING)
    private TipoPago formaPago;
    private String estado;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVentaEntity> detalles;
    @Column(name = "total_iva")
    private Double totalIva;
    private Double subtotal;
    private String cliente;

}
